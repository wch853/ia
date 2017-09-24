/**
 * 获取系统提示
 */
function checkTip() {
    checkWarn();
}

function checkWarn() {
    $.ajax({
        url: 'sys/warn/getUnhandleRecordCount',
        success: function (res) {
            var count = res.data;
            if (count > 0) {
                $('#tip-view-point').addClass('tip-view-point');
                $('#warn-count').text(count);
            }
        }
    });
}

checkTip();