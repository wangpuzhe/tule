var table
$(function() {
    var url = ctx+"/room/kfListRoom";
    loadTable(url);
})

function loadTable(url) {
    $("#kxTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function () {
                var data ={};
                var param = {};
                var pageInfo =$('#kxTable').DataTable().page.info();
                data.pageNum=pageInfo.page+1;
                data.pageSize=pageInfo.length;
                param.zt="空房";
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

//获取行信息



// 打开模态框
function openModal(){
    //获取行信息
    var row = $('#kxTable').DataTable().rows(['.selected']).data()[0];
    if(undefined!=row){
        //赋值
        $('#no').val(row.no);
        $('#mc').val(row.mc);
        //制空
        $('#dfr').val("");
        $('#dfrdhhm').val("");
        $('#kssj').val("");
        $('#jssj').val("");
        $('#je').val("");
        $('#rzts').val("");
        $('#qd').val("");
        $('#jbr').val("");
        $('#bz').val("");
        $('#addModal').modal();
    }else {
        layer.msg("请选择一行",{icon:5});
    }

}
var free_post_flag = false;
function submit() {
    //如果正在提交则直接返回，停止执行
    if(free_post_flag) return;
    //标记当前状态为正在提交状态
    free_post_flag = true;
    if($('#loginForm').validator('isFormValid')) {
        var data={};
        data.fjid = $('#kxTable').DataTable().rows(['.selected']).data()[0].id;
        data.no=$("#no").val();
        data.mc=$("#mc").val();
        data.dfr=$("#dfr").val();
        data.dfrdhhm=$("#dfrdhhm").val();
        data.rzsj=$("#kssj").val();
        data.tfsj=$("#jssj").val();
        data.rzts=$("#rzts").val();
        data.je=$("#je").val();
        data.qd=$("#qd").val();
        data.jbr=$("#jbr").val();
        data.bz=$("#bz").val();
        data.sfjh="0";
        var url = ctx + "/roomReservation/addRoomReservation";
        $.ajax({
            url: url,
            type: "post",
            data: data,
            dataType: "json",
            success: function (data) {
                free_post_flag =false; //在提交成功之后将标志标记为可提交状态
                if(data.code==0){
                    layer.msg("预定成功", {icon: 6});
                    cancel();
                    $('#kxTable').DataTable().ajax.reload();
                }else{
                    layer.alert(data.msg, {icon: 5},0);
                }
            },error:function () {
                free_post_flag =false; //AJAX失败也需要将标志标记为可提交状态
            }
        })
    }
}

function cancel(){
    $('#addModal').modal('hide');
}

function search() {
    $('#kxTable').DataTable().ajax.reload();
}


// 计算两个时间之间的天数差
function judgeTime(startDate,endDate){
    var start = startDate.replace(/-|' '|:/g, '');
    var end = endDate.replace(/-|' '|:/g, '');
    var date1 = start.toString();
    var year1 = date1.substring(0, 4);
    var month1 = date1.substring(4, 6);
    var day1 = date1.substring(6, 8);
    var d1 = new Date(year1 + '/' + month1 + '/' + day1);
    var date2 = end.toString();
    var year2 = date2.substring(0, 4);
    var month2 = date2.substring(4, 6);
    var day2 = date2.substring(6, 8);
    var d2 = new Date(year2 + '/' + month2 + '/' + day2);
    var iday = parseInt(d2 - d1) / 1000 / 60 / 60 / 24;
    return iday;
}

function jisuan() {
    var i = judgeTime($("#kssj").val(),$("#jssj").val());
    $('#rzts').val(i);
}