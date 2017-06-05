var Menu = {

	"exportAllMenu": function() {
		window.self.location.href = contextPath + "/menu/exportAllMenu";
	},

	"initSelectMenuTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/menu/getMenuTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			$('#tree').fadeOut();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initMenuTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/menu/getMenuTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			Menu.initMenuList();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initMenuList": function() {
		$.ajax({
			type: "get",
			url: contextPath + "/menu/getMenuList",
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
		window.self.location.href = contextPath + "/menu/toAdd?parentID=" + $("#parentID").val();
	},

	"toUpdate": function(id) {
		window.self.location.href = contextPath + "/menu/toUpdate?id=" + id;
	},

	"deleteMenu": function(id) {
		$.ajax({
			type: "post",
			url: contextPath + "/menu/delete",
			data: {
				"id": id
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Menu.initMenuList();
					Menu.initMenuTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='menu']").each(function() {
			if(true == $(this).is(':checked')) {
				ids.push($(this).val());
			}
		});
		if(ids.length == 0) {
			parent.showInfo("请选择要删除的机构");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/menu/batchDelete",
			data: {
				"id": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Menu.initMenuList();
					Menu.initMenuTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toList": function() {
		window.self.location.href = contextPath + "/menu/list?parentID=" + $("#parentID").val();
	},

	"checkForm": function() {
		var text = $("#text").val();
		if(!text) {
			parent.showInfo("名称不能为空");
			return false;
		}
		var parentID = $("#parentID").val();
		if(!parentID) {
			parent.showInfo("父菜单不能为空");
			return false;
		}
		var orderNo = $("#orderNo").val();
		if(!orderNo) {
			parent.showInfo("排序不能为空");
			return false;
		}
		for(var i = 0; i < 4; i++) {
			orderNo = orderNo.replace(",", "");
		}
		$("#orderNo").val(orderNo);
		return true;
	},

	"end": null

};