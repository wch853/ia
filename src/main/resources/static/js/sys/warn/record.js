// 激活侧边栏
$('[data-target="#warn-man"]').trigger('click').parent().find('li:eq(1) a').addClass('side-active');

var startTime = '';
var endTime = '';
$('#queryDate').daterangepicker({
    // singleDatePicker: true,
    locale: {
        applyLabel: '确认',
        cancelLabel: '取消',
        format: 'YYYY-MM-DD',
        customRangeLabel : '自定义',
        separator : ' 至 '
    },
    ranges: {
        '今天': [moment(), moment()],
        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        '过去7天': [moment().subtract(6, 'days'), moment()],
        '过去30天': [moment().subtract(29, 'days'), moment()],
        '本月': [moment().startOf('month'), moment().endOf('month')],
        '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
}, function (start, end) {
    startTime = start.format('YYYY-MM-DD');
    endTime = end.format('YYYY-MM-DD');
}).val('');

$("#recordTable").bootstrapTable({
    url: 'sys/warn/getWarnRecord',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            fieldId: $('#queryFieldId').val(),
            warnType: $('#queryWarnType').val(),
            start: startTime,
            end: endTime,
            flag: $('#queryFlag').val()
        }
    },
    columns: [{
        field: 'id',
        title: '记录编号'
    }, {
        field: 'fieldId',
        title: '大棚编号'
    }, {
        formatter: function (value, row, index) {
            return convertWarnType(row.warnType);
        },
        title: '报警类型'
    }, {
        field: 'warnVal',
        title: '报警值'
    }, {
        field: 'warnTime',
        title: '报警时间'
    }, {
        field: 'handleTime',
        title: '处理时间'
    }, {
        formatter: function (value, row, index) {
            /** @namespace row.flag */
            var flag = row.flag;
            var format = '';
            if (flag === '0') {
                format = '未处理';
            } else if (flag === '1') {
                format = '已处理';
            } else if (flag === '2') {
                format = '已忽略';
            }
            return format;
        },
        title: '处理标志'
    } ],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25, 50]
});

// 设置bootstrap-select大小
$('#queryToolBar .selectpicker').selectpicker({
    width: '180.67px'
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

// 重置
$('#resetBtn').click(function () {
    $('#queryDate').val('');
    $('#queryToolBar .selectpicker').selectpicker('val', '');
    startTime = '';
    endTime = '';
    $('#queryBtn').trigger('click');
});

// 查询
$('#queryBtn').click(function () {
    $("#recordTable").bootstrapTable('selectPage', 1);
});