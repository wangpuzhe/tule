<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<c:set var="ctx" value="<%=basePath%>"/>
<script type="text/javascript">
    var ctx = "${ctx}";
</script>
<html>
<head>
    <link rel="stylesheet" href="${ctx}/js/room/yjt.css">
</head>
<body>
<div id="yjtDiv" style="display: none;margin-left: 100px">
    <div class="title" style="text-align: center;font-size: 35px;">
        <span >途乐公寓押金条</span>
    </div>
    <div class="title" style="text-align: center;margin-left: 60%;font-size: 25px;">日期:<span id="dysj"></span></div>
    <table width="90%"  border="1" bordercolor="#a0c6e5" style="border-collapse:collapse;margin-left: 20px">
        <tr>
            <th style="width: 15%">房间号</th>
            <th style="width: 20%">房间名称</th>
            <th style="width: 30%">入住时间</th>
            <th style="width: 30%">姓名</th>
        </tr>
        <tr>
            <td id="dyfjh"></td>
            <td id="dyfjmc"></td>
            <td id="dyrzsj"></td>
            <td id="dyxm"></td>
        </tr>
        <tr>
            <th>房间密码</th>
            <th>押金</th>
            <th>退房时间</th>
            <th>电话</th>
        </tr>
        <tr>
            <td id="dyfjmm"></td>
            <td id="dyyj"></td>
            <td id="dytfsj"></td>
            <td id="dydh"></td>
        </tr>
        <tr>
            <th colspan="2">前台电话</th>
            <th colspan="2">备注</th>
        </tr>
        <tr>
            <td id="qtdh" colspan="2">029-89361918</td>
            <td id="dybz" colspan="2"></td>
        </tr>
    </table>
</div>
<script src="${ctx}/js/jqprint/jquery-migrate-1.2.1.min.js"></script>
<script src="${ctx}/js/jqprint/jquery.jqprint-0.3.js"></script>
<script src="${ctx}/js/common/yjt.js"></script>
</body>
</html>
