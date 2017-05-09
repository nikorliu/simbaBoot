$(document).ready(function() {
	changeFrameHeight();
	initMenu();
});

function changeFrameHeight() {
	var ifm = document.getElementById("contentiframe");
	ifm.height = document.documentElement.clientHeight - 108;
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

function forwardMenu(url){
	if(!!url){
		$("#contentiframe").attr("src",url);
	}
}
