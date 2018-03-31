var record = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#alarm-man"]').trigger('click').parent().find('li:eq(1) a').addClass('side-active');

        $("#record-table").bootstrapTable({
            url: 'sys/alarm/record/data',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    deviceId: $('#device-id').val().trim(),
                    dataType: $('#data-type').val().trim(),
                    start: record.getTime(0),
                    end: record.getTime(1),
                    handleFlag: $('#hadle-flag').val()
                }
            },
            columns: [{
                field: 'id',
                title: '记录编号'
            }, {
                field: 'deviceId',
                title: '终端设备编号'
            }, {
                field: 'dataTypeName',
                title: '报警类型'
            }, {
                field: 'value',
                title: '报警值'
            }, {
                field: 'alarmTime',
                title: '报警时间'
            }, {
                field: 'handleTime',
                title: '处理时间'
            }, {
                formatter: function (value, row, index) {
                    return record.vm.handleFlagMap.get(row.handleFlag);
                },
                title: '处理标志'
            }],
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 10,
            pageList: [5, 10, 25, 50]
        });

        // 重置
        $('#reset-btn').click(function () {
            $('#query-date').val('');
            $('#query-tool-bar .selectpicker').selectpicker('val', '');
            $('#query-btn').trigger('click');
        });

        // 查询
        $('#query-btn').click(function () {
            $("#record-table").bootstrapTable('selectPage', 1);
        });

        $('#query-date').daterangepicker({
            locale: {
                applyLabel: '确认',
                cancelLabel: '取消',
                format: 'YYYY-MM-DD',
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
    getTime: function (index) {
        var time = $('#query-date').val().split(' - ');
        if (time.length > 1) {
            return time[index].trim();
        } else {
            return '';
        }
    },
    /**
     * 发送ajax请求
     * @param url
     * @param method
     * @param params
     * @param callback
     */
    sendRequest: function (url, method, params, callback) {
        $.ajax({
            method: method,
            url: url,
            data: params,
            success: function (res) {
                if (undefined != callback) {
                    callback(res);
                }
            }
        });
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            endDevices: [],
            dataTypes: [],
            handleFlagMap: new Map([[0, '未处理'], [1, '已处理'], [2, '已忽略']])
        },
        mounted: function () {
            this.$nextTick(function () {
                record.init();
                record.sendRequest('sys/file/device/list', 'get', null, this.fillEndDevice);
                record.sendRequest('sys/data/type/list', 'get', null, this.fillDataType);
            });
        },
        methods: {
            fillEndDevice: function (res) {
                this.endDevices = res.data;
            },
            fillDataType: function (res) {
                this.dataTypes = res.data;
            }
        }
    })
}
