$(function() {
    var url = ctx+"/room/listRzRoom";
    if(type){
        if(type=='jrdtf'){
            $("#title").text("今日待退房");
        }
    }else{
        $("#title").text("已入住");
    }
    loadTable(url);
    $('#rzTable tbody').on( 'click', 'a#edit', function () {
        var row = $('#rzTable').DataTable().row( $(this).parents('tr') ).data();
        openModal('chakan',row);
    } );
})
function loadTable(url) {

    $("#rzTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function () {
                var data ={};
                var param = {};
                var pageInfo =$('#rzTable').DataTable().page.info();
                data.pageNum=pageInfo.page+1;
                data.pageSize=pageInfo.length;
                param.zt="入住";
                param.sfjh="0";
                if(type=='jrdtf'){
                    param.type=type;
                }
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
            {"data": "fjid", "bSortable": true,"visible" : false,},
            {"data": "jlid", "bSortable": true,"visible" : false,},
            {"data": "ydid", "bSortable": true,"visible" : false,},
            {"data": "fjno", "bSortable": true},
            {"data": "fjmc", "bSortable": false},
            {"data": "fjlx", "bSortable": false},
            {"data": "fjzt", "bSortable": false},
            {"data": "lkid", "bSortable": true,"visible" : false,},
            {"data": "lkxm", "bSortable": false},
            {"data": "lkdh", "bSortable": false},
            {"data": "jltfsj", "bSortable": false},
            {"data": "jlje", "bSortable": false},
            {"data": "jlyj", "bSortable": false},
            {"data": "jlqd", "bSortable": false},
            {"data": "jlcjr", "bSortable": false},
            {"data": "cz", "bSortable": false}
        ],
        "aoColumnDefs":[//设置列的属性，此处设置第一列不排序
            {"bSortable": false, "aTargets": [0]},
            {
                "targets":-1,
                "data": null,
                "bSortable": false,
                "defaultContent": "<p>&nbsp;&nbsp;&nbsp;&nbsp;<a id=\"edit\" href=\"#\">查看</p>"
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
        }
    });
    //$("#table_local_filter input[type=search]").css({ width: "auto" });//右上角的默认搜索文本框，不写这个就超出去了。
}
function openModal(type,row) {
    var rows;
    if(type=='chakan'){
        $("#submit").hide();
        rows = row;
    }else if(type=='tuifang'){
        $("#submit").show();
        rows = $('#rzTable').DataTable().rows(['.selected']).data()[0];
    }
    if(undefined!=rows){
        $('#fjid').val(rows.fjid);
        $('#ydid').val(rows.ydid);
        $('#fjno').val(rows.fjno);
        $('#fjmc').val(rows.fjmc);
        $('#fjlx').val(rows.fjlx);
        $('#fjzt').val(rows.fjzt);
        $('#jlid').val(rows.jlid);
        $('#jldfr').val(rows.jldfr);
        $('#jlrzts').val(rows.jlrzts);
        $('#jlrzsj').val(rows.jlrzsj);
        $('#jltfsj').val(rows.jltfsj);
        $('#jlje').val(rows.jlje);
        $('#jlyj').val(rows.jlyj);
        $('#jlyj').val(rows.jlyj);
        $('#jlqd').val(rows.jlqd);
        $('#jlcjr').val(rows.jlcjr);
        $('#jlbz').val(rows.jlbz);
        $('#jlbz').val(rows.jlbz);
        $('#jlstyj').val(rows.jlyj);
        //取旅客list
        var jlid = rows.jlid;
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
    }else {
        layer.msg("请选择一行",{icon: 5});
    }
}
//修改房间状态  从入住到待打扫
function editRoom(fjid,fjzt) {
    var url = ctx+"/room/editRoom";
    var data ={};
    data.id= fjid;
    data.zt=fjzt;
    $.ajax({
        url:url,
        type:"post",
        data:data,
        dataType:"json",
        success:function(data){
        }
    })
}
function submit() {
    var url = ctx+"/roomRecord/updateRecord";
    if($('#form_roomRecord').validator('isFormValid')) {
        var fjid = $("#fjid").val();
        var fjzt = "待打扫";
        $.ajax({
            url: url,
            data: $('#form_roomRecord').serialize(),
            type: "post",
            dataType: "json",
            success: function (result) {
                if ("0" == result.code) {
                    editRoom(fjid, fjzt);
                    $('#addModal').modal('hide');
                    $('#rzTable').DataTable().ajax.reload();
                    layer.msg(result.msg, {icon: 6});
                } else {
                    layer.msg(result.msg, {icon: 5});
                }
            }
        })
    }
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
function search() {
    $('#rzTable').DataTable().ajax.reload();
}

function xuzhu(){
   if($('#rzTable').DataTable().rows(['.selected']).data().length!=1){
       common.alertErrorMsg("请选择一行!");
       return;
   }
   var row = $('#rzTable').DataTable().rows(['.selected']).data()[0];
   $("#xzjlid").val(row.jlid);
   $("#xzfjno").val(row.fjno);
   $("#xzfjmc").val(row.fjmc);
   $("#xzfjlx").val(row.fjlx);
   $("#xzfjzt").val(row.fjzt);
   $("#xztfsj").val(row.jltfsj);
   $("#xzydid").val(row.ydid);
    $('#xzsj').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        todayBtn: false,
        format: 'yyyy-mm-dd hh:ii:ss',
        minView: 1
    }).on('change',function(){
        var days = common.judgeTime($("#xztfsj").val(),$("#xzsj").val());
        if(days<0){
            $("#xzsj").val("");
            $("#xzts").val("");
            common.alertErrorMsg("续住时间不能小于退房时间");
        }else {
            $("#xzts").val(days);
        }
    });
   $("#xuzhuModal").modal();
}

function xuzhuSubmit(){
    var url = ctx+"/roomRecord/xuzhu";
    if($('#form_xuru').validator('isFormValid')) {
        debugger;
        $.ajax({
            url: url,
            data: {id:$("#xzjlid").val(),xzsj:$("#xzsj").val(),xzff:$("#xzff").val(),xzts:$("#xzts").val(),xzqd:$("#xzqd").val(),xzjbr:$('#xzjbr').val(),xzbz:$('#xzbz').val(),ydid:$("#xzydid").val()},
            type: "post",
            dataType: "json",
            success: function (result) {
                if ("0" == result.code) {
                    $('#xuzhuModal').modal('hide');
                    $('#rzTable').DataTable().ajax.reload();
                    common.alertSuccessMsg(result.msg);xuzhu
                } else {
                    common.alertErrorMsg(result.msg);
                }
            }
        })
    }
}



