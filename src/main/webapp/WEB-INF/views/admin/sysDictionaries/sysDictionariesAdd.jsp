<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
        $('#sysDictionariesAddForm').form({
            url : '${path }/sysDictionaries/add',
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
                    parent.$.modalDialog.openner_treeGrid.treegrid('reload');//之所以能在这里调用到parent.$.modalDialog.openner_treeGrid这个对象，是因为resource.jsp页面预定义好了
                    //parent.layout_west_tree.tree('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                	parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        var status="${sysDictionaries.status}"
        if(status){
	        $("select[name='status']").val("${sysDictionaries.status}");
        }
    });
</script>
<div style="padding: 3px;">
    <form id="sysDictionariesAddForm" method="post">
		<input type="hidden" name="id" value=${sysDictionaries.id}>
        <table class="grid">
            <tr>	
                <td>字典名称</td>
                <td><input name="name" value="${sysDictionaries.name}" type="text" placeholder="字典名称" class="easyui-validatebox span2" data-options="required:true" ></td>
            </tr>
            <tr>
                <td>编码</td>
                <td><input name="code" value="${sysDictionaries.code}" type="text" placeholder="请输入字典编码" class="easyui-validatebox span2" data-options="width:140,height:29,required:true" ></td>
            </tr>
            <tr>
                <td>版本</td>
                <td><input name="version" value="${sysDictionaries.version}"  class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">正常</option>
                        <option value="1">停用</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>上级字典Code</td>
                <td><input name="parentCode" value="${sysDictionaries.parentCode}" type="text" placeholder="请输入字典编码" class="easyui-validatebox span2" data-options="width:140,height:29" ></td>
            </tr>
        </table>
    </form>
</div>