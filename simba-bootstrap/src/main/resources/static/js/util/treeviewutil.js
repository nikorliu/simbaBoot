var TreeViewUtil = {

	/**
	 * 初始化bootstrap treeview树
	 */
	"initTree": function(treeId, url, clickNodeFn, initFn, treeProps) {
		$.ajax({
			url: url,
			dataType: "json",
			async: true,
			type: "get",
			success: function(data) {
				if(!treeProps) {
					treeProps = {
						"data": data
					};
				} else {
					treeProps.data = data;
				}
				$('#' + treeId).treeview(treeProps);
				if(!!initFn) {
					initFn();
				}
				if(!!clickNodeFn) {
					$('#' + treeId).on('nodeSelected', function(event,
						node) {
						clickNodeFn(node);
					});
				}
			}
		});
	},

	/**
	 * 选中树中的节点
	 */
	"selectTreeNode": function(treeId, id, text) {
		id = parseInt(id + "");
		var nodes = $('#' + treeId).treeview('search', [text, { ignoreCase: false, exactMatch: false }]);
		if(!nodes || nodes.length == 0) {
			return;
		}
		var node = null;
		if(nodes.length == 1) {
			node = nodes[0];
		} else {
			var l = nodes.length;
			for(var i = 0; i < l; i++) {
				if(nodes[i].id == id) {
					node = nodes[i];
					break;
				}
			}
		}
		$('#' + treeId).treeview('toggleNodeSelected', [node, { silent: true }]);
	},

	"end": null
};