// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#machineFileTable").bootstrapTable({
    url: 'sys/file/getMachines',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            machineId: $('#queryMachineId').val().trim(),
            machineType: $('#queryMachineType').val().trim(),
            blockId: $('#queryBlock').val(),
            useStatus: $('#queryUseStatus').val()
        }
    },
    columns: [{
        field: 'machineId',
        title: '机械编号'
    }, {
        field: 'machineType',
        title: '机械型号'
    }, {
        field: 'block.blockName',
        title: '机械所属地块'
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
        field: 'machinePs',
        title: '机械备注'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyMachine(' + "'" + row.machineId + "', '" + row.machineType + "', '"
                + convertBlockName(row.block) + "', '" + row.useStatus + "', '" + convertNull(row.machinePs) + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>',
                '<a type="button" class="btn btn-operate" href="javascript:removeMachine(' + "'" + row.machineId + "'" + ')">' +
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
    $("#machineFileTable").bootstrapTable('selectPage', 1);
});

// 查询
$('#queryBtn').click(function () {
    $("#machineFileTable").bootstrapTable('selectPage', 1);
});

// 设置bootstrap-select大小
$('.selectpicker').selectpicker({
    width: '180.67px'
});

// 数据提交
function deliverData(path, machineId, machineType, blockId, useStatus, machinePs) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            machineId: machineId,
            machineType: machineType,
            blockId: blockId,
            useStatus: useStatus,
            machinePs: machinePs
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
            $("#machineFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function () {
    // 清空新增modal内容
    $('.modal :text').val('');
    $('#addMachinePs').val('');
    $('#addModal .selectpicker').each(function () {
        var val = $(this).find('option:first').val();
        $(this).selectpicker('val', val);
    });

    $('#addModal').modal('show');
});

$('#saveAdd').click(function () {
    var machineId = $('#addMachineId').val().trim();
    var machineType = $('#addMachineType').val().trim();
    var blockId = $('#addBlockId').val();
    var useStatus = $('#addUseStatus').val();
    var machinePs = $('#addMachinePs').val().trim();

    $('#addModal').modal('hide');

    if (machineId === '' || machineType === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (machinePs.length > 80) {
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
                    deliverData('sys/file/addMachine', machineId, machineType, blockId, useStatus, machinePs);
                }
            }
        });
    }
});

// 修改
function modifyMachine(machineId, machineType, blockId, useStatus, machinePs) {
    $('#modifyMachineId').text(machineId);
    $('#modifyMachineType').val(machineType);
    $('#modifyBlockId').selectpicker('val', blockId);
    $('#modifyUseStatus').selectpicker('val', useStatus);
    $('#modifyMachinePs').val(machinePs);

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var machineId = $('#modifyMachineId').text();
    var machineType = $('#modifyMachineType').val().trim();
    var blockId = $('#modifyBlockId').val();
    var useStatus = $('#modifyUseStatus').val();
    var machinePs = $('#modifyMachinePs').val().trim();

    $('#modifyModal').modal('hide');

    if (machineType === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (machinePs.length > 80) {
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
                    deliverData('sys/file/modifyMachine', machineId, machineType, blockId, useStatus, machinePs);
                }
            }
        });
    }
});

// 删除
function removeMachine(machineId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除机械信息',
        callback: function (flag) {
            if (flag) {
                deliverData('sys/file/removeMachine', machineId);
            }
        }
    });
}