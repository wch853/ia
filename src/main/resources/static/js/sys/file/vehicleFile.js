$("#vehicleFileTable").bootstrapTable({
    url: 'sys/file/getVehicles',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit
        }
    },
    columns: [{
        field: 'vehId',
        title: '车辆编号'
    }, {
        field: 'vehType',
        title: '车辆型号'
    }, {
        field: 'block.blockName',
        title: '车辆所属地块'
    }, {
        formatter: function (value, row, index) {
            var useStatus = row.useStatus;
            var format = '故障中';
            if (useStatus == 1) {
                format = '使用中'
            } else if (useStatus == 0) {
                format = '未使用'
            }
            return format;
        },
        title: '使用状态'
    }, {
        field: 'vehPs',
        title: '车辆备注'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25]
});