var crop = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#sys-man"]').trigger('click');
        $('[data-target="#file-man"]').trigger('click').addClass('side-active');

        $("#crop-table").bootstrapTable({
            url: 'sys/file/crop/data',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    cropId: $('#crop-name').val().trim()
                }
            },
            columns: [{
                field: 'id',
                title: '作物编号'
            }, {
                field: 'cropName',
                title: '作物名称'
            }, {
                formatter: function (value, row, index) {
                    return [
                        '<a type="button" class="btn btn-operate mod" href="javascript:void(0);">' +
                        '<i class="fa fa-pencil"></i> 修改' +
                        '</a>',
                        '<a type="button" class="btn btn-operate rmv" href="javascript:void(0);">' +
                        '<i class="fa fa-times"></i> 删除' +
                        '</a>'
                    ].join('');
                },
                events: {
                    'click .mod': function (e, value, row, index) {
                        crop.modifyCrop(row.id, row.cropName);
                    },
                    'click .rmv': function (e, value, row, index) {
                        crop.removeCrop(row.id);
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

        // 查询
        $('#query-btn').click(function () {
            $('#crop-table').bootstrapTable('selectPage', 1);
        });

        // 重置
        $('#reset-btn').click(function () {
            $('#query-tool-bar :text').val('');
            $('#crop-table').bootstrapTable('selectPage', 1);
        });
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
        })
    },
    /**
     * 修改作物信息
     * @param id
     * @param cropName
     */
    modifyCrop(id, cropName) {
        crop.vm.isAdd = false;
        crop.vm.modifyId = id;
        $('#edit-crop-name').val(cropName);
        $('#crop-modal').modal('show');
    },
    /**
     * 删除作物信息
     * @param id
     */
    removeCrop(id) {
        bootbox.confirm({
            title: '提示',
            message: '确认删除作物信息',
            callback: function (flag) {
                if (flag) {
                    crop.sendRequest('sys/file/crop/remove', 'POST', {id: id}, function (res) {
                        var message = '删除作物信息失败！';
                        if (res.message) {
                            message = '删除作物信息成功！';
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
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            isAdd: null,
            modifyId: null
        },
        mounted: function () {
            this.$nextTick(function () {
                crop.init();
            });
        },
        methods: {
            /**
             * 新增作物信息
             */
            addCrop: function () {
                this.isAdd = true;
                $('#crop-modal').modal('show');
            },
            /**
             * 保存操作信息
             */
            saveAdd: function () {
                $('#crop-modal').modal('hide');
                var cropName = $('#edit-crop-name').val();
                if (cropName == '') {
                    bootbox.alert({
                        title: '提示',
                        message: '请输入完整信息！'
                    });
                    return;
                }
                var params = {
                    cropName: cropName
                };
                var messagePrefix;
                var urlSuffix;
                if (crop.vm.isAdd) {
                    messagePrefix = '新增';
                    urlSuffix = 'add';
                } else {
                    params.id = this.modifyId;
                    messagePrefix = '修改';
                    urlSuffix = 'modify';
                }
                bootbox.confirm({
                    title: '提示',
                    message: '确认' + messagePrefix + '作物信息',
                    callback: function (flag) {
                        if (flag) {
                            crop.sendRequest('sys/file/crop/' + urlSuffix, 'POST', params, function (res) {
                                var message = messagePrefix + '作物信息失败！';
                                if (res.success) {
                                    message = messagePrefix + '作物信息成功！';
                                }
                                bootbox.alert({
                                    title: '提示',
                                    message: message
                                });
                            });
                            $('#reset-btn').trigger('click');
                        }
                    }
                });
            }
        },
        computed: {
            cropModalTitle: function () {
                var title;
                if (this.isAdd) {
                    title = '新增作物信息';
                } else {
                    title = '修改作物信息';
                }
                return title;
            }
        }
    })
};
