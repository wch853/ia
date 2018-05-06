var chart = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#data-man"]').trigger('click').parent().find('li:eq(2) a').addClass('side-active');

        $('#query-date').daterangepicker({
            locale: {
                applyLabel: '确认',
                cancelLabel: '取消',
                format: 'YYYY-MM-DD HH:mm:ss',
                customRangeLabel: '自定义',
                separator: ' - '
            },
            ranges: {
                '今天': [moment(), moment()],
                '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
                '过去7天': [moment().subtract(6, 'days'), moment()],
                '过去30天': [moment().subtract(29, 'days'), moment()],
                '本月': [moment().startOf('month'), moment().endOf('month')],
                '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
            },
        }).val('');
    },
    chartOption: echarts.init(document.getElementById('chart-container')),
    /**
     * 初始化空chart
     */
    initChart: function (lengends, category, series) {
        var option = {
            title: {
                text: '数据图表分析'
            },
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: lengends
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
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
            tooltip: {
                trigger: 'axis',
                position: function (pt) {
                    return [pt[0], '10%'];
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
                start: 80,
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
        chart.chartOption.setOption(option, true);
    },
    /**
     * 随机颜色
     * @returns {string}
     */
    getRandomColor: function () {
        return '#' + (Math.random() * 0xffffff << 0).toString(16);
    },
    /**
     * 发送请求
     * @param url
     * @param method
     * @param params
     * @param callback
     */
    sendRequest: function (url, method, params, callback) {
        $.ajax({
            url: url,
            type: method,
            data: params,
            success: function (res) {
                if (undefined != callback) {
                    callback(res);
                }
            }
        });
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
                name: i,
                type: 'line',
                smooth: true,
                symbol: 'none',
                sampling: 'average',
                itemStyle: {
                    normal: {
                        color: chart.getRandomColor()
                    }
                },
                data: el
            };
            series.push(singleSeries)
        });
        return series;
    },
    /**
     * 获取起止时间
     * @param index
     * @returns String
     */
    getTime: function (index) {
        var time = $('#query-date').val().split(' - ');
        if (time.length > 1) {
            return time[index].trim();
        } else {
            return '';
        }
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            sections: [],
            dataTypes: []
        },
        mounted: function () {
            var that = this;
            this.$nextTick(function () {
                chart.init();
                chart.sendRequest('sys/data/type/data', 'GET', null, function (res) {
                    that.dataTypes = res.rows;
                });
                chart.sendRequest('sys/file/section/data', 'GET', null, function (res) {
                    that.sections = res.rows;
                });
            });
        },
        methods: {
            constructChart: function () {
                var start = chart.getTime(0);
                var end = chart.getTime(1);
                var dataTypes = $('#query-data-type').val();
                var sectionId = $('#query-section').val();

                var param = {
                    dataTypes: dataTypes,
                    sectionId: sectionId,
                    start: start,
                    end: end
                };
                chart.sendRequest('sys/data/chart', 'POST', param, function (res) {
                    if (res.success) {
                        var dateList = res.data.dateList;
                        var typeToData = res.data.dataMap;

                        var legends = [];
                        $.each(typeToData, function (i, el) {
                            legends.push(i);
                        });

                        var series = chart.getSeries(typeToData);
                        chart.initChart(legends, dateList, series);
                    } else {
                        bootbox.alert({
                            title: '提示',
                            message: '获取数据失败！'
                        });
                    }
                });
            }
        }
    })
};