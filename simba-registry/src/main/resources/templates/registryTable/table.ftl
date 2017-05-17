<#list list as registryTable>
	<tr>
		<td><input type="checkbox" name="registryTable" value="${registryTable.id}"></td>
		<td>${registryTable.code}</td>
		<td>${registryTable.value}</td>
		<td>${registryTable.description}</td>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="RegistryTable.toUpdate(${registryTable.id});"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="RegistryTable.deleteRegistryTable(${registryTable.id});"><i class="fa fa-remove"></i>删除</button>
		</td>
	</tr>
</#list>