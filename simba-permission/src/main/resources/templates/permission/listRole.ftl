<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>系统首页</title>
		<#include "../adminlte.ftl"/>
		<#include "../iCheck.ftl"/>
		<script type="text/javascript" src="${base}/js/common/checkbox.js"></script>
		<script type="text/javascript" src="${base}/js/permission/role.js"></script>
	</head>

	<body>
		<div>
			<!-- Content Wrapper. Contains page content -->
			<div class="">
				<section class="content">
					<div class="row">
						<!-- /.col -->
						<div class="col-md-12">
							<div class="box box-primary">
								<div class="box-header with-border">
									<h3 class="box-title">角色管理</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body no-padding">
									<div class="mailbox-controls">
										<div class="mailbox-controls">
											<!-- Check all button -->
											<button type="button" class="btn btn-default btn-sm checkbox-toggle" onclick="Role.toAdd();"><i class="fa fa-plus"></i>
                新增</button>
											<button type="button" class="btn btn-default btn-sm" onclick="Role.batchDelete();"><i class="fa fa-remove"></i>删除</button>
											<div class="pull-right">

											</div>
										</div>
									</div>
									<!-- /.pull-right -->
								</div>
								<div class="table-responsive">
									<table class="table table-hover table-striped table-bordered">
										<thead>
											<tr>
												<th><input type="checkbox" name="checkAll" id="checkAll">全选</th>
												<th>名称</th>
												<th>描述</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
											<#list list as role>
												<tr>
													<td><input type="checkbox" name="role" value="${role.name}"></td>
													<td>${role.name}</td>
													<td>${role.description}</td>
													<td>
														<button type="button" class="btn btn-default btn-sm" onclick="Role.toUpdate('${role.name}');"><i class="fa fa-pencil-square-o"></i>修改</button>
														<button type="button" class="btn btn-default btn-sm" onclick="Role.deleteRole('${role.name}');"><i class="fa fa-remove"></i>删除</button>
														<button type="button" class="btn btn-default btn-sm" onclick="Role.toAssignPermission('${role.name}');"><i class="fa fa-wrench"></i>分配权限</button>
													</td>
												</tr>
											</#list>
										</tbody>
									</table>
								</div>
								<!-- /.mail-box-messages -->
							</div>
						</div>
						<!-- /. box -->
					</div>
					<!-- /.col -->
			</div>
			<!-- /.row -->
			</section>
			<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->
		<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
		</div>
		<!-- ./wrapper -->

	</body>
	<script type="text/javascript">
		$(document).ready(function() {
			CheckBox.init();
			setTimeout("CheckBox.bindCheckAll();", 1000);
		});
	</script>

</html>