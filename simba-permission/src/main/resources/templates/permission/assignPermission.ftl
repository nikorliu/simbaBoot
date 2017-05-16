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
		<script type="text/javascript" src="${base}/js/permission/role.js"></script>
	</head>

	<body>
		<input type="hidden" id="roleName" name="roleName" value="${roleName}" />
		<input type="hidden" id="permissionids" name="permissionids" value="${ids!}" />

		<div>
			<!-- Content Wrapper. Contains page content -->
			<div class=" ">
				<section class="content">
					<div class="row">

						<!-- /.col -->
						<div class="col-md-12">
							<div class="box box-primary">
								<div class="box-body">
									<div class="form-group">
										<div id="tree"></div>
									</div>
								</div>
								<!-- /.box-body -->

								<div class="box-footer">
									<button type="submit" class="btn btn-success" onclick="Role.assignPermission();">分配</button>
									<button type="button" class="btn" onclick="top.hideModal();">取消</button>
								</div>

							</div>
						</div>
						<!-- /. box -->
					</div>
					<!-- /.col -->
			</div>
			<!-- /.row -->
			<div id="errInfo" style="color: red;"></div>
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
			Role.initSelectPermissionTree("${ids!}", "${texts!}");
		});

		function showInfo(info) {
			$("#errInfo").html(info);
			$("#errInfo").fadeIn();
			setTimeout("hideInfo();", 1500);
		}

		function hideInfo() {
			$("#errInfo").fadeOut();
		}
	</script>

</html>