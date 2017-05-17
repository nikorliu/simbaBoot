var ${className} = {

	"toAdd": function() {
		window.self.location.href = contextPath + "/${firstLower}/toAdd";
	},

	"batchDelete": function() {
		var ids = new Array();
		$("input[name='${firstLower}']").each(function() {
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
			url: contextPath + "/${firstLower}/batchDelete",
			data: {
				"id": ids.join(",")
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					${className}.init${className}List(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"init${className}List": function(start, pageSize) {
		$.ajax({
			type: "get",
			url: contextPath + "/${firstLower}/getList",
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
			url: contextPath + "/${firstLower}/count",
			async: true,
			data: {
			},
			async: true,
			dataType: "json",
			success: function(data) {
				var total = data.data;
				var pageHtml = Page.init(total, start, pageSize, "${className}.clickPager");
				$("#page").html(pageHtml);
			}
		});
	},

	"clickPager": function(start, pageSize) {
		${className}.init${className}List(start, pageSize);
	},

	"toUpdate": function(id) {
		window.self.location.href = contextPath + "/${firstLower}/toUpdate?id=" + id;
	},

	"delete${className}": function(id) {
		$.ajax({
			type: "post",
			url: contextPath + "/${firstLower}/batchDelete",
			data: {
				"id": id
			},
			async: true,
			dataType: "json",
			success: function(data) {
				if(data.code == 200) {
					${className}.init${className}List(0, Page.size);
				} else {
					parent.showInfo(data.msg);
				}
			}
		});
	},

	"checkForm": function() {
		return true;
	},

	"toList": function() {
		window.self.location.href = contextPath + "/${firstLower}/list";
	},

	"end": null
};