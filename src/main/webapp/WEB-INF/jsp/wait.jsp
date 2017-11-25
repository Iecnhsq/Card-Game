<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Wait Page</title>
        <style><%@include file="/Style/wait.css" %></style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <div class="head" style="margin-top: 10px;">
            <div style="clear: both"></div>
            <div style="float: left"><a class ="textTop">Card game</a></div>
            <div style="float: left; margin-top: 8px; margin-left: 260px"><a class ="onlineT">${ifNo} ${pOnline} !</a></div>
            <div style="float: right; margin-top: 15px;">Select language <a href="#">RUS</a> | <a href="#">EN</a></div>
            <div style="clear: both"></div>
            <div style="clear: both"></div>
            <form action="/CardGame/account.html" style="float: left; margin-top: 10px">
                <input id="bl1" class="topButton" type="submit" value="${login}"/>
            </form>
            <form action="/CardGame/classs.html" style="float: left; margin-top: 10px; margin-left: 10px">
                <input id="bl2" class="topButton" type="submit" value="${classs}"/>
            </form>
            <a href='main.html?exit=true' style="float: left; margin-top: 10px; margin-left: 10px">
                <input  id="blEx" class="topButton" type='submit' value='Exit'/>
            </a>
            <a href='main.html' style="float: left; margin-top: 10px; margin-left: 10px">
                <input  id="blEx" class="topButton" type='submit' value='Back to Main'/>
            </a>
            <div id="refresh">
                <script type="text/javascript">
                    setInterval(function () {
                        $("#refresh").load("wait.html #refresh");
                    }, 5000);
                </script>
                <form action="/CardGame/wait.html" style="float: left; margin-top: 10px; margin-left: 10px">
                    <input id="refresh" class ="topButton" type="submit" value="Refresh"/>
                </form>
            </div>
            <div style="clear: both"></div>
        </div>                  
    </body>
</html>
