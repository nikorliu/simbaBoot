var GaugeUtil = {

	/**
	 * 初始化仪表盘
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
				subtext : ''
			},
			tooltip : {
				formatter : "{a} <br/>{b} : {c}%"
			},
			toolbox : {
				feature : {
					restore : {},
					saveAsImage : {}
				}
			},
			series : [ {
				name : '',
				type : 'gauge',
				detail : {
					formatter : '{value}%'
				},
				data : []
			} ]
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
				option.series = [ {
					name : json.name,
					type : 'gauge',
					detail : {
						formatter : '{value}%'
					},
					data : [ {
						value : json.value,
						name : json.valueName
					} ]
				} ]
				// 使用刚指定的配置项和数据显示图表。
				chart.setOption(option);
			}
		});
		return chart;
	},

	"end" : null

};