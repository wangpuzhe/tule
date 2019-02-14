<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt"%>
<%@ page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<c:set var="ctx" value="<%=basePath%>"/>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>途乐公寓</title>
    <meta name="description" content="途乐公寓登录">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/app.css">
    <link rel="stylesheet" href="${ctx}/plugins/layer/mobile/need/layer.css">
    <link rel="stylesheet" href="${ctx}/plugins/layer/theme/default/layer.css">
    <script src="${ctx}/plugins/assets/js/jquery.min.js"></script>
</head>
<body data-type="login">
<script src="${ctx}/plugins/assets/js/theme.js"></script>
<div class="am-g tpl-g">
    <!-- 风格切换 -->
  <%--  <div class="tpl-skiner">
        <div class="tpl-skiner-toggle am-icon-cog">
        </div>
        <div class="tpl-skiner-content">
            <div class="tpl-skiner-content-title">
                选择主题
            </div>
            <div class="tpl-skiner-content-bar">
                <span class="skiner-color skiner-white" data-color="theme-white"></span>
                <span class="skiner-color skiner-black" data-color="theme-black"></span>
            </div>
        </div>
    </div>--%>
    <div class="tpl-login">
        <div class="tpl-login-content">
            <div class="tpl-login-logo">
            </div>
            <form class="am-form tpl-form-line-form" data-am-validator id="loginForm">
                <fieldset>
                <div class="am-form-group">
                    <label for="userName">用户名：</label>
                    <input type="text" class="tpl-form-input" id="userName" placeholder="请输入账号" required>
                </div>

                <div class="am-form-group">
                    <label for="password">密码：</label>
                    <input type="password" class="tpl-form-input" id="password" placeholder="请输入密码" required>
                </div>
                <%--<div class="am-form-group tpl-login-remember-me">
                    <input id="remember-me" type="checkbox">
                    <label for="remember-me">
                    记住密码
                     </label>
                </div>--%>
                <div class="am-form-group">
                    <button id="loginBtn" type="button" class="am-btn am-btn-primary  am-btn-block tpl-btn-bg-color-success  tpl-login-btn">登录</button>
                </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<script src="${ctx}/plugins/assets/js/app.js"></script>
<script src="${ctx}/plugins/layer/layer.js"></script>
<script src="${ctx}/js/login.js"></script>
</body>
</html>