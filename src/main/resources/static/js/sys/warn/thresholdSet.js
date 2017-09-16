// 激活侧边栏
$('[data-target="#warn-man"]').trigger('click').parent().find('li:eq(0) a').addClass('side-active');

$('#temperature-slider').ionRangeSlider();