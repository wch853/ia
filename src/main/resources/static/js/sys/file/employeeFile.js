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
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25]
});