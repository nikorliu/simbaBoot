var RegistryType = {

	"initSelectRegistryTypeTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/registryType/getRegistryTypeTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			$('#tree').fadeOut();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initRegistryTypeTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/registryType/getRegistryTypeTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			RegistryType.initRegistryTypeList();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initRegistryTypeList": function() {
		$.ajax({
			type: "get",
			url: contextPath + "/registryType/getRegistryTypeList",
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
		window.self.location.href = contextPath + "/registryType/toAdd?parentID=" + $("#parentID").val();
	},

	"toUpdate": function(id) {
		window.self.location.href = contextPath + "/registryType/toUpdate?id=" + id;
	},

	"deleteRegistryType": function(id) {
		$.ajax({
			type: "post",
			url: contextPath + "/registryType/delete",
			data: {
				"id": id
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					RegistryType.initRegistryTypeList();
					RegistryType.initRegistryTypeTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='registryType']").each(function() {
			if(true == $(this).is(':checked')) {
				ids.push($(this).val());
			}
		});
		if(ids.length == 0) {
			parent.showInfo("请选择要删除的记录");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/registryType/batchDelete",
			data: {
				"id": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					RegistryType.initRegistryTypeList();
					RegistryType.initRegistryTypeTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toList": function() {
		window.self.location.href = contextPath + "/registryType/list?parentID=" + $("#parentID").val();
	},

	"checkForm": function() {
		var text = $("#text").val();
		if(!text) {
			parent.showInfo("名称不能为空");
			return false;
		}
		var parentID = $("#parentID").val();
		if(!parentID) {
			parent.showInfo("父注册类型不能为空");
			return false;
		}
		return true;
	},

	"end": null

};