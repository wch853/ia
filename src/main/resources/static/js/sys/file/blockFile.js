$("#blockFileTable").bootstrapTable({
    url: 'sys/file/getBlocks',
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
                '<a href="javascript:modifyBlock(' + "'" + row.blockId + "', '" + row.blockName + "', '"
                + row.blockLoc + "', '" + convertBlockPs(row.blockPs) + "'" + ')">' +
                '<i class="glyphicon glyphicon-pencil"></i>修改' +
                '</a>',
                '<a href="javascript:removeBlock(' + "'" + row.blockId + "'" + ')">' +
                '<i class="glyphicon glyphicon-remove"></i>删除' +
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

// 处理json中BlockPs可能出现的null值
function convertBlockPs(blockPs) {
    if (null === blockPs) {
        return '';
    } else {
        return blockPs;
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

// 设置bootbox中文支持
bootbox.setLocale('zh_CN');

// 数据提交
function deliverData(path, blockId, blockName, blockLoc, blockPs) {
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
            bootbox.alert({
                title: '提示',
                message: res.message
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
                    deliverData('sys/file/addBlock', blockId, blockName, blockLoc, blockPs);
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
                    deliverData('sys/file/modifyBlock', blockId, blockName, blockLoc, blockPs);
                }
            }
        });
    }
});

// 删除
function removeBlock(blockId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除地块信息',
        callback: function (flag) {
            if (flag) {
                deliverData('sys/file/removeBlock', blockId);
            }
        }
    });
}