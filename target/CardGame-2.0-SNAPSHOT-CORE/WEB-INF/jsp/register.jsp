<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Register Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <style><%@include file="/src/style/common.css" %></style>
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
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
            function playAudio() {
                var myAudio = new Audio();
                myAudio.src = "src/sound/click.mp3";
                myAudio.autoplay = true;
            }
        </script>
        <div style="margin: 100px auto; width: 350px">
            <div class="form1">
                <h1 style="text-align: center">Registration</h1>
                <div style="font-size: 22px; text-align: center"><span id="logResult"/></div>
                <div style="font-size: 22px; text-align: center"><span id="passResult"/></div>
                <div style="font-size: 22px; text-align: center"><span id="equalResult"/></div>
                <div style="font-size: 22px; text-align: center"><span id="mailResult"/></div>
                <fieldset>
                    <form action="register.html" method="POST">
                        <input type="text" placeholder="Login" name="login" id="log" onblur="loginCheck()" required/><br>
                        <input type="password" placeholder="Password" name="pass" id="password" onkeyup="passCheck()" required/><br>
                        <input type="password" placeholder="Repeat Password" name="pass2" id="password2" onkeyup="equalCheck()" required/><br>
                        <input type="text" placeholder="City" name="city"/><br>
                        <input type="tel" placeholder="Phone" name="phone"/><br>
                        <input type="email" placeholder="E-Mail" name="email" id="mail" onblur="mailCheck()" required/><br>
                        <input type="submit" value="Sign Up" onclick="playAudio();" />
                    </form>
                    <p><a href="login.html" onmousedown="playAudio();">Already registered? Sign in now.</a></p>
                </fieldset>
            </div>
        </div>
    </body>
</html>