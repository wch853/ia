// 激活侧边栏
$('[data-target="#sys-man"]').trigger('click');
$('[data-target="#file-man"]').trigger('click').addClass('side-active');

$("#employeeFileTable").bootstrapTable({
    url: 'sys/file/employee/data',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit,
            empId: $('#queryEmpId').val().trim(),
            empName: $('#queryEmpName').val().trim()
        }
    },
    columns: [{
        field: 'empId',
        title: '员工编号'
    }, {
        field: 'empName',
        title: '员工姓名'
    }, {
        field: 'empTel',
        title: '联系电话'
    }, {
        field: 'empPosition',
        title: '职位'
    }, {
        field: 'empAge',
        title: '年龄'
    }, {
        field: 'empSex',
        title: '性别'
    }, {
        field: 'empMail',
        title: '邮箱'
    }, {
        formatter: function (value, row, index) {
            var format;
            /** @namespace row.mailStatus */
            var status = row.mailStatus;
            status === 1 ? format = '是' : format = '否';
            return format;
        },
        title: '邮件推送'
    }, {
        field: 'empPs',
        title: '员工备注'
    }, {
        formatter: function (value, row, index) {
            /** @namespace row.empMail */
            return [
                '<a type="button" class="btn btn-operate" href="javascript:modifyEmp(' + "'" + row.empId + "', '"
                + row.empName + "', '" + row.empTel + "', '" + convertNull(row.empPosition) + "', '" + convertNull(row.empAge)
                + "', '" + convertNull(row.empSex) + "', '" + convertNull(row.empMail) + "', '" + row.mailStatus + "', '" + convertNull(row.empPs) + "'" + ')">' +
                '<i class="fa fa-pencil"></i> 修改' +
                '</a>',
                '<a type="button" class="btn btn-operate" href="javascript:removeEmp(' + "'" + row.empId + "'" + ')">' +
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
    $('#employeeFileTable').bootstrapTable('selectPage', 1);
});

// 重置
$('#resetBtn').click(function () {
    $('#queryToolBar :text').val('');
    $('#employeeFileTable').bootstrapTable('selectPage', 1);
});

// 设置bootstrap-select大小
$('.selectpicker').selectpicker({
    width: '180.67px'
});

// 数据提交
function sendRequest(path, empId, empName, empTel, empPosition, empAge, empSex, empMail, mailStatus, empPs) {
    $.ajax({
        url: path,
        type: 'post',
        data: {
            empId: empId,
            empName: empName,
            empTel: empTel,
            empPosition: empPosition,
            empAge: empAge,
            empSex: empSex,
            empMail: empMail,
            mailStatus: mailStatus,
            empPs: empPs
        },
        success: function (res) {
            var message = '操作失败';
            if (res.code === 200) {
                message = "操作成功！";
            } else if (res.code === 300 && res.message) {
                message = res.message;
            }
            bootbox.alert({
                title: '提示',
                message: message
            });
            $("#employeeFileTable").bootstrapTable('selectPage', 1);
        }
    });
}

// 新增
$('#addBtn').click(function () {
    $('.modal :text').val('');
    $('#addEmpSex').find('option:first').prop('selected', true).selectpicker('refresh');
    $('#addEmpPs').val('');
    $('#addModal').modal('show');
});

$('#saveAdd').click(function () {
    var empId = $('#addEmpId').val().trim();
    var empName = $('#addEmpName').val().trim();
    var empTel = $('#addEmpTel').val().trim();
    var empPosition = $('#addEmpPosition').val().trim();
    var empAge = $('#addEmpAge').val().trim();
    var empSex = $('#addEmpSex').val();
    var empMail = $('#addEmpMail').val().trim();
    var mailStatus = $('#addMailStatus').val();
    var empPs = $('#addEmpPs').val().trim();

    $('#addModal').modal('hide');

    var alertMessage = '';

    if (empId === '' || empName === '' || empTel === '') {
        alertMessage = '请输入完整信息！';
    } else if ('' !== empAge && !/^[0-9]{1,3}$/.test(empAge)) {
        alertMessage = '年龄输入有误！';
    } else if ('' !== empMail && !/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/.test(empMail)) {
        alertMessage = '邮箱输入有误！';
    } else if (empPs.length > 80) {
        alertMessage = '员工备注限输入80个字符！';
    }

    if (alertMessage !== '') {
        bootbox.alert({
            title: '提示',
            message: alertMessage
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认新增员工信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/employee/add', empId, empName, empTel, empPosition, empAge, empSex, empMail, mailStatus, empPs);
                }
            }
        });
    }
});

// 修改
function modifyEmp(empId, empName, empTel, empPosition, empAge, empSex, empMail, mailStatus, empPs) {
    $('#modifyEmpId').text(empId);
    $('#modifyEmpName').val(empName);
    $('#modifyEmpTel').val(empTel);
    $('#modifyEmpPosition').val(empPosition);
    $('#modifyEmpAge').val(empAge);
    $('#modifyEmpSex').selectpicker('val', empSex);
    $('#modifyEmpMail').val(empMail);
    $('#modifyMailStatus').selectpicker('val', mailStatus);
    $('#modifyEmpPs').val(empPs);

    $('#modifyModal').modal('show');
}

$('#saveModify').click(function () {
    var empId = $('#modifyEmpId').text();
    var empName = $('#modifyEmpName').val().trim();
    var empTel = $('#modifyEmpTel').val().trim();
    var empPosition = $('#modifyEmpPosition').val().trim();
    var empAge = $('#modifyEmpAge').val().trim();
    var empSex = $('#modifyEmpSex').val();
    var empMail = $('#modifyEmpMail').val().trim();
    var mailStatus = $('#modifyMailStatus').val();
    var empPs = $('#modifyEmpPs').val().trim();

    $('#modifyModal').modal('hide');

    var alertMessage = '';

    if (empName === '' || empTel === '') {
        alertMessage = '请输入完整信息！';
    } else if ('' !== empAge && !/^[0-9]{1,3}$/.test(empAge)) {
        alertMessage = '年龄输入有误！';
    } else if ('' !== empMail && !/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+$/.test(empMail)) {
        alertMessage = '邮箱输入有误！';
    } else if (empPs.length > 80) {
        alertMessage = '员工备注限输入80个字符！';
    }

    if (alertMessage !== '') {
        bootbox.alert({
            title: '提示',
            message: alertMessage
        });
    } else {
        bootbox.confirm({
            title: '提示',
            message: '确认修改员工信息',
            callback: function (flag) {
                if (flag) {
                    sendRequest('sys/file/employee/modify', empId, empName, empTel, empPosition, empAge, empSex, empMail, mailStatus, empPs);
                }
            }
        });
    }
});

// 删除
function removeEmp(empId) {
    bootbox.confirm({
        title: '提示',
        message: '确认删除员工信息',
        callback: function (flag) {
            if (flag) {
                sendRequest('sys/file/employee/remove', empId);
            }
        }
    });
}