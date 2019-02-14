var table
$(function() {
    var url = ctx+"/room/listRoom";
    loadTable(url);

})

function loadTable(url) {
    $("#ddsTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function () {
                var data ={};
                var param = {};
                var pageInfo =$('#ddsTable').DataTable().page.info();
                data.pageNum=pageInfo.page+1;
                data.pageSize=pageInfo.length;
                param.zt="待打扫";
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

function dasao() {
    layer.confirm('确定要打扫吗?', {icon: 3, title:'提示'}, function(index){
        var roomRows = $('#ddsTable').DataTable().rows(['.selected']).data()[0];
        if(undefined!=roomRows){
            var id = roomRows.id;
            var no = roomRows.no;
            var mc = roomRows.mc;
            var lx = roomRows.lx;
            var zt = "空房";
            var data ={};
            data.id=id;
            data.no=no;
            data.mc=mc;
            data.lx=lx;
            data.zt=zt;
            var url = ctx+"/room/editRoom";
            $.ajax({
                url:url,
                type:"post",
                data:data,
                dataType:"json",
                success:function(d){
                    if("0"==d.code){
                        $('#ddsTable').DataTable().ajax.reload();
                        layer.msg("打扫成功",{icon:6});
                    }else {
                        layer.msg("打扫失败,请联系管理员！",{icon:5});
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
    $('#ddsTable').DataTable().ajax.reload();
}
