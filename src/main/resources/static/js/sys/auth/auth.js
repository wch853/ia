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

        // 新增弹窗
        $('#addBtn').click(function () {
            $('.modal :text').val('');
            if ($('#user-man').hasClass('active')) {
                $('#user-add-modal .selectpicker').each(function () {
                    var val = $(this).find('option:first').val();
                    $(this).selectpicker('val', val);
                });
                $('#user-add-modal').modal('show');
            } else if ($('#role-man').hasClass('active')) {
                $('#role-add-modal').modal('show');
            }
        });

        $('.selectpicker').selectpicker({
            width: '180.67px'
        });

        // 保存新增用户
        $('#save-add-user').click(function () {
            auth.addUser();
        });

        // 保存用户角色
        $('#save-user-role').click(function () {
            auth.saveUserRoles($(this).attr('user'));
        });

        // 保存角色
        $('#save-add-role').click(function () {
            auth.saveRole();
        });

        // 保存角色权限
        $('#save-role-permission').click(function () {
            auth.saveRolePermissions($(this).attr('role'));
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
                } else if (status === 0) {
                    fmt = '无效';
                }
                return fmt;
            },
            title: '账号状态'
        }, {
            formatter: function (value, row, index) {
                return [
                    '<a type="button" class="btn btn-operate" href="javascript:auth.getUserRoles(' + row.id + ')">' +
                    '<i class="fa fa-pencil"></i> 编辑角色' +
                    '</a>',
                    '<a type="button" class="btn btn-operate" href="javascript:auth.modifyUserStatus(' + "'" + row.id + "', '" +
                    row.status + "'" + ')">' + auth.convertStatus(row.status) + '</a>',
                    '<a type="button" class="btn btn-operate" href="javascript:auth.removeUser(' + "'" + row.id + "'" + ')">' +
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
                    '<a type="button" class="btn btn-operate" href="javascript:auth.getRolePermissions(' + row.id + ')">' +
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
     * ajax通用响应
     * @param res
     */
    ajaxResponse: function (res) {
        var message;
        if (res.code === 200) {
            message = "操作成功！";
        } else if (res.code === 300 && res.message) {
            message = res.message;
        } else {
            message = "操作失败！";
        }
        bootbox.alert({
            title: '提示',
            message: message
        });
        $("#auth-table").bootstrapTable('selectPage', 1);
    },
    /**
     * 新增用户
     */
    addUser: function () {
        var username = $('#add-username').val().trim();
        var name = $('#add-name').val().trim();
        var status = $('#add-status').val();

        $('#user-add-modal').modal('hide');

        var regex = /^[a-zA-Z0-9]{6,16}$/;
        if (!regex.test(username)) {
            bootbox.alert({
                title: '提示',
                message: '用户账户应为6~16位字母数字组合！'
            });
        } else if (name.length === 0) {
            bootbox.alert({
                title: '提示',
                message: '用户名称不可为空！'
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认新增用户',
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
                                auth.ajaxResponse(res);
                            }
                        });
                    }
                }
            });
        }
    },
    /**
     * 点击编辑角色
     * @param id
     */
    getUserRoles: function (id) {
        if (id === 1) {
            bootbox.alert({
                title: '提示',
                message: 'root账号不可编辑角色！'
            });
            return;
        }
        $('#save-user-role').attr('user', id);
        $.ajax({
            url: 'sys/auth/user/role',
            data: {
                userId: id
            },
            success: function (res) {
                /** @namespace res.data.allRoles */
                var allRoles = res.data.allRoles;
                /** @namespace res.data.hasRoles */
                var hasRoles = res.data.hasRoles;
                var hasRoleIds = [];
                var options = [];
                $.each(allRoles, function (i, el) {
                    /** @namespace el.roleName */
                    options += '<option value="' + el.id + '">' + el.roleName + '</option>'
                });
                $.each(hasRoles, function (i, el) {
                    hasRoleIds.push(el.id.toString());
                });
                $('#role-select').empty().html(options).multiSelect({
                    selectableHeader: '<div class="select-header">所有角色</div>',
                    selectionHeader: '<div class="select-header">已有角色</div>'
                }).multiSelect('deselect_all').multiSelect('select', hasRoleIds).multiSelect('refresh');
            }
        });
        $('#role-modal').modal('show');
    },
    /**
     * 保存用户角色
     * @param id
     */
    saveUserRoles: function (id) {
        $('#role-modal').modal('hide');
        if (id === '1') {
            bootbox.alert({
                title: '提示',
                message: 'root账号不可编辑角色！'
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认分配角色',
                callback: function (flag) {
                    if (flag) {
                        var roleIds = $('#role-select').val();
                        $.ajax({
                            url: 'sys/auth/user/role/add',
                            type: 'post',
                            data: {
                                userId: id,
                                roleIds: roleIds
                            },
                            success: function (res) {
                                auth.ajaxResponse(res);
                            }
                        });
                    }
                }
            });
        }
    },
    /**
     * 修改用户账号使用状态
     * @param id
     * @param status
     */
    modifyUserStatus: function (id, status) {
        if (id === '1') {
            bootbox.alert({
                title: '提示',
                message: 'root账号不可修改使用状态！'
            });
            return;
        }
        var newStatus = status === '1' ? '禁用' : '启用';
        bootbox.confirm({
            title: '提示',
            message: '确认将用户账户状态修改为<span class="status-data">' + newStatus + '</span>',
            callback: function (flag) {
                if (flag) {
                    $.ajax({
                        url: 'sys/auth/user/modify',
                        type: 'post',
                        data: {
                            id: id,
                            status: status === '1' ? 0 : 1
                        },
                        success: function (res) {
                            auth.ajaxResponse(res);
                        }
                    })
                }
            }
        });
    },
    /**
     * 删除用户
     * @param id
     */
    removeUser: function (id) {
        if (id === '1') {
            bootbox.alert({
                title: '提示',
                message: 'root账号不可删除！'
            });
            return;
        }
        bootbox.confirm({
            title: '提示',
            message: '确认删除该账号',
            callback: function (flag) {
                if (flag) {
                    $.ajax({
                        url: 'sys/auth/user/remove',
                        type: 'post',
                        data: {
                            userId: id
                        },
                        success: function (res) {
                            auth.ajaxResponse(res);
                        }
                    })
                }
            }
        });
    },
    /**
     * 新增角色
     */
    saveRole: function () {
        $('#role-add-modal').modal('hide');
        var roleName = $('#add-role-name').val().trim();
        if (roleName.length === 0) {
            bootbox.alert({
                title: '提示',
                message: '角色名称不可为空！'
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认添加该角色',
                callback: function (flag) {
                    if (flag) {
                        $.ajax({
                            url: 'sys/auth/role/add',
                            type: 'post',
                            data: {
                                roleName: roleName
                            },
                            success: function (res) {
                                auth.ajaxResponse(res);
                            }
                        })
                    }
                }
            });
        }
    },
    /**
     * 点击编辑权限
     * @param id
     */
    getRolePermissions: function (id) {
        if (id === 1) {
            bootbox.alert({
                title: '提示',
                message: 'root账号不可编辑权限！'
            });
            return;
        }
        $('#save-role-permission').attr('role', id);
        $.ajax({
            url: 'sys/auth/role/permission',
            data: {
                roleId: id
            },
            success: function (res) {
                /** @namespace res.data.allPermissions */
                var allPermissions = res.data.allPermissions;
                /** @namespace res.data.hasPermissions */
                var hasPermissions = res.data.hasPermissions;
                var hasPermissionIds = [];
                var options = [];
                $.each(allPermissions, function (i, el) {
                    /** @namespace el.urlName */
                    options += '<option value="' + el.id + '">' + el.urlName + '</option>'
                });
                $.each(hasPermissions, function (i, el) {
                    hasPermissionIds.push(el.id.toString());
                });
                $('#permission-select').empty().html(options).multiSelect({
                    selectableHeader: '<div class="select-header">所有权限</div>',
                    selectionHeader: '<div class="select-header">已有权限</div>'
                }).multiSelect('deselect_all').multiSelect('select', hasPermissionIds).multiSelect('refresh');
            }
        });
        $('#permission-modal').modal('show');
    },
    /**
     * 保存角色权限
     */
    saveRolePermissions: function (id) {
        var roleId = $('#save-role-permission').attr('role');
        $('#permission-modal').modal('hide');
        if (id === '1') {
            bootbox.alert({
                title: '提示',
                message: 'root账号不可编辑权限！'
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认分配权限',
                callback: function (flag) {
                    if (flag) {
                        var permissionIds = $('#permission-select').val();
                        $.ajax({
                            url: 'sys/auth/role/permission/add',
                            type: 'post',
                            data: {
                                roleId: id,
                                permissionIds: permissionIds
                            },
                            success: function (res) {
                                auth.ajaxResponse(res);
                            }
                        });
                    }
                }
            });
        }
    },
    /**
     * 删除角色
     * @param id
     */
    removeRole: function (id) {
        if (id === '1') {
            bootbox.alert({
                title: '提示',
                message: '超级管理员角色不可删除！'
            });
        }
        bootbox.confirm({
            title: '提示',
            message: '确认删除该角色',
            callback: function (flag) {
                if (flag) {
                    $.ajax({
                        url: 'sys/auth/role/remove',
                        type: 'post',
                        data: {
                            roleId: id
                        },
                        success: function (res) {
                            auth.ajaxResponse(res);
                        }
                    })
                }
            }
        });
    }
};
/**
 * 启动
 */
auth.init();