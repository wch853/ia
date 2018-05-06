var block = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#sys-man"]').trigger('click');
        $('[data-target="#file-man"]').trigger('click').addClass('side-active');

        $("#block-table").bootstrapTable({
            url: 'sys/file/block/data',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    blockName: $('#block-name').val().trim()
                }
            },
            columns: [{
                field: 'id',
                title: '地块编号'
            }, {
                field: 'blockName',
                title: '地块名称'
            }, {
                field: 'blockLocation',
                title: '地块位置'
            }, {
                formatter: function (value, row, index) {
                    return [
                        '<a type="button" class="btn btn-operate mod" href="javascript:void(0);">' +
                        '<i class="fa fa-pencil"></i> 修改' +
                        '</a>',
                        '<a type="button" class="btn btn-operate del" href="javascript:void(0)">' +
                        '<i class="fa fa-times"></i> 删除' +
                        '</a>'
                    ].join('');
                },
                events: {
                    'click .mod': function (e, value, row, index) {
                        block.modifyBlock(row.id, row.blockName, row.blockLocation);
                    },
                    'click .del': function (e, value, row, index) {
                        block.removeBlock(row.id);
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
            $('#block-table').bootstrapTable('selectPage', 1);
        });

        // 重置
        $('#reset-btn').click(function () {
            $('#query-tool-bar :text').val('');
            $('#block-table').bootstrapTable('selectPage', 1);
        });


        // 新增
        $('#add-btn').click(function () {
            $('.modal :text').val('');
            $('#addBlockPs').val('');
            $('#add-modal').modal('show');
        });

        $('#save-add').click(function () {
            var blockName = $('#add-block-name').val().trim();
            var blockLocation = $('#add-block-location').val().trim();

            $('#add-modal').modal('hide');

            if (blockName == '' || blockLocation == '') {
                bootbox.alert({
                    title: '提示',
                    message: '请输入完整信息！'
                });
            } else {
                bootbox.confirm({
                    title: '提示',
                    message: '确认新增地块信息',
                    callback: function (flag) {
                        if (flag) {
                            block.sendRequest('sys/file/block/add', null, blockName, blockLocation);
                        }
                    }
                });
            }
        });

        $('#save-modify').click(function () {
            var blockName = $('#modify-block-name').val().trim();
            var blockLocation = $('#modify-block-loc').val().trim();

            $('#modify-modal').modal('hide');

            if (blockName == '' || blockLocation == '') {
                bootbox.alert({
                    title: '提示',
                    message: '请输入完整信息！'
                });
            } else {
                bootbox.confirm({
                    title: '提示',
                    message: '确认修改地块信息',
                    callback: function (flag) {
                        if (flag) {
                            block.sendRequest('sys/file/block/modify', block.vm.modifyId, blockName, blockLocation);
                        }
                    }
                });
            }
        });
    },
    /**
     * 发送请求
     * @param path
     * @param id
     * @param blockName
     * @param blockLocation
     */
    sendRequest: function (path, id, blockName, blockLocation) {
        $.ajax({
            url: path,
            type: 'POST',
            data: {
                id: id,
                blockName: blockName,
                blockLocation: blockLocation
            },
            success: function (res) {
                var message = '操作失败';
                if (res.success) {
                    message = "操作成功！";
                }
                bootbox.alert({
                    title: '提示',
                    message: message
                });
                $("#block-table").bootstrapTable('selectPage', 1);
            }
        });
    },
    modifyBlock: function (id, blockName, blockLocation) {
        block.vm.modifyId = id;
        $('#modify-block-name').val(blockName);
        $('#modify-block-loc').val(blockLocation);
        $('#modify-modal').modal('show');
    },
    removeBlock: function (id) {
        bootbox.confirm({
            title: '提示',
            message: '确认删除地块',
            callback: function (flag) {
                if (flag) {
                    block.sendRequest('sys/file/block/remove', id);
                }
            }
        });
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            modifyId: null
        },
        mounted: function () {
            this.$nextTick(function () {
                block.init();
            })
        }
    })
};
