var Permission = {

	"initSelectPermissionTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/permission/getPermissionTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			$('#tree').fadeOut();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initPermissionTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/permission/getPermissionTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			Permission.initPermissionList();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initPermissionList": function() {
		$.ajax({
			type: "get",
			url: contextPath + "/permission/getPermissionList",
			data: {
				"parentID": $("#parentID").val()
			},
			async: true,
			dataType: "html",
			success: function(html) {
				$("#table").find("tbody").html(html);
				CheckBox.init();
				setTimeout("CheckBox.bindCheckAll();", 1000);
			}
		});
	},

	"toAdd": function() {
		window.self.location.href = contextPath + "/permission/toAdd?parentID=" + $("#parentID").val();
	},

	"toUpdate": function(id) {
		window.self.location.href = contextPath + "/permission/toUpdate?id=" + id;
	},

	"deletePermission": function(id) {
		$.ajax({
			type: "post",
			url: contextPath + "/permission/delete",
			data: {
				"id": id
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Permission.initPermissionList();
					Permission.initPermissionTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='permission']").each(function() {
			if(true == $(this).is(':checked')) {
				ids.push($(this).val());
			}
		});
		if(ids.length == 0) {
			parent.showInfo("请选择要删除的权限");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/permission/batchDelete",
			data: {
				"ids": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Permission.initPermissionList();
					Permission.initPermissionTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toList": function() {
		window.self.location.href = contextPath + "/permission/list?parentID=" + $("#parentID").val();
	},

	"checkForm": function() {
		var text = $("#text").val();
		if(!text) {
			parent.showInfo("权限名称不能为空");
			return false;
		}
		var parentID = $("#parentID").val();
		if(!parentID) {
			parent.showInfo("父权限不能为空");
			return false;
		}
		return true;
	},

	"end": null

};