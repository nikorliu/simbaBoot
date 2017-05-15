<#list list as user>
	<tr>
		<td><input type="checkbox" name="user" value="${user.account}"></td>
		<td>${user.account}</td>
		<td>${user.name}</td>
		<#list user.exts as ext>
			<td>${ext}</td>
		</#list>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="User.toUpdate(${user.account});"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="User.deleteUser(${user.account});"><i class="fa fa-remove"></i>删除</button>
			<button type="button" class="btn btn-default btn-sm" onclick="User.resetPwd(${user.account});"><i class="fa fa-refresh"></i>重置密码</button>
			<button type="button" class="btn btn-default btn-sm" onclick="User.toAssignRole(${user.account});"><i class="fa fa-wrench"></i>分配角色</button>
		</td>
	</tr>
</#list>