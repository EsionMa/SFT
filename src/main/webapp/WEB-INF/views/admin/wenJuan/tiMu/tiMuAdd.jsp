<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
var tmflObj=null;
    $(function() {
    	tmflObj=$("#tmfl"); 
        $('#tiMuAddForm').form({
            url : '${path }/wenJuan/tiMu/add',
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
        
        tmflObj.combobox({
        	url:"${path}/sysDictionaries/listByParentCode?parentCode=TI_MU_FEN_LEI", 
        	valueField:"id",
        	textField:"name",
        	width:150,
        	panelHeight:'auto',
        	editable:false,
        	value:"请选择",
        	loadFilter:function(data){
        		var newData=[];
        		newData.push({name:"请选择"});
        		for(var i=0;i<data.length;i++){
        			newData.push(data[i]);
        		}
        		return newData; 
        	}
        });
//         tmflObj.combobox('select','请选择');  
    });
</script>
<div style="padding: 3px;">
    <form id="tiMuAddForm" method="post">
		<input type="hidden" name="id" value=${tiMu.id}>
        <table class="grid">
            <tr>	
                <td>标题:</td>
                <td><input name="name" value="${tiMu.bt}" type="text" placeholder="标题" class="easyui-validatebox span2" data-options="required:true" ></td>
                <td>题目分类:</td>
                <td><select id="tmfl" name="tmfl" ></select></td>
            </tr>
            <tr>
                <td style="text-align:left;">是否必填：</td>
		        <td style="text-align:left;" colspan="3">
		            <span class="radioSpan">
		                <input type="radio" name="sfbt" value="0">否</input>
		                <input type="radio" name="sfbt" value="1">是</input>
		            </span>
		        </td>
            </tr>
            <tr>
                <td style="text-align:left;">题型选择：</td>
		        <td style="text-align:left;" colspan="3">
		            <span class="radioSpan">
		                <input type="radio" name="sfbt" value="1">单选</input>
		                <input type="radio" name="sfbt" value="2">多选</input>
		                <input type="radio" name="sfbt" value="2">填空题</input>
		            </span>
		        </td>
            </tr>
            <tr>
            	<td>答题选项:</td>
            	<td colspan="3"></td>
            </tr>
            <tr>
                <td>答题描述</td>
                <td><input name="dtms" value="${tiMu.dtms}" type="text" placeholder="请输入字典编码" class="easyui-validatebox span2" data-options="width:140,height:29" ></td>
            </tr>
        </table>
    </form>
</div>
<style>
  .radioSpan {
      position: relative;
      border: 0px solid #95B8E7;
      background-color: #fff;
      vertical-align: middle;
      display: inline-block;
      overflow: hidden;
      white-space: nowrap;
      margin: 0;
      padding: 0;
      -moz-border-radius: 5px 5px 5px 5px;
      -webkit-border-radius: 5px 5px 5px 5px;
      border-radius: 5px 5px 5px 5px;
      display:block;
    }
</style>