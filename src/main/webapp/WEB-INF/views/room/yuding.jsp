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
    var type ="${type}";
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
    <%-- <link rel="stylesheet" href="${ctx}/plugins/assets/css/amazeui.datatables.min.css"/>--%>
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
                <div class="widget-title  am-cf" id="title">已预订</div>
            </div>
            <div class="widget-body  am-fr">
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <div class="am-form-group">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-sm">
                                <button type="button" class="am-btn am-btn-default am-btn-success" onClick="openModal('add')"><span class="am-icon-plus"></span> 入住</button>
                                <button type="button" class="am-btn am-btn-default am-btn-danger" onClick="delRecord()"><span class="am-icon-trash-o"></span> 取消预订</button>
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
                        <input type="text" queryTableId="ydTable" class="enterQuery am-form-field " id="keywordsInput"  placeholder="请输入房间号或房间名称">
                        <span class="am-input-group-btn">
                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
                                    type="button" onclick="search()"></button>
                        </span>
                    </div>
                </div>
                <div class="am-u-sm-12">
                    <%--<table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="kxTable">--%>
                    <table width="100%" class="table table-bordered table-striped table-hover " id="ydTable">
                        <thead>
                        <tr>
                            <th style="display: none">预定id</th>
                            <th style="display: none">房间id</th>
                            <th>房间号</th>
                            <th>房间名称</th>
                            <th>预定人</th>
                            <th>预定人电话号码</th>
                            <th>预定时间</th>
                            <th>预计入住时间</th>
                            <th>预计离店时间</th>
                            <th>预计入住天数</th>
                            <th>金额</th>
                            <th>渠道</th>
                            <th>经办人</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<!--模态框-->
<div class="modal" tabindex="-1" role="dialog" id="addModal">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">入住</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form class="am-form" id="form_room">
                    <fieldset>
                        <legend>房间信息</legend>
                        <div class="form-row" style="display: none">
                            <div class="form-group col-md-3">
                                <label for="fjid">房间id: </label>
                                <input type="text" class="form-control" id="fjid" name="id" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="fjno">房间号: </label>
                                <input type="text" class="form-control" id="fjno" name="no" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="fjmc">房间名: </label>
                                <input type="text" class="form-control" id="fjmc" name="mc" readonly="readonly">
                            </div>
                        </div>
                    </fieldset>
                </form>
                <%--房间记录表--%>
                <form class="am-form" id="form_roomRecord">
                    <fieldset>
                        <legend>预定信息</legend>
                        <div class="form-row" style="display: none">
                            <div class="form-group col-md-3">
                                <label for="ydid">预定id: </label>
                                <input type="text" class="form-control" id="ydid" name="id" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="jldfr">订房人: </label>
                                <input type="text" class="form-control" id="jldfr" name="dfr" readonly="readonly">
                            </div>
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
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="jlje">房费: </label>
                                <input type="text" class="form-control" id="jlje" name="je" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlyj">押金: </label>
                                <input type="number" required class="form-control" id="jlyj" name="yj">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlqd">渠道: </label>
                                <input type="text" class="form-control" id="jlqd" name="qd" readonly="readonly">
                            </div>
                            <div class="form-group col-md-3">
                                <label for="jlcjr">经办人: </label>
                                <input type="text" class="form-control" id="jlcjr" name="cjr" readonly="readonly">
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <label for="jlbz">备注: </label>
                                <input type="text" class="form-control" id="jlbz" name="bz">
                            </div>
                        </div>
                    </fieldset>
                </form>
                <%--入住人员表表--%>
                <form class="am-form" id="form_roomCustomer" data-am-validator>
                    <fieldset>
                        <legend>入住人员信息</legend>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <button type="button" class="btn btn-primary" onclick="addRows()">增加同行人员</button>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-3">
                                <label for="lkmc">旅客姓名: </label>
                                <input type="text" class="form-control zdbqInput" autocomplete="off" id="lkmc" name="xm" required>
                            </div>
                            <div class="form-group col-md-3">
                                <label for="lkdh">旅客电话: </label>
                                <input type="text" class="form-control" id="lkdh" name="dh" required minlength="8" maxlength="11">
                            </div>
                            <div class="form-group col-md-4">
                                <label for="lkzjhm">旅客证件号码: </label>
                                <input type="text" class="form-control" id="lkzjhm" name="zjhm"  required>
                            </div>
                            <div class="form-group col-md-2">
                                <label>操作</label>
                                <button type="text" style="height: 3rem" onclick="delkenren(this)" class="form-control btn btn-secondary kerenDelBtn">删除</button>
                            </div>
                        </div>
                        <div id="addDiv">

                        </div>
                    </fieldset>
                </form>
            </div>
            <div class="modal-footer">
                <input type="text" class="form-control" placeholder="请输入房间密码" id="fjmm"/>
                <input type="text" class="form-control" placeholder="请输入备注信息" id="fjbz"/>
                <button type="button" class="btn btn-primary" onclick="printYj()">打印押金条</button>
                <button type="button" class="btn btn-primary" onclick="submit()">提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<jsp:include page="../other/yjt.jsp"></jsp:include>
<script>
    $(function() {
        var newDate = new Date();
        // 时间选择器初始化
        $('#kssj, #jssj').datetimepicker({
            language: 'zh-CN',
            autoclose: true,
            todayBtn: true,
            format: 'yyyy-mm-dd hh:ii',
            // minView: 2,
            minuteStep: 10, // 时间选择 精确到10分钟
            startDate: newDate
            // endDate: '2020-12-30'
        });
// 时间选择器互动（结束时间不得早于开始时间）
        $('#kssj').on('changeDate', function(ev){
            $('#jssj').datetimepicker('setStartDate', ev.date);
            var d = $('#jssj').val();
            if (d) {
                var date = new Date(d.replace(/-/g, '/'));
                if(date != 'Invalid Date' && date < ev.date){
                    $('#jssj').datetimepicker('setDate', ev.date)
                }
            }
        });
    })
</script>
<script src="${ctx}/js/common/common.js"></script>
<script src="${ctx}/js/common/lkbq.js"></script>
<script src="${ctx}/plugins/bootstrap3-typeahead/bootstrap3-typeahead.min.js"></script>
<script src="${ctx}/plugins/assets/js/theme.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<script src="${ctx}/plugins/datetimepicker-master/js/amazeui.datetimepicker.min.js"></script>
<script src="${ctx}/plugins/datetimepicker-master/js/locales/amazeui.datetimepicker.zh-CN.js"></script>
<script src="${ctx}/plugins/DataTables/datatables.js"></script>
<script src="${ctx}/plugins/DataTables/Bootstrap-4-4.1.1/js/bootstrap.js"></script>
<script src="${ctx}/plugins/DataTables/DataTables-1.10.18/js/dataTables.bootstrap4.js"></script>
<script src="${ctx}/plugins/layer/layer.js"></script>
<script src="${ctx}/js/room/yuding.js"></script>
</body>
</html>
