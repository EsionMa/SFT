<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/commons/global.jsp" %>
<script type="text/javascript">
    var userDataGrid;
    $(function() {
        userDataGrid = $('#userDataGrid').datagrid({
            url : '${path }/dataImport/query',
            fit : true,
            striped : true,
            rownumbers : true,
            pagination : true,
            singleSelect : true,
            idField : 'zyNo',
            sortName : 'createTime',
            sortOrder : 'asc',
            pageSize : 20,
            pageList : [ 10, 20, 30, 40, 50, 100, 200, 300, 400, 500 ],
            columns : [ [ {
                width : '80',
                title : '姓名',
                field : 'hzName',
                sortable : true
            },{
                width : '80',
                title : '出院科室id',
                field : 'cyDeptId',
                hidden : true
            }, {
                    width : '80',
                    title : '住院号',
                    field : 'zyNo',
                    hidden : false
                },{
                width : '150',
                title : '出院科室',
                field : 'cyDeptName'
            },{
                width : '130',
                title : '出院时间',
                field : 'cytime',
                sortable : true
            },  {
                width : '140',
                title : '管床护士名',
                field : 'gcHsName',
                sortable : true
            },{
                width : '90',
                title : '管床医生名',
                field : 'gcDoctorName',
                sortable : true
            },{
                    field : 'action',
                    title : '操作',
                    width : 130,
                    formatter : function(value, row, index) {
                        var str = '';
                        <shiro:hasRole name="system">
                        str += $.formatString('<a href="javascript:void(0)" class="user-easyui-linkbutton-edit" data-options="plain:true,iconCls:\'fi-pencil icon-blue\'" onclick="importPatient(\'{0}\');" >导入随访通</a>', row.zyNo);
                        </shiro:hasRole>
                        return str;
                    }
                }] ],
            onLoadSuccess:function(data){
                $('.user-easyui-linkbutton-edit').linkbutton({text:'导入随访通'});
            },
            toolbar : '#userToolbar'
        });
    });



    function importPatient(zyNo) {
        if (zyNo == undefined) {//点击右键菜单才会触发这个
            var rows = ipDataGrid.datagrid('getSelections');
            zyNo = rows[0].zyNo;
        } else {//点击操作里面的删除图标会触发这个
            userDataGrid.datagrid('unselectAll').datagrid('uncheckAll');
        }
        parent.$.messager.confirm('询问', '是否导入该患者？', function(b) {
            if (b) {
                progressLoad();
                $.post('${path }/dataImport/import', {
                    zyNo : zyNo
                }, function(result) {
                    if (result.code == 'SF0000') {
                        parent.$.messager.alert('提示', result.msg, 'info');
                    }else {
                        parent.$.messager.alert('提示', result.msg, 'info');
                    }
                    progressClose();
                }, 'JSON');
            }
        });
    }

    function searchUserFun() {
        userDataGrid.datagrid('load', $.serializeObject($('#searchUserForm')));
    }
    function cleanUserFun() {
        $('#searchUserForm input').val('');
        userDataGrid.datagrid('load', {});
    }
</script>
<div class="easyui-layout" data-options="fit:true,border:false">
    <div data-options="region:'north',border:false" style="height: 30px; overflow: hidden;background-color: #fff">
        <form id="searchUserForm">
            <table>
                <tr>
                    <th>住院号:</th>
                    <td><input name="zyNo" placeholder="请输入患者住院号"/></td>
                    <th>离院时间:</th>
                    <td>
                        <input name="boStTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />至
                        <input  name="boEdTime" placeholder="点击选择时间" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd HH:mm:ss'})" readonly="readonly" />
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-magnifying-glass',plain:true" onclick="searchUserFun();">查询</a>
                        <a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'fi-x-circle',plain:true" onclick="cleanUserFun();">清空</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div data-options="region:'center',border:true,title:'患者列表'" >
        <table id="userDataGrid" data-options="fit:true,border:false"></table>
    </div>
</div>
