<#list list as org>
	<tr>
		<td><input type="checkbox" name="org" value="${org.id}"></td>
		<td>${org.text}</td>
		<#list org.exts as ext>
			<td>${ext}</td>
		</#list>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="Org.toUpdate(${org.id});"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Org.deleteOrg(${org.id});"><i class="fa fa-remove"></i>删除</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Org.toAssignRole(${org.id});"><i class="fa fa-wrench"></i>分配角色</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Org.clearRole(${org.id});"><i class="fa fa-refresh"></i>清空角色</button>
		</td>
	</tr>
</#list>