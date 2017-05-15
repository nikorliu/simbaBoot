var Role = {

	"toAdd": function() {
		window.self.location.href = contextPath + "/role/toAdd";
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='role']").each(function() {
			if(true == $(this).is(':checked')) {
				ids.push($(this).val());
			}
		});
		if(ids.length == 0) {
			parent.showInfo("请选择要删除的角色");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/role/batchDelete",
			data: {
				"roleNames": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Role.toList();
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toUpdate": function(name) {
		window.self.location.href = contextPath + "/role/toUpdate?name=" + name;
	},

	"deleteRole": function(name) {
		$.ajax({
			type: "post",
			url: contextPath + "/role/batchDelete",
			data: {
				"roleNames": name
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Role.toList();
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toAssignPermission": function(name) {
		window.self.location.href = contextPath + "/user/toAssignPermission?name=" + name;
	},

	"toList": function() {
		window.self.location.href = contextPath + "/role/list";
	},

	"checkForm": function() {

	},

	"end": null

};