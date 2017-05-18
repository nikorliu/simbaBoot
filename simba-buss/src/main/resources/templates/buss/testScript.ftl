<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>系统首页</title>
		<#include "../adminlte.ftl"/>
		<script type="text/javascript" src="${base}/js/buss/buss.js"></script>
	</head>

	<body>
		<div>
			<!-- Content Wrapper. Contains page content -->
			<div class="">
				<section class="content">
					<div class="row">
						<input type="hidden" id="name" name="name" value="${buss.name}" />
						<!-- /.col -->
						<div class="col-md-12">
							<div class="box box-primary">
								<div class="box-header with-border">
									<h3 class="box-title">${buss.name}(${buss.description})</h3>
								</div>
								<div class="box-body">
									<div class="form-group">
										<label for="params">参数</label>
										<input type="text" class="form-control" id="params" name="params" placeholder="请输入参数：(参数格式为key1=value1&key2=value2)">
									</div>
									<div class="form-group">
										<div style="color: red;" id="executeReslut"></div>
									</div>
								</div>
								<!-- /.box-body -->

								<div class="box-footer">
									<button type="button" class="btn btn-success" onclick="Buss.executeTestScript();">测试</button>
									<button type="button" class="btn" onclick="top.hideModal();">取消</button>
								</div>

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
		$(document).ready(function() {});
	</script>

</html>