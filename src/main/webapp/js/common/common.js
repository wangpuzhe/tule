var common={
    alertErrorMsg:function(msg){
        layer.msg(msg,{icon:5});
    },
    alertSuccessMsg:function(msg){
        layer.msg(msg,{icon: 6});
    },
    // 计算两个时间之间的天数差
    judgeTime:function(startDate,endDate){
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
}
//全局查询按钮添加回车事件
$('.enterQuery').bind('keyup', function(event) {
    if (event.keyCode == "13") {
       var tableId = $(this).attr('queryTableId');
       $("#"+tableId).DataTable().ajax.reload();
    }
});

//提取多对象
(function($){
    $.fn.serializeJson = function(){
        var jsonData1 = {};
        var serializeArray = this.serializeArray();
        // 先转换成{"id": ["12","14"], "name": ["aaa","bbb"], "pwd":["pwd1","pwd2"]}这种形式
        $(serializeArray).each(function () {
            if (jsonData1[this.name]) {
                if ($.isArray(jsonData1[this.name])) {
                    jsonData1[this.name].push(this.value);
                } else {
                    jsonData1[this.name] = [jsonData1[this.name], this.value];
                }
            } else {
                jsonData1[this.name] = this.value;
            }
        });
        // 再转成[{"id": "12", "name": "aaa", "pwd":"pwd1"},{"id": "14", "name": "bb", "pwd":"pwd2"}]的形式
        var vCount = 0;
        // 计算json内部的数组最大长度
        for(var item in jsonData1){
            var tmp = $.isArray(jsonData1[item]) ? jsonData1[item].length : 1;
            vCount = (tmp > vCount) ? tmp : vCount;
        }

        if(vCount > 1) {
            var jsonData2 = new Array();
            for(var i = 0; i < vCount; i++){
                var jsonObj = {};
                for(var item in jsonData1) {
                    jsonObj[item] = jsonData1[item][i];
                }
                jsonData2.push(jsonObj);
            }
            return JSON.stringify(jsonData2);
        }else{
            return "[" + JSON.stringify(jsonData1) + "]";
        }
    };
})(jQuery);