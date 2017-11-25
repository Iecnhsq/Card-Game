<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Register Page</title>
        <style><%@include file="/Style/register.css" %></style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
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
        </script>
        <div style="margin: 100px auto; width: 600px">
            <div style="clear: both"></div>
            <div class="form1">
                <div>
                    <h1 style="text-align: center">Registration</h1>
                    <fieldset>
                        <p></p>
                        <form action="register.html" method="POST">
                            <input type="text" placeholder="Login" name="login" id="log" onblur="loginCheck()" required/><br>
                            <input type="password" placeholder="Password" name="pass" id="password" onkeyup="passCheck()" required/><br>
                            <input type="password" placeholder="Repeat Password" name="pass2" id="password2" onkeyup="equalCheck()" required/><br>
                            <input type="text" placeholder="City" name="city"/><br>
                            <input type="tel" placeholder="Phone" name="phone"/><br>
                            <input type="email" placeholder="E-Mail" name="email" id="mail" onblur="mailCheck()" required/><br>
                            <input type="submit" value="Sign Up" />
                        </form>
                        <!--<p>Login with: <a href="#">Facebook</a> <a href="#">Google +</a></p>-->
                        <a href="/CardGame/login.html">Already registered? Sign in now.</a>
                    </fieldset>
                </div>
            </div>
            <div class="form2">
                <h2 style="text-align: center">Notice</h2>
                <div><b>- Do not use simple passwords and logins.
                        <br>- All fields are required!<br>
                        - If you filled the form correctly, you will receive a mail with a login and password</b></div><br><br>
                <div style="font-size: 22px; text-align: center"><span id="logResult"/></div>
                <div style="font-size: 22px; text-align: center"><span id="passResult"/></div><br>
                <div style="font-size: 22px; text-align: center"><span id="equalResult"/></div>
                <div style="font-size: 22px; text-align: center"><span id="mailResult"/></div>
            </div>
            <div style="clear: both"></div>
        </div>
    </body>
</html>