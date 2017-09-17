// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#vehicleFileTable").bootstrapTable({
    url: 'sys/file/getVehicles',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            vehicleId: $('#queryVehicleId').val().trim(),
            vehicleType: $('#queryVehicleType').val().trim(),
            blockId: $('#queryBlock').val(),
            useStatus: $('#queryUseStatus').val()
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
            var format = '故障中';
            if (useStatus === '1') {
                format = '使用中'
            } else if (useStatus === '0') {
                format = '未使用'
            }
            return format;
        },
        title: '使用状态'
    }, {
        field: 'vehiclePs',
        title: '车辆备注'
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
    $("#vehicleFileTable").bootstrapTable('selectPage', 1);
});

// 查询
$('#queryBtn').click(function () {
    $("#vehicleFileTable").bootstrapTable('selectPage', 1);
});

// 设置bootstrap-select大小
$('.selectpicker').selectpicker({
    width: '180.67px'
});

// 数据提交
function deliverData(path, vehicleId, vehicleType, blockId, useStatus, vehiclePs) {
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
            bootbox.alert({
                title: '提示',
                message: res.message
            });
            $("#vehicleFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function () {
    // 清空新增modal内容
    $('.modal :text').val('');
    $('#addVehiclePs').val('');
    $('#addModal .selectpicker').each(function () {
        var val = $(this).find('option:first').val();
        $(this).selectpicker('val', val);
    });

    $('#addModal').modal('show');
});

$('#saveAdd').click(function () {
    var vehicleId = $('#addVehicleId').val().trim();
    var vehicleType = $('#addVehicleType').val().trim();
    var blockId = $('#addBlockId').val();
    var useStatus = $('#addUseStatus').val();
    var vehiclePs = $('#addVehiclePs').val().trim();

    $('#addModal').modal('hide');

    if (vehicleId === '' || vehicleType === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (vehiclePs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '机械备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增机械信息',
            callback: function (flag) {
                if (flag) {
                    deliverData('sys/file/addVehicle', vehicleId, vehicleType, blockId, useStatus, vehiclePs);
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

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var vehicleId = $('#modifyVehicleId').text();
    var vehicleType = $('#modifyVehicleType').val().trim();
    var blockId = $('#modifyBlockId').val();
    var useStatus = $('#modifyUseStatus').val();
    var vehiclePs = $('#modifyVehiclePs').val().trim();

    $('#modifyModal').modal('hide');

    if (vehicleType === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (vehiclePs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '机械备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改机械信息',
            callback: function (flag) {
                if (flag) {
                    deliverData('sys/file/modifyVehicle', vehicleId, vehicleType, blockId, useStatus, vehiclePs);
                }
            }
        });
    }
});

// 删除
function removeVehicle(vehicleId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除大棚信息',
        callback: function (flag) {
            if (flag) {
                deliverData('sys/file/removeVehicle', vehicleId);
            }
        }
    });
}