<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Support Page</title>
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
                padding: 5px;
                border: 1px #52500d solid;
                margin: 2px;
            }
            input[type="submit"] {
                background-color: #545247;
                color: ghostwhite;
                font-size: 14pt;
            }
            textarea {
                resize: none;
                margin: 2px;
            }
            .b1 {
                padding: 10px;
                border-radius: 4px;             
            }
            .d{
                width: 400px;
                background: none;
                margin: 0px auto;
            }
        </style>
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
        </script>
        <div class="all">
            <h1><b>Write a message to the support team</b></h1><br>
            <p style="text-align: justify; padding: 0px 60px 0px 60px">
                If you have any problems with the game, write to us. Specify the name 
                of the problem and briefly describe its essence.<br>
                We will try to solve it in the shortest possible time.
            </p>
            <div class="d" style="margin-bottom: 10px"><span id="logResultSupp"/></div>
            <form action="support.html" method="POST">
                <fieldset>
                    <input type="text" size="40" placeholder="Your problem" required name="err"/>
                    <input type="text" size="40" placeholder="Subject" required name="sub"/>
                    <input type="text" size="40" placeholder="Your Login" name="log" id="login" onblur="loginCheckSupp()" required/>
                    <textarea cols="42" rows="10" required name="mess" maxlength="250" placeholder="Enter Your message..."></textarea><br>             
                    <input type="submit" value="Send" class="b1"/>
                </fieldset>
            </form><br><br>
            <a href="/CardGame/main.html">Back to main</a>
        </div>
    </body>
</html>
