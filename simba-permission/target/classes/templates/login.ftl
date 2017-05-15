<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>系统登录页</title>
		<#include "bootstrap.ftl"/>
			<link href="${request.getContextPath()}/css/login.css" rel="stylesheet">
	</head>

	<body>
		<div class="container">
			<#if errMsg??>
				<div class="alert alert-danger alert-dismissible" role="alert" id="errDiv" style="position:fixed;margin: 0 auto;width:70%;">
					<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button> ${errMsg!}
				</div>
			</#if>
			<div class="form row">
				<form class="form-horizontal col-sm-offset-3 col-md-offset-3" id="login_form" action="${request.getContextPath()}/login/login" method="post">
					<h3 class="form-title">登录系统</h3>
					<div class="col-sm-9 col-md-9">
						<div class="form-group">
							<i class="fa fa-user fa-lg"></i>
							<input class="form-control" type="text" placeholder="请输入您的账号" id="userName" name="userName" value="${userName!}" autofocus="autofocus" />
						</div>
						<div class="form-group">
							<i class="fa fa-lock fa-lg"></i>
							<input class="form-control" type="password" placeholder="请输入您的密码" value="${password!}" id="password" name="password" />
						</div>
						<div class="form-group">
							<label class="checkbox">
							<input type="checkbox" name="remember" value="1"/> 记住我
						</label>
							<hr />

						</div>
						<div class="form-group">
							<input type="submit" class="btn btn-success pull-right" value="登录" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</body>
	<script type="text/javascript">
		if(window.parent != window) {
			top.location.href = contextPath + "/login/toLogin";
		}
		$(document).ready(function() {
			var errMsg = "${errMsg!}";
			if(!!errMsg) {
				setTimeout("closeErrDiv()", 1500);
			}
		});

		function closeErrDiv() {
			$("#errDiv").fadeOut();
		}
	</script>

</html>