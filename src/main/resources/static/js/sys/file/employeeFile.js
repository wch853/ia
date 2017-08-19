$("#employeeFileTable").bootstrapTable({
    url: 'sys/file/getEmployees',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit
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
        title: '员工联系电话'
    }, {
        field: 'empPosition',
        title: '员工职位'
    }, {
        field: 'empAge',
        title: '员工年龄'
    }, {
        field: 'empSex',
        title: '员工性别'
    }, {
        field: 'empPs',
        title: '员工备注'
    }, {
        formatter: function (value, row, index) {
            return [
                '<a href="javascript:modifyEmp(' + "'" + row.empId + "', '" + row.empName + "', '"
                + row.empTel + "', '" + convertNull(row.empPosition) + "', '" + convertNull(row.empAge) + "', '"
                + convertNull(row.empSex) + "', '" + convertNull(row.empPs) + "'" + ')">' +
                '<i class="glyphicon glyphicon-pencil"></i>修改' +
                '</a>',
                '<a href="javascript:removeEmp(' + "'" + row.empId + "'" + ')">' +
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

// 处理json中可能出现的null值
function convertNull(param) {
    if (null === param) {
        return '';
    } else {
        return param;
    }
}