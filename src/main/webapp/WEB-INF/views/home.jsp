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
<body>
<script src="${ctx}/plugins/assets/js/theme.js"></script>
<div class="row-content am-cf">
    <div class="row  am-cf">
        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
            <div class="widget widget-purple am-cf">
                <div class="widget-statistic-header">
                    今日空房数
                </div>
                <div class="widget-statistic-body">
                    <div class="widget-statistic-value">
                        <a style="text-decoration:underline;color: white" href="javascript:;" class="roomNum" data-url="${ctx}/room/free">${tjxx.jrkfs}</a>
                    </div>
                    <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                </div>
            </div>
        </div>
        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
            <div class="widget widget-purple am-cf">
                <div class="widget-statistic-header">
                    今日待入住数
                </div>
                <div class="widget-statistic-body">
                    <div class="widget-statistic-value">
                        <a style="text-decoration:underline;color: white" href="javascript:;" class="roomNum" data-url="${ctx}/room/yuding?type=jrdrz">${tjxx.jrdrzs}</a>
                    </div>
                    <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                </div>
            </div>
        </div>
        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
            <div class="widget widget-purple am-cf">
                <div class="widget-statistic-header">
                    今日待退房数
                </div>
                <div class="widget-statistic-body">
                    <div class="widget-statistic-value">
                        <a style="text-decoration:underline;color: white" href="javascript:;" class="roomNum" data-url="${ctx}/room/ruzhu?type=jrdtf">${tjxx.jrdtfs}</a>
                    </div>
                    <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="row  am-cf">
        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
            <div class="widget widget-primary  am-cf">
                <div class="widget-statistic-header">
                    已预定数
                </div>
                <div class="widget-statistic-body">
                    <div class="widget-statistic-value">
                        <a style="text-decoration:underline;color: white" href="javascript:;" class="roomNum" data-url="${ctx}/room/yuding">${tjxx.yds}</a>
                    </div>
                    <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                </div>
            </div>
        </div>
        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
            <div class="widget widget-primary am-cf">
                <div class="widget-statistic-header">
                    已入住数
                </div>
                <div class="widget-statistic-body">
                    <div class="widget-statistic-value">
                        <a style="text-decoration:underline;color: white" href="javascript:;" class="roomNum" data-url="${ctx}/room/ruzhu">${tjxx.rzs}</a>
                    </div>
                    <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                </div>
            </div>
        </div>
        <div class="am-u-sm-4 am-u-md-4 am-u-lg-4">
            <div class="widget widget-primary am-cf">
                <div class="widget-statistic-header">
                    待打扫数
                </div>
                <div class="widget-statistic-body">
                    <div class="widget-statistic-value">
                        <a style="text-decoration:underline;color: white" href="javascript:;" class="roomNum" data-url="${ctx}/room/daidasao">${tjxx.ddss}</a>
                    </div>
                    <span class="widget-statistic-icon am-icon-credit-card-alt"></span>
                </div>
            </div>
        </div>
    </div>
    <div class="row am-cf">
        <iframe src="${ctx}/room/ydList" style="height: 100%;width: 100%"></iframe>
    </div>
</div>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.datatables.min.js"></script>
<script src="${ctx}/plugins/assets/js/dataTables.responsive.min.js"></script>
<script src="${ctx}/js/home.js"></script>
</body>
</html>
