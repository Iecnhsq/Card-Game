<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Main Page</title>
        <style>
            <%@include file="/Style/main.css" %>
        </style>
    </head>
    <body>
        <div class="head" style="margin-top: 10px;">
            <div style="clear: both"></div>
            <div style="float: left"><a class ="textTop">Card game</a></div>
            <div style="float: left; margin-top: 8px; margin-left: 260px"><a class ="onlineT">${ifNo} ${pOnline} !</a></div>
            <div style="float: right; margin-top: 15px;">Select language <a href="#">RUS</a> | <a href="#">EN</a></div>
            <div style="clear: both"></div>
            <div style="clear: both"></div>
            <div class="bform">
                <c:if test="${!isLogin}">
                    <form action="/CardGame/login.html" style="float: left; margin-top: 10px">
                        <input id="b1" class="topButton" type="submit" value="Login"/>
                    </form>
                </c:if>
                <c:if test="${isLogin}">
                    <form action="/CardGame/account.html" style="float: left; margin-top: 10px">
                        <input id="bl1" class="topButton" type="submit" value="${login}"/>
                    </form>
                </c:if>
                <c:if test="${!isLogin}">
                    <form action="/CardGame/register.html" style="float: left; margin-top: 10px; margin-left: 10px">
                        <input id="b2" class="topButton" type="submit" value="Create account"/>
                    </form>
                </c:if>
                <c:if test="${isLogin}">
                    <form action="/CardGame/classs.html" style="float: left; margin-top: 10px; margin-left: 10px">
                        <input id="bl2" class="topButton" type="submit" value="${classs}"/>
                    </form>
                    <a href='main.html?exit=true' style="float: left; margin-top: 10px; margin-left: 10px">
                        <input  id="blEx" class="topButton" type='submit' value='Exit'/>
                    </a>
                    <c:if test="${isAdmP}">
                        <form action="/CardGame/admin.html" style="float: left; margin-top: 10px; margin-left: 113px">
                            <input id="b2" class="topButton" type="submit" value="Admin Panel"/>
                        </form>
                    </c:if>
                </c:if>
            </div>
            <div style="clear: both"></div>
            <div class="mainmenu" style="margin-top: 10px">
                <c:if test="${!isLogin}">
                    <a href="/CardGame/about.html">About</a> | <a href="/CardGame/news.html">News</a> | <a href="/CardGame/support.html">Suppot</a>
                </c:if>
                <c:if  test="${isLogin}">
                    <a> Registered : <b>${rDate}</b></a>&emsp;
                    <a> LVL : <b>${lvl}</b></a>&emsp;
                    <a> Points: <b>${pts}</b></a>&emsp;
                    <a> Money : <b>${mon}</b></a>&emsp;
                    <a> Class : <b>${classs}</b></a>
                </c:if>
            </div>
        </div>
        <div class ="mainDeck" style="margin-top: 10px">
            <c:if test="${!isLogin}">
                <h1 style="text-align: center"><b>HELLO AND WELLCOME!</b></h1>
                <p>
                    This is most popularity online card game like as Hearthstone.
                </p>
                <div style="width: 400px; margin: auto; border: 1px solid black; border-radius: 5px; padding: 10px">
                    <p style="text-align: center">
                        <b style="color: yellow; font-size: 12pt">2017.11.17 in 20:00 by Admin</b><br>
                    <h3 style="text-align: center">New Global Update</h3>
                    </p>
                    <p style="text-align: justify; color: gainsboro">
                    <ul>
                        <li>New UI</li>
                        <li>New Hero class and cards set</li>
                        <li>New system fight</li>
                        <li>And many new things...</li>
                    </ul>                  
                    </p>
                    <p style="text-align: center"><a href="/CardGame/news.html">Get more info by...</a></p>
                </div>
            </c:if>
            <c:forEach items="${card}" var="c">
                <a href="#" onclick="">
                    <div class="cardBlocks" title="LVL: ${c.level}; Damage: ${c.damage}; Health: ${c.health}; Name: ?">
                        <b><div id="name">Name</div>
                            <div id="lvl">${c.level}</div>
                            <div id="dmg">${c.damage}</div>
                            <div id="HP">${c.health}</div></b>
                    </div>
                </a>
            </c:forEach>
            <div style="clear: both"></div>
            <div class ="mainButton" style="margin-left: 300px">
                <c:if test="${isLogin}">
                    <form id= "1" action="/CardGame/card.html" style="float: left; margin-top: 20px">
                        <input id="cD" class ="topButton" type="submit" value="Change Deck"/>
                    </form>
                </c:if>
                <c:if test="${isLogin}">
                    <c:if test="${card.size()>=10}">
                        <form id= "2" action="/CardGame/wait.html" style="float: left; margin-top: 20px; margin-left: 10px">
                            <input id="sO" class ="topButton" type="submit" value="Search opponent"/>
                        </form>
                    </c:if>
                    <c:if test="${card.size()<10}">
                        <input type="button" id="sO1" class ="topButton" value="Search opponent" style="float: left; margin-top: 20px; margin-left: 10px;"/>
                    </c:if>
                </c:if>
            </div>
            <div style="clear: both"></div>
        </div>
        <footer>
            <%@include file="../jsp/footer.jspf" %>
        </footer>
    </body>
</html>
