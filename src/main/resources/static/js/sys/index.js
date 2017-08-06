$(function () {
    $('a.tooltips').hover(function (e) {
        var text = $(this).find('div.fieldCard div:first').text();
        text = text + "<br>" + "数据状态：正常<br>" + "温度：23.45℃<br>" + "湿度：54.45%<br>"
            + "数据上传时间：17/05/11 18:56:05";
        $('body').append('<div id="tooltip">' + text + '</div>');
        $("#tooltip").css({
            left: e.pageX + 'px',
            top: e.pageY + 'px'
        }).fadeIn(500);
    }, function () {
        $("#tooltip").remove();
    });

    $('.breadcrumb').click(function () {
        $(this).siblings('.row').toggle(300);
    });
});