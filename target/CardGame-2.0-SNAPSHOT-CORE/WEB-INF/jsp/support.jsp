<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Support Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <style><%@include file="/src/style/common.css" %></style>
    </head>
    <body>
        <script type="text/javascript">
            function loginCheckSupp() {
                $.ajax({
                    url: "logchecksupp.html",
                    data: ({login: $('#login').val()}),
                    success: function (data) {
                        $('#logResultSupp').html(data);
                    }
                });
            }
            function playAudio() {
                var myAudio = new Audio();
                myAudio.src = "src/sound/click.mp3";
                myAudio.autoplay = true;
            }
        </script>
        <div style="margin: 100px auto; width: 700px">
            <div class="form1">
                <h1 style="text-align: center"><b>Write a message to the support team</b></h1>
                <p style="text-align: justify">
                    If you have any problems with the game, write to us. Specify the name 
                    of the problem and briefly describe its essence.<br>
                    We will try to solve it in the shortest possible time.
                </p>
                <div style="font-size: 22px; text-align: center"><span id="logResultSupp"/></div>
                <form action="support.html" method="POST">
                    <fieldset>
                        <input type="text" placeholder="Your problem" required name="err"/><br>
                        <input type="text" placeholder="Subject" required name="sub"/><br>
                        <input type="text" placeholder="Your Login" name="log" id="login" onblur="loginCheckSupp()" required/><br>
                        <textarea required name="mess" maxlength="250" placeholder="Enter Your message..."></textarea><br>             
                        <input type="submit" value="Send" onclick="playAudio();"/>
                    </fieldset>
                </form>
                <p style="text-align: center"><a href="main.html" onmousedown="playAudio();">Back to main</a></p>
            </div>
        </div>
    </body>
</html>
