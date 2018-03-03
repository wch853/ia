// 激活侧边栏
$('[data-target="#data-man"]').trigger('click').parent().find('li:eq(0) a').addClass('side-active');

$('#queryDate').daterangepicker({
    locale: {
        applyLabel: '确认',
        cancelLabel: '取消',
        format: 'YYYY-MM-DD',
        customRangeLabel: '自定义',
        separator: ' 至 '
    },
    ranges: {
        '今天': [moment(), moment()],
        '昨天': [moment().subtract(1, 'days'), moment().subtract(1, 'days')],
        '过去7天': [moment().subtract(6, 'days'), moment()],
        '过去30天': [moment().subtract(29, 'days'), moment()],
        '本月': [moment().startOf('month'), moment().endOf('month')],
        '上月': [moment().subtract(1, 'month').startOf('month'), moment().subtract(1, 'month').endOf('month')]
    },
}).val('');

$("#recordTable").bootstrapTable({
    url: 'sys/data/record',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            sensorId: $('#querySensorId').val(),
            fieldId: $('#queryFieldId').val(),
            dataType: $('#queryDataType').val(),
            start: getTime(0),
            end: getTime(1)
        }
    },
    columns: [{
        field: 'id',
        title: '记录编号'
    }, {
        field: 'sensorId',
        title: '大棚编号'
    }, {
        field: 'fieldId',
        title: '大棚编号'
    }, {
        formatter: function (value, row, index) {
            return convertDataType(row.dataType);
        },
        title: '数据类型'
    }, {
        field: 'val',
        title: '数据值'
    }, {
        field: 'recordTime',
        title: '记录时间'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25, 50]
});

/**
 * 获取起止时间
 * @param index
 * @returns String
 */
function getTime(index) {
    var time = $('#queryDate').val().split(' 至 ');
    if (time.length > 1) {
        return time[index].trim();
    } else {
        return '';
    }
}

// 设置bootstrap-select大小
$('#queryToolBar .selectpicker').selectpicker({
    width: '180.67px'
});

/**
 * 转换数据类型
 */
function convertDataType(dataType) {
    var type = '';
    switch (dataType) {
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
            type = null;
    }
    return type;
}

// 重置
$('#resetBtn').click(function () {
    $('#queryDate').val('');
    $('#queryToolBar .selectpicker').selectpicker('val', '');
    $('#queryBtn').trigger('click');
});

// 查询
$('#queryBtn').click(function () {
    $("#recordTable").bootstrapTable('selectPage', 1);
});