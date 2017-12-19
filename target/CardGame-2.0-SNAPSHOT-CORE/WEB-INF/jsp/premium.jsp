<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game</title>
        <style>
            <%@include file="/src/style/common.css" %>
        </style>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
            function playAudio() {
                var myAudio = new Audio;
                myAudio.src = "src/sound/click.mp3";
                myAudio.play();
            }
        </script>
        <h1>Hello World!</h1>
    </body>
</html>
