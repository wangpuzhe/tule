$(function() {
    var url = ctx+"/roomReservation/ydlist";
    if(type){
        if(type=='jrdrz'){
            $("#title").text("今日待入住");
        }
    }else{
        $("#title").text("已预订");
    }
    loadTable(url);

})


function loadTable(url) {
    $("#ydTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function () {
                var data ={};
                var param = {};
                var pageInfo =$('#ydTable').DataTable().page.info();
                data.pageNum=pageInfo.page+1;
                data.pageSize=pageInfo.length;
                if(type=='jrdrz'){
                    param.type=type;
                }
                //param.zt="预定";
                //是否激活--激活状态
                param.sfjh="0";
                param.keywords=$("#keywordsInput").val();
                data.queryJson=JSON.stringify(param);
                return data;
            }
        },
        bServerSide:true,
        // lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
        paging: true,//分页
        ordering: false,//是否启用排序
        searching: false,//搜索
        select:true,
        pagingType: "full_numbers",//分页样式的类型
        bLengthChange:false, //用于隐藏属性
        columns: [
            {"data": "id", "bSortable": true,"visible" : false,},
            {"data": "fjid", "bSortable": true,"visible" : false,},
            {"data": "no", "bSortable": true},
            {"data": "mc", "bSortable": false},
            {"data": "dfr", "bSortable": false},
            {"data": "dfrdhhm", "bSortable": false},
            {"data": "cjsj", "bSortable": false},
            {"data": "rzsj", "bSortable": false},
            {"data": "tfsj", "bSortable": false},
            {"data": "rzts", "bSortable": false},
            {"data": "je", "bSortable": false},
            {"data": "qd", "bSortable": false},
            {"data": "jbr", "bSortable": false},
        ],
        language: {
            //lengthMenu: '<select class="form-control input-xsmall">' + '<option value="1">1</option>' + '<option value="10">10</option>' + '<option value="20">20</option>' + '<option value="30">30</option>' + '<option value="40">40</option>' + '<option value="50">50</option>' + '</select>条记录',//左上角的分页大小显示。
            //search: '<span class="label label-success">搜索：</span>',//右上角的搜索文本，可以写html标签
            paginate: {//分页的样式内容。
                previous: "上一页",
                next: "下一页",
                first: "第一页",
                last: "最后"
            },
            zeroRecords: "没有内容",//table tbody内容为空时，tbody的内容。
            //下面三者构成了总体的左下角的内容。
            info: "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
            infoEmpty: "0条记录",//筛选为空时左下角的显示。
            infoFiltered: ""//筛选之后的左下角筛选提示，
        }
    });
    //$("#table_local_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。
}

// 打开模态框
function openModal(type){
    //获取行信息
    if(type=="add"){
        var row = $('#ydTable').DataTable().rows(['.selected']).data()[0];
        if(undefined!=row){
            var start = row.rzsj;
            //if(!judgeTime(start.replace(/-|' '|:/g, ''))==0){
            if(isToday(start.replace(/-|' '|:/g, ''))<0){
                common.alertErrorMsg("不能提前入住");
            }else{
            $.ajax({
                url:ctx + "/room/checkZt",
                type: "get",
                data:{id:row.fjid},
                dataType: "json",
                sync:false,
                success: function (data) {
                    var flag =data.result;
                    if("入住"==flag){
                        common.alertErrorMsg("此房间还有人入住,请管理员检查!");
                        layer.msg("此房间还有人入住,请管理员检查!",{icon:5});
                    }else if ("待打扫"==flag){
                        common.alertErrorMsg("此房间还没打扫,请联系保洁阿姨!");
                    }else{
                        //获取记录信息
                        //赋值  要拿到记录信息
                        $('#fjid').val(row.fjid);
                        $('#fjno').val(row.no);
                        $('#fjmc').val(row.mc);
                        $('#ydid').val(row.id);
                        $('#jldfr').val(row.dfr);
                        $('#jlrzts').val(row.rzts);
                        $('#jlrzsj').val(row.rzsj);
                        $('#jltfsj').val(row.tfsj);
                        $('#jlje').val(row.je);
                        $('#jlyj').val('0');
                        $('#jlqd').val(row.qd);
                        $('#jlcjr').val(row.jbr);
                        $('#jlbz').val(row.bz);
                        $('#lkmc').val("");
                        $('#lkdh').val("");
                        $('#lkzjhm').val("");
                        $('#addDiv').empty();
                        lk.initTypeahead();
                        $('#addModal').modal();
                    }
                }
            });
        }
        }
        else {
            layer.msg("请选择一行",{icon:5});
        }
    }
    else if(type=="eidt"){
        var row = $('#ydTable').DataTable().rows(['.selected']).data()[0];
        if(undefined!=row){

        }else {
            layer.msg("请选择一行",{icon:5});
        }

    }
}
function submit() {
    var roomCustomer = $('#form_roomCustomer').serializeJson();
    if($('#form_roomCustomer').validator('isFormValid') && $('#form_roomRecord').validator('isFormValid')) {
        var url = ctx + "/roomRecord/ruzhu";
        $.ajax({
            url: url,
            type: "post",
            data: {roomId: $('#fjid').val(),
                reservationId: $('#ydid').val(),
                roomCustomers: roomCustomer,
                recordBz: $('#jlbz').val(),
                recordYj:$('#jlyj').val()
            },
            dataType: "json",
            success: function (data) {
                layer.msg("入住成功", {icon: 6});
                cancel();
                $('#ydTable').DataTable().ajax.reload();
            }
        })
    }
}

function cancel(){
    $('#addModal').modal('hide');
}

function delRecord(){
    layer.confirm('确定要取消吗?', {icon: 3, title:'提示'}, function(index){
        var row = $('#ydTable').DataTable().rows(['.selected']).data()[0];
        if(undefined!=row){
            var data = {};
            data.id = row.id;
            var url = ctx+"/roomReservation/delReservation";
            $.ajax({
                url:url,
                type:"post",
                data:data,
                async: false,
                dataType:"json",
                success:function(d){
                    if("0"==d.code){
                        $('#ydTable').DataTable().ajax.reload();
                        layer.msg("取消成功!",{icon:6});
                    }else {
                        layer.msg("请联系管理员!",{icon:5});
                    }
                }
            })
        }else{
            layer.msg("请选择一行",{icon:5});
        }
        layer.close(index);
    });
}

function search() {
    $('#ydTable').DataTable().ajax.reload();
}
// 判断时间为今天
function isToday(data){
    var date = data.toString();
    var year = date.substring(0, 4);
    var month = date.substring(4, 6);
    var day = date.substring(6, 8);
    var d1 = new Date(year + '/' + month + '/' + day);
    var dd = new Date();
    var y = dd.getFullYear();
    var m = dd.getMonth() + 1;
    var d = dd.getDate();
    var d2 = new Date(y + '/' + m + '/' + d);
    var iday = parseInt(d2 - d1) / 1000 / 60 / 60 / 24;
    return iday;
}
