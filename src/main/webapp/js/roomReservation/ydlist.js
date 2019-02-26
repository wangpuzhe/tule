$(function() {
    // 时间选择器初始化
    var newDate = new Date();
    // 时间选择器初始化
    $('#kssj, #jssj').datetimepicker({
        language: 'zh-CN',
        autoclose: true,
        todayBtn: true,
        format: 'yyyy-mm-dd hh:ii:ss',
        minView: "hour",
        //minuteStep: 10,
        startDate: newDate
        // endDate: '2020-12-30'
    });
// 时间选择器互动（结束时间不得早于开始时间）
    $('#kssj').on('changeDate', function(ev){
        $('#jssj').datetimepicker('setStartDate', ev.date);
        var d = $('#jssj').val();
        if (d) {
            var date = new Date(d.replace(/-/g, '/'));
            if(date != 'Invalid Date' && date < ev.date){
                $('#jssj').datetimepicker('setDate', ev.date)
            }
        }
    });
    var url = ctx+"/roomReservation/ydData";
    loadTable(url);

})

function loadTable(url) {
    $("#rzjlTable").dataTable({
        ajax: {
            url: url,
            type: 'post',
            data:function(){
                var data ={};
                var param = {};
                var pageInfo =$('#rzjlTable').DataTable().page.info();
                data.pageNum=pageInfo.page+1;
                data.pageSize=pageInfo.length;
                param.lx=$("#lxSelect").val();
                param.kssj=$("#kssj").val();
                param.jssj=$("#jssj").val();
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
}

function search(){
    $('#rzjlTable').DataTable().ajax.reload();
}

function yuding(){
    var row = $('#rzjlTable').DataTable().rows(['.selected']).data()[0];
    var dateSpan, iDays,sDate1,sDate2;
    sDate1 = Date.parse($("#kssj").val());
    sDate2 = Date.parse($("#jssj").val());
    dateSpan = sDate2 - sDate1;
    dateSpan = Math.abs(dateSpan);
    iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
    var i = common.judgeTime($("#kssj").val(),$("#jssj").val());
    if(!isNaN(iDays)) {
        if(undefined!=row){
            $('#no').val(row.no);
            $('#mc').val(row.mc);
            $('#rzts').val(i);
            $('#dfr').val("");
            $('#je').val("");
            $('#dfrdhhm').val("");
            $('#qd').val("");
            $('#jbr').val("");
            $('#bz').val("");
            $('#addModal').modal();
        }else{
            layer.msg("请选择一行",{icon:5});
        }
    } else{
        layer.msg("请选择时间",{icon:5});
    }
}
function submit() {
    if($('#loginForm').validator('isFormValid')) {
        //获取页面数据
        var info ={};
        info.fjid = $('#rzjlTable').DataTable().rows(['.selected']).data()[0].id;
        info.no = $('#no').val();
        info.mc = $('#mc').val();
        info.rzts = $('#rzts').val();
        info.dfrdhhm = $('#dfrdhhm').val();
        info.dfr = $('#dfr').val();
        info.rzsj = $('#kssj').val();
        info.tfsj = $('#jssj').val();
        info.je = $('#je').val();
        info.qd = $('#qd').val();
        info.jbr = $('#jbr').val();
        info.bz = $('#bz').val();
        //激活状态
        info.sfjh = "0";//0为预定状态
        var url = ctx + "/roomReservation/addRoomReservation";
        $.ajax({
            url: url,
            type: "post",
            data: info,
            dataType: "json",
            success: function (data) {
                if(data.code==0){
                    layer.msg("预定成功", {icon: 6});
                    $('#addModal').modal('hide');
                    $('#rzjlTable').DataTable().ajax.reload();
                }else{
                    layer.alert(data.msg, {icon: 5});
                }
            }
        })
    }
}
function yjrz() {
    var row = $('#rzjlTable').DataTable().rows(['.selected']).data()[0];
    var dateSpan, iDays,sDate1,sDate2;
    sDate1 = Date.parse($("#kssj").val());
    sDate2 = Date.parse($("#jssj").val());
    dateSpan = sDate2 - sDate1;
    dateSpan = Math.abs(dateSpan);
    iDays = Math.floor(dateSpan / (24 * 3600 * 1000));
    var i = common.judgeTime($("#kssj").val(),$("#jssj").val());
    if(!isNaN(iDays)) {
        if(undefined!=row){
            var zt = row.zt;
            if(zt!='空房'){
                common.alertErrorMsg("只有空房才可以一键入住！");
                return;
            }
            $("#fjid").val(row.id);
            $('#fjno').val(row.no);
            $('#fjmc').val(row.mc);
            $('#jlrzsj').val($("#kssj").val());
            $('#jltfsj').val($("#jssj").val());
            $('#jlrzts').val(i);
            lk.initTypeahead();
            $('#yjrzModal').modal();
        }else{
            common.alertErrorMsg("请选择一行！");
        }
    } else{
        common.alertErrorMsg("请选择时间！");
    }
}

function yjrzSubmit() {
    if($('#form_roomRecord').validator('isFormValid')){
        if($('#form_roomCustomer').validator('isFormValid')) {
            var ydxx = $("#form_roomRecord").serializeJson();
            var roomCustomer = $('#form_roomCustomer').serializeJson();
            debugger;
            var url = ctx + "/roomRecord/yjrz";
            $.ajax({
                url: url,
                type: "post",
                data: {roomId: $('#fjid').val(),
                    ydxx:ydxx,
                    roomCustomers: roomCustomer,
                    recordBz: $('#jlbz').val(),
                    recordYj:$('#jlyj').val()
                },
                dataType: "json",
                success: function (data) {
                    if(data.code==0){
                        common.alertSuccessMsg(data.msg);
                        $('#yjrzModal').modal('hide');
                        $('#rzjlTable').DataTable().ajax.reload();
                    }else{
                        $('#yjrzModal').modal('hide');
                        layer.alert(data.msg, {icon: 5},0);
                    }
                }
            })
        }
    }

}