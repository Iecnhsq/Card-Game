<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - News Page</title>
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
        </style>
    </head>
    <body>
        <div>
            <h1><b>News on Card Game</b></h1><br>
            <c:forEach items="${allnews}" var="a">
                <div style="width: 400px; margin: 20px auto; border: 1px solid bisque; padding: 10px">
                    <p style="text-align: center">
                        <b style="color: yellow; font-size: 12pt">${a.timein} by ${a.author}</b><br>
                    <h3 style="text-align: center">${a.subject}</h3>
                    </p>
                    <p style="text-align: justify; color: gainsboro">
                        ${a.text}
                    </p>
                </div><br>
            </c:forEach>
            <div style="width: 400px; margin: auto; border: 1px solid black; border-radius: 5px; padding: 10px; text-align: justify">
                <p style="text-align: center">
                    <b style="color: yellow; font-size: 12pt">2017.11.17 in 20:00 by Admin</b><br>
                <h3 style="text-align: center">New Global Update</h3>
                </p>
                <p style="text-align: justify; color: gainsboro">
                <ul>
                    <li>New UI</li>
                    <li>New Hero class and cards set</li>
                    <li>New system fight</li>
                    <li>Improved graphics</li>
                    <li>24/7 Support</li>
                </ul>                  
                </p>
            </div><br><br>
            <a href="/CardGame/main.html">Back to main</a>
        </div>
    </body>
</html>
