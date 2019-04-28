<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var ipDataGrid;
    $(function() {
        ipDataGrid = $('#ipDataGrid').datagrid({
            url : '${path }/ipconfig/dataGrid',
            striped :true,
            rownumbers :true,
            pagination :true,
            singleSelect :true,
            idField : 'id',
            sortName : 'id',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            frozenColumns : [ [ {
                width : '40',
                title : '编号',
                field : 'id',
                sortable : true
            }, {
                width : '150',
                title : 'ip地址',
                field : 'ipAddress',
                sortable : true
            },{
                width:'220',
                title:'备注',
                field:'remark'
            } , {
                width:'180',
                title:'授权过期时间',
                field:'time'
            }, {
                width : '80',
                title : '修改人',
                field : 'userName',
                sortable : true
            }, {
                width : '180',
                title : '创建时间',
                field : 'createTime'
            } , {
                width : '180',
                title : '修改时间',
                field : 'updateTime'
            } ,
                {
                field : 'action',
                title : '操作',
                width : 200,
                formatter : function(value, row) {
                    var str = '';
                    <shiro:hasPermission name="/ipconfig/edit">
                    	str += $.formatString('<a href="javascript:void(0)" class="ip-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="editIp(\'{0}\');" >编辑</a>', row.id);
                    </shiro:hasPermission>
                    <shiro:hasPermission name="/ipconfig/delete">
                    	str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                    	str += $.formatString('<a href="javascript:void(0)" class="ip-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteIp(\'{0}\');" >删除</a>', row.id);
                    </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.ip-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.ip-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#ipToolBar'
        });
    });

    function addIp() {
        parent.$.modalDialog({
            title : '添加',
            width : 500,
            height : 300,
            href : '${path }/ipconfig/addPage',
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = ipDataGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#ipAddForm');
                    f.submit();
                }
            } ]
        });
    }

    function editIp(id) {
        if (id == undefined) {
            var rows = ipDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {
            ipDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.modalDialog({
            title : '编辑',
            width : 500,
            height : 300,
            href : '${path }/ipconfig/editPage?id=' + id,
            buttons : [ {
                text : '确定',
                handler : function() {
                    parent.$.modalDialog.openner_dataGrid = ipDataGrid;//因为添加成功之后，需要刷新这个dataGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#ipEditForm');
                    f.submit();
                }
            } ]
        });
    }

    function deleteIp(id) {
        if (id == undefined) {//点击右键菜单才会触发这个
            var rows = ipDataGrid.datagrid('getSelections');
            id = rows[0].id;
        } else {//点击操作里面的删除图标会触发这个
            ipDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '您是否要删除当前ip地址？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/ipconfig/delete', {
                    id : id
                }, function(result) {
                    if (result.success) {
                        parent.$.messager.alert('提示', result.msg, 'info');
                        ipDataGrid.datagrid('reload');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }

</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',fit:true,border:false">
        <table id="ipDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
<div id="ipToolBar" style="display: none;">
    <shiro:hasPermission name="/ipconfig/add">
        <a onclick="addIp();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>