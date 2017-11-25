<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Recovery Page</title>
        <style type="text/css" media="all">
            body {
                background-image: url(Image/fon.png);
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
            div {
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
            fieldset {
                width: 400px;
                text-align: center;
                margin: auto;
                border: 2px #A9A9A9 solid;
                border-radius: 8px;
                background-color: rgba(84,82,71,.5);
            }
        </style>
    </head>
    <body>
        <div>
            <h1><b>Password Recovery on Card Game</b></h1><br>
            <p>We just sent you to E-mail letter with validation code. Please enter it in the box below.</p>
            <form action="/CardGame/recovery.html" method="POST">
                <fieldset>
                    <input type="text" placeholder="Code from E-mail" name="answer" size="40" required /><br>
                    <input type="submit" value="Apply" class="b1"/>
                </fieldset>
            </form><br><br>
            <a href="/CardGame/main.html">Back to main</a>
        </div>
    </body>
</html>
