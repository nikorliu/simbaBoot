var CheckBox = {

	"init": function() {
		$('input[type="checkbox"]').iCheck({
			checkboxClass: 'icheckbox_flat-green',
			radioClass: 'iradio_flat-green'
		});
	},

	"bindCheckAll": function() {
		$("#checkAll").on("ifChecked", function(event) {
			$('input[type="checkbox"]').iCheck('check');
		});
		$("#checkAll").on("ifUnchecked", function(event) {
			$('input[type="checkbox"]').iCheck('uncheck');
		});
	},

	"end": null
};