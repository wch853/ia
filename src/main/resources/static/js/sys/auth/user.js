var user = {
    init: function () {
        // 默认加载用户名称tab页
        user.loadName();

        // 用户名称tab页
        $('#name-man').click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            user.loadName();
        });

        // 密码管理tab页
        $('#password-man').click(function () {
            $(this).addClass('active').siblings().removeClass('active');
            user.loadPassword();
        });

        $('.reset-btn').click(function () {
            user.clearInput();
        });

        $('#submit-name').click(function () {
            user.modifyName();
        });

        $('#submit-password').click(function () {
            user.modifyPassword();
        });
    },
    /**
     * 输入框清空
     */
    clearInput: function () {
        $('.user-modify-input').val('');
    },
    /**
     * 加载用户名称tab页
     */
    loadName: function () {
        $('#name-modify').show();
        $('#password-modify').hide();
        user.clearInput();
    },
    /**
     * 加载密码管理tab页
     */
    loadPassword: function () {
        $('#name-modify').hide();
        $('#password-modify').show();
        user.clearInput();
    },
    /**
     * 发送请求
     */
    sendRequest: function (name, password, prePassword) {
        $.ajax({
            url: 'sys/auth/user/modify',
            type: 'post',
            data: {
                name: name,
                password: password,
                prePassword: prePassword
            },
            success: function (res) {
                var message = '操作失败！';
                if (res.code === 200) {
                    message = "操作成功！";
                } else if (res.code === 300 && res.message) {
                    message = res.message;
                }
                bootbox.confirm({
                    title: '提示',
                    message: message,
                    callback: function () {
                        window.location.reload();
                    }
                });
            }
        })
    },
    /**
     * 修改用户名称
     */
    modifyName: function () {
        var name = $('#name').val().trim();
        if (name === '') {
            bootbox.alert({
                title: '提示',
                message: '用户名称不可为空'
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认修改用户名称',
                callback: function (flag) {
                    if (flag) {
                        user.sendRequest(name, null, null);
                    }
                }
            });
        }
    },
    /**
     * 修改密码
     */
    modifyPassword: function () {
        var prePassword = $('#pre-password').val().trim();
        var password = $('#password').val().trim();
        var rePassword = $('#re-password').val().trim();

        var alertMessage = '';
        if (prePassword.length === 0 || password.length === 0 || rePassword.length === 0) {
            alertMessage = '密码不可为空！';
        } else if (password !== rePassword) {
            alertMessage = '两次输入的密码不一致！';
        } else if (password.length < 6 || password.length > 16) {
            alertMessage = '请输入6~16位密码';
        }

        if ('' !== alertMessage) {
            bootbox.alert({
                title: '提示',
                message: alertMessage
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认修改密码',
                callback: function (flag) {
                    if (flag) {
                        user.sendRequest(null, password, prePassword);
                    }
                }
            });
        }
    }
};
/**
 * 启动
 */
user.init();