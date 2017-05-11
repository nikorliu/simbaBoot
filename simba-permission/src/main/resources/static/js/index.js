$(document).ready(function() {
	changeFrameHeight();
	initMenu();
});

function changeFrameHeight() {
	var ifm = document.getElementById("contentiframe");
	ifm.height = document.documentElement.clientHeight - 38 - $(".main-header").height() - $(".main-footer").height();
}

window.onresize = function() { changeFrameHeight(); }

function initMenu() {
	$.ajax({
		type: "get",
		url: contextPath + "/menu/showAllMenus",
		async: true,
		dataType: "html",
		success: function(html) {
			$("#menuTree").append(html);
		}
	});

}

function forwardMenu(url) {
	if(!!url) {
		$("#contentiframe").attr("src", url);
	}
}

function logout() {
	window.self.location.href = contextPath + "/login/logout";
}

function toModifyInfo() {

}

function toModifyPwd() {

}