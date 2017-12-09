// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#blockFileTable").bootstrapTable({
    url: 'sys/file/block/data',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            blockId: $('#queryBlockId').val().trim(),
            blockName: $('#queryBlockName').val().trim()
        }
    },
    columns: [{
        field: 'blockId',
        title: '地块编号'
    }, {
        field: 'blockName',
        title: '地块名称'
    }, {
        field: 'blockLoc',
        title: '地块位置'
    }, {
        field: 'blockPs',
        title: '备注'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyBlock(' + "'" + row.blockId + "', '"
                + row.blockName + "', '" + row.blockLoc + "', '" + convertNull(row.blockPs) + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>',
                '<a type="button" class="btn btn-operate" href="javascript:removeBlock(' + "'" + row.blockId + "'" + ')">' +
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

// 查询
$('#queryBtn').click(function () {
    $('#blockFileTable').bootstrapTable('selectPage', 1);
});

// 重置
$('#resetBtn').click(function () {
    $('#queryToolBar :text').val('');
    $('#blockFileTable').bootstrapTable('selectPage', 1);
});

// 数据提交
function sendRequest(path, blockId, blockName, blockLoc, blockPs) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            blockId: blockId,
            blockName: blockName,
            blockLoc: blockLoc,
            blockPs: blockPs
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
            $("#blockFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function () {
    $('.modal :text').val('');
    $('#addBlockPs').val('');
    $('#addModal').modal('show');
});

$('#saveAdd').click(function () {
    var blockId = $('#addBlockId').val().trim();
    var blockName = $('#addBlockName').val().trim();
    var blockLoc = $('#addBlockLoc').val().trim();
    var blockPs = $('#addBlockPs').val().trim();

    $('#addModal').modal('hide');

    if (blockId === '' || blockName === '' || blockLoc === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (blockPs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '地块备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增地块信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/block/add', blockId, blockName, blockLoc, blockPs);
                }
            }
        });
    }
});

// 修改
function modifyBlock(blockId, blockName, blockLoc, blockPs) {
    $('#modifyBlockId').text(blockId);
    $('#modifyBlockName').val(blockName);
    $('#modifyBlockLoc').val(blockLoc);
    $('#modifyBlockPs').val(blockPs);

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var blockId = $('#modifyBlockId').text();
    var blockName = $('#modifyBlockName').val().trim();
    var blockLoc = $('#modifyBlockLoc').val().trim();
    var blockPs = $('#modifyBlockPs').val().trim();

    $('#modifyModal').modal('hide');

    if (blockName === '' || blockLoc === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (blockPs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '地块备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改地块信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/modifyBlock', blockId, blockName, blockLoc, blockPs);
                }
            }
        });
    }
});

// 删除
function removeBlock(blockId) {
    bootbox.confirm({
        title: '提示',
        message: '删除地块并删除该地块下的所有大棚？',
        callback: function (flag) {
            if (flag) {
                sendRequest('sys/file/block/remove', blockId);
            }
        }
    });
}