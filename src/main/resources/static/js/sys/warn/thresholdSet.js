// 激活侧边栏
$('[data-target="#warn-man"]').trigger('click').parent().find('li:eq(0) a').addClass('side-active');

$("#thresholdTable").bootstrapTable({
    url: 'sys/warn/getWarnThreshold',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            thresholdType: $('#queryThresholdType').val()
        }
    },
    columns: [{
        field: 'id',
        title: '阈值编号'
    }, {
        formatter: function (value, row, index) {
            /** @namespace row.thresholdType */
            return convertThresholdType(row.thresholdType);
        },
        title: '阈值类型'
    }, {
        field: 'floor',
        title: '阈值下限'
    }, {
        field: 'ceil',
        title: '阈值上限'
    }, {
        formatter: function (value, row, index) {
            var useStatus = row.useStatus;
            var format = '';
            if (useStatus === '1') {
                format = '使用中';
            } else if (useStatus === '0') {
                format = '未使用';
            }
            return format;
        },
        title: '使用状态'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyWarnThreshold(' + "'" + row.id + "', '"
                + row.thresholdType + "', '" + row.floor + "', '" + row.ceil + "', '" + row.useStatus + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>'
            ].join('');
        },
        title: '操作'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25, 50]
});

/**
 * 转换阈值类型
 */
function convertThresholdType(thresholdType) {
    var type = '';
    switch (thresholdType) {
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
    $('#queryToolBar .selectpicker').selectpicker('val', '');
    $('#queryBtn').trigger('click');
});

// 查询
$('#queryBtn').click(function () {
    $("#thresholdTable").bootstrapTable('selectPage', 1);
});

$('.selectpicker').selectpicker({
    width: '180.67px'
});

var $range = $('#threshold-range');

function modifyWarnThreshold(id, thresholdType, floor, ceil, useStatus) {
    $('#modifyThresholdId').text(id);
    $('#modifyThresholdType').text(convertThresholdType(thresholdType));
    $('#modifyUseStatus').selectpicker('val', useStatus);

    $range.ionRangeSlider({
        min: 0,
        max: 100,
        type: 'double',
        from: floor,
        to: ceil,
        step: 0.01,
        grid: true,
        grid_num: 10,
        drag_interval: true
    });

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var id = $('#modifyThresholdId').text();
    var floor = $range.data('from');
    var ceil = $range.data('to');
    var useStatus = $('#modifyUseStatus').val();

    $('#modifyModal').modal('hide');

    bootbox.confirm({
        title: '提示',
        message: '确认修改阈值信息',
        callback: function (flag) {
            if (flag) {
                deliverData('sys/warn/modifyWarnThreshold', id, floor, ceil, useStatus);
            }
        }
    });
});

function deliverData(path, id, floor, ceil, useStatus) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            id: id,
            floor: floor,
            ceil: ceil,
            useStatus: useStatus
        },
        success: function (res) {
            bootbox.alert({
                title: '提示',
                message: res.message
            });
            $("#thresholdTable").bootstrapTable('selectPage', 1);
        }
    });
}
