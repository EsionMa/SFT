<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var sysDictionariesTreeGrid;
    $(function() {
        sysDictionariesTreeGrid = $('#sysDictionariesTreeGrid').treegrid({
            url : '${path }/sysDictionaries/treeGrid',
            idField : 'id',
            treeField : 'name',
            parentField : 'pid',
            fit : true,
            fitColumns : false,
            border : false,
            loadFilter:function(data,parentId){
            	var result=[];
            	if(data && data.length>0){
            		for(var i=0;i<data.length;i++){
            			var row=data[i];
            			row.pid=null; 
            			row.iconCls='fi-folder';
                		for(var j=0;j<data.length;j++){
							if(i!=j && row.parentCode==data[j].code){
								row.pid=data[j].id;
								row=data[i];
								break;
							}
                		}
                		result.push(row);
                	}
            	}
            	data=result;
            	var opt = sysDictionariesTreeGrid.treegrid('options'); 
                var idFiled, textFiled, parentField;
                console.log(opt);
                if (opt.parentField) {
                    idFiled = opt.idFiled || 'id';
                    textFiled = opt.textFiled || 'text';
                    parentField = opt.parentField;
                    var i, l, treeData = [], tmpMap = [];
                    for (i = 0, l = data.length; i < l; i++) {
                        tmpMap[data[i][idFiled]] = data[i];
                    }
                    for (i = 0, l = data.length; i < l; i++) {
                        if (tmpMap[data[i][parentField]] && data[i][idFiled] != data[i][parentField]) {
                            if (!tmpMap[data[i][parentField]]['children'])
                                tmpMap[data[i][parentField]]['children'] = [];
                            data[i]['text'] = data[i][textFiled];
                            tmpMap[data[i][parentField]]['children'].push(data[i]);
                        } else {
                            data[i]['text'] = data[i][textFiled];
                            treeData.push(data[i]);
                        }
                    }
                    return treeData;
                }
            	return data; 
            },
            frozenColumns : [ [ {
                field : 'name',
                title : '字典名',
                width : 180
            },{
                title : 'id',
                field : 'id',
                width : 230 ,
                hidden : true
            } ] ],
            columns : [ [  {
                field : 'code',
                title : '编码',
                width : 200
            }, {
                field : 'version',
                title : '版本',
                width : 60
            }, {
            	field:"pid",
            	title:"父ID",
            	width:230,
            	hidden : true
            },{
                field : 'parentCode',
                title : '父Code',
                width : 150
            }, {
                field : 'status',
                title : '状态',
                width : 40,
                formatter : function(value, row, index) {
                    switch (value) {
                    case '0':
                        return '正常';
                    case '1':
                        return '停用';
                    }
                }
            }, {
                field : 'action',
                title : '操作',
                width : 130,
                formatter : function(value, row, index) {
                    var str = '';
                        <shiro:hasPermission name="/sysDictionaries/edit">
                            str += $.formatString('<a href="javascript:void(0)" class="sysDictionaries-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="addSysDictionariesFun(\'{0}\');" >编辑</a>', row.id);
                        </shiro:hasPermission>
                        <shiro:hasPermission name="/sysDictionaries/delete">
                            str += '&nbsp;&nbsp;|&nbsp;&nbsp;';
                            str += $.formatString('<a href="javascript:void(0)" class="sysDictionaries-easyui-linkbutton-del" data-options="plain:true,iconCls:\'fi-x icon-red\'" onclick="deleteSysDictionariesFun(\'{0}\');" >删除</a>', row.id);
                        </shiro:hasPermission>
                    return str;
                }
            } ] ],
            onLoadSuccess:function(data){
                $('.sysDictionaries-easyui-linkbutton-edit').linkbutton({text:'编辑'});
                $('.sysDictionaries-easyui-linkbutton-del').linkbutton({text:'删除'});
            },
            toolbar : '#sysDictionariesToolbar'
        });
    });

    function editSysDictionariesFun(id) {
        if (id != undefined) {
            sysDictionariesTreeGrid.treegrid('select', id);
        }
        var node = sysDictionariesTreeGrid.treegrid('getSelected');
        if (node) {
            parent.$.modalDialog({
                title : '编辑',
                width : 500,
                height : 350,
                href : '${path }/sysDictionaries/editPage?id=' + node.id,
                buttons : [ {
                    text : '确定',
                    handler : function() {
                        parent.$.modalDialog.openner_treeGrid = sysDictionariesTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                        var f = parent.$.modalDialog.handler.find('#sysDictionariesEditForm');
                        f.submit();
                    }
                } ]
            });
        }
    }

    function deleteSysDictionariesFun(id) {
    	if(!id){
    		id=""
    	}
	    parent.$.messager.confirm('询问', '您是否要删除当前资源？删除当前资源会连同子资源一起删除!', function(b) {
	        if (b) {
	            progressLoad();
	            $.post('${path }/sysDictionaries/delete', {
	                id : id
	            }, function(result) {
	                if (result.success) {
	                    parent.$.messager.alert('提示', result.msg, 'info');
	                    sysDictionariesTreeGrid.treegrid('reload');
	                    parent.layout_west_tree.tree('reload');
	                }
	                progressClose();
	            }, 'JSON');
	        }
	    });
    }

    function addSysDictionariesFun(id) {
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
            href : '${path }/sysDictionaries/addPage?id='+id,
            buttons : [ {
                text : title,
                handler : function() {
                    parent.$.modalDialog.openner_treeGrid = sysDictionariesTreeGrid;//因为添加成功之后，需要刷新这个treeGrid，所以先预定义好
                    var f = parent.$.modalDialog.handler.find('#sysDictionariesAddForm');
                    f.submit();
                }
            } ]
        });
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'center',border:false"  style="overflow: hidden;">
        <table id="sysDictionariesTreeGrid"></table>
    </div>
</div>
<div id="sysDictionariesToolbar" style="display: none;">
    <shiro:hasPermission name="/sysDictionaries/add">
        <a onclick="addSysDictionariesFun();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'fi-plus icon-green'">添加</a>
    </shiro:hasPermission>
</div>