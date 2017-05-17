var RegistryTable = {

	"initSelectRegistryTableTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/registryType/getRegistryTypeTree", function(data) {
			$("#typeName").val(data.text);
			$("#typeID").val(data.id);
			$('#tree').fadeOut();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initRegistryTableTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/registryType/getRegistryTypeTree", function(data) {
			$("#typeName").val(data.text);
			$("#typeID").val(data.id);
			RegistryTable.initRegistryTableList(0, Page.size);
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initRegistryTableList": function(start, pageSize) {
		$.ajax({
			type: "get",
			url: contextPath + "/registryTable/getList",
			data: {
				"typeID": $("#typeID").val(),
				"pageStart": start,
				"pageSize": pageSize
			},
			async: true,
			dataType: "html",
			success: function(html) {
				$("#table").find("tbody").html(html);
				CheckBox.init();
				setTimeout("CheckBox.bindCheckAll();", 1000);
			}
		});
		$.ajax({
			type: "get",
			url: contextPath + "/registryTable/count",
			async: true,
			data: {
				"typeID": $("#typeID").val()
			},
			async: true,
			dataType: "json",
			success: function(data) {
				var total = data.data;
				var pageHtml = Page.init(total, start, pageSize, "RegistryTable.clickPager");
				$("#page").html(pageHtml);
			}
		});
	},

	"clickPager": function(start, pageSize) {
		RegistryTable.initRegistryTableList(start, pageSize);
	},

	"toAdd": function() {
		window.self.location.href = contextPath + "/registryTable/toAdd?typeID=" + $("#typeID").val();
	},

	"toUpdate": function(id) {
		window.self.location.href = contextPath + "/registryTable/toUpdate?id=" + id;
	},

	"deleteRegistryTable": function(id) {
		$.ajax({
			type: "post",
			url: contextPath + "/registryTable/delete",
			data: {
				"id": id
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					RegistryTable.initRegistryTableList(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='registryTable']").each(function() {
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
			url: contextPath + "/registryTable/batchDelete",
			data: {
				"ids": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					RegistryTable.initRegistryTableList(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toList": function() {
		window.self.location.href = contextPath + "/registryTable/list?typeID=" + $("#typeID").val();
	},

	"checkForm": function() {
		var code = $("#code").val();
		if(!code) {
			parent.showInfo("编码不能为空");
			return false;
		}
		var value = $("#value").val();
		if(!value) {
			parent.showInfo("值不能为空");
			return false;
		}
		var typeID = $("#typeID").val();
		if(!typeID) {
			parent.showInfo("注册类型不能为空");
			return false;
		}
		return true;
	},

	"end": null

};