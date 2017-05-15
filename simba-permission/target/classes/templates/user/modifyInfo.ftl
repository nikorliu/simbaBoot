<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>系统首页</title>
		<#include "../adminlte.ftl"/>
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
								<div class="box-body">
									<div class="form-group">
										<label for="account">账号</label>
										<input type="text" class="form-control" id="account" name="account" placeholder="请输入账号" readonly="true" value="${user.account}">
									</div>
									<div class="form-group">
										<label for="name">用户名</label>
										<input type="text" class="form-control" id="name" name="name" placeholder="请输入用户名" value="${user.name}">
									</div>
									<#list descs as ext>
										<div class="form-group">
											<label for="name">${ext.name}</label>
											<input type="text" class="form-control" id="${ext.key}" name="${ext.key}" value="${ext.value}" placeholder="请输入${ext.name}">
										</div>
									</#list>
									<div class="form-group">
										<div id="errInfo" style="color: red;"></div>
									</div>
								</div>
								<!-- /.box-body -->

								<div class="box-footer">
									<button type="button" class="btn btn-success" onclick="modifyInfo();">提交</button>
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
		$(document).ready(function() {

		});

		function modifyInfo() {
			var name = $("#name").val();
			if(!name) {
				showInfo("用户名不能为空");
				return false;
			}
			$.ajax({
				"url": contextPath + "/user/modifyInfo",
				type: "post",
				dataType: "json",
				async: true,
				data: {
					<#list descs as ext >
					"${ext.key}": $("#${ext.key}").val(),
					</#list>
					"account": $("#account").val(),
					"name": $("#name").val()
				},
				success: function(data) {
					if(data.code == 200) {
						$("#loginName", parent.document).html($("#name").val());
						top.hideModal();
					} else {
						showInfo(data.msg);
					}
				}
			});
		}

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