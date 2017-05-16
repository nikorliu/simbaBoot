<#list list as menu>
	<tr>
		<td><input type="checkbox" name="menu" value="${menu.id}"></td>
		<td>${menu.text}</td>
		<td>${menu.url}</td>
		<td>${menu.orderNo}</td>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="Menu.toUpdate(${menu.id?c});"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Menu.deleteMenu(${menu.id?c});"><i class="fa fa-remove"></i>删除</button>
		</td>
	</tr>
</#list>