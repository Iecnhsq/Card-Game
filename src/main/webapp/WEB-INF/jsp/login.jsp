<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Login Page</title>
        <style><%@include file="/Style/login.css" %></style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
            function equalCheck() {
                $.ajax({
                    url: "equalscheck.html",
                    data: ({login: $('#log').val(), pass: $('#password').val()}),
                    success: function (data) {
                        $('#equalResult').html(data);
                    }
                });
            }
        </script>
        <div style="margin: 100px auto; width: 610px">
            <div style="clear: both"></div>
            <div class="form1">
                <h1 style="text-align: center">Authorization</h1>
                <fieldset>
                    <form action="/CardGame/login.html" method="POST">
                        <input type="text" placeholder="Login" name="login" id="log" required /><br>
                        <input type="password" placeholder="Password" name="pass" id="password" required onblur="equalCheck()"/><br>
                        <input type="submit" value="Sign in" />
                    </form>
                    <!--<p>Login with: <a href="#">Facebook</a> <a href="#">Google +</a></p>-->
                    <p><a href="/CardGame/register.html">Don't have an account? Sign up now.</a><br><br>
                        <a href="/CardGame/forgotpass.html">Forgot password?</a></p>
                </fieldset>
            </div>
            <div class="form2">
                <h2 style="text-align: center">Notice</h2>
                <div><b> - Always check our url-address<br><br>
                        <a href="#">!adress!</a></b></div><br><br>
                <div style="font-size: 22px; text-align: center"><span id="equalResult"/></div>
            </div>
            <div style="clear: both"></div>
        </div>
    </body>
</html>
