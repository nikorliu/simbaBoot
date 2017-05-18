var Buss = {

	"toAdd": function() {
		window.self.location.href = contextPath + "/buss/toAdd";
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='buss']").each(function() {
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
			url: contextPath + "/buss/batchDelete",
			data: {
				"names": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Buss.initBussList(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"initBussList": function(start, pageSize) {
		$.ajax({
			type: "get",
			url: contextPath + "/buss/getList",
			data: {
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
			url: contextPath + "/buss/count",
			async: true,
			data: {},
			async: true,
			dataType: "json",
			success: function(data) {
				var total = data.data;
				var pageHtml = Page.init(total, start, pageSize, "Buss.clickPager");
				$("#page").html(pageHtml);
			}
		});
	},

	"clickPager": function(start, pageSize) {
		Buss.initBussList(start, pageSize);
	},

	"toUpdate": function(name) {
		window.self.location.href = contextPath + "/buss/toUpdate?name=" + name;
	},

	"deleteBuss": function(name) {
		$.ajax({
			type: "post",
			url: contextPath + "/buss/delete",
			data: {
				"name": name
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					Buss.initBussList(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"checkForm": function() {
		var name = $("#name").val();
		if(!name) {
			parent.showInfo("名称不能为空");
			return false;
		}
		var script = $("#script").val();
		if(!script) {
			parent.showInfo("脚本不能为空");
			return false;
		}
		return true;
	},

	"toList": function() {
		window.self.location.href = contextPath + "/buss/list";
	},

	"show": function(name) {
		top.showModal("查看脚本", contextPath + "/buss/showScript?name=" + name, 600);
	},

	"test": function(name) {
		top.showModal("测试脚本", contextPath + "/buss/testScript?name=" + name, 250);
	},

	"executeTestScript": function() {
		$.ajax({
			url: contextPath + "/buss/execute?json&scriptName=" + $("#name").val(),
			type: "post",
			dataType: "json",
			async: true,
			data: $("#params").val(),
			success: function(data) {
				if(data.code == 200) {
					$("#executeReslut").html("执行结果为 " + data.data);
				} else {
					$("#executeReslut").html("执行结果为 " + data.msg);
				}
			},
			error: function() {
				$("#executeReslut").html("执行脚本失败");
			}
		});
	},

	"end": null

};