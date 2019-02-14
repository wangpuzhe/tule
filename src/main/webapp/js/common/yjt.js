function printYj() {
    if($('#form_roomCustomer').validator('isFormValid')) {
        var date =  new Date().Format("yyyy-MM-dd");
        $("#yjtDiv").show();
        var krxx = $('#form_roomCustomer').find("div:eq(2)");
        $("#dyfjh").text($("#fjno").val());
        $("#dyfjmc").text($("#fjmc").val());
        $("#dyyj").text("￥"+$("#jlyj").val()+"元");
        $("#dyrzsj").text($("#jlrzsj").val());
        $("#dytfsj").text($("#jltfsj").val());
        $("#dyfjmm").text($("#fjmm").val());
        $("#dyjbr").text($("#jlcjr").val());
        $("#dybz").text($("#fjbz").val());
        $("#dysj").text(date);
        $("#dyxm").text(krxx.find("input[name='xm']").val());
        $("#dydh").text(krxx.find("input[name='dh']").val());
        $("#yjtDiv").jqprint({
            debug: false,
            importCSS: true,
            printContainer: true,
            operaSupport: false
        });
        $("#yjtDiv").hide();
    }else {
        layer.msg("请检查录入信息是否正确!",{icon:5});
    }
}

Date.prototype.Format = function (fmt) {
    var o = {
        "M+": this.getMonth() + 1, //月份
        "d+": this.getDate(), //日
        "H+": this.getHours(), //小时
        "m+": this.getMinutes(), //分
        "s+": this.getSeconds(), //秒
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度
        "S": this.getMilliseconds() //毫秒
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}