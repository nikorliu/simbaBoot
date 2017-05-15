var Org = {

	"initSelectOrgTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/org/getOrgTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			$('#tree').fadeOut();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initOrgTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/org/getOrgTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			Org.initOrgList();
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"initOrgList": function() {
		$.ajax({
			type: "get",
			url: contextPath + "/org/getOrgList",
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
		window.self.location.href = contextPath + "/org/toAdd?parentID=" + $("#parentID").val();
	},

	"toUpdate": function(id) {
		window.self.location.href = contextPath + "/org/toUpdate?id=" + id;
	},

	"deleteOrg": function(id) {
		$.ajax({
			type: "post",
			url: contextPath + "/org/delete",
			data: {
				"id": id
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Org.initOrgList();
					Org.initOrgTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='org']").each(function() {
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
			url: contextPath + "/org/batchDelete",
			data: {
				"ids": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Org.initOrgList();
					Org.initOrgTree($("#parentID").val(), $("#parentName").val());
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toList": function() {
		window.self.location.href = contextPath + "/org/list?parentID=" + $("#parentID").val();
	},

	"checkForm": function() {
		var text = $("#text").val();
		if(!text) {
			parent.showInfo("机构名称不能为空");
			return false;
		}
		var parentID = $("#parentID").val();
		if(!parentID) {
			parent.showInfo("父机构不能为空");
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