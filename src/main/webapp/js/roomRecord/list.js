var table
$(function() {
    // 时间选择器初始化
    $('#kssj, #jssj').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        todayBtn: false,
        format: 'yyyy-mm-dd hh:ii:ss',
        minView: 1
    });
    var url = ctx+"/roomRecord/rzjlData";
    loadTable(url);
    $('#rzjlTable tbody').on( 'click', 'a#edit', function () {
        var data = $('#rzjlTable').DataTable().row($(this).parents('tr')).data();
        openModal(data);
    } );
})

function loadTable(url) {
    var data ={};
    var param = {};
    param.zt="空房";
    param.keywords="";
    data.queryJson=JSON.stringify(param);
    $("#rzjlTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function(){
                var data ={};
                var param = {};
                //var pageInfo =$('#rzjlTable').DataTable().page.info();
                //data.pageNum=pageInfo.page+1;
                //data.pageSize=pageInfo.length;
                param.sfjh="1";
                param.ghzt=$("#sfghSelect").val();
                param.kssj=$("#kssj").val();
                param.jssj=$("#jssj").val();
                param.jbr=$("#jbrInput").val();
                data.queryJson=JSON.stringify(param);
                return data;
            }
        },
        //bServerSide:true,
        // lengthMenu: [5, 10, 20, 30],//这里也可以设置分页，但是不能设置具体内容，只能是一维或二维数组的方式，所以推荐下面language里面的写法。
        paging: true,//分页
        ordering: false,//是否启用排序
        searching: false,//搜索
        select:true,
        pagingType: "full_numbers",//分页样式的类型
        bLengthChange:false, //用于隐藏属性
        columns: [
            {"data": "no", "bSortable": false},
            {"data": "cjsj", "bSortable": false},
            {"data": "rzts", "bSortable": false},
            {"data": "rzsj", "bSortable": false},
            {"data": "tfsj", "bSortable": false},
            {"data": "cjr", "bSortable": false},
            {"data": "yj", "bSortable": false},
            {"data": "styj", "bSortable": false},
            {"data": "je", "bSortable": false},
            {"data": "ghzt", "bSortable": false},
            {"data": "cz", "bSortable": false}
        ],
        "aoColumnDefs":[//设置列的属性，此处设置第一列不排序
            {"bSortable": false, "aTargets": [0]},
            {
                "targets":-1,
                "data": null,
                "bSortable": false,
                "defaultContent": "<p style='text-align: center'><a id=\"edit\" href=\"#\">查看</p>"
            }

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
        },
        "footerCallback": function ( row, data, start, end, display ) {
            var api = this.api(), data;
            // Remove the formatting to get integer data for summation
            var intVal = function ( i ) {
                return typeof i === 'string' ?
                    i.replace(/[\$,]/g, '')*1 :
                    typeof i === 'number' ?
                        i : 0;
            };
            total = api
                .column(8)
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );
            // Total over this page
            pageTotal = api
                .column(8, { page: 'current'} )
                .data()
                .reduce( function (a, b) {
                    return intVal(a) + intVal(b);
                }, 0 );
            $( api.column(8).footer() ).html(
                '当页：￥'+pageTotal.toFixed(2) +' (全部:￥'+ total.toFixed(2) +' )'
            );
        }
    });
}

function doSearch(){
    $('#rzjlTable').DataTable().ajax.reload();
}

function guihuan(){
    var ids="";
    var datas =$('#rzjlTable').DataTable().rows(['.selected']).data();
    if(datas.length==0){
        common.alertErrorMsg('至少选择一行，如需多选请按住CTRL键');
        return;
    }
    var password = $("#ghmm").val();
    if(password!='123456'){
        common.alertErrorMsg("请输入正确的密码！")
        return;
    }
    for(var i=0;i<datas.length;i++){
        ids+=datas[i].id+",";
    }
    if(ids.length>0){
        ids=ids.substr(0,ids.length-1);
    }
    var url = ctx+"/roomRecord/guihuan";
    $.ajax({
        url:url,
        type:"post",
        data:{ids:ids,zt:$("#ghzt").val()},
        dataType:"json",
        success:function(data){
            ghModalCancle();
            common.alertSuccessMsg('修改成功');
            $('#rzjlTable').DataTable().ajax.reload();
        }
    })
}

function openModal(data) {
        $('#jlid').val(data.id);
        $('#jlrzts').val(data.rzts);
        $('#jlrzsj').val(data.rzsj);
        $('#jltfsj').val(data.tfsj);
        $('#jlje').val(data.je);
        $('#jlyj').val(data.yj);
        $('#jlstyj').val(data.styj);
        $('#jlqd').val(data.qd);
        $('#jlcjr').val(data.cjr);
        $('#jlbz').val(data.bz);
        //取旅客list
        var jlid = data.id;
        $("#addDiv").empty();
        var d = selectCustomerByJlid(jlid);
        for(var i=0;i<d.length;i++){
            var lkxm = d[i].xm;
            var lkdh = d[i].dh;
            var lkzjhm = d[i].zjhm;
            var html = "<div class=\"form-row\"><div class=\"form-group col-md-4\">\n" +
                "                                <label for=\"lkmc\">旅客姓名: </label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"lkmc\" name=\"mc\" value='"+lkxm+"' readonly=\"readonly\">\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group col-md-4\">\n" +
                "                                <label for=\"lkdh\">旅客电话: </label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"lkdh\" name=\"dh\" value='"+lkdh+"' readonly=\"readonly\">\n" +
                "                            </div>\n" +
                "                            <div class=\"form-group col-md-4\">\n" +
                "                                <label for=\"lkzjhm\">旅客证件号码: </label>\n" +
                "                                <input type=\"text\" class=\"form-control\" id=\"lkzjhm\" name=\"zjhm\" value='"+lkzjhm+"' readonly=\"readonly\">\n" +
                "                            </div></div>";
            $("#addDiv").append(html);
        }
        $('#addModal').modal();
}
function selectCustomerByJlid(jlid) {
    var url = ctx+"/roomCustomer/selectCustomerByJlid";
    var obj;
    $.ajax({
        url:url,
        type:"get",
        data:{"jlid":jlid},
        dataType:"json",
        async:false,
        success:function (d) {
            obj =d.result;
        }
    })
    return obj;
}

function cancel(){
    $('#addModal').modal('hide');
}
function openGhModal(){
    $("#ghmm").val("");
    var datas =$('#rzjlTable').DataTable().rows(['.selected']).data();
    if(datas.length==0){
        common.alertErrorMsg('至少选择一行，如需多选请按住CTRL键');
        return;
    }
    $('#ghModal').modal();
}
function ghModalCancle(){
    $('#ghModal').modal('hide');
}