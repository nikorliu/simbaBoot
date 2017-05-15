<#list list as permission>
	<tr>
		<td><input type="checkbox" name="permission" value="${permission.id}"></td>
		<td>${permission.text}</td>
		<td>${permission.url}</td>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="Permission.toUpdate('${permission.id}');"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Permission.deletePermission('${permission.id}');"><i class="fa fa-remove"></i>删除</button>
		</td>
	</tr>
</#list>