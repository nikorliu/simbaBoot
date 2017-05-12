<#list list as org>
	<tr>
		<td><input type="checkbox" name="org" value="${org.id}"></td>
		<td>${org.text}</td>
		<#list org.exts as ext>
			<td>${ext}</td>
		</#list>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="Org.toUpdate(${org.id});"><i class="fa fa-hand-pointer-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Org.deleteOrg(${org.id});"><i class="fa fa-remove"></i>删除</button>
		</td>
	</tr>
</#list>