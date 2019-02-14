var lk ={
    initTypeahead:function(){
        var url = ctx+"/roomRecord/findLkxx";
        $(".zdbqInput").typeahead({
            source: function (query, process) {
                return $.ajax({
                    url: url,
                    type: 'post',
                    data: {name: query},
                    success: function (result) {
                        var datas =JSON.parse(result);
                        var names = [];
                        $.each(datas, function (index, data) {
                            var obj = data.xm+"|"+data.dh+"|"+data.zjhm;
                            names.push(obj);
                        });
                        return process(names);
                    },
                });
            },
            updater: function (obj) {
                var data = obj.split("|");
                var row = $(this)[0].$element.parent().parent();
                row.find("input[name='dh']").val(data[1]);
                row.find("input[name='zjhm']").val(data[2]);
                return data[0];
            },
            minLength: 1,//键入字数多少开始补全
            showHintOnFocus: "true",//将显示所有匹配项
            fitToElement: false,//选项框宽度与输入框一致
            items: 'all',//下拉选项中出现条目的最大数量。也可以设置为“all”
            autoSelect: true,//允许你决定是否自动选择第一个建议
            afterSelect: function (item) {
                //console.log(item);//打印对应的id
            },
            delay: 500//在查找之间添加延迟
        });
    }
}

function addRows() {
    var Html = "<div class=\"form-row\"> <div class=\"form-group col-md-3\">\n" +
        "                                <label for=\"lkmc\">旅客姓名: </label>\n" +
        "                                <input type=\"text\" class=\"form-control zdbqInput\" autocomplete='off' id=\"lkmc\" name=\"xm\" required>\n" +
        "                            </div>\n" +
        "                            <div class=\"form-group col-md-3\">\n" +
        "                                <label for=\"lkdh\">旅客电话: </label>\n" +
        "                                <input type=\"text\" class=\"form-control\" id=\"lkdh\" name=\"dh\" required minlength='8' maxlength='11' >\n" +
        "                            </div>\n" +
        "                            <div class=\"form-group col-md-4\">\n" +
        "                                <label for=\"lkzjhm\">旅客证件号码: </label>\n" +
        "                                <input type=\"text\" class=\"form-control\" id=\"lkzjhm\" name=\"zjhm\" required>\n" +
        "                            </div>\n"+
        "                            <div class=\"form-group col-md-2\">\n" +
        "                                <label>操作 </label>\n" +
        "                                <button type=\"text\" style=\"height: 3rem\" onclick=\"delkenren(this)\" class=\"form-control btn btn-secondary kerenDelBtn\">删除</button>\n" +
        "                            </div></div>";
    $("#addDiv").append(Html);
}

function delkenren(obj){
    $(obj).parent().parent().remove();
}