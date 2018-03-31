var field = {
    init: function () {
        // 激活侧边栏
        $('[data-target="#sys-man"]').trigger('click');
        $('[data-target="#file-man"]').trigger('click').addClass('side-active');

        $("#field-table").bootstrapTable({
            url: 'sys/file//field/data',
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    fieldName: $('#field-name').val().trim(),
                    sectionId: $('#section-id').val(),
                    cropId: $('#crop-id').val()
                }
            },
            columns: [{
                field: 'id',
                title: '大棚编号'
            }, {
                field: 'fieldName',
                title: '大棚名称'
            }, {
                field: 'sectionId',
                title: '所属区块编号'
            }, {
                field: 'cropName',
                title: '种植作物名称'
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
                        field.modifyField(row.id, row.fieldName, row.sectionId, row.cropId);
                    },
                    'click .rmv': function (e, value, row, index) {
                        field.removeField(row.id);
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
            $("#field-table").bootstrapTable('selectPage', 1);
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
        });
    },
    modifyField: function (id, fieldName, sectionId, cropId) {
        field.vm.isAdd = false;
        field.vm.modifyId = id;
        $('#edit-field-name').val(fieldName);
        $('#edit-section-id').selectpicker('val', sectionId);
        $('#edit-crop-id').selectpicker('val', cropId);
        $('#field-modal').modal('show');
    },
    clearModalInput: function () {
        $('#field-modal :text').val('');
        $('#field-modal .selectpicker').selectpicker('val', '');
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            sections: [],
            crops: [],
            isAdd: null,
            modifyId: null
        },
        mounted: function () {
            this.$nextTick(function () {
                field.init();
                field.sendRequest('sys/file/section/list', 'get', null, this.fillSectionList);
                field.sendRequest('sys/file/crop/list', 'get', null, this.fillCropList);
            });
        },
        methods: {
            fillSectionList: function (res) {
                this.sections = res.data;
            },
            fillCropList: function (res) {
                this.crops = res.data;
            },
            addField: function () {
                field.clearModalInput();
                $('#field-modal').modal('show');
            },
            saveAdd: function () {
                field.vm.isAdd = true;
                var fieldName = $('#edit-field-name').val().trim();
                var sectionId = $('#edit-section-id').selectpicker('val');
                var cropId = $('#edit-crop-id').selectpicker('val');
                if (fieldName == '' || sectionId == 0 || cropId == 0) {
                    bootbox.alert({
                        title: '提示',
                        message: '请输入完整信息！'
                    });
                    return
                }
                var params = {
                    fieldName: trim,
                    sectionId: sectionId,
                    cropId: cropId
                };
                var messagePrefix;
                var urlSuffix;
                if (this.isAdd) {
                    messagePrefix = '新增';
                    urlSuffix = 'add';
                } else {
                    messagePrefix = '修改';
                    urlSuffix = 'modify';
                    params.id = field.vm.modifyId;
                }
                bootbox.confirm({
                    title: '提示',
                    message: '确认' + messagePrefix + '大棚信息',
                    callback: function (flag) {
                        if (flag) {
                            field.sendRequest('sys/file/field/' + urlSuffix, 'post', params, function (res) {
                                var message = messagePrefix + '大棚信息失败！';
                                if (res.success) {
                                    message = messagePrefix + '大棚信息成功！';
                                }
                                bootbox.alert({
                                    title: '提示',
                                    message: message
                                })
                            });
                        }
                    }
                });
            }
        },
        computed: {
            fieldModalTitle: function () {
                var title;
                if (this.isAdd) {
                    title = '新增大棚信息'
                } else {
                    title = '修改大棚信息'
                }
                return title;
            }
        }
    })
};