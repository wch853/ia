$("#cropFileTable").bootstrapTable({
    url: 'sys/file/getCrops',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit
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
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25]
});