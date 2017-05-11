var LineUtil = {

	/**
	 * 初始化折线图
	 * 
	 * @param id
	 *            dom元素的id
	 * @param url
	 *            获取后台数据的url
	 * @param data
	 *            获取后台数据的url对应的参数
	 * @returns
	 */
	"init" : function(id, url, data) {
		// 基于准备好的dom，初始化echarts实例
		var chart = echarts.init(document.getElementById(id));
		// 指定图表的配置项和数据
		var option = {
			title : {
				text : ''
			},
			tooltip : {
				trigger : 'axis'
			},
			legend : {
				data : []
			},
			toolbox : {
				show : true,
				feature : {
					dataZoom : {
						yAxisIndex : 'none'
					},
					dataView : {
						readOnly : false
					},
					magicType : {
						type : [ 'line', 'bar' ]
					},
					restore : {},
					saveAsImage : {}
				}
			},
			xAxis : {
				type : 'category',
				boundaryGap : false,
				data : []
			},
			yAxis : {
				type : 'value'
			},
			series : [

			]
		};

		$.ajax({
			"url" : url,
			"data" : data,
			"async" : true,
			"dataType" : "json",
			"type" : "get",
			"success" : function(json) {
				option.title.text = json.title;
				option.title.subtext = json.subtext;
				option.legend.data = json.legendList;
				option.xAxis = [ {
					type : 'category',
					boundaryGap : false,
					data : json.xAxisList
				} ]
				var values = new Array();
				var size = json.valueList.length;
				for (var i = 0; i < size; i++) {
					var value = {
						name : json.legendList[i],
						type : 'line',
						data : json.valueList[i]
					};
					values.push(value);
				}
				option.series = values;
				// 使用刚指定的配置项和数据显示图表。
				chart.setOption(option);
			}
		});
		return chart;
	},

	"end" : null

};