<link rel="stylesheet" href="${base}/js/plugins/datepicker/datepicker3.css">
<script type="text/javascript" src="${base}/js/plugins/datepicker/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="${base}/js/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".datepicker").datepicker({
			autoclose: true,
			format: 'yyyy-mm-dd',
			calendarWeeks: true,
			clearBtn: true
		});
	});
</script>