var detail = {
    init: function () {
        /**
         * 激活侧边栏
         */
        $('[data-target="#alarm-man"]').trigger('click').parent().find('li:eq(1) a').addClass('side-active');


        $('#check-all').click(function () {
            $('.timeline-check').prop('checked', true);

            detail.verifyCheck();
        });

        $('#uncheck-all').click(function () {
            $('.timeline-check').each(function () {
                var $check = $(this);
                if ($(this).is(':checked')) {
                    $check.prop('checked', false);
                } else {
                    $check.prop('checked', true);
                }
            });

            detail.verifyCheck();
        });
    },
    verifyCheck: function () {
        var flag = false;
        $('.timeline-check').each(function () {
            if ($(this).is(':checked')) {
                flag = true;
                return false;
            }
        });

        $('#btn-handle').prop('disabled', !flag);
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
            alarmRecords: []
        },
        mounted: function () {
            var that = this;
            this.$nextTick(function () {
                that.timelineInit(that);
            });
        },
        methods: {
            timelineInit: function (that) {
                detail.sendRequest('sys/alarm/record/unhandle', 'GET', null, function (res) {
                    if (res.success) {
                        that.alarmRecords = [];
                        $.each(res.data, function (i, el) {
                            var dateTime = el.alarmTime;
                            var date = dateTime.split(' ')[0];
                            var time = dateTime.split(' ')[1];
                            var timeGap = moment(dateTime, 'YYYY-MM-DD HH:mm:ss').fromNow();
                            el.date = date;
                            el.time = time;
                            el.timeGap = timeGap;
                            el.analysis = that.analysisVal(el.value, el.floor, el.ceil);
                            that.alarmRecords.push(el);
                        });
                    }
                });
                detail.init();
            },
            analysisVal: function (val, floor, ceil) {
                var analysis = '';
                if (val < floor) {
                    analysis = '低于阈值范围';
                } else if (val > ceil) {
                    analysis = '高于阈值范围';
                } else {
                    analysis = '无效报警记录';
                }
                return analysis;
            },
            checkIsChecked: function () {
                detail.verifyCheck();
            },
            markHandle: function (flag) {
                if (flag != 1 && flag != 2) {
                    return;
                }
                var type = (flag == 1 ? '已处理' : '已忽略');
                bootbox.confirm({
                    title: '提示',
                    message: '<p>确认将所选报警记录状态修改为<span class="status-data">' + type + '</span></p>',
                    callback: function (res) {
                        if (res) {
                            var ids = [];
                            $('.timeline-check:checked').each(function (i, el) {
                                ids.push(el.value);
                            });
                            detail.sendRequest('sys/alarm/record/modify', 'POST', {ids: ids, flag: flag}, function (res) {
                                var message = '失败';
                                if (res.success) {
                                    message = '成功';
                                }
                                bootbox.alert({
                                    title: '提示',
                                    message: '修改报警记录状态标志' + message
                                });
                                detail.vm.timelineInit(detail.vm);
                            });
                        }
                    }
                });
            }
        }
    })
};