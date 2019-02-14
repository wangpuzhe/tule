/**
 * Created by kiah on 2018/11/10.
 */
$(function() {
    //iframe中session超时,跳转打开窗口还是嵌入在iframe中问题解决
    if (window.parent != window) {
        window.parent.location.reload(true);
    }
});

$('#userName,#password').bind('keyup', function(event) {
    if (event.keyCode == "13") {
       login();
    }
});
$("#loginBtn").on("click",function(){
   login();
});

function login() {
    if($('#loginForm').validator('isFormValid')){
        var userName = $("#userName").val();
        var password = $("#password").val();
        var data ={};
        data.userName=userName;
        data.password=password;
        var url = ctx+"/tule/loginByName";
        $.ajax({
            url:url,
            type:"post",
            data:data,
            dataType:"json",
            success:function(data){
                if("0"==data.code){
                    window.location.href=ctx+"/tule/index";
                }else{
                    layer.msg(data.msg,{icon:5})
                }
            }
        })
    }
}