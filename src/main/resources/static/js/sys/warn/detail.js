// 激活侧边栏
$('[data-target="#warn-man"]').trigger('click').parent().find('li:eq(2) a').addClass('side-active');

/**
 * 计算报警距离时间
 */
$('.compute-time').each(function (i, el) {
    var time = $(this).attr('time');

    $(this).text(res);
});

/**
 * 转换报警类型
 */
function convertWarnType(warnType) {
    var type = '';
    switch (warnType) {
        case '1':
            type = '温度';
            break;
        case '2':
            type = "湿度";
            break;
        case '3':
            type = '土壤温度';
            break;
        case '4':
            type = '土壤湿度';
            break;
        case '5':
            type = '光照度';
            break;
        case '6':
            type = 'CO2浓度';
            break;
        case '7':
            type = 'pH（土壤酸碱度）';
            break;
        case '8':
            type = 'N（氮含量）';
            break;
        case '9':
            type = 'P（磷含量）';
            break;
        case '10':
            type = 'K（钾含量）';
            break;
        case '11':
            type = 'Hg（汞含量）';
            break;
        case '12':
            type = 'Pb（铅含量）';
            break;
        default:
            type = '';
    }

    return type;
}

/**
 * 正选、反选操作
 */
function verifyCheck() {
    var flag = false;
    $('.timeline-check').each(function () {
        if ($(this).is(':checked')) {
            flag = true;
            return;
        }
    });

    $('#btn-operate').prop('disabled', !flag);
}

$('#check-all').click(function () {
    $('.timeline-check').prop('checked', true);

    verifyCheck();
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

    verifyCheck();
});

/**
 * 时间轴
 */
initTimeline();

function initTimeline() {
    $('.timeline').empty();

    $.ajax({
        url: 'sys/warn/getUnHandleRecord',
        success: function (res) {
            var list = res.data;

            if (null === list || 0 === list.length) {
                var html = '<div class="alert alert-warning col-md-8"><a href="#" class="close" data-dismiss="alert">' +
                    '</a><strong>无待处理报警记录！</strong></div>';
                $('.timeline').append(html);
            } else {
                fillTimeline(list);

                $('.timeline-check').change(function () {
                    verifyCheck();
                });
            }
        }
    });
}

function fillTimeline(list) {
    $.each(list, function (i, el) {
        var id = el.id;
        var fieldId = el.fieldId;
        var warnType = convertWarnType(el.warnType);
        /** @namespace el.warnVal */
        var val = el.warnVal;
        /** @namespace el.warnTime */
        var dateTime = el.warnTime;
        var date = dateTime.split(' ')[0];
        var time = dateTime.split(' ')[1];
        /** @namespace el.warnThreshold */
        var floor = el.warnThreshold.floor;
        var ceil = el.warnThreshold.ceil;
        var analysis = analysisVal(val, floor, ceil);

        var html = '<div class="timeline-item"><div class="row"><div class="col-xs-3 timeline-date"><label>' +
            '<input type="checkbox" class="timeline-check" value="' + id + '">' + '<i class="fa fa-bell-o"></i><div>' +
            date + '</div>' + '<div>' + time + '</div><small class="text-muted">' +
            moment(dateTime, 'YYYY-MM-DD HH:mm:ss').fromNow() + '</small></label></div><div class="col-xs-7 timeline-content">' +
            '<p>报警记录编号：<span class="status-data">' + id + '</span></p><p>大棚编号：<span class="status-data">' +
            fieldId + '</span></p><p>类型：<span class="warn-type status-data">' + warnType + '</span></p>' +
            '<p>异常值：<span class="status-data-warn">' + val + '</span>' + ' ( 阈值范围：' + '<span class="status-data">' +
            floor + '</span> ~ <span class="status-data">' + ceil + '</span> )' + '</p>' +
            '<p>报警分析：<span class="status-data">' + analysis + '</span></p ></div></div></div>';

        $('.timeline').append(html);
    });
}

function analysisVal(val, floor, ceil) {
    var analysis = '';
    if (val < floor) {
        analysis = '低于阈值范围';
    } else if (val > ceil) {
        analysis = '高于阈值范围';
    } else {
        analysis = '无效报警记录';
    }
    return analysis;
}

function handleWarn(flag) {
    var type = '';
    if (1 === flag) {
        type = '已处理';
    } else if (2 === flag) {
        type = '已忽略';
    }

    bootbox.confirm({
        title: '提示',
        message: '确认将所选报警记录修改为<span class="status-data">' + type + '</span>',
        callback: function (res) {
            if (res) {
                var ids = [];
                $('.timeline-check:checked').each(function (i, el) {
                    ids.push(el.value);
                });
                deliverData(ids, flag);
            }
        }
    });
}

function deliverData(ids, flag) {
    $.ajax({
        url: 'sys/warn/modifyWarnRecord',
        type: 'post',
        data: {
            ids: ids,
            flag: flag
        },
        success: function (res) {
            initTimeline();
            bootbox.alert({
               title: '提示',
               message: res.message
            });
        }
    });
}