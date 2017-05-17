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
		<script type="text/javascript" src="${base}/js/registryTable/registryTable.js"></script>
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
									<h3 class="box-title">新增注册类型表</h3>
								</div>
								<form role="form" onsubmit="return RegistryTable.checkForm();" id="form" action="${base}/registryTable/add">
									<div class="box-body">
										<div class="form-group">
											<label for="code">编码</label>
											<input type="text" class="form-control" id="code" name="code" placeholder="请输入编码">
										</div>
										<div class="form-group">
											<label for="value">值</label>
											<input type="text" class="form-control" id="value" name="value" placeholder="请输入值">
										</div>
										<div class="form-group">
											<label for="description">描述</label>
											<input type="text" class="form-control" id="description" name="description" placeholder="请输入描述">
										</div>
										<div class="form-group">
											<label for="typeName">注册类型</label>
											<input type="hidden" id="typeID" name="typeID" value="${typeID}" />
											<input type="text" onclick="$('#tree').fadeToggle();" class="form-control" id="typeName" name="typeName" value="${typeName}" placeholder="请选择注册类型">
											<div id="tree" style="display: none;"></div>
										</div>
									</div>
									<!-- /.box-body -->

									<div class="box-footer">
										<button type="submit" class="btn btn-success">提交</button>
										<button type="button" class="btn" onclick="RegistryTable.toList();">取消</button>
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
			RegistryTable.initSelectRegistryTableTree($("#typeID").val(), $("#typeName").val());
		});
	</script>

</html>