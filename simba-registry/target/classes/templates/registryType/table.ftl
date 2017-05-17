<#list list as registryType>
	<tr>
		<td><input type="checkbox" name="registryType" value="${registryType.id}"></td>
		<td>${registryType.text}</td>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="RegistryType.toUpdate(${registryType.id});"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="RegistryType.deleteRegistryType(${registryType.id});"><i class="fa fa-remove"></i>删除</button>
		</td>
	</tr>
</#list>