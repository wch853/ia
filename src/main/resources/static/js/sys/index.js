var index = {
    init: function () {
        $('.field-card').hover(function () {

        }, function () {
            $(this).popover('destroy');
        });
    },
    appendStatus: function (data, fieldName) {
        var html = '<ul><li>区块编号: ' + '<span class="status-data">' + fieldName + '</span></li>';
        $.each(data, function (i, el) {
            html += '<li>' + el.dataTypeName + '：<span class="status-data">' + el.value + '</span></li>'
        });
        return html + '</ul>';
    },
    /**
     * 发送ajax请求
     * @param url
     * @param method
     * @param params
     * @param callback
     */
    sendRequest: function (url, method, params, callback) {
        $.ajax({
            method: method,
            url: url,
            data: params,
            success: function (res) {
                if (undefined != callback) {
                    callback(res);
                }
            }
        });
    },
    vm: new Vue({
        el: '#wrapper',
        data: {
            blocks: []
        },
        mounted: function () {
            var that = this;
            this.$nextTick(function () {
                index.sendRequest('sys/file/block/section/list', 'GET', null, function (res) {
                    that.blocks = res.data;
                    index.init();
                });
            });
        },
        methods: {
            over: function ($event, sectionId, sectionName) {
                index.sendRequest('sys/data/status', 'GET', {sectionId: sectionId}, function (res) {
                    $($event.target).popover('destroy').popover({
                        title: '<span>区块名称：' + sectionName + '</span>',
                        placement: 'auto',
                        html: true,
                        content: index.appendStatus(res.data, sectionId)
                    }).popover('show');
                });
            },
            out: function () {
                $('.popover').prev().popover('destroy');
            }
        }
    })
};