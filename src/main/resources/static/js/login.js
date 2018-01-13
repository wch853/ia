var login = {
    init: function () {
        login.getUser();
        login.ajaxLogin();
        $('#error-message').hide();

        var height = $(window).height();
        console.log(height);
        if (height < 600) {
            $('.content').css({
                'position': 'relative',
                'min-height': '600px'
            })
        }
    },
    /**
     * 查询用户事件
     */
    getUser: function () {
        $.ajax({
            url: 'sys/user/info',
            success: function (res) {
                if (res.code === 201) {
                    window.location.href = 'sys';
                }
            }
        });
    },
    /**
     * 触发登录事件
     */
    ajaxLogin: function () {
        $('#submit').click(function () {
            $.ajax({
                url: 'login',
                type: 'POST',
                data: {
                    username: $('#username').val().trim(),
                    password: $('#password').val().trim()
                },
                success: function (res) {
                    if (res.code === 200) {
                        window.location.href = 'sys'
                    } else {
                        var message = '登录失败！';
                        if (res.message) {
                            message = res.message;
                        }
                        login.popErrorMessage(message);
                    }
                }
            });
        });
    },
    /**
     * 登录失败弹出提示
     */
    popErrorMessage: function (message) {
        $('#error-message')
            .text(message)
            .fadeIn(1000)
            .animate({top: '-135px'})
            .delay(2400)
            .fadeOut(500)
            .css({top: '135px'});
    }
};
login.init();