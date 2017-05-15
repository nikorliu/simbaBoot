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
		<script type="text/javascript" src="${base}/js/permission/permission.js"></script>
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
									<h3 class="box-title">修改权限</h3>
								</div>
								<form role="form" onsubmit="return Permission.checkForm();" id="form" action="${base}/permission/update">
									<input type="hidden" name="id" id="id" value="${permission.id}"/>
									<div class="box-body">
										<div class="form-group">
											<label for="text">名称</label>
											<input type="text" class="form-control" id="text" name="text" placeholder="请输入名称" value="${permission.text}">
										</div>
										<div class="form-group">
											<label for="parentName">父权限</label>
											<input type="hidden" id="parentID" name="parentID" value="${parentID}" />
											<input type="text" onclick="$('#tree').fadeToggle();" class="form-control" id="parentName" name="parentName" value="${parentName}" placeholder="请选择父权限">
											<div id="tree" style="display: none;"></div>
										</div>
										<div class="form-group">
											<label for="url">URL</label>
											<input type="text" class="form-control" id="url" name="url" placeholder="请输入URL,多个URL使用英文逗号隔开" value="${permission.url}">
										</div>
									</div>
									<!-- /.box-body -->

									<div class="box-footer">
										<button type="submit" class="btn btn-success">提交</button>
										<button type="button" class="btn" onclick="Permission.toList();">取消</button>
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
			Permission.initSelectPermissionTree($("#parentID").val(), $("#parentName").val());
		});
	</script>

</html>