var AnnularUtil = {

	/**
	 * 初始化环形图
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
				text : '',
				subtext : '',
				x : 'center'
			},
			tooltip : {
				trigger : 'item',
				formatter : "{a} <br/>{b}: {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				x : 'left',
				data : []
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
				var values = new Array();
				var size = json.valueList.length;
				for (var i = 0; i < size; i++) {
					var value = {
						"name" : json.legendList[i],
						"value" : json.valueList[i]
					};
					values.push(value);
				}
				option.series = [ {
					name : json.name,
					type : 'pie',
					radius : [ '50%', '70%' ],
					avoidLabelOverlap : false,
					label : {
						normal : {
							show : false,
							position : 'center'
						},
						emphasis : {
							show : true,
							textStyle : {
								fontSize : '30',
								fontWeight : 'bold'
							}
						}
					},
					labelLine : {
						normal : {
							show : false
						}
					},
					data : values
				} ]
				// 使用刚指定的配置项和数据显示图表。
				chart.setOption(option);
			}
		});
		return chart;
	},

	"end" : null

};