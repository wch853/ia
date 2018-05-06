var sysTop = {
    init: function () {
        sysTop.checkTip();
        sysTop.fillUserInfo();

        sysTop.establishWebSocketConnect();
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
    /**
     * 获取系统提示
     */
    checkTip: function () {
        sysTop.sendRequest('sys/alarm/record/unhandle/count', 'GET', null, function (res) {
            if (res.false) {
                return;
            }
            var count = res.data;
            if (count > 0) {
                $('#tip-view-point').addClass('tip-view-point');
                sysTop.popAlarmTip(count);
            }
        });
    },
    popAlarmTip: function (count) {
        $('#warn-count').text(count);
        $.toast({
            text: '当前已收到' + count + '条<a href="sys/alarm/detail">报警记录</a> !',
            heading: '报警提示',
            icon: 'error',
            showHideTransition: 'slide',
            hideAfter: false,
            allowToastClose: true,
            position: 'top-right',
            textAlign: 'left',
            loader: false
        });
    },
    /**
     * 获取当前用户名称
     */
    fillUserInfo: function () {
        sysTop.sendRequest('sys/user/info', 'GET', null, function (res) {
            var name;
            if (res.code == 201) {
                name = res.data;
            } else {
                name = 'wch853'
            }
            $('.user-info-name').empty().text(name);
        });
    },
    /**
     * 建立WebSocket连接
     */
    establishWebSocketConnect: function () {
        var websocket;
        var path = window.location.host + '/ia';
        var protocolSuffix = ('https:' == window.location.protocol ? 's' : '');
        if (window.WebSocket) {
            websocket = new WebSocket('ws' + protocolSuffix + '://' + path + '/tip/handler');
        } else {
            // console.log('Not Support WebSocket! It\'s recommended to use chrome!');
            bootbox.alert({
                title: '提示',
                message: '您的浏览器不支持WebSocket，建议切换到谷歌浏览器获取最佳体验！'
            });
            websocket = new SockJS('http' + protocolSuffix + '://' + path + '/sockjs/tip/handler')
        }

        // 配置WebSocket连接生命周期
        websocket.onopen = function () {
            console.log('WebSocket连接成功！');
        };

        websocket.onmessage = function (event) {
            var result = JSON.parse(event.data);
            sysTop.popTip(result)

            var code = result.code;
            if (code == 202) {
                var count = result.data;
                $('#tip-view-point').addClass('tip-view-point');
                // 弹出报警提示
                sysTop.popAlarmTip(count);
            }
        };

        websocket.onerror = function () {
            // console.log('WebSocket连接异常！');
        };

        websocket.onclose = function () {
            // console.log('WebSocket连接断开，请刷新页面！');
        };

        window.onbeforeunload = function () {
            websocket.close();
        };
    },
    vm: new Vue({
        el: '#sys-top',
        data: {},
        mounted: function () {
            var that = this;
            this.$nextTick(function () {
                sysTop.init();
            });
        },
        methods: {
            showExampleTip: function () {
                $.toast({
                    text: "终端NO.1727已上线！",
                    heading: '上线提示',
                    icon: 'success',
                    showHideTransition: 'fade',
                    allowToastClose: true,
                    hideAfter: 20000,
                    position: 'top-right',
                    textAlign: 'left',
                    loader: true,
                    loaderBg: '#ff8000',
                });

                $.toast({
                    text: "终端NO.309已下线！",
                    heading: '下线警告',
                    icon: 'warning',
                    showHideTransition: 'fade',
                    allowToastClose: true,
                    hideAfter: 10000,
                    stack: 5,
                    position: 'top-right',
                    textAlign: 'left',
                    loader: true,
                    loaderBg: '#8080ff',
                });
            }
        }
    })
}