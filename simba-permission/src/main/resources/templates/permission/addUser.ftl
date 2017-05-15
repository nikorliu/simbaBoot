<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>系统首页</title>
		<#include "../adminlte.ftl"/>
		<script type="text/javascript" src="${base}/js/plugins/bootstrap-treeview.min.js"></script>
		<script type="text/javascript" src="${base}/js/util/treeviewutil.js"></script>
		<script type="text/javascript" src="${base}/js/permission/user.js"></script>
	</head>

	<body>
		<div>
			<!-- Content Wrapper. Contains page content -->
			<div class=" ">
				<section class="content">
					<div class="row">

						<!-- /.col -->
						<div class="col-md-12">
							<div class="box box-primary">
								<div class="box-header with-border">
									<h3 class="box-title">新增用户</h3>
								</div>
								<form role="form" onsubmit="return User.checkForm();" id="form" action="${base}/user/add">
									<div class="box-body">
										<div class="form-group">
											<label for="text">账号</label>
											<input type="text" class="form-control" id="account" name="account" placeholder="请输入账号">
										</div>
										<div class="form-group">
											<label for="text">用户名</label>
											<input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名">
										</div>
										<div class="form-group">
											<label for="parentName">所属机构</label>
											<input type="hidden" id="orgID" name="orgID" value="${orgID}" />
											<input type="text" onclick="$('#tree').show();" class="form-control" id="orgName" name="orgName" value="${orgName}" placeholder="请选择所属机构">
											<div id="tree" style="display: none;"></div>
										</div>
										<#list descs as ext>
											<div class="form-group">
												<label for="text">${ext.name}</label>
												<input type="text" class="form-control" id="${ext.key}" name="${ext.key}" placeholder="${ext.name}">
											</div>
										</#list>
									</div>
									<!-- /.box-body -->

									<div class="box-footer">
										<button type="submit" class="btn btn-success">提交</button>
										<button type="button" class="btn" onclick="User.toList();">取消</button>
									</div>
								</form>

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
			User.initSelectOrgTree($("#orgID").val(), $("#orgName").val());
		});
	</script>

</html>