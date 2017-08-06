$("#sensorFileTable").bootstrapTable({
    url: 'sys/file/getSensors',
    queryParams: function (params) {
        return {
            offset: params.offset,
            limit: params.limit
        }
    },
    columns: [{
        field: 'sensorId',
        title: '传感器编号'
    }, {
        formatter: function (value, row, index) {
            var sensorFun = row.sensorFun;
            var format = '';
            if (sensorFun == 1) {
                format = '温度传感器'
            } else if (sensorFun == 2) {
                format = '湿度传感器'
            }
            return format;
        },
        title: '传感器功能类型'
    }, {
        field: 'field.fieldId',
        title: '传感器所属大棚'
    }, {
        field: 'sensorType',
        title: '传感器型号'
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
        field: 'sensorPs',
        title: '传感器备注'
    }],
    striped: true,
    pagination: true,
    sidePagination: 'server',
    pageSize: 10,
    pageList: [5, 10, 25]
});