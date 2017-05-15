var Page = {

	/**
	 * 构造分页组件
	 */
	"init": function(total, start, pageSize, clickFnName) {
		//数字页最多显示页数
		var pageTotalNum = 10;
		//当前页的上下数字页数
		var pageInterval = 5;
		var disabled = false;
		//总页数
		var pageTotal = total / pageSize;
		pageTotal = parseInt(pageTotal);
		if(total % pageSize > 0 || pageTotal == 0) {
			pageTotal = pageTotal + 1;
		}
		var disabledProp = " disabled=\"true\" ";
		//当前页
		var currentPage = start / pageSize + 1;
		currentPage = parseInt(currentPage);
		var html = "";
		html += "<ul class=\"pagination pull-right pagination-sm\">";
		//首页
		html += "<li class=\"previous";
		if(currentPage == 1) {
			html += " disabled";
			disabled = true;
		} else {
			disabled = false;
		}
		html += "\">";
		html += "<a " + (disabled ? disabledProp : "") + (disabled ? "" : " href=\"javascript:" + clickFnName + "(0," + pageSize + ");\"") + ">首页</a></li>";
		//上页
		html += "<li class=\"";
		if(currentPage == 1) {
			html += " disabled";
			disabled = true;
		} else {
			disabled = false;
		}
		html += "\">";
		html += "<a  " + (disabled ? disabledProp : "") + (disabled ? "" : " href=\"javascript:" + clickFnName + "(" + (start - pageSize) + "," + pageSize + ");\"") + ">上页</a></li>";
		var pageIndex = 0;
		var startPage = currentPage - pageInterval;
		if(startPage < 0) {
			startPage = 0;
		}
		//数字页
		for(var i = startPage; i < pageTotal && pageIndex < pageTotalNum; i++) {
			html += "<li class=\"";
			if(i + 1 == currentPage) {
				html += " disabled active ";
				disabled = true;
			} else {
				disabled = false;
			}
			html += "\">";
			html += "<a  " + (disabled ? disabledProp : "") + (disabled ? "" : "href=\"javascript:" + clickFnName + "(" + (i * pageSize) + "," + pageSize + ");\"") + ">" + (i + 1) + "</a></li>";
			pageIndex = pageIndex + 1;
		}
		//下页
		html += "<li class=\"";
		if(currentPage == pageTotal) {
			html += " disabled";
			disabled = true;
		} else {
			disabled = false;
		}
		html += "\">";
		html += "<a  " + (disabled ? disabledProp : "") + (disabled ? "" : "href=\"javascript:" + clickFnName + "(" + (start + pageSize) + "," + pageSize + ");\"") + ">下页</a></li>";
		//尾页
		html += "<li class=\"next";
		if(currentPage == pageTotal) {
			html += " disabled";
			disabled = true;
		} else {
			disabled = false;
		}
		html += "\">";
		html += "<a  " + (disabled ? disabledProp : "") + (disabled ? "" : "href=\"javascript:" + clickFnName + "(" + (pageTotal * pageSize - pageSize) + "," + pageSize + ");\"") + " >尾页</a></li>";
		html += "</ul>";
		return html;
	},

	"size": 10,

	"end": null
};