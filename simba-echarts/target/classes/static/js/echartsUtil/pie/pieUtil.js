var PieUtil = {

	/**
	 * 初始化饼图
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
				formatter : "{a} <br/>{b} : {c} ({d}%)"
			},
			legend : {
				orient : 'vertical',
				left : 'left',
				data : []
			},
			series : [ {
				name : '',
				type : 'pie',
				radius : '55%',
				center : [ '50%', '60%' ],
				data : [

				],
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
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
					radius : '55%',
					center : [ '50%', '60%' ],
					data : values,
					itemStyle : {
						emphasis : {
							shadowBlur : 10,
							shadowOffsetX : 0,
							shadowColor : 'rgba(0, 0, 0, 0.5)'
						}
					}
				} ];
				// 使用刚指定的配置项和数据显示图表。
				chart.setOption(option);
			}
		});
		return chart;
	},

	"end" : null

};