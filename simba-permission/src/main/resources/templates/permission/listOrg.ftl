<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<title>系统首页</title>
		<#include "../adminlte.ftl">
			<link rel="stylesheet" href="${base}/js/plugins/iCheck/flat/blue.css">
			<script type="text/javascript" src="${base}/js/plugins/bootstrap-treeview.min.js"></script>
			<script type="text/javascript" src="${base}/js/plugins/iCheck/icheck.min.js"></script>
			<script type="text/javascript" src="${base}/js/common/table.js"></script>
	</head>

	<body>
		<div>
			<!-- Content Wrapper. Contains page content -->
			<div class=" ">
				<section class="content">
					<div class="row">
						<div class="col-md-3">
							<div id="tree"></div>
							<!-- /.box -->
						</div>
						<!-- /.col -->
						<div class="col-md-9">
							<div class="box box-primary">
								<div class="box-header with-border">
									<h3 class="box-title">机构管理</h3>
								</div>
								<!-- /.box-header -->
								<div class="box-body no-padding">
									<div class="mailbox-controls">
										<!-- Check all button -->
										<button type="button" class="btn btn-default btn-sm checkbox-toggle"><i class="fa fa-square-o"></i>
                新增</button>
										<div class="btn-group">
											<button type="button" class="btn btn-default btn-sm"><i class="fa fa-trash-o"></i></button>
											<button type="button" class="btn btn-default btn-sm"><i class="fa fa-reply"></i></button>
											<button type="button" class="btn btn-default btn-sm"><i class="fa fa-share"></i></button>
										</div>
										<!-- /.btn-group -->
										<button type="button" class="btn btn-default btn-sm"><i class="fa fa-refresh"></i></button>
										<div class="pull-right">

										</div>
									</div>
									<!-- /.pull-right -->
								</div>
								<div class="table-responsive">
									<table class="table table-hover table-striped table-bordered">
										<thead>
											<tr class="active">
												<th>名称</th>
												<th>城市</th>
												<th>密码</th>
												<th>名称</th>
												<th>城市</th>
												<th>密码</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td><input type="checkbox" name="org"></td>
												<td class="mailbox-star">
													<a href="#"><i class="fa fa-star-o text-yellow"></i></a>
												</td>
												<td class="mailbox-name">
													<a href="read-mail.html">Alexander Pierce</a>
												</td>
												<td class="mailbox-subject"><b>AdminLTE 2.0 Issue</b> - Trying to find a solution to this problem...
												</td>
												<td class="mailbox-attachment"></td>
												<td class="mailbox-date">2 days ago</td>
											</tr>
											<tr>
												<td><input type="checkbox"></td>
												<td class="mailbox-star">
													<a href="#"><i class="fa fa-star-o text-yellow"></i></a>
												</td>
												<td class="mailbox-name">
													<a href="read-mail.html">Alexander Pierce</a>
												</td>
												<td class="mailbox-subject"><b>AdminLTE 2.0 Issue</b> - Trying to find a solution to this problem...
												</td>
												<td class="mailbox-attachment"><i class="fa fa-paperclip"></i></td>
												<td class="mailbox-date">4 days ago</td>
											</tr>
											<tr>
												<td><input type="checkbox"></td>
												<td class="mailbox-star">
													<a href="#"><i class="fa fa-star text-yellow"></i></a>
												</td>
												<td class="mailbox-name">
													<a href="read-mail.html">Alexander Pierce</a>
												</td>
												<td class="mailbox-subject"><b>AdminLTE 2.0 Issue</b> - Trying to find a solution to this problem...
												</td>
												<td class="mailbox-attachment"><i class="fa fa-paperclip"></i></td>
												<td class="mailbox-date">15 days ago</td>
											</tr>
										</tbody>
									</table>
									<!-- /.table -->
								</div>
								<!-- /.mail-box-messages -->
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
		<button onclick="s()">获取</button>
	</body>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tree').treeview({
				data: getTree(),
				enableLinks: true,
				multiSelect: false,
				showCheckbox: true
			});

			$('#tree').on('nodeChecked', function(event,
				data) {
				//
				console.log(data);
			});

			$('input[type="checkbox"]').iCheck({
				checkboxClass: 'icheckbox_flat-blue',
				radioClass: 'iradio_flat-blue'
			});
		});

		function s() {
			console.log($('#tree').treeview('getSelected'));
		}

		function getTree() {
			return [{
					text: "Parent 1",
					href: "javascript:alert('hello world!!!');",
					nodes: [{
							text: "Child1",
							nodes: [{
									text: "Grandchild1"
								},
								{
									text: "Grandchild2"
								}
							]
						},
						{
							text: "Child2",
							href: "http://www.baidu.com"
						}
					]
				},
				{
					id:1200,
					text: "Parent2",
					parentID:250,
					name:"my god",
					value:"value1111"
				},
				{
					text: "Parent3"
				},
				{
					text: "Parent4"
				},
				{
					text: "Parent5"
				}
			];;
		}
	</script>

</html>