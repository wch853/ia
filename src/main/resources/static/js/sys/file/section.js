var section = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#sys-man"]').trigger('click');
        $('[data-target="#file-man"]').trigger('click').addClass('side-active');

        $("#section-table").bootstrapTable({
            url: 'sys/file/section/data',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    id: $('#section-id').val().trim()
                }
            },
            columns: [{
                field: 'id',
                title: '区块编号'
            }, {
                field: 'sectionName',
                title: '区块名称'
            }, {
                field: 'blockName',
                title: '地块名称'
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
                        section.modifySection(value, row, index);
                    },
                    'click .rmv': function (e, value, row, index) {
                        section.removeSection(value, row, index);
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
            $('#section-id').val('');
            $('#query-btn').trigger('click');
        });

        // 查询
        $('#query-btn').click(function () {
            $("#section-table").bootstrapTable('selectPage', 1);
        });
    },
    clearModalInput: function () {
        $('#edit-section-name').val('');
        $('#edit-block').selectpicker('val', $('#edit-block').find('option:first').val());
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
     * 修改区块信息
     * @param value
     * @param row
     * @param index
     */
    modifySection: function (value, row, index) {
        section.vm.isAdd = false;
        section.vm.modifyId = row.id;
        $('#edit-section-name').val(row.sectionName);
        $('#edit-block').selectpicker('val', row.blockId);
        $('#section-modal').modal('show');
    },
    /**
     * 删除区块信息
     * @param value
     * @param row
     * @param index
     */
    removeSection: function (value, row, index) {
        bootbox.confirm({
            title: '提示',
            message: '确认删除区块信息',
            callback: function (flag) {
                if (flag) {
                    section.sendRequest('sys/file/section/remove', 'post', {id: row.id}, function (res) {
                        var message = '删除区块信息失败！';
                        if (res.success) {
                            message = '删除区块信息成功！';
                        }
                        bootbox.alert({
                            title: '提示',
                            message: message
                        });
                        $('#reset-btn').trigger('click');
                    });
                }
            }
        })
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            isAdd: true,
            blocks: [],
            modifyId: null
        },
        mounted: function () {
            var that = this;
            this.$nextTick(function () {
                section.init();
                section.sendRequest('sys/file/block/list', 'get', null, function (res) {
                    if (res.success) {
                        that.blocks = res.data;
                    }
                });
            });
        },
        methods: {
            /**
             * 点击新增按钮操作
             */
            addSection: function () {
                this.isAdd = true;
                $('#section-modal').modal('show');
                section.clearModalInput();
            },
            /**
             * 保存操作
             */
            saveAdd: function () {
                $('#section-modal').modal('hide');
                var name = $('#edit-section-name').val().trim();
                var blockId = $('#edit-block').selectpicker('val');
                if ('' == name || blockId == 0) {
                    bootbox.alert({
                        title: '提示',
                        message: '请输入完整信息！'
                    });
                    return;
                } else {
                    var params = {
                        sectionName: name,
                        blockId: blockId
                    };
                    var messagePrefix;
                    var urlSuffix;
                    if (section.vm.isAdd) {
                        messagePrefix = '新增';
                        urlSuffix = 'add';
                    } else {
                        messagePrefix = '修改';
                        urlSuffix = 'modify';
                        params.id = section.vm.modifyId;
                    }
                    bootbox.confirm({
                        title: '提示',
                        message: '确认' + messagePrefix + '区块信息',
                        callback: function (flag) {
                            if (flag) {
                                section.sendRequest('sys/file/section/' + urlSuffix, 'post', params, function (res) {
                                    var message = messagePrefix + '区块信息成功！';
                                    if (!res.success) {
                                        message = messagePrefix + '区块信息失败！';
                                    }
                                    bootbox.alert({
                                        title: '提示',
                                        message: message
                                    });
                                    $('#reset-btn').trigger('click');
                                });
                            }
                        }
                    })
                }
            }
        },
        computed: {
            sectionModalTitle: function () {
                var title;
                if (this.isAdd) {
                    title = '新增区块信息';
                } else {
                    title = '修改区块信息';
                }
                return title;
            }
        }
    })
};