// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#cropFileTable").bootstrapTable({
    url: 'sys/file/getCrops',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            cropId: $('#queryCropId').val().trim(),
            cropName: $('#queryCropName').val().trim()
        }
    },
    columns: [{
        field: 'cropId',
        title: '作物编号'
    }, {
        field: 'cropName',
        title: '作物名称'
    }, {
        field: 'cropPs',
        title: '作物备注'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyCrop(' + "'" + row.cropId + "', '"
                + row.cropName + "', '" + convertNull(row.cropPs) + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>',
                '<a type="button" class="btn btn-operate" href="javascript:removeCrop(' + "'" + row.cropId + "'" + ')">' +
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
    $('#cropFileTable').bootstrapTable('selectPage', 1);
});

// 重置
$('#resetBtn').click(function () {
    $('#queryToolBar :text').val('');
    $('#cropFileTable').bootstrapTable('selectPage', 1);
});

// 数据提交
function deliverData(path, cropId, cropName, cropPs) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            cropId: cropId,
            cropName: cropName,
            cropPs: cropPs
        },
        success: function (res) {
            bootbox.alert({
                title: '提示',
                message: res.message
            });
            $("#cropFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function () {
    $('.modal :text').val('');
    $('#addCropPs').val('');
    $('#addModal').modal('show');
});

$('#saveAdd').click(function () {
    var cropId = $('#addCropId').val().trim();
    var cropName = $('#addCropName').val().trim();
    var cropPs = $('#addCropPs').val().trim();

    $('#addModal').modal('hide');

    if (cropId === '' || cropName === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (cropPs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '作物备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增作物信息',
            callback: function (flag) {
                if (flag) {
                    deliverData('sys/file/addCrop', cropId, cropName, cropPs);
                }
            }
        });
    }
});

// 修改
function modifyCrop(cropId, cropName, cropPs) {
    $('#modifyCropId').text(cropId);
    $('#modifyCropName').val(cropName);
    $('#modifyCropPs').val(cropPs);

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var cropId = $('#modifyCropId').text();
    var cropName = $('#modifyCropName').val().trim();
    var cropPs = $('#modifyCropPs').val().trim();

    $('#modifyModal').modal('hide');

    if (cropName === '') {
        bootbox.alert({
            title: '提示',
            message: '请输入完整信息！'
        });
    } else if (cropPs.length > 80) {
        bootbox.alert({
            title: '提示',
            message: '作物备注限输入80个字符！'
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改作物信息',
            callback: function (flag) {
                if (flag) {
                    deliverData('sys/file/modifyCrop', cropId, cropName, cropPs);
                }
            }
        });
    }
});

// 删除
function removeCrop(cropId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除作物信息',
        callback: function (flag) {
            if (flag) {
                deliverData('sys/file/removeCrop', cropId);
            }
        }
    });
}