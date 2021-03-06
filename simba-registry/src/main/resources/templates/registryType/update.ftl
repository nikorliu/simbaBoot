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
		<script type="text/javascript" src="${base}/js/registryType/registryType.js"></script>
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
									<h3 class="box-title">修改注册类型</h3>
								</div>
								<form role="form" onsubmit="return RegistryType.checkForm();" id="form" action="${base}/registryType/update">
									<input type="hidden" id="id" name="id" value="${registryType.id}" />
									<div class="box-body">
											<div class="form-group">
												<label for="text">名称</label>
												<input type="text" class="form-control" id="text" name="text" value="${registryType.text}" placeholder="请输入名称">
											</div>
										<div class="form-group">
											<label for="parentName">父注册类型</label>
											<input type="hidden" id="parentID" name="parentID" value="${registryType.parentID}" />
											<input type="text" onclick="$('#tree').fadeToggle();" class="form-control" id="parentName" name="parentName" value="${parentName}" placeholder="请选择注册类型">
											<div id="tree" style="display: none;"></div>
										</div>
									</div>
									<!-- /.box-body -->

									<div class="box-footer">
										<button type="submit" class="btn btn-success">提交</button>
										<button type="button" class="btn" onclick="RegistryType.toList();">取消</button>
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
			RegistryType.initSelectRegistryTypeTree($("#parentID").val(), $("#parentName").val());
		});
	</script>

</html>