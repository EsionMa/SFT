<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    $(function() {
//         $('#resourceEditPid').combotree({
//             url : '${path }/sysDictionaries/tree',
//             parentField : 'pid',
//             lines : true,
//             panelHeight : 'auto',
//             value : '${sysDictionaries.pid}'
//         });
        
        $('#sysDictionariesAddForm').form({
            url : '${path }/sysDictionaries/edit',
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
                    parent.layout_west_tree.tree('reload');
                    parent.$.modalDialog.handler.dialog('close');
                } else {
                	parent.$.messager.alert('提示', result.msg, 'warning');
                }
            }
        });
        $("#resourceEditStatus").val("${sysDictionaries.status}");
        $("#resourceEditType").val("${sysDictionaries.resourceType}");
        $("#resourceEditOpenMode").val("${sysDictionaries.openMode}");
        $("#resourceEditOpened").val("${sysDictionaries.opened}");
    });
</script>
<div style="padding: 3px;">
    <form id="sysDictionariesAddForm" method="post">
        <table  class="grid">
            <tr>
                <td>资源名称</td>
                <td>
                    <input name="id" type="hidden"  value="${sysDictionaries.id}" >
                    <input name="name" type="text" placeholder="请输入资源名称" value="${sysDictionaries.name}" class="easyui-validatebox span2" data-options="required:true" >
                </td>
                <td>资源类型</td>
                <td>
                    <select id="resourceEditType" name="resourceType" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">菜单</option>
                        <option value="1">按钮</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>资源路径</td>
                <td><input name="url" type="text" value="${sysDictionaries.url}" placeholder="请输入资源路径" class="easyui-validatebox span2" ></td>
                <td>打开方式</td>
                <td>
                    <select id="resourceEditOpenMode" name="openMode" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option>无(用于上层菜单)</option>
                        <option value="ajax">ajax</option>
                        <option value="iframe">iframe</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>菜单图标</td>
                <td><input name="icon" value="${sysDictionaries.icon}"/></td>
                <td>排序</td>
                <td><input name="seq" value="${sysDictionaries.seq}" class="easyui-numberspinner" style="width: 140px; height: 29px;" required="required" data-options="editable:false"></td>
            </tr>
            <tr>
                <td>状态</td>
                <td>
                    <select id="resourceEditStatus" name="status" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">正常</option>
                        <option value="1">停用</option>
                    </select>
                </td>
                <td>菜单状态</td>
                <td>
                    <select id="resourceEditOpened" name="opened" class="easyui-combobox" data-options="width:140,height:29,editable:false,panelHeight:'auto'">
                        <option value="0">关闭</option>
                        <option value="1">打开</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>上级资源</td>
                <td colspan="3"><select id="resourceEditPid" name="pid" style="width: 200px; height: 29px;"></select>
                <a class="easyui-linkbutton" href="javascript:void(0)" onclick="$('#pid').combotree('clear');" >清空</a></td>
            </tr>
        </table>
    </form>
</div>
