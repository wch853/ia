var endDeviceFile = {
  init: function () {
      // 激活侧边栏
      $('[data-target="#sys-man"]').trigger('click');
      $('[data-target="#file-man"]').trigger('click').addClass('side-active');

      $("#end-device-file-table").bootstrapTable({
          url: 'sys/file/terminal/data',
          queryParams: function (params) {
              return {
                  offset: params.offset,
                  limit: params.limit,
                  model: $('#end-device-model').val().trim(),
                  useStatus: $('#use-status').val()
              }
          },
          columns: [{
              field: 'id',
              title: '终端编号'
          }, {
              field: 'model',
              title: '终端型号'
          }, {
              field: 'mac',
              title: '终端Mac地址'
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
  }
};





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
    $("#end-device-file-table").bootstrapTable('selectPage', 1);
});

// 查询
$('#query-btn').click(function () {
    $("#end-device-file-table").bootstrapTable('selectPage', 1);
});

// 数据提交
function sendRequest(path, terminalId, terminalType, useStatus, terminalPs) {
    $.ajax({
        url: path,
        type: 'POST',
        data: {
            terminalId: terminalId,
            terminalType: terminalType,
            useStatus: useStatus,
            terminalPs: terminalPs
        },
        success: function (res) {
            var message = '操作失败';
            if (res.success) {
                message = "操作成功！";
            }
            bootbox.alert({
                title: '提示',
                message: message
            });
            $("#end-device-file-table").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#add-btn').click(function () {
    // 清空新增modal内容
    $('.modal :text').val('');
    $('#addTerminalPs').val('');
    $('#add-modal .selectpicker').each(function () {
        var val = $(this).find('option:first').val();
        $(this).selectpicker('val', val);
    });

    $('#add-modal').modal('show');
});

$('#save-add').click(function () {
    var terminalId = $('#addTerminalId').val().trim();
    var terminalType = $('#addTerminalType').val().trim();
    var useStatus = $('#addUseStatus').val();
    var terminalPs = $('#addTerminalPs').val().trim();

    $('#add-modal').modal('hide');

    var alertMessage = '';

    if (terminalId == '' || terminalType == '' || useStatus == '') {
        alertMessage = '请输入完整信息！';
    } else if (terminalPs.length > 80) {
        alertMessage = '终端备注限输入80个字符！';
    }

    if ('' !== alertMessage) {
        bootbox.alert({
            title: '提示',
            message: alertMessage
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增终端信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/terminal/add', terminalId, terminalType, useStatus, terminalPs);
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

    $('#modify-modal').modal('show');
}

$('#save-modify').click(function () {
    var terminalId = $('#modifyTerminalId').text();
    var terminalType = $('#modifyTerminalType').val().trim();
    var useStatus = $('#modifyUseStatus').val();
    var terminalPs = $('#modifyTerminalPs').val().trim();

    $('#modify-modal').modal('hide');

    var alertMessage = '';

    if (terminalType == '' || useStatus == '') {
        alertMessage = '请输入完整信息！';
    } else if (terminalPs.length > 80) {
        alertMessage = '终端备注限输入80个字符！';
    }

    if ('' !== alertMessage) {
        bootbox.alert({
            title: '提示',
            message: alertMessage
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改终端信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/terminal/modify', terminalId, terminalType, useStatus, terminalPs);
                }
            }
        });
    }
});

// 删除
function removeTerminal(terminalId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除终端信息',
        callback: function (flag) {
            if (flag) {
                sendRequest('sys/file/terminal/remove', terminalId);
            }
        }
    });
}