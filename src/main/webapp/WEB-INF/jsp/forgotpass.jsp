<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Password Recovery Page</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
        <style type="text/css" media="all">
            body {
                background-image: url(Image/fon.png);
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
            .all {
                width: 1024px;
                margin: auto;
                border-radius: 8px;
                background-color: rgba(84,82,71,.5);
                color: white;
                font: 14pt Arial, Helvetica, sans-serif;
                padding: 0px 0px 30px 0px;
                text-align: center;
            }
            a {
                text-decoration: none;
                color: white;
                padding: 10px;
                background-color: #545247;
                border-radius: 4px;
            }
            a:hover {
                color: gainsboro;
            }
            fieldset {
                width: 400px;
                text-align: center;
                margin: auto;
                border: 2px #A9A9A9 solid;
                border-radius: 8px;
                background-color: rgba(84,82,71,.5);
            }
            input {
                padding: 5px 5px 5px 10px;
                border: 1px #52500d solid;
                margin: 2px;
            }
            input[type="submit"] {
                background-color: #545247;
                color: ghostwhite;
            }
            .b1 {
                padding: 10px;
                border-radius: 4px;             
            }
            p {
                padding: 10px;
                border-radius: 8px;
            }
            .d{
                width: 400px;
                margin: 0px auto;
            }
        </style>
    </head>
    <body>
        <script type="text/javascript">
            function checkRec() {
                $.ajax({
                    url: "checkrec.html",
                    data: ({login: $('#log').val(), email: $('#email').val()}),
                    success: function (data) {
                        $('#resultRec').html(data);
                    }
                });
            }
        </script>
        <div class="all">
            <h1><b>Password Recovery on Card Game</b></h1><br>
            <p>If you have forgotten your password, please enter your valid login and E-mail.</p>
            <div class="d" style="margin-bottom: 10px"><span id="resultRec"/></div>
            <form action="/CardGame/forgotpass.html" method="POST">
                <fieldset>
                    <input type="text" size="40" placeholder="Login" name="login" id="log" required />
                    <input type="email" size="40" placeholder="Enter Your E-mail" name="mail" id="email" onblur="checkRec()" required /><br>
                    <input type="submit" value="Recovery" class="b1"/>
                </fieldset>
            </form><br><br>
            <a href="/CardGame/main.html">Back to main</a>
        </div>
    </body>
</html>
