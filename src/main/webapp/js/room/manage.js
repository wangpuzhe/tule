var table
$(function() {
    var url = ctx+"/room/listRoom";
    loadTable(url);

})

function loadTable(url) {

    $("#manageTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function () {
                var data ={};
                var param = {};
                var pageInfo =$('#manageTable').DataTable().page.info();
                data.pageNum=pageInfo.page+1;
                data.pageSize=pageInfo.length;
                param.zt="";
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
            {"data": "no", "bSortable": true},
            {"data": "mc", "bSortable": false},
            {"data": "lx", "bSortable": false},
            {"data": "zt", "bSortable": false}
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
    $('#flag').val(type);
    if("add"== type){
        $('#no').val("");
        $('#mc').val("");
        $('#lx').val("");
        $('#zt').val("");
        $('#bz').val("");
        $('#addModal').modal();
    } else if ("edit"== type){
        var data = $('#manageTable').DataTable().rows(['.selected']).data()[0];
        if(undefined!=data){

            $('#zt').val(data.zt);
            $('#no').val(data.no);
            $('#mc').val(data.mc);
            $('#lx').val(data.lx);
            $('#bz').val(data.bz);
            $('#addModal').modal();
        }else {
            layer.msg("请选择一行",{icon:5});
        }
    }
}

function submit() {
    if($('#loginForm').validator('isFormValid')) {
        var type = $("#flag").val();
        var no = $('#no').val();
        var mc = $('#mc').val();
        var lx = $('#lx').val();
        var zt = $('#zt').val();
        var bz = $('#bz').val();
        if ("add" == type) {
            //获取页面数据
            var data = {};
            data["no"] = no;
            data["mc"] = mc;
            data["lx"] = lx;
            data["zt"] = zt;
            data["bz"] = bz;
            var url = ctx + "/room/addRoom";
            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "json",
                success: function (data) {
                    layer.msg(data.msg, {icon: 6});
                    $('#manageTable').DataTable().ajax.reload();
                    cancel();
                }
            })
        } else if ("edit" == type) {
            var data = {};
            var id = $('#manageTable').DataTable().rows(['.selected']).data()[0].id;
            data["id"] = id;
            data["no"] = no;
            data["mc"] = mc;
            data["lx"] = lx;
            data["zt"] = zt;
            data["bz"] = bz;
            var url = ctx + "/room/editRoom";
            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "json",
                success: function (data) {
                    layer.msg(data.msg, {icon: 6});
                    $('#manageTable').DataTable().ajax.reload();
                    cancel();

                }
            })
        }
        // alert ("啦啦啦啦");
    }
}
function del() {
    var rows = $('#manageTable').DataTable().rows(['.selected']).data()[0];
    if(rows!=undefined){
        var id = rows.id;
        var url = ctx+"/room/delRoom";
        var data = {};
        data.roomId = id;
        $.ajax({
            url:url,
            type:"post",
            data:data,
            dataType:"json",
            success:function(data){
                layer.msg(data.msg,{icon:6});
                $('#manageTable').DataTable().ajax.reload();
            }
        })
    }else {
        layer.msg("请选择一行",{icon:5});
    }
}
function cancel(){
    $('#addModal').modal('hide');
}
function search() {
    $('#manageTable').DataTable().ajax.reload();
}
