$('#submit').click(function () {
    $.ajax({
        url: 'login',
        type: 'POST',
        data: {
            username: $('#username').val().trim(),
            password: $('#password').val().trim()
        },
        success: function (res) {
            if (res.code === 200) {
                window.location.href = 'sys'
            } else {
                alert("Login Failed!");
            }
        }
    });
});