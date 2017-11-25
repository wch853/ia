// 激活侧边栏
$('[data-target="#data-man"]').trigger('click').parent().find('li:eq(1) a').addClass('side-active');

// 设置bootstrap-select大小
$('#queryToolBar .selectpicker').selectpicker({
    width: '180.67px'
});

var data = [10, 15.34, 17, 12];
var category = ["2017/11/25 13:00", "2017/11/25 14:00", "2017/11/25 15:00", "2017/11/25 16:00",
    "2017/11/26 13:00", "2017/11/26 14:00", "2017/11/26 15:00", "2017/11/26 16:00",
    "2017/11/27 13:00", "2017/11/27 14:00", "2017/11/27 15:00", "2017/11/27 16:00",
    "2017/11/28 13:00", "2017/11/28 14:00", "2017/11/28 15:00", "2017/11/28 16:00",
    "2017/11/29 13:00", "2017/11/29 14:00", "2017/11/29 15:00", "2017/11/29 16:00"
];
var title = '我的图';

var type = ['温度', '湿度'];

var chart = {
    chartOption: echarts.init(document.getElementById('chart')),
    /**
     * 初始化空chart
     */
    initChart: function (legends, category, series) {
        var option = {
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
                }
            },
            title: {
                text: '数据分析'
            },
            legend: {
                data: legends
            },
            toolbox: {
                feature: {
                    dataZoom: {
                        yAxisIndex: 'none'
                    },
                    restore: {},
                    saveAsImage: {}
                }
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: category
            },
            yAxis: {
                type: 'value',
                boundaryGap: [0, '100%']
            },
            dataZoom: [{
                type: 'inside',
                start: 0,
                end: 100
            }, {
                start: 0,
                end: 100,
                handleIcon: 'M10.7,11.9v-1.3H9.3v1.3c-4.9,0.3-8.8,4.4-8.8,9.4c0,5,3.9,9.1,8.8,9.4v1.3h1.3v-1.3c4.9-0.3,8.8-4.4,8.8-9.4C19.5,16.3,15.6,12.2,10.7,11.9z M13.3,24.4H6.7V23h6.6V24.4z M13.3,19.6H6.7v-1.4h6.6V19.6z',
                handleSize: '80%',
                handleStyle: {
                    color: '#fff',
                    shadowBlur: 3,
                    shadowColor: 'rgba(0, 0, 0, 0.6)',
                    shadowOffsetX: 2,
                    shadowOffsetY: 2
                }
            }],
            series: series
        };
        chart.chartOption.setOption(option);
    },
    /**
     * 转换数据类型
     */
    convertDataType: function (dataType) {
        var type = '';
        switch (dataType) {
            case '1':
                type = '温度';
                break;
            case '2':
                type = "湿度";
                break;
            case '3':
                type = '土壤温度';
                break;
            case '4':
                type = '土壤湿度';
                break;
            case '5':
                type = '光照度';
                break;
            case '6':
                type = 'CO2浓度';
                break;
            case '7':
                type = 'pH（土壤酸碱度）';
                break;
            case '8':
                type = 'N（氮含量）';
                break;
            case '9':
                type = 'P（磷含量）';
                break;
            case '10':
                type = 'K（钾含量）';
                break;
            case '11':
                type = 'Hg（汞含量）';
                break;
            case '12':
                type = 'Pb（铅含量）';
                break;
            default:
                type = '';
        }
        return type;
    },
    /**
     * 通过指定数据类型和大棚编号查询数据
     * @param types
     * @param fieldId
     */
    sendRequest: function (types, fieldId) {
        var dataTypes = types.split(',');
        $.ajax({
            url: 'sys/data/getChartData',
            data: {
                dataTypes: dataTypes,
                fieldId: fieldId
            },
            success: function (res) {
                if (200 === res.code) {
                    var data = res.data;
                    // 图例
                    var legends = chart.getLegends(dataTypes);
                    /** @namespace data.dateList 日期列表 */
                    var dateList = data.dateList;
                    /** @namespace data.dataMap 数据类型-数据列表 */
                    var typeToData = data.dataMap;
                    var series = chart.getSeries(typeToData);
                    chart.initChart(legends, dateList, series);
                } else if (300 === res.code) {
                    $('#chart').empty();
                    if (res.message) {
                        bootbox.alert({
                            title: '提示',
                            message: res.message
                        });
                    }
                }
            }
        });
    },
    /**
     * 将数据类型代码转为数据类型名称集合
     * @param dataTypes
     * @returns {Array}
     */
    getLegends: function (dataTypes) {
        var legends = [];
        $.each(dataTypes, function (i, el) {
            legends.push(chart.convertDataType(el));
        });
        return legends;
    },
    /**
     * 解析数据
     * @param typeToData
     * @returns {Array}
     */
    getSeries: function (typeToData) {
        var series = [];
        $.each(typeToData, function (i, el) {
            var singleSeries = {
                name: '',
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: '#f2f'
                    }
                },
                data: []
            };
            singleSeries.name = chart.convertDataType(i);
            singleSeries.data = el;
            series.push(singleSeries);
        });
        return series;
    }
};

$('#init-chart').click(function () {
    var types = $('#queryDataType').val();
    var fieldId = $('#queryFieldId').val();
    if (types && fieldId) {
        chart.sendRequest(types, fieldId);
    } else {
        bootbox.alert({
           title: '提示',
           message: '请选择数据类型和大棚编号！'
        });
    }
});

// TODO 不同数据使用不同颜色