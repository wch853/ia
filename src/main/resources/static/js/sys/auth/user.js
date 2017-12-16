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
    sendRequest: function (name, password) {
        $.ajax({
            url: 'sys/auth/user/modify',
            type: 'post',
            data: {
                name: name,
                password: password
            },
            success: function (res) {
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
                window.location.reload();
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
                        user.sendRequest(name, null);
                    }
                }
            });
        }
    },
    /**
     * 修改密码
     */
    modifyPassword: function () {
        var password = $('#password').val().trim();
        var rePassword = $('#re-password').val().trim();
        if (password.length === 0 || rePassword.length === 0) {
            bootbox.alert({
                title: '提示',
                message: '密码不可为空！'
            });
        } else if (password !== rePassword) {
            bootbox.alert({
                title: '提示',
                message: '两次输入的密码不一致！'
            });
        } else if (password.length < 6 || password.length > 16) {
            bootbox.alert({
                title: '提示',
                message: '请输入6~16位密码'
            });
        } else {
            bootbox.confirm({
                title: '提示',
                message: '确认修改密码',
                callback: function (flag) {
                    if (flag) {
                        user.sendRequest(null, password);
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