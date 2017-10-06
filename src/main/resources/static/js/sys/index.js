$('.index-bread').click(function () {
    $(this).siblings('.row').toggle(300);
    var $icon = $(this).find('.icon-collapse');
    if ($icon.hasClass('fa-compress')) {
        $icon.removeClass('fa-compress').addClass('fa-expand');
    } else {
        $icon.removeClass('fa-expand').addClass('fa-compress');
    }
});

$('.field-card').popover('destroy');

$('.field-card').hover(function () {
    var $card = $(this);
    var fieldId = $(this).attr('code');
    var fieldName = $(this).find('.card-body-content').text();

    $.ajax({
        url: 'sys/getFieldStatus',
        data: {
            fieldId: fieldId
        },
        beforesend: function () {
            $card.popover('destroy').popover({
                title: '大棚编号：' + fieldId,
                placement: 'auto',
                html: true,
                content: function () {
                    return '<img alt="loading..." src="image/loading.gif">';
                }
            }).popover('show');
        },
        success: function (res) {
            $card.popover('destroy').popover({
                title: '大棚编号：' + fieldId,
                placement: 'auto',
                html: true,
                content: appendStatus(res.data, fieldName)
            }).popover('show');
        }
    });
}, function () {
    $(this).popover('destroy');
});

function appendStatus(data, fieldName) {

    var html = '<ul><li>名称: ' + '<span class="status-data">' + fieldName + '</span></li>';

    $.each(data, function (i, el) {
        var statusItem = convertData(i, el);

        html += statusItem;
    });

    return html + '</ul>';
}

/**
 * 转换大棚状态数据
 */
function convertData(i, el) {

    var html = '';
    switch (i) {
        case 'updateTime':
            html ='<li>更新时间: ' + '<span class="status-data">' + el + '</span>' + '</li>';
            break;
        case 'temperature':
            html = '<li>温度: ' + '<span class="status-data">' + el + '</span>' + ' °C</li>';
            break;
        case 'moisture':
            html = '<li>湿度: ' + '<span class="status-data">' + el + '</span>' + ' %</li>';
            break;
        case 'soilTemperature':
            html = '<li>土壤温度: ' + '<span class="status-data">' + el + '</span>' + ' °C</li>';
            break;
        case 'soilMoisture':
            html = '<li>土壤湿度: ' + '<span class="status-data">' + el + '</span>' + ' %</li>';
            break;
        case 'light':
            html = '<li>光照强度: ' + '<span class="status-data">' + el + '</span>' + ' LUX</li>';
            break;
        case 'co2':
            html = '<li>Co2浓度: ' + '<span class="status-data">' + el + '</span>' + ' PPM</li>';
            break;
        case 'ph':
            html = '<li>ph酸碱度: ' + '<span class="status-data">' + el + '</span>' + '</li>';
            break;
        case 'n':
            html = '<li>氮含量: ' + '<span class="status-data">' + el + '</span>' + ' mg/kg</li>';
            break;
        case 'p':
            html = '<li>磷含量: ' + '<span class="status-data">' + el + '</span>' + ' mg/kg</li>';
            break;
        case 'k':
            html = '<li>钾含量: ' + '<span class="status-data">' + el + '</span>' + ' mg/kg</li>';
            break;
        case 'hg':
            html = '<li>汞含量: ' + '<span class="status-data">' + el + '</span>' + ' mg/kg</li>';
            break;
        case 'pb':
            html = '<li>铅含量: ' + '<span class="status-data">' + el + '</span>' + ' mg/kg</li>';
            break;
        default:
            html = '';
    }

    return html;
}