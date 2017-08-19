$("#machineFileTable").bootstrapTable({
    url: 'sys/file/getMachines',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit
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
            var format = '故障中';
            if (useStatus === '1') {
                format = '使用中'
            } else if (useStatus === '0') {
                format = '未使用'
            }
            return format;
        },
        title: '使用状态'
    }, {
        field: 'machinePs',
        title: '机械备注'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25, 50]
});