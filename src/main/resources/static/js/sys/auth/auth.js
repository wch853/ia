var auth = {
    /**
     * 初始化
     */
    init: function () {
        // 激活侧边栏
        $('[data-target="#sys-man"]').trigger('click');
        $('#auth-man').addClass('side-active');

        // 重置
        $('#resetBtn').click(function () {
            $('#query-name').val('');
            $('#queryBtn').trigger('click');
        });

        // 查询
        $('#queryBtn').click(function () {
            $("#auth-table").bootstrapTable('selectPage', 1);
        });

        // 用户管理tab页
        $('#user-man').click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            auth.loadUser();
        });

        // 角色管理tab页
        $('#role-man').click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            auth.loadRole();
        });

        $('#user-man').trigger('click');

        // 新增用户
        $('#addBtn').click(function () {
            $('.modal :text').val('');
            $('#user-add-modal .selectpicker').each(function () {
                var val = $(this).find('option:first').val();
                $(this).selectpicker('val', val);
            });
            $('#user-add-modal').modal('show');
        });

        $('.selectpicker').selectpicker({
            width: '180.67px'
        });

        // 保存新增用户
        $('#save-add-user').click(function () {
            auth.addUser();
        });
    },
    /**
     * 初始化tab
     */
    initTab: function (url, columns, nameType) {
        $('#query-name-label').text(nameType);
        $("#auth-table").bootstrapTable('destroy').bootstrapTable({
            url: url,
            queryParams: function (params) {
                return {
                    offset: params.offset,
                    limit: params.limit,
                    name: $('#query-name').val().trim()
                }
            },
            columns: columns,
            striped: true,
            pagination: true,
            sidePagination: 'server',
            pageSize: 10,
            pageList: [5, 10, 25, 50]
        });
    },
    /**
     * 加载用户列表
     */
    loadUser: function () {
        var columns = [{
            field: 'id',
            title: '用户编号'
        }, {
            field: 'username',
            title: '用户账号'
        }, {
            field: 'name',
            title: '用户名称'
        }, {
            formatter: function (value, row, index) {
                var fmt = '-';
                var status = row.status;
                if (status === 1) {
                    fmt = '有效';
                } else if (status === 2) {
                    fmt = '无效';
                }
                return fmt;
            },
            title: '账号状态'
        }, {
            formatter: function (value, row, index) {
                return [
                    '<a type="button" class="btn btn-operate" href="javascript:auth.modifyRole(' + row.id + ')">' +
                    '<i class="fa fa-pencil"></i> 编辑角色' +
                    '</a>',
                    '<a type="button" class="btn btn-operate" href="javascript:removeBlock(' + "'" + row.id + "', '" +
                    row.status + "'" + ')">' + auth.convertStatus(row.status) + '</a>',
                    '<a type="button" class="btn btn-operate" href="javascript:auth.removeRole(' + "'" + row.id + "'" + ')">' +
                    '<i class="fa fa-times"></i> 删除' +
                    '</a>'
                ].join('');
            },
            title: '操作'
        }];
        auth.initTab('sys/auth/user', columns, '用户账号：');
    },
    /**
     * 加载角色列表
     */
    loadRole: function () {
        var columns = [{
            field: 'id',
            title: '角色编号'
        }, {
            field: 'roleName',
            title: '角色名称'
        }, {
            formatter: function (value, row, index) {
                return [
                    '<a type="button" class="btn btn-operate" href="javascript:auth.modifyPermission(' + row.id + ')">' +
                    '<i class="fa fa-pencil"></i> 编辑权限' +
                    '</a>',
                    '<a type="button" class="btn btn-operate" href="javascript:auth.removeRole(' + "'" + row.id + "'" + ')">' +
                    '<i class="fa fa-times"></i> 删除' +
                    '</a>'
                ].join('');
            },
            title: '操作'
        }];
        auth.initTab('sys/auth/role', columns, '角色名称：');
    },
    /**
     * 用户状态转换
     * @param status
     * @returns {string}
     */
    convertStatus: function (status) {
        var format = '';
        var prefix = '<i class="fa ';
        var iconType = '';
        var suffix = '"></i> ';
        if (1 === status) {
            iconType = 'fa-minus';
            suffix += "禁用";
        } else if (0 === status) {
            iconType = 'fa-check';
            suffix += "启用";
        } else {
            return format;
        }
        format += prefix + iconType + suffix;
        return format;
    },
    /**
     * 新增用户
     */
    addUser: function () {
        var username = $('#add-username').val().trim();
        var name = $('#add-name').val().trim();
        var status = $('#add-status').val();

        $('#user-add-modal').modal('hide');

        // TODO 校验
        if (username.length < 6) {
            bootbox.alert({
                title: '提示',
                message: '请输入完整信息！'
            });
        } else if (fieldPs.length > 80) {
            bootbox.alert({
                title: '提示',
                message: '大棚备注限输入80个字符！'
            });
        } else {

        bootbox.confirm({
            title: '提示',
            message: '确认修改大棚信息',
            callback: function (flag) {
                if (flag) {
                    $.ajax({
                        url: 'sys/auth/user/add',
                        type: 'post',
                        data: {
                            username: username,
                            name: name,
                            status: status
                        },
                        success: function (res) {
                            var message;
                            if (res.code === 200) {
                                message = "操作成功！";
                            } else {
                                message = "操作失败！";
                            }
                            bootbox.alert({
                                title: '提示',
                                message: message
                            });
                            $("#auth-table").bootstrapTable('selectPage', 1);
                        }
                    });
                }
            }
        });
    },
    modifyRole: function (id) {
        $('#role-select').multiSelect('addOption', {value: 'test', text: 'test', index: 0, nested: 'optgroup_label'});
        $('#role-modal').modal('show');
    }
};

auth.init();