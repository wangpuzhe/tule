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
                <div class="widget-title  am-cf">待打扫</div>
            </div>
            <div class="widget-body  am-fr">
                <div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
                    <div class="am-form-group">
                        <div class="am-btn-toolbar">
                            <div class="am-btn-group am-btn-group-sm">
                                <button type="button" class="am-btn am-btn-default am-btn-success"
                                        onClick="dasao()"><span class="am-icon-plus"></span> 打扫
                                </button>
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
                        <input type="text" queryTableId="ddsTable" class="enterQuery am-form-field" id="keywordsInput" placeholder="请输入房间号或房间名称">
                        <span class="am-input-group-btn">
                            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search"
                                    type="button" onclick="search()"></button>
                        </span>
                    </div>
                </div>
                <div class="am-u-sm-12">
                    <%--<table width="100%" class="am-table am-table-compact am-table-striped tpl-table-black " id="kxTable">--%>
                    <table width="100%" class="table table-bordered table-striped table-hover " id="ddsTable">
                        <thead>
                        <tr>
                            <th style="display: none">房间id</th>
                            <th>房间号</th>
                            <th>房间名称</th>
                            <th>房间类型</th>
                            <th>房间状态</th>
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
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h3 class="modal-title">预订</h3>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
               <%-- <p>Modal body text goes here.</p>--%>
                   <div class="form-row">
                       <div class="form-group col-md-6">
                           <label for="no">房间号: </label>
                           <input type="text" class="form-control" id="no" disabled="disabled">
                       </div>
                       <div class="form-group col-md-6">
                           <label for="mc">房间名: </label>
                           <input type="text" class="form-control" id="mc" disabled="disabled">
                       </div>
                   </div>
                   <div class="form-row">
                       <div class="form-group col-md-6">
                           <label for="dfr">订房人: </label>
                           <input type="text" class="form-control" id="dfr">
                       </div>
                       <div class="form-group col-md-6">
                           <label for="rzts">入住天数: </label>
                           <input type="text" class="form-control" id="rzts">
                       </div>
                   </div>
                   <%--时间选择器--%>
                   <div class="form-row">
                       <div class="form-group col-md-6">
                           <label>入住时间: </label>
                           <input id="kssj" class="am-form-field"  placeholder="入住时间" type="text" />
                       </div>
                       <div class="form-group col-md-6">
                           <label>退房时间: </label>
                           <input id="jssj" class="am-form-field" placeholder="退房时间" type="text"/>
                       </div>
                   </div>

                   <div class="form-row">
                       <div class="form-group col-md-6">
                           <label for="je">房费: </label>
                           <input type="text" class="form-control" id="je">
                       </div>
                       <div class="form-group col-md-6">
                           <label for="yj">押金: </label>
                           <input type="text" class="form-control" id="yj">
                       </div>
                   </div>
                   <div class="form-row">
                       <div class="form-group col-md-6">
                           <label for="qd">渠道:</label>
                           <select id="qd" class="form-control">
                               <option value=" ">--请选择--</option>
                               <option value="美团">美团</option>
                               <option value="去哪儿">去哪儿</option>
                               <option value="飞猪">飞猪</option>
                               <option value="携程">携程</option>
                               <option value="途牛">途牛</option>
                               <option value="艺龙">艺龙</option>
                               <option value="榛果">榛果</option>
                               <option value="爱彼迎">爱彼迎</option>
                               <option value="好订网">好订网</option>
                               <option value="途家">途家</option>
                               <option value="小猪">小猪</option>
                               <option value="木鸟">木鸟</option>
                               <option value="其他">其他</option>
                           </select>
                       </div>
                       <div class="form-group col-md-6">
                           <label for="cjr">经办人: </label>
                           <input type="text" class="form-control" id="cjr">
                       </div>
                   </div>
                   <div class="form-row">
                       <div class="form-group col-md-12">
                           <label for="bz">备注: </label>
                           <input type="text" class="form-control" id="bz">
                       </div>
                   </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="submit()">提交</button>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>

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

<script src="${ctx}/plugins/assets/js/theme.js"></script>
<script src="${ctx}/plugins/assets/js/amazeui.min.js"></script>
<script src="${ctx}/plugins/datetimepicker-master/js/amazeui.datetimepicker.min.js"></script>
<script src="${ctx}/plugins/datetimepicker-master/js/locales/amazeui.datetimepicker.zh-CN.js"></script>
<script src="${ctx}/plugins/DataTables/datatables.js"></script>
<script src="${ctx}/plugins/DataTables/Bootstrap-4-4.1.1/js/bootstrap.js"></script>
<script src="${ctx}/plugins/DataTables/DataTables-1.10.18/js/dataTables.bootstrap4.js"></script>
<script src="${ctx}/plugins/layer/layer.js"></script>
<script src="${ctx}/js/room/daidasao.js"></script>
<script src="${ctx}/js/common/common.js"></script>
</body>
</html>
