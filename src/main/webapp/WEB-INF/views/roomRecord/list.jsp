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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>途乐公寓</title>
    <meta name="description" content="途乐公寓">
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" type="image/png" href="${ctx}/plugins/assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="${ctx}/plugins/assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <script src="${ctx}/plugins/assets/js/echarts.min.js"></script>
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.min.css"/>
    <link rel="stylesheet" href="${ctx}/plugins/datetimepicker-master/css/amazeui.datetimepicker.css"/>
    <link rel="stylesheet" href="${ctx}/plugins/DataTables/datatables.css" />
    <link rel="stylesheet" href="${ctx}/plugins/DataTables/Bootstrap-4-4.1.1/css/bootstrap.css" />
    <link rel="stylesheet" href="${ctx}/plugins/DataTables/DataTables-1.10.18/css/dataTables.bootstrap4.css" />
    <link rel="stylesheet" href="${ctx}/plugins/assets/css/app.css">
    <link rel="stylesheet" href="${ctx}/plugins/layer/mobile/need/layer.css">
    <link rel="stylesheet" href="${ctx}/plugins/layer/theme/default/layer.css">
    <link rel="stylesheet" href="${ctx}/css/style.css">
    <script src="${ctx}/plugins/assets/js/jquery.min.js"></script>
</head>
<body>
<!-- 内容区域 -->
<div class="row-content am-cf">
    <div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
        <div class="widget am-cf">
            <div class="widget-head am-cf">
                <div class="widget-title  am-cf">入住记录</div>
            </div>
            <div class="widget-body  am-fr">
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                    <div class="am-form-group">
                        <div class="am-btn-toolbar am-u-sm-6 am-u-md-6 am-u-lg-6">
                            <div class="am-btn-group am-btn-group-sm">
                                <button type="button" class="am-btn am-btn-lg am-btn-success"
                                        onClick="openGhModal()"><span class="am-icon-exchange">归还</span>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                    <div class="am-form-group">
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <input id="kssj" class="am-form-field"  placeholder="开始时间" type="text" />
                            </div>
                            <div class="form-group col-md-6">
                                <input id="jssj" class="am-form-field" placeholder="结束时间" type="text"/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                    <div class="tpl-form-border-form cl-p">
                    <select class="selectpicker show-tick" id="sfghSelect"  title="请选择一项" data-live-search="true" data-size="3">
                        <option value="">请选择是否归还</option>
                        <option value="是">已归还</option>
                        <option value="否">未归还</option>
                    </select>
                    </div>
                </div>
                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                        <input type="text" queryTableId="rzjlTable" class="enterQuery am-form-field " id = "jbrInput" placeholder="请输入经办人or房间号">
                        <span class="am-input-group-btn">
                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button" onclick="doSearch()"></button>
                        </span>
                    </div>
                </div>
                <div class="am-u-sm-12">
                    <table width="100%" class="table table-bordered table-striped table-hover" id="rzjlTable">
                        <thead>
                        <tr>
                            <th>房间号</th>
                            <th>预定时间</th>
                            <th>入住天数</th>
                            <th>入住时间</th>
                            <th>退房时间</th>
                            <th>经办人</th>
                            <th>押金</th>
                            <th>实退押金</th>
                            <th>实收金额</th>
                            <th>是否归还</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <tfoot>
                        <tr>
                            <th colspan="8" style="text-align:right">金额合计:</th>
                            <th></th>
                        </tr>
                        </tfoot>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!--入住模态框-->
<div class="modal" tabindex="-1" role="dialog" id="addModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">入住记录</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <%--房间记录表--%>
                <form class="am-form" id="form_roomRecord" data-am-validator>
                    <fieldset>
                        <legend>入住信息</legend>
                        <div class="form-row" style="display: none">
                            <div class="form-group col-md-3">
                                <label for="jlid">记录id: </label>
                                <input type="text" class="form-control" id="jlid" name="id" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="jlrzts">入住天数: </label>
                                <input type="text" class="form-control" id="jlrzts" name="rzts" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlrzsj">入住时间: </label>
                                <input type="text" class="form-control" id="jlrzsj" name="rzsj" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jltfsj">退房时间: </label>
                                <input type="text" class="form-control" id="jltfsj" name="tfsj" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlcjr">经办人: </label>
                                <input type="text" class="form-control" id="jlcjr" name="cjr" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="jlje">房费: </label>
                                <input type="text" class="form-control" id="jlje" name="je" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlyj">押金: </label>
                                <input type="text" class="form-control" id="jlyj" name="yj" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlstyj">实退押金: </label>
                                <input type="text" class="form-control" id="jlstyj" name="styj" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlqd">渠道: </label>
                                <input type="text" class="form-control" id="jlqd" name="qd" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="jlbz">备注: </label>
                                <input type="text" class="form-control" id="jlbz" name="bz" readonly="readonly">
                            </div>
                        </div>
                    </fieldset>
                </form>
                <%--入住人员表表--%>
                <form class="am-form" id="form_roomCustomer" >
                    <fieldset>
                        <legend>入住人员信息</legend>
                        <div id="addDiv">
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<!--归还密码模态框-->
<div class="modal" tabindex="-1" role="dialog" id="ghModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">请输入密码</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="am-form" id="form_gh" data-am-validator>
                    <fieldset>
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <label for="jlid">密码: </label>
                                <input type="password" class="form-control" id="ghmm">
                            </div>
                            <div class="form-group col-md-6">
                                <label for="jlqd">是否归还: </label>
                                <select class="selectpicker show-tick" id="ghzt"  title="请选择一项" data-live-search="true" data-size="2">
                                    <option value="是">是</option>
                                    <option value="否">否</option>
                                </select>
                            </div>
                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary"  onclick="guihuan()">确定</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<script src="${ctx}/plugins/assets/js/theme.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<script src="${ctx}/plugins/datetimepicker-master/js/amazeui.datetimepicker.min.js"></script>
<script src="${ctx}/plugins/datetimepicker-master/js/locales/amazeui.datetimepicker.zh-CN.js"></script>
<script src="${ctx}/plugins/DataTables/datatables.js"></script>
<script src="${ctx}/plugins/DataTables/Bootstrap-4-4.1.1/js/bootstrap.js"></script>
<script src="${ctx}/plugins/DataTables/DataTables-1.10.18/js/dataTables.bootstrap4.js"></script>
<script src="${ctx}/plugins/layer/layer.js"></script>
<script src="${ctx}/js/roomRecord/list.js"></script>
<script src="${ctx}/js/common/common.js"></script>
</body>
</html>
