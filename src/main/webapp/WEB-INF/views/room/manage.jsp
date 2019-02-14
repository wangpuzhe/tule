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
    <%--<link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.datatables.min.css" />--%>
    <link rel="stylesheet" href="${ctx}/plugins/DataTables/datatables.css" />
    <link rel="stylesheet" href="${ctx}/plugins/DataTables/Bootstrap-4-4.1.1/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/plugins/DataTables/DataTables-1.10.18/css/dataTables.bootstrap4.css" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/app.css">
    <link rel="stylesheet" href="${ctx}/plugins/layer/mobile/need/layer.css">
    <link rel="stylesheet" href="${ctx}/plugins/layer/theme/default/layer.css">
    <script src="${ctx}/plugins/assets/js/jquery.min.js"></script>
</head>
<body>
<!-- 内容区域 -->
<div class="row-content am-cf">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <div class="widget am-cf">
            <div class="widget-head am-cf">
                <div class="widget-title  am-cf">房间管理</div>
            </div>
            <div class="widget-body  am-fr">
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <div class="am-form-group">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-sm">
                                <button type="button" class="am-btn am-btn-default am-btn-success" onClick="openModal('add')"><span class="am-icon-plus"></span> 新增</button>
                                <button type="button" class="am-btn am-btn-default am-btn-secondary" onClick="openModal('edit')"><span class="am-icon-save"></span> 编辑</button>
                                <%--<button type="button" class="am-btn am-btn-default am-btn-warning"><span class="am-icon-archive"></span> 审核</button>--%>
                                <button type="button" class="am-btn am-btn-default am-btn-danger" onClick="del()"><span class="am-icon-trash-o"></span> 删除</button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                    <div class="am-form-group tpl-table-list-select">

                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                        <input type="text" queryTableId="manageTable" class="enterQuery am-form-field " id = "keywordsInput"  placeholder="请输入房间号或房间名称">
                        <span class="am-input-group-btn">
                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button" onclick="search()"></button>
                        </span>
                    </div>
                </div>
                <div class="am-u-sm-12">
                    <%--<table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="manageTable">--%>
                    <table width="100%" class="table table-bordered table-striped table-hover" id="manageTable">
                        <thead>
                        <tr>
                            <th style="display:none;">id</th>
                            <th>房间号</th>
                            <th>房间名称</th>
                            <th>房间类型</th>
                            <th>房间状态</th>
                        </tr>
                        </thead>
                    </table>
                    <%--<div class="am-cf">
                        <ul class="am-pagination">
                            <input type="hidden" class="dataTable_pageNum" value="1">
                            <li class="am-active"><a href="#"  class="pageButton prePage" pageNum="1">上一页</a></li>
                            <li class="am-active"><a href="#"  class="pageButton nextPage" pageNum="1">下一页</a></li>
                        </ul>
                    </div>--%>
                </div>
            </div>
        </div>
    </div>
</div>
<!--模态框-->
<div class="modal" tabindex="-1" role="dialog" id="addModal">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">房间信息</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%-- <p>Modal body text goes here.</p>--%>
                <form data-am-validator id="loginForm">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="no">房间号: </label>
                        <input type="text" class="form-control" id="no" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="mc">房间名: </label>
                        <input type="text" class="form-control" id="mc" required>
                    </div>
                </div>

                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="lx">房间类型</label>
                        <select id="lx" class="form-control" required>
                            <option value=" ">--请选择--</option>
                            <option value=大床>大床</option>
                            <option value="标间">标间</option>
                        </select>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="zt">房间状态</label>
                        <select id="zt" class="form-control" required>
                            <option value=" ">--请选择--</option>
                            <option value="空房">空房</option>
                            <option value="预定">预定</option>
                            <option value="入住">入住</option>
                            <option value="待打扫">待打扫</option>
                        </select>
                    </div>
                    <div class="form-group col-md-12">
                        <label for="bz">备注</label>
                        <input type="text" class="form-control" id="bz">
                    </div>
                    <div class="form-group col-md-12" style="display: none">
                        <label for="flag">flag</label>
                        <input type="text" class="form-control" id="flag">
                    </div>
                </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submit()">提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--模态框结束--%>
<script src="${ctx}/plugins/assets/js/theme.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<%--<script src="${ctx}/plugins/assets/js/amazeui.datatables.min.js"></script>--%>
<%--<script src="${ctx}/plugins/assets/js/dataTables.responsive.min.js"></script>--%>
<script src="${ctx}/plugins/DataTables/datatables.js"></script>
<script src="${ctx}/plugins/DataTables/Bootstrap-4-4.1.1/js/bootstrap.js"></script>
<script src="${ctx}/plugins/DataTables/DataTables-1.10.18/js/dataTables.bootstrap4.js"></script>
<script src="${ctx}/plugins/layer/layer.js"></script>
<script src="${ctx}/js/room/manage.js"></script>
<script src="${ctx}/js/common/common.js"></script>
</body>
</html>
