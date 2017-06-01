var Role = {

	"clearPermission": function(name) {
		$.ajax({
			type: "post",
			url: contextPath + "/role/clearPermission",
			async: true,
			data: {
				roleName: name
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					parent.showSuccessInfo("清空权限成功");
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

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
		top.showModal("分配权限", contextPath + "/role/toAssignPermission?name=" + name, 700);
	},

	"assignPermission": function() {
		var permissionids = $("#permissionids").val();
		if(!permissionids) {
			showInfo("请选择要分配的权限");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/role/assignPermission",
			async: true,
			dataType: "json",
			data: {
				"permissionID": permissionids,
				"roleName": $("#roleName").val()
			},
			success: function(data) {
				if(data.code == 200) {
					top.hideModal();
				} else {
					showInfo(data.msg);
				}
			}
		});
	},

	"initSelectPermissionTree": function(ids, texts) {
		TreeViewUtil.initMultiTree("tree", contextPath + "/permission/getPermissionTree", function(data) {
			var id = data.id;
			var permissionids = $("#permissionids").val();
			if(permissionids == "") {
				$("#permissionids").val(id);
			} else {
				$("#permissionids").val(permissionids + "," + id);
			}
		}, function(data) {
			var id = data.id;
			var permissionids = $("#permissionids").val();
			if(permissionids == id) {
				$("#permissionids").val("");
			} else {
				var idArray = permissionids.split(",");
				var newIDArray = new Array();
				for(var i = 0; i < idArray.length; i++) {
					if(id != idArray[i]) {
						newIDArray.push(idArray[i]);
					}
				}
				$("#permissionids").val(newIDArray.join(","));
			}
		}, function() {
			if(!!ids && !!texts) {
				TreeViewUtil.checkTreeNode("tree", ids, texts);
			}
		});
	},

	"toList": function() {
		window.self.location.href = contextPath + "/role/list";
	},

	"checkForm": function() {
		var name = $("#name").val();
		if(!name) {
			parent.showInfo("名称不能为空");
			return false;
		}
		return true;
	},

	"end": null

};