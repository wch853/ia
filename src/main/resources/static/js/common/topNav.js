/**
 * 弹出提示
 */
function popTip(count) {
    var $popTip = $('.pop-tip');
    $popTip.find('span').text(count);
    $popTip.fadeIn(500);
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

$('.pop-tip').click(function () {
    $(this).hide();
});

var websocket;

/**
 * 建立WebSocket连接
 */
function getConnect() {
    var path = window.location.hostname + ":8000/wa";
    if (window.WebSocket) {
        websocket = new WebSocket('ws://' + path + '/tipHandler');
    } else {
        console.log('Not Support WebSocket! It\'s recommended to use chrome!');
        bootbox.alert({
            title: '提示',
            message: '您的浏览器不支持WebSocket，建议切换到谷歌浏览器获取最佳体验！'
        });
        websocket = new SockJS('http://' + path + '/sockjs-tipHandler')
    }

    // 配置WebSocket连接生命周期
    websocket.onopen = function () {
        console.log('WebSocket连接成功！');
    };

    websocket.onmessage = function (event) {
        var result = JSON.parse(event.data);
        var code = result.code;
        var count = result.data;

        if (code === 202) {
            $('#tip-view-point').addClass('tip-view-point');
            $('#warn-count').text(count);
            popTip(count);
        }
    };

    websocket.onerror = function () {
        console.log('WebSocket连接异常！');
    };

    websocket.onclose = function () {
        console.log('WebSocket连接断开，请刷新页面！');
    };

    window.onbeforeunload = function () {
        websocket.close();
    };
}

getConnect();