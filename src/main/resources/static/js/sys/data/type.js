var type = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#data-man"]').trigger('click').parent().find('li:eq(0) a').addClass('side-active');

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
                title: '监控状态'
            }, {
                formatter: function (value, row, index) {
                    var prefix = '<a type="button" class="btn btn-operate change-use" href="javascript:void(0);">';
                    var suffix = '</a><a type="button" class="btn btn-operate mod" href="javascript:void(0);">' +
                        '<i class="fa fa-pencil"></i> 修改</a>' +
                        '<a type="button" class="btn btn-operate rmv" href="javascript:void(0);">' +
                        '<i class="fa fa-times"></i> 删除</a>'
                    var format = '';
                    if (row.useStatus == 0) {
                        // 未监控
                        format = prefix + '<i class="fa fa-laptop"></i> 监控' + suffix;
                    } else if (row.useStatus == 1) {
                        // 监控中
                        format = prefix + '<i class="fa fa-circle-o-notch"></i> 忽略' + suffix;
                    }
                    return format
                },
                events: {
                    'click .change-use': function (e, value, row, index) {
                        type.changeUseStatus(row.id, row.useStatus);
                    },
                    'click .mod': function (e, value, row, index) {
                        type.modifyType(row.id, row.dataTypeName, row.dataShortName, row.floor, row.ceil);
                    },
                    'click .rmv': function (e, value, row, index) {
                        type.removeType(row.id);
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
    clearModalInput: function () {
        $('#type-modal :text').val('');
        $('#type-modal .selectpicker').selectpicker('val', '');
    },
    /**
     * 更改数据监控状态
     * @param id
     * @param useStatus
     */
    changeUseStatus: function (id, useStatus) {
        bootbox.confirm({
            title: '提示',
            message: '确认更改数据监控状态',
            callback: function (flag) {
                if (!flag) {
                    return;
                }
                var params = {
                    id: id,
                    useStatus: useStatus == 1 ? 0 : 1
                };
                type.sendRequest('sys/data/type/modify', 'POST', params, function (res) {
                    var message;
                    if (res.success) {
                        message = '更新数据监控状态成功！'
                    } else {
                        message = '更新数据监控状态失败！';
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
    /**
     * 修改
     * @param id
     * @param dataTypeName
     * @param dataShortName
     * @param floor
     * @param ceil
     */
    modifyType: function (id, dataTypeName, dataShortName, floor, ceil) {
        type.vm.isAdd = false;
        type.vm.modifyId = id;
        $('#edit-data-type-name').val(dataTypeName);
        $('#edit-data-type-short-name').val(dataShortName);
        $('#edit-floor').val(floor);
        $('#edit-ceil').val(ceil);
        $('#type-modal').modal('show');
    },
    /**
     * 删除
     * @param id
     */
    removeType: function (id) {
        bootbox.confirm({
            title: '提示',
            message: '确认删除数据类型信息',
            callback: function (flag) {
                if (flag) {
                    type.sendRequest('sys/data/type/remove', 'POST', {id: id}, function (res) {
                        var message = (res.success ? '成功' : '失败');
                        bootbox.alert({
                            title: '提示',
                            message: '删除数据类型信息' + message
                        });
                        $('#reset-btn').trigger('click');
                    });
                }
            }
        });
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            useStatusMap: new Map([[0, '未监控'], [1, '监控中']]),
            isAdd: null,
            modifyId: null
        },
        mounted: function () {
            this.$nextTick(function () {
                type.init();
            });
        },
        methods: {
            addDataType: function () {
                type.clearModalInput();
                $('#type-modal').modal('show');
                type.vm.isAdd = true;
            },
            saveAdd: function () {
                var dataTypeName = $('#edit-data-type-name').val().trim();
                var dataShortName = $('#edit-data-type-short-name').val().trim();
                var floor = $('#edit-floor').val().trim();
                var ceil = $('#edit-ceil').val().trim();

                $('#type-modal').modal('hide');
                if (dataTypeName == '' || dataShortName == '' || floor == '' || ceil == '' || isNaN(floor) || isNaN(ceil)) {
                    bootbox.alert({
                        title: '提示',
                        message: '请输入正确信息！'
                    });
                    return
                }
                var params = {
                    dataTypeName: dataTypeName,
                    dataShortName: dataShortName,
                    floor: floor,
                    ceil: ceil
                };
                var messagePrefix;
                var urlSuffix;
                if (this.isAdd) {
                    messagePrefix = '新增';
                    urlSuffix = 'add';
                } else {
                    messagePrefix = '修改';
                    urlSuffix = 'modify';
                    params.id = type.vm.modifyId;
                }
                bootbox.confirm({
                    title: '提示',
                    message: '确认' + messagePrefix + '数据类型信息',
                    callback: function (flag) {
                        if (flag) {
                            type.sendRequest('sys/data/type/' + urlSuffix, 'POST', params, function (res) {
                                var message = messagePrefix + '数据类型信息失败！';
                                if (res.success) {
                                    message = messagePrefix + '数据类型信息成功！';
                                }
                                bootbox.alert({
                                    title: '提示',
                                    message: message
                                });
                                $('#reset-btn').trigger('click');
                            });
                        }
                    }
                });
            }
        },
        computed: {
            typeModalTitle: function () {
                var title;
                if (this.isAdd) {
                    title = '新增数据类型信息'
                } else {
                    title = '修改数据类型信息'
                }
                return title;
            }
        }
    })
}