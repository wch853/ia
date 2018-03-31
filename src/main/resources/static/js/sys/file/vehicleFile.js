// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#vehicleFile-table").bootstrapTable({
    url: 'sys/file/vehicle/data',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            vehicleId: $('#queryVehicleId').val().trim(),
            vehicleType: $('#queryVehicleType').val().trim(),
            blockId: $('#queryBlock').val(),
            useStatus: $('#use-status').val()
        }
    },
    columns: [{
        field: 'vehicleId',
        title: '车辆编号'
    }, {
        field: 'vehicleType',
        title: '车辆型号'
    }, {
        field: 'block.blockName',
        title: '车辆所属地块'
    }, {
        formatter: function (value, row, index) {
            var useStatus = row.useStatus;
            var format = '';
            if (useStatus == '0') {
                format = '未使用';
            } else if (useStatus == '1') {
                format = '使用中';
            } else if (useStatus == '2') {
                format = '故障中';
            }
            return format;
        },
        title: '使用状态'
    }, {
        field: 'vehiclePs',
        title: '备注'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyVehicle(' + "'" + row.vehicleId + "', '" + row.vehicleType + "', '"
                + convertBlockName(row.block) + "', '" + row.useStatus + "', '" + convertNull(row.vehiclePs) + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>',
                '<a type="button" class="btn btn-operate" href="javascript:removeVehicle(' + "'" + row.vehicleId + "'" + ')">' +
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

// 处理json中block可能出现的null值
function convertBlockName(block) {
    try {
        return block.blockId;
    } catch (err) {
        return '';
    }
}

// 处理json中可能出现的null值
function convertNull(param) {
    if (null == param) {
        return '';
    } else {
        return param;
    }
}

// 重置
$('#reset-btn').click(function () {
    $('#query-tool-bar :text').val('');
    $('#query-tool-bar .selectpicker').selectpicker('val', '');
    $("#vehicleFile-table").bootstrapTable('selectPage', 1);
});

// 查询
$('#query-btn').click(function () {
    $("#vehicleFile-table").bootstrapTable('selectPage', 1);
});

// 设置bootstrap-select大小
$('.selectpicker').selectpicker({
    width: '180.67px'
});

// 数据提交
function sendRequest(path, vehicleId, vehicleType, blockId, useStatus, vehiclePs) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            vehicleId: vehicleId,
            vehicleType: vehicleType,
            blockId: blockId,
            useStatus: useStatus,
            vehiclePs: vehiclePs
        },
        success: function (res) {
            var message = '操作失败';
            if (res.code == 200) {
                message = "操作成功！";
            } else if (res.code == 300 && res.message) {
                message = res.message;
            }
            bootbox.alert({
                title: '提示',
                message: message
            });
            $("#vehicleFile-table").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#add-btn').click(function () {
    // 清空新增modal内容
    $('.modal :text').val('');
    $('#addVehiclePs').val('');
    $('#add-modal .selectpicker').each(function () {
        var val = $(this).find('option:first').val();
        $(this).selectpicker('val', val);
    });

    $('#add-modal').modal('show');
});

$('#save-add').click(function () {
    var vehicleId = $('#addVehicleId').val().trim();
    var vehicleType = $('#addVehicleType').val().trim();
    var blockId = $('#addBlockId').val();
    var useStatus = $('#addUseStatus').val();
    var vehiclePs = $('#addVehiclePs').val().trim();

    $('#add-modal').modal('hide');

    var alertMessage = '';

    if (vehicleId == '' || vehicleType == '') {
        alertMessage = '请输入完整信息！';
    } else if (vehiclePs.length > 80) {
        alertMessage = '车辆备注限输入80个字符！';
    }

    if ('' !== alertMessage) {
        bootbox.alert({
            title: '提示',
            message: alertMessage
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增车辆信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/vehicle/add', vehicleId, vehicleType, blockId, useStatus, vehiclePs);
                }
            }
        });
    }
});

// 修改
function modifyVehicle(vehicleId, vehicleType, blockId, useStatus, vehiclePs) {
    $('#modifyVehicleId').text(vehicleId);
    $('#modifyVehicleType').val(vehicleType);
    $('#modifyBlockId').selectpicker('val', blockId);
    $('#modifyUseStatus').selectpicker('val', useStatus);
    $('#modifyVehiclePs').val(vehiclePs);

    $('#modify-modal').modal('show');
}

$('#save-modify').click(function () {
    var vehicleId = $('#modifyVehicleId').text();
    var vehicleType = $('#modifyVehicleType').val().trim();
    var blockId = $('#modifyBlockId').val();
    var useStatus = $('#modifyUseStatus').val();
    var vehiclePs = $('#modifyVehiclePs').val().trim();

    $('#modify-modal').modal('hide');

    var alertMessage = '';

    if (vehicleType == '') {
        alertMessage = '请输入完整信息！';
    } else if (vehiclePs.length > 80) {
        alertMessage = '车辆备注限输入80个字符！';
    }

    if ('' !== alertMessage) {
        bootbox.alert({
            title: '提示',
            message: alertMessage
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改车辆信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/vehicle/modify', vehicleId, vehicleType, blockId, useStatus, vehiclePs);
                }
            }
        });
    }
});

// 删除
function removeVehicle(vehicleId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除车辆信息',
        callback: function (flag) {
            if (flag) {
                sendRequest('sys/file/vehicle/remove', vehicleId);
            }
        }
    });
}