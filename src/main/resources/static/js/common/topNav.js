/**
 * 弹出提示
 */
function popTip(count) {
    $('#tip-li').popover('destroy').popover({
        title: '提示',
        placement: 'bottom',
        html: true,
        content: '您有<span class="status-data-warn">' + count + '</span>条待办事项'
    }).popover('show');

    // TODO 关闭popover
}

/**
 * 获取系统提示
 */
function checkTip() {
    $.ajax({
        url: 'sys/warn/getUnhandleRecordCount',
        success: function (res) {
            var count = res.data;
            if (count > 0) {
                $('#tip-view-point').addClass('tip-view-point');
                $('#warn-count').text(count);
                popTip(count);
            }
        }
    });
}

checkTip();

var websocket;

/**
 * 建立WebSocket连接
 */
function getConnect() {
    var path = window.location.hostname + ":8000/" + window.location.pathname.split("/")[1];
    if (window.WebSocket) {
        console.log('Support WebSocket.');
        websocket = new WebSocket('ws://' + path + '/tipHandler');
    } else {
        console.log('Not Support WebSocket! It\'s recommended to use chrome!');
        bootbox.alert({
            title: '提示',
            message: '您的浏览器不支持WebSocket，请切换到chrome获取最佳体验！'
        });
        websocket = new SockJS('http://' + path + '/sockjs-tipHandler')
    }

    // 配置WebSocket连接生命周期
    websocket.onopen = function () {
        console.log('WebSocket open!');
    };

    websocket.onmessage = function (event) {
        // TODO onmessage
    };

    websocket.onerror = function () {
        console.log('WebSocket error!');
        bootbox.alert({
            title: '提示',
            message: 'WebSocket连接异常，请刷新页面！',
            callback: function () {
                window.location.reload();
            }
        });
    };

    websocket.onclose = function () {
        console.log('WebSocket close!');
        bootbox.alert({
            title: '提示',
            message: 'WebSocket连接断开，请刷新页面！',
            callback: function () {
                window.location.reload();
            }
        });
    };

    window.onbeforeunload = function () {
        websocket.close();
    };
}

getConnect();