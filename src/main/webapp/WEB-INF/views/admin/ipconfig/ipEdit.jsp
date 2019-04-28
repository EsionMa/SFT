<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#ipEditForm').form({
            url : '${path }/ipconfig/edit',
            onSubmit : function() {
                progressLoad();
                var isValid = $(this).form('validate');
                if (!isValid) {
                    progressClose();
                }
                return isValid;
            },
            success : function(result) {
                progressClose();
                result = $.parseJSON(result);
                if (result.success) {
                    parent.$.modalDialog.openner_dataGrid.datagrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_dataGrid这个对象，是因为user.jsp页面预定义好了
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                	parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
    })
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false" title="" style="overflow: hidden;padding: 3px;">
        <form id="ipEditForm" method="post">
            <table class="grid">
                <tr>
                    <td>ip地址</td>
                    <td><input name="id" type="hidden"  value="${authority.id}">
                    <input name="ipAddress" type="text" placeholder="请输入ip地址" class="easyui-validatebox" data-options="required:true" value="${authority.ipAddress}"></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><input name="id" type="hidden"  value="${authority.remark}">
                        <input name="remark" type="text" placeholder="输入备注" class="easyui-validatebox" data-options="required:true" value="${authority.remark}"></td>
                </tr>
                <tr>
                    <td>授权结束时间</td>
                    <td><input name="time"  class="easyui-datetimebox" style="width: 140px; height: 29px;" value="${authority.endTime}" required="required" data-options="editable:false" ></td>
                </tr>
            </table>
        </form>
    </div>
</div>