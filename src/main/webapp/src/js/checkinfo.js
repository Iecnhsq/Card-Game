function equalCheck() {
    $.ajax({
        url: "equalscheck.html",
        data: ({login: $('#log').val(), pass: $('#password').val()}),
        success: function (data) {
            $('#equalResult').html(data);
        }
    });
}
function loginCheckSupp() {
    $.ajax({
        url: "logchecksupp.html",
        data: ({login: $('#login').val()}),
        success: function (data) {
            $('#logResultSupp').html(data);
        }
    });
}
function loginCheck() {
    $.ajax({
        url: "logcheck.html",
        data: ({login: $('#log').val()}),
        success: function (data) {
            $('#logResult').html(data);
        }
    });
}
function passCheck() {
    $.ajax({
        url: "passcheck.html",
        data: ({pass: $('#password').val()}),
        success: function (data) {
            $('#passResult').html(data);
        }
    });
}
function equalCheck() {
    $.ajax({
        url: "equalcheck.html",
        data: ({pass: $('#password').val(), pass2: $('#password2').val()}),
        success: function (data) {
            $('#equalResult').html(data);
        }
    });
}
function mailCheck() {
    $.ajax({
        url: "mailcheck.html",
        data: ({email: $('#mail').val()}),
        success: function (data) {
            $('#mailResult').html(data);
        }
    });
}
function checkRec() {
    $.ajax({
        url: "checkrec.html",
        data: ({login: $('#log').val(), email: $('#email').val()}),
        success: function (data) {
            $('#resultRec').html(data);
        }
    });
}
function checkRec1() {
    $.ajax({
        url: "checkrec1.html",
        data: ({login: $('#log').val(), email: $('#email').val()}),
        success: function (data) {
            $('#resultRec1').html(data);
        }
    });
}