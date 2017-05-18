<link rel="stylesheet" href="${base}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="${base}/js/plugins/datetimepicker/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="${base}/js/plugins/datetimepicker/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".datetimepicker").datetimepicker({
			autoclose: true,
			format: 'yyyy-mm-dd hh:ii:ss',
			calendarWeeks: true,
			clearBtn: true,
			todayBtn: true,
			todayHighlight: true,
			language: "zh-CN"
		});
	});
</script>