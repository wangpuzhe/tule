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
    <meta name="description" content="途乐公寓">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="${ctx}/plugins/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${ctx}/plugins/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="${ctx}/plugins/assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/app.css">
    <script src="${ctx}/plugins/assets/js/jquery.min.js"></script>
</head>
<body data-type="index">
<script src="${ctx}/plugins/assets/js/theme.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.datatables.min.js"></script>
<script src="${ctx}/plugins/assets/js/dataTables.responsive.min.js"></script>
<script src="${ctx}/plugins/assets/js/app.js"></script>
<div class="am-g tpl-g">
    <!-- 头部 -->
    <header>
        <!-- logo -->
        <div class="am-fl tpl-header-logo">
            <a href="javascript:;"><img src="${ctx}/plugins/assets/img/logo.png" alt=""></a>
        </div>
        <!-- 右侧内容 -->
        <div class="tpl-header-fluid">
            <!-- 侧边切换 -->
            <div class="am-fl tpl-header-switch-button am-icon-list">
                <span>
                </span>
            </div>
            <!-- 其它功能-->
            <div class="am-fr tpl-header-navbar">
                <ul>
                    <!-- 欢迎语 -->
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;"> <span style="font-size: x-large;text-align: center">途乐公寓，欢迎您！</span> </a>
                    </li><%--
                    <li class="am-text-sm tpl-header-navbar-welcome">
                        <a href="javascript:;" onclick="toggleSkin()">切换风格</span> </a>
                    </li>--%>
                </ul>
            </div>
        </div>
    </header>
    <!-- 侧边导航栏 -->
    <div class="left-sidebar">
        <!-- 菜单 -->
        <ul class="sidebar-nav">
            <li class="sidebar-nav-heading">途乐公寓</li>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="active syLink" data-url="${ctx}/tule/home">
                    <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                </a>
            </li>

            <li class="sidebar-nav-heading">清逸生活尽在途乐<span class="sidebar-nav-heading-info"></span></li>
            <li class="sidebar-nav-link">
                <a href="javascript:;"  class="syLink" data-url="${ctx}/room/ydList">
                    <i class="am-icon-tv sidebar-nav-link-logo"></i> 开始预订
                </a>
            </li>
            <li class="sidebar-nav-link">
                <a href="javascript:;" class="sidebar-nav-sub-title">
                    <i class="am-icon-table sidebar-nav-link-logo"></i> 房态管理
                    <span class="am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico"></span>
                </a>
                <ul class="sidebar-nav sidebar-nav-sub">
                    <li class="sidebar-nav-link">
                        <a href="javascript:;" class="syLink" data-url="${ctx}/room/free">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 今日空闲房间
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="javascript:;" class="syLink" data-url="${ctx}/room/yuding">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 已预定房间
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="javascript:;" class="syLink" data-url="${ctx}/room/ruzhu">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 已入住房间
                        </a>
                    </li>

                    <li class="sidebar-nav-link">
                        <a href="javascript:;" class="syLink" data-url="${ctx}/room/daidasao">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 待打扫房间
                        </a>
                    </li>
                    <li class="sidebar-nav-link">
                        <a href="javascript:;" class="syLink" data-url="${ctx}/room/manage">
                            <span class="am-icon-angle-right sidebar-nav-link-logo"></span> 房间管理
                        </a>
                    </li>
                </ul>
            </li>
            <li class="sidebar-nav-link">
                <a href="javascript:;"  class="syLink" data-url="${ctx}/roomRecord/list">
                    <i class="am-icon-clone sidebar-nav-link-logo"></i> 入住记录
                    <%--<span class="am-badge am-badge-secondary sidebar-nav-link-logo-ico am-round am-fr am-margin-right-sm">6</span>--%>
                </a>
            </li>
        </ul>
    </div>
    <!-- 内容区域 -->
    <div class="tpl-content-wrapper" id="tab">
        <iframe id="syIframe" src="${ctx}/tule/home" style="height: 100%;width: 100%"/>
    </div>
</div>
</div>
</body>
</html>
