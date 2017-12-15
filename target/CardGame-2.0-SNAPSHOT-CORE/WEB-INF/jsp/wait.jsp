<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Wait Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <style><%@include file="/src/style/common.css" %></style>
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
            function playAudio() {
                var myAudio = new Audio();
                myAudio.src = "src/sound/click.mp3";
                myAudio.autoplay = true;
            }
            function refresh() {
                window.location.href = "http://localhost:8084/CardGame/wait.html";
            }
            setInterval("refresh()", 5000);
        </script>
        <div style="margin: 100px auto; width: 350px">
            <div class="form1">
                <h1 style="text-align: center">Waiting for battles...</h1>
                <a class="onlineT">${ifNo} ${pOnline} !</a>
                <p>
                    Who expects: <b>${login}</b><br>
                    Your class: <b>${classs}</b><br>
                    Your level: <b>${level}</b>
                <p style="text-align: center">
                    <a href="main.html?exit=true" onmousedown="playAudio();">You want to quit?</a>&emsp; | &emsp;
                    <a href="main.html" onmousedown="playAudio();">Back to Main</a>
                <p>
                <div id="refresh">
                    <form action="wait.html">
                        <input id="refresh" type="submit" value="Refresh" onclick="playAudio();"/>
                    </form>
                </div>
                </p>
                </p>
                </p>
            </div>
        </div>                
    </body>
</html>
