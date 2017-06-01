var User = {

	"clearRole": function(account) {
		$.ajax({
			type: "post",
			url: contextPath + "/user/clearRole",
			async: true,
			data: {
				account: account
			},
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					parent.showSuccessInfo("清空角色成功");
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toAdd": function() {
		window.self.location.href = contextPath + "/user/toAdd?orgID=" + $("#parentID").val();
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='user']").each(function() {
			if(true == $(this).is(':checked')) {
				ids.push($(this).val());
			}
		});
		if(ids.length == 0) {
			parent.showInfo("请选择要删除的用户");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/user/batchDelete",
			data: {
				"accounts": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					User.initUserList(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"initUserList": function(start, pageSize) {
		$.ajax({
			type: "get",
			url: contextPath + "/user/getUserList",
			data: {
				"orgID": $("#parentID").val(),
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
			url: contextPath + "/user/countUser",
			async: true,
			data: {
				"orgID": $("#parentID").val()
			},
			async: true,
			dataType: "json",
			success: function(data) {
				var total = data.data;
				var pageHtml = Page.init(total, start, pageSize, "User.clickPager");
				$("#page").html(pageHtml);
			}
		});
	},

	"clickPager": function(start, pageSize) {
		User.initUserList(start, pageSize);
	},

	"initOrgTree": function(id, text) {
		TreeViewUtil.initTree("tree", contextPath + "/org/getOrgTree", function(data) {
			$("#parentName").val(data.text);
			$("#parentID").val(data.id);
			User.initUserList(0, Page.size);
		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.selectTreeNode("tree", id, text);
			}
		});
	},

	"toUpdate": function(account) {
		window.self.location.href = contextPath + "/user/toUpdate?account=" + account;
	},

	"deleteUser": function(account) {
		$.ajax({
			type: "post",
			url: contextPath + "/user/batchDelete",
			data: {
				"accounts": account
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					User.initUserList(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"toAssignRole": function(account) {
		top.showModal("分配角色", contextPath + "/user/toAssignRole?account=" + account, 700);
	},

	"assignRole": function() {
		var ids = new Array();
		$("input[name='role']").each(function() {
			if(true == $(this).is(':checked')) {
				ids.push($(this).val());
			}
		});
		if(ids.length == 0) {
			showInfo("请选择要分配的角色");
			return false;
		}
		$.ajax({
			type: "post",
			url: contextPath + "/user/assignRole",
			data: {
				"roleName": ids.join(","),
				"account": $("#account").val()
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					top.hideModal();
				} else {
					showInfo(data.msg);
				}
			}
		});
	},

	"resetPwd": function(account) {
		$.ajax({
			url: contextPath + "/user/resetPwd?json",
			type: "post",
			dataType: "json",
			async: true,
			data: {
				account: account
			},
			success: function(data) {
				if(data.code == 200) {
					parent.showSuccessInfo("重置密码成功");
				} else {
					parent.showInfo(data.msg);
				}
			},
			error: function() {
				parent.showInfo("重置密码失败");
			}
		});
	},

	"checkForm": function() {
		var account = $("#account").val();
		if(!account) {
			parent.showInfo("账号不能为空");
			return false;
		}
		var name = $("#name").val();
		if(!name) {
			parent.showInfo("用户名不能为空");
			return false;
		}
		var orgID = $("#orgID").val();
		if(!orgID) {
			parent.showInfo("所属机构不能为空");
			return false;
		}
		return true;
	},

	"toList": function() {
		var orgID = $("#orgID").val();
		window.self.location.href = contextPath + "/user/list?orgID=" + orgID.split(",")[0];
	},

	"initSelectOrgTree": function(ids, texts) {
		TreeViewUtil.initMultiTree("tree", contextPath + "/org/getOrgTree", function(data) {
			var id = data.id;
			var text = data.text;
			var orgID = $("#orgID").val();
			var orgName = $("#orgName").val();
			if(orgID == "") {
				$("#orgID").val(id);
				$("#orgName").val(text);
			} else {
				$("#orgID").val(orgID + "," + id);
				$("#orgName").val(orgName + "," + text);
			}
		}, function(data) {
			var id = data.id;
			var text = data.text;
			var orgID = $("#orgID").val();
			var orgName = $("#orgName").val();
			if(orgID == id) {
				$("#orgID").val("");
				$("#orgName").val("");
			} else {
				var orgIDArray = orgID.split(",");
				var orgNameArray = orgName.split(",");
				var newOrgIDArray = new Array();
				var newOrgNameArray = new Array();
				for(var i = 0; i < orgIDArray.length; i++) {
					if(id != orgIDArray[i]) {
						newOrgIDArray.push(orgIDArray[i]);
						newOrgNameArray.push(orgNameArray[i]);
					}
				}
				$("#orgID").val(newOrgIDArray.join(","));
				$("#orgName").val(newOrgNameArray.join(","));
			}
		}, function() {
			if(!!ids && !!texts) {
				TreeViewUtil.checkTreeNode("tree", ids, texts);
			}
		});
	},

	"end": null
};