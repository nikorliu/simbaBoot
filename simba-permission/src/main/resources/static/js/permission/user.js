var User = {

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
		window.self.location.href = contextPath + "/user/toAssignRole?account=" + account;
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

		return true;
	},

	"toList": function() {
		window.self.location.href = contextPath + "/user/list?orgID=" + ("#orgID").val();
	},

	"initSelectOrgTree": function(ids, texts) {
		TreeViewUtil.initMultiTree("tree", contextPath + "/org/getOrgTree", function(data) {

		}, function(data) {

		}, function() {
			if(!!id && !!text) {
				TreeViewUtil.checkTreeNode("tree", ids, texts);
			}
		});
	},

	"end": null
};