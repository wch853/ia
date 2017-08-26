$('#queryBtn').click(function () {
    var loc = $('#queryFile').val();

    if ('' !== loc) {
        window.location.href = loc;
    }
});