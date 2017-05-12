<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>系统首页</title>
		<#include "adminlte.ftl"/>
			<script type="text/javascript" src="${base}/js/index.js"></script>
	</head>

	<body class="hold-transition skin-green-light sidebar-mini">
		<div class="wrapper">
			<header class="main-header">
				<!-- Logo -->
				<a href="#" class="logo">
					<!-- mini logo for sidebar mini 50x50 pixels -->
					<span class="logo-mini"><b>系统</b></span>
					<!-- logo for regular state and mobile devices -->
					<span class="logo-lg"><b>管理</b>系统</span>
				</a>
				<!-- Header Navbar: style can be found in header.less -->
				<nav class="navbar navbar-static-top">
					<!-- Sidebar toggle button-->
					<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</a>
					<div class="alert alert-danger alert-dismissible" role="alert" id="errDiv" style="position:fixed;margin: 0 auto;width:65%;height:5%;display: none;">
						<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
						<div id="errInfo"></div>
					</div>
					<div class="navbar-custom-menu">
						<ul class="nav navbar-nav">
							<!-- User Account: style can be found in dropdown.less -->
							<li class="dropdown user user-menu">
								<a href="#" class="dropdown-toggle" data-toggle="dropdown">
									<img src="${base}/img/user2-160x160.jpg" class="user-image" alt="User Image">
									<span class="hidden-xs">超级管理员</span>
								</a>
								<ul class="dropdown-menu">
									<!-- Menu Footer-->
									<li class="user-footer">
										<div class="pull-left">
											<a href="#" class="btn btn-default btn-flat" onclick="toModifyInfo();">个人信息</a>
										</div>
										<div class="pull-right">
											<a href="#" class="btn btn-default btn-flat" onclick="toModifyPwd();">修改密码</a>
										</div>
									</li>
								</ul>
							</li>

							<li>
								<a href="#" onclick="logout();" data-toggle="control-sidebar" title="退出"><i class="fa fa-power-off"></i></a>
							</li>
						</ul>
					</div>
				</nav>
			</header>
			<!-- Left side column. contains the logo and sidebar -->
			<aside class="main-sidebar">
				<!-- sidebar: style can be found in sidebar.less -->
				<section class="sidebar">
					<!-- sidebar menu: : style can be found in sidebar.less -->
					<ul class="sidebar-menu" id="menuTree">
						<li class="header">主菜单</li>
					</ul>
				</section>
				<!-- /.sidebar -->
			</aside>
			<!--
			
-->
			<!-- Content Wrapper. Contains page content -->
			<div class="content-wrapper">
				<iframe id="contentiframe" style="border-top-width: 0px; border-left-width: 0px; border-bottom-width: 0px;border-right-width: 0px;width: 100%;" src="${base}/home" frameborder="0" scrolling="auto"></iframe>
			</div>
			<!-- /.content-wrapper -->

			<footer class="main-footer">
				<div class="pull-right hidden-xs">
					<b>版本</b> 2.0.0
				</div>
				<strong>Copyright &copy; 2014-2016 .</strong> All rights reserved.
			</footer>

			<!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
			<div class="control-sidebar-bg"></div>
		</div>
		<!-- ./wrapper -->

	</body>

</html>