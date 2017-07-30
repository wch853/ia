$("#blockFileTable").bootstrapTable({
    url: 'sys/file/getBlocks',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit
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
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25]
});