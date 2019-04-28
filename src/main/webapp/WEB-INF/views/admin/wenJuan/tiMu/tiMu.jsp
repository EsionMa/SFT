<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var tiMuDataGrid;
    $(function() {
        tiMuDataGrid = $('#tiMuDataGrid').datagrid({
            url : '${path }/wenJuan/tiMu/dataGrid',
            idField : 'id',
            fit : true,
            fitColumns : false,
            border : false,
            pagination:true,
            rownumbers:true,
            loadFilter:function(data,parentId){
            	return data; 
            },
            frozenColumns : [ [ {
                field : 'id',
                title : 'id',
                width : 180,
                hidden:true
            },{
                field : 'bt',
                title : '题目标题',
                width : 230 
            } ] ],
            columns : [ [  {
                field : 'tmfl',
                title : '题目分类',
                width : 200
            }, {
                field : 'txxz',
                title : '题型选择',
                width : 60,
                formatter:function(value,row,index){
                	if(value=="1"){
                		return "单选";
                	}
                	if(value=="2"){
                		return "多选";
                	}
                	if(value=="3"){
                		return "填空题";
                	}
                }
            }, {
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                    var str = '';
                        <shiro:hasPermission name="/wenJuan/tiMu/edit">
                            str += $.formatString('<a href="javascript:void(0)" class="tiMu-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="addTiMuFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/wenJuan/tiMu/delete">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="tiMu-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteTiMuFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.tiMu-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.tiMu-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#tiMuToolbar'
        });
    });

    function editTiMuFun(id) {
        if (id != undefined) {
            tiMuDataGrid.datagrid('select', id);
        }
        var node = tiMuDataGrid.datagrid('getSelected');
        if (node) {
            parent.$.modalDialog({
                title : '编辑',
                width : 500,
                height : 350,
                href : '${path }/wenJuan/tiMu/editPage?id=' + node.id,
                buttons : [ {
                    text : '确定',
                    handler : function() {
                        parent.$.modalDialog.openner_treeGrid = tiMuDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#tiMuEditForm');
                        f.submit();
                    }
                } ]
            });
        }
    }

    function deleteTiMuFun(id) {
    	if(!id){
    		id=""
    	}
	    parent.$.messager.confirm('询问', '您是否要删除当前题目？', function(b) {
	        if (b) {
	            progressLoad();
	            $.post('${path }/wenJuan/tiMu/delete', {
	                id : id
	            }, function(result) {
	                if (result.success) {
	                    parent.$.messager.alert('提示', result.msg, 'info');
	                    tiMuDataGrid.datagrid('reload');
	                    parent.layout_west_tree.tree('reload');
	                }
	                progressClose();
	            }, 'JSON');
	        }
	    });
    }

    function addTiMuFun(id) {
    	var title;
    	if(!id){
    		id="";
    		title="添加";
    	}else{
    		title="编辑";
    	}
        parent.$.modalDialog({
            title : title,
            width : 500,
            height : 350,
            href : '${path }/wenJuan/tiMu/addPage?id='+id,
            buttons : [ {
                text : title,
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = tiMuDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#tiMuAddForm');
                    f.submit();
                }
            } ]
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false"  style="overflow: hidden;">
        <table id="tiMuDataGrid"></table>
    </div>
</div>
<div id="tiMuToolbar" style="display: none;">
    <shiro:hasPermission name="/wenJuan/tiMu/add">
        <a onclick="addTiMuFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">新增</a>
    </shiro:hasPermission>
</div>