var query = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#data-man"]').trigger('click').parent().find('li:eq(1) a').addClass('side-active');

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

        $("#record-table").bootstrapTable({
            url: 'sys/data/record',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    sectionId: $('#query-section').val(),
                    dataType: $('#query-data-type').val(),
                    start: query.getTime(0),
                    end: query.getTime(1)
                }
            },
            columns: [{
                field: 'id',
                title: '记录编号'
            }, {
                field: 'deviceId',
                title: '来源终端编号'
            }, {
                field: 'sectionId',
                title: '来源区块编号'
            }, {
                field: 'dataTypeName',
                title: '数据类型'
            }, {
                field: 'value',
                title: '数据值'
            }, {
                field: 'receiveTime',
                title: '上传时间'
            }, {
                field: 'recordTime',
                title: '记录时间'
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
    vm: new Vue({
        el: '#wrapper',
        data: {
            sections: [],
            dataTypes: [],
            count: 0
        },
        mounted: function () {
            var that = this;
            this.$nextTick(function () {
                query.init();
                query.sendRequest('sys/file/section/data', 'GET', null, function (res) {
                    that.sections = res.rows;
                });
                query.sendRequest('sys/data/type/data', 'GET', null, function (res) {
                    that.dataTypes = res.rows;
                });
            });
        },
        updated: function () {
            this.$nextTick(function () {
                $('.selectpicker').selectpicker('refresh');
            });
        }
    })
};