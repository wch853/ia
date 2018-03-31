var type = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#data-man"]').trigger('click').parent().find('li:eq(2) a').addClass('side-active');

        $("#data-type-table").bootstrapTable({
            url: 'sys/data/type/data',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    dataTypeName: $('#data-type-name').val().trim(),
                    dataShortName: $('#data-short-name').val().trim(),
                    useStatus: $('#use-status').val()
                }
            },
            columns: [{
                field: 'id',
                title: '数据类型编号'
            }, {
                field: 'dataTypeName',
                title: '数据类型名称'
            }, {
                field: 'dataShortName',
                title: '数据类型缩写'
            }, {
                field: 'floor',
                title: '数据阈值下限'
            }, {
                field: 'ceil',
                title: '数据阈值上限'
            }, {
                formatter: function (value, row, index) {
                    return type.vm.useStatusMap.get(row.useStatus);
                },
                title: '使用状态'
            }, {
                formatter: function (value, row, index) {
                    var prefix = '<a type="button" class="btn btn-operate change-use" href="javascript:void(0);">';
                    var suffix = '</a>';
                    var format = '';
                    if (row.useStatus == 0) {
                        // 未使用
                        format = prefix + '<i class="fa fa-pencil"></i> 使用' + suffix;
                    } else if (row.useStatus == 1) {
                        // 使用中
                        format = prefix + '<i class="fa fa-times"></i> 禁用' + suffix;
                    }
                    return format
                },
                events: {
                    'click .change-use': function (e, value, row, index) {
                        type.changeUseStatus(row.id, row.useStatus);
                    }
                },
                title: '操作'
            }],
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 10,
            pageList: [5, 10, 25, 50]
        });

        // 重置
        $('#reset-btn').click(function () {
            $('#query-tool-bar :text').val('');
            $('#query-tool-bar .selectpicker').selectpicker('val', '');
            $('#query-btn').trigger('click');
        });

        // 查询
        $('#query-btn').click(function () {
            $("#data-type-table").bootstrapTable('selectPage', 1);
        });
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
    /**
     * 更改数据使用状态
     * @param id
     * @param useStatus
     */
    changeUseStatus: function (id, useStatus) {
        bootbox.confirm({
            title: '提示',
            message: '确认更改数据使用状态',
            callback: function (flag) {
                if (!flag) {
                    return;
                }
                var params = {
                    id: id,
                    useStatus: useStatus == 1 ? 0 : 1
                };
                type.sendRequest('sys/data/type/modify', 'post', params, function (res) {
                    var message;
                    if (res.success) {
                        message = '更新数据使用状态成功！'
                    } else {
                        message = '更新数据使用状态失败！';
                        if (res.message) {
                            message = res.message;
                        }
                    }
                    $('#reset-btn').trigger('click');
                    bootbox.alert({
                        title: '提示',
                        message: message
                    });
                });
            }
        })
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            useStatusMap: new Map([[0, '未使用'], [1, '使用中']])
        },
        mounted: function () {
            this.$nextTick(function () {
                type.init();
            });
        }
    })
}