<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Recovery Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
        <style><%@include file="/src/style/common.css" %></style>
    </head>
    <body>
        <script type="text/javascript">
            function checkRec() {
                $.ajax({
                    url: "checkrec1.html",
                    data: ({login: $('#log').val(), email: $('#email').val()}),
                    success: function (data) {
                        $('#resultRec1').html(data);
                    }
                });
            }
            function playAudio() {
                var myAudio = new Audio();
                myAudio.src = "src/sound/click.mp3";
                myAudio.autoplay = true;
            }
        </script>
        <div style="margin: 100px auto; width: 450px">
            <div class="form1">
                <h1 style="text-align: center">Password Recovery</h1>
                <p style="text-align: justify">We just sent you to E-mail letter with validation code. Please enter it in the box below.</p>
                <!-- <div style="font-size: 22px; text-align: center"><span id="resultRec1"/></div> -->
                <form action="recovery.html" method="POST">
                    <fieldset>
                        <input type="text" placeholder="Code from E-mail" name="answer" required /><br>
                        <input type="submit" value="Apply" onclick="playAudio();"/>
                    </fieldset>
                </form>
                <p style="text-align: center"><a href="main.html" onmousedown="playAudio();">Back to main</a></p>
            </div>
        </div>
    </body>
</html>
