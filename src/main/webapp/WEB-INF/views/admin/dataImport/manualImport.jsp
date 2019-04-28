<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var excelDataGrid;
    $(function () {
        excelDataGrid = $('#excelDataGrid').datagrid({
            url: '${path }/dataImport/dataGrid',
            striped: true,
            rownumbers: true,
            pagination: true,
            singleSelect: true,
            sortName: 'uploadTime',
            sortOrder: 'asc',
            pageSize: 20,
            pageList: [10, 20, 30, 40, 50, 100, 200, 300, 400, 500],
            frozenColumns: [[{
                width: '60',
                title: 'id',
                field: 'id',
                sortable: true
            }, {
                width: '80',
                title: '文件名',
                field: 'fileName',
                sortable: true
            }, {
                width: '80',
                title: '上传人名',
                field: 'userName',
                sortable: true
            }, {
                width: '200',
                title: '上传时间',
                field: 'uploadTime',
                sortable: true
            }, {
                width: '100',
                title: '总数',
                field: 'amount'
            }, {
                width: '100',
                title: '成功数',
                field: 'succeed'
            }, {
                width: '150',
                title: '状态',
                field: 'status',
                sortable: true,
                formatter: function (value, row, index) {
                    switch (value) {
                        case 0:
                            return '失败';
                        case 1:
                            return '成功';
                    }
                }
            }]],
            toolbar: '#excelToolbar'
        });
    });

    function addExcelFun() {
        parent.$.modalDialog({
            title: '导入文件',
            width: 400,
            height: 250,
            href: '${path }/dataImport/uploadPage',
            buttons: [{
                text: '确定',
                handler: function () {
                    parent.$.modalDialog.openner_dataGrid = excelDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#excelAddForm');
                    f.submit();
                }
            }]
        });
    }
    function searchUploadFun() {
        excelDataGrid.datagrid('load', $.serializeObject($('#searchUploadForm')));
    }
    function cleanUploadFun() {
        $('#searchUploadForm input').val('');
        excelDataGrid.datagrid('load', {});
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="; overflow: hidden;background-color: #fff">
        <form id="searchUploadForm">
            <table>
                <tr>
                    <th>上传时间:</th>
                    <td>
                        <input name="staDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               readonly="readonly"/>至
                        <input name="endDate" placeholder="点击选择时间"
                               onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})"
                               readonly="readonly"/>
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchUploadFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanUploadFun();">清空</a>
                    </td>
                    <td>
                        <a onclick="addExcelFun();" href="javascript:void(0);" class="easyui-linkbutton"
                           data-options="plain:true,iconCls:'fi-plus icon-green'"
                           style="position: absolute;right: 10px;top: 1px ">导入附件</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',fit:true,border:false,title:'记录列表'">
        <table id="excelDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="excelToolbar" style="display: none;">
    <%--<shiro:hasPermission name="/dataImport/add">--%>
       <%-- <a onclick="addExcelFun();" href="javascript:void(0);" class="easyui-linkbutton"
           data-options="plain:true,iconCls:'fi-plus icon-green'"
           style="position: absolute;right: 10px;top: 1px ">导入附件</a>--%>
    <%--</shiro:hasPermission>--%>
</div>
