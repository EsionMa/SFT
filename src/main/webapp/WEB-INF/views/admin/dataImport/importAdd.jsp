<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#excelAddForm').form({
            url : '${path }/dataImport/upload',
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
</script>
<div class="easyui-layout" data-options="fit:true,border:false" >
    <div data-options="region:'center',border:false" style="overflow: hidden;padding: 3px;" >
        <form id="excelAddForm" method="post" enctype="multipart/form-data">
            <table class="grid">
                <tr>
                    <td>文件: </td>
                    <td>
                        <input type="file" name="file"/>
                    </td>
                </tr>
                <tr>
                    <td>上传目标: </td>
                    <td>
                        <select name="id" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                            <option value="1">用户信息表</option>
                            <option value="2">患者信息表</option>
                            <option value="3">住院信息表</option>
                            <option value="4">门诊信息表</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" rowspan="5" >
                        <div><span style="color: red">注意:导入文件前请确认：文件类型、字段类型、是否选择文件、上传目标类型。</span></div>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>