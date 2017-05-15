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
										<label for="oldPwd">原密码</label>
										<input type="text" class="form-control" id="oldPwd" name="oldPwd" placeholder="请输入原密码">
									</div>
									<div class="form-group">
										<label for="newPwd">新密码</label>
										<input type="text" class="form-control" id="newPwd" name="newPwd" placeholder="请输入新密码">
									</div>
									<div class="form-group">
										<label for="confirmPwd">确认密码</label>
										<input type="text" class="form-control" id="confirmPwd" name="confirmPwd" placeholder="请输入确认密码">
									</div>
									<div class="form-group">
										<div id="errInfo" style="color: red;"></div>
									</div>
								</div>
								<!-- /.box-body -->

								<div class="box-footer">
									<button type="button" class="btn btn-success" onclick="modifyPwd();">提交</button>
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

		function modifyPwd() {
			var oldPwd = $("#oldPwd").val();
			var newPwd = $("#newPwd").val();
			var confirmPwd = $("#confirmPwd").val();
			if(!oldPwd) {
				showInfo("原密码不能为空");
				return false;
			}
			if(!newPwd) {
				showInfo("新密码不能为空");
				return false;
			}
			if(!confirmPwd) {
				showInfo("确认密码不能为空");
				return false;
			}
			if(confirmPwd != newPwd) {
				showInfo("确认密码和新密码不一致");
				return false;
			}
			if(oldPwd == newPwd) {
				showInfo("旧密码和新密码不能一样");
				return false;
			}
			$.ajax({
				type: "post",
				url: contextPath + "/user/modifyPwd",
				async: true,
				dataType: "json",
				data: {
					"newPwd": newPwd,
					"oldPwd": oldPwd,
					"confirmPwd": confirmPwd
				},
				success: function(data) {
					if(data.code == 200) {
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