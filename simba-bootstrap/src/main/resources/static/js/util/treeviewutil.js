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
	 * 初始化多选的treeview树
	 */
	"initMultiTree": function(treeId, url, selectNodeFn, unselectNodeFn, initFn, treeProps) {
		$.ajax({
			url: url,
			dataType: "json",
			async: true,
			type: "get",
			success: function(data) {
				if(!treeProps) {
					treeProps = {
						"data": data,
						"multiSelect": true,
						"showCheckbox": true
					};
				} else {
					treeProps.data = data;
					treeProps.multiSelect = true;
					treeProps.showCheckbox = true;
				}
				$('#' + treeId).treeview(treeProps);
				if(!!initFn) {
					initFn();
				}
				if(!!selectNodeFn) {
					$('#' + treeId).on('nodeChecked', function(event,
						node) {
						selectNodeFn(node);
					});
				}
				if(!!unselectNodeFn) {
					$('#' + treeId).on('nodeUnchecked', function(event,
						node) {
						unselectNodeFn(node);
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

	/**
	 * 选中多选树中的节点
	 */
	"checkTreeNode": function(treeId, ids, texts) {
		var idArray = ids.split(",");
		var textArray = texts.split(",");
		for(var i = 0; i < idArray.length; i++) {
			var id = idArray[i];
			var text = textArray[i];
			var nodes = $('#' + treeId).treeview('search', [text, { ignoreCase: false, exactMatch: false }]);
			if(!nodes || nodes.length == 0) {
				continue;
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
			$('#' + treeId).treeview('toggleNodeChecked', [node, { silent: true }]);
		}
	},

	"end": null
};