// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#terminalFileTable").bootstrapTable({
    url: 'sys/file/getTerminals',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            terminalId: $('#queryVehicleId').val().trim(),
            terminalType: $('#queryVehicleType').val().trim(),
            useStatus: $('#queryUseStatus').val()
        }
    },
    columns: [{
        field: 'terminalId',
        title: '终端编号'
    }, {
        field: 'terminalType',
        title: '终端型号'
    }, {
        formatter: function (value, row, index) {
            var useStatus = row.useStatus;
            var format = '';
            if (useStatus === '0') {
                format = '未使用';
            } else if (useStatus === '1') {
                format = '使用中';
            } else if (useStatus === '2') {
                format = '故障中';
            }
            return format;
        },
        title: '使用状态'
    }, {
        field: 'vehiclePs',
        title: '终端备注'
    }, {
        formatter: function (value, row, index) {
            /** @namespace row.terminalPs */
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyTerminal(' + "'" + row.terminalId + "', '" + row.terminalType + "', '"
                + row.useStatus + "', '" + convertNull(row.terminalPs) + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>',
                '<a type="button" class="btn btn-operate" href="javascript:removeTerminal(' + "'" + row.terminalId + "'" + ')">' +
                '<i class="fa fa-times"></i> 删除' +
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

// 处理json中可能出现的null值
function convertNull(param) {
    if (null === param) {
        return '';
    } else {
        return param;
    }
}

// 重置
$('#resetBtn').click(function () {
    $('#queryToolBar :text').val('');
    $('#queryToolBar .selectpicker').selectpicker('val', '');
    $("#terminalFileTable").bootstrapTable('selectPage', 1);
});

// 查询
$('#queryBtn').click(function () {
    $("#terminalFileTable").bootstrapTable('selectPage', 1);
});

// 设置bootstrap-select大小
$('.selectpicker').selectpicker({
    width: '180.67px'
});

// 数据提交
function sendRequest(path, terminalId, terminalType, useStatus, terminalPs) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            terminalId: terminalId,
            terminalType: terminalType,
            useStatus: useStatus,
            terminalPs: terminalPs
        },
        success: function (res) {
            var message;
            if (res.code === 200) {
                message = "操作成功！";
            } else {
                message = "操作失败！";
            }
            bootbox.alert({
                title: '提示',
                message: message
            });
            $("#terminalFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function () {
    // 清空新增modal内容
    $('.modal :text').val('');
    $('#addTerminalPs').val('');
    $('#addModal .selectpicker').each(function () {
        var val = $(this).find('option:first').val();
        $(this).selectpicker('val', val);
    });

    $('#addModal').modal('show');
});

$('#saveAdd').click(function () {
    var terminalId = $('#addTerminalId').val().trim();
    var terminalType = $('#addVehicleType').val().trim();
    var useStatus = $('#addUseStatus').val();
    var terminalPs = $('#addTerminalPs').val().trim();

    $('#addModal').modal('hide');

    if (terminalId === '' || terminalType === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (terminalPs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '终端备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增终端信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/addTerminal', terminalId, terminalType, useStatus, terminalPs);
                }
            }
        });
    }
});

// 修改
function modifyTerminal(terminalId, terminalType, useStatus, terminalPs) {
    $('#modifyTerminalId').text(terminalId);
    $('#modifyTerminalType').val(terminalType);
    $('#modifyUseStatus').selectpicker('val', useStatus);
    $('#modifyTerminalPs').val(terminalPs);

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var terminalId = $('#modifyTerminalId').text();
    var terminalType = $('#modifyTerminalType').val().trim();
    var useStatus = $('#modifyUseStatus').val();
    var terminalPs = $('#modifyTerminalPs').val().trim();

    $('#modifyModal').modal('hide');

    if (terminalType === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (terminalPs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '终端备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改终端信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/modifyTerminal', terminalId, terminalType, useStatus, terminalPs);
                }
            }
        });
    }
});

// 删除
function removeVehicle(terminalId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除终端信息',
        callback: function (flag) {
            if (flag) {
                sendRequest('sys/file/removeTerminal', terminalId);
            }
        }
    });
}