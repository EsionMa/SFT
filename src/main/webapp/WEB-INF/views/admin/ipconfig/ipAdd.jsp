<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#ipAddForm').form({
            url : '${path }/ipconfig/add',
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
    });
    /*$('#ip_add_date').datebox({
        onSelect: function(date){
            var y = date.getFullYear();
            var m = date.getMonth()+1;
            var d = date.getDate();
            return y+'-'+m+'-'+'d'+' 00:00:00';
        }
    });*/

</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="ipAddForm" method="post">
            <table class="grid">
                <tr>
                    <td>ip地址</td>
                    <td><input name="ipAddress" type="text" placeholder="请输入IP地址" class="easyui-validatebox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>备注</td>
                    <td><input name="remark" type="text" placeholder="请输入备注" class="easyui-validatebox" data-options="required:true" value=""></td>
                </tr>
                <tr>
                    <td>授权过期时间</td>
                    <td><input name="time"  placeholder="请选择授权过期时间" class="easyui-datetimebox" data-options="required:true"></td>
                </tr>
            </table>
        </form>
    </div>
</div>