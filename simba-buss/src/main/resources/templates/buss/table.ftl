<#list list as buss>
	<tr>
		<td><input type="checkbox" name="buss" value="${buss.name}"></td>
		<td>${buss.name}</td>
		<td>${buss.description}</td>
		<td>
			<button type="button" class="btn btn-default btn-sm" onclick="Buss.toUpdate('${buss.name}');"><i class="fa fa-pencil-square-o"></i>修改</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Buss.deleteBuss('${buss.name}');"><i class="fa fa-remove"></i>删除</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Buss.show('${buss.name}');"><i class="fa fa-eye"></i>查看脚本</button>
			<button type="button" class="btn btn-default btn-sm" onclick="Buss.test('${buss.name}');"><i class="fa fa-key"></i>测试脚本</button>
		</td>
	</tr>
</#list>