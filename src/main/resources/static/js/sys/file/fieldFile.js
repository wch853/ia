$("#fieldFileTable").bootstrapTable({
    url: 'sys/file/getFields',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            fieldName: $('#queryFieldText').val(),
            blockId: $('#queryBlockText').val(),
            cropId: $('#queryCropText').val()
        }
    },
    columns: [{
        field: 'fieldId',
        title: '大棚编号'
    }, {
        field: 'fieldName',
        title: '大棚名称'
    }, {
        field: 'block.blockName',
        title: '所属地块'
    }, {
        field: 'crop.cropName',
        title: '种植作物'
    }, {
        formatter: function(value, row, index) {
            var useStatus = row.useStatus;
            var format;
            if (useStatus == 1) {
                format = '使用中'
            } else if (useStatus == 0) {
                format = '未使用'
            }
            return format;
        },
        title: '使用状态'
    }, {
        field: 'fieldPs',
        title: '备注'
    }, {
        formatter: function(value, row, index) {
          return '-'
        },
        title: '操作'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25]
});

// 重置
$('#resetBtn').click(function() {
    // redirect
    window.location.href='sys/file/field';
});

// 查询
$('#queryBtn').click(function() {
    $("#fieldFileTable").bootstrapTable('selectPage', 1);
});

// 数据提交
function deliverData(path, fieldId, fieldName, blockId, cropId, useStatus, fieldPs) {
    $.ajax({
       url: path,
       data: {
           fieldId: fieldId,
           fieldName: fieldName,
           blockId: blockId,
           cropId: cropId,
           useStatus: useStatus,
           fieldPs: fieldPs
       },
        success: function(res) {
            bootbox.alert({
                title: '提示',
                message: res.message
            });
            $("#fieldFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function() {
    $('#addModal').modal('show');
});

$('#saveAdd').click(function() {
    var fieldId = $('#addFieldIdText');
    var fieldName = $('#addFieldNameText');
    var blockId = $('#addBlockIdText').val();
    var cropId = $('#addCropIdText').val();
    var useStatus = $('#addUseStatusText').val();
    var fieldPs = $('#addFieldPsText').val();
    // TODO verify null
    bootbox.confirm({
        title: '提示',
        message: '确认新增大棚信息',
        callback: function (flag) {
            if (flag) {
                $('#addModal').modal('hide');
                deliverData('sys/file/addField', fieldId, fieldName, blockId, cropId, useStatus, fieldPs);
            }
        }
    });
});
