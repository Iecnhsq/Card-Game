<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="author" content="IT Academy CONTACT 2017" />
        <meta name="description" content="Card Game" />
        <meta name="keywords" content="Card, Game, Card Game" />
        <title>Card Game</title>
        <style>
            <%@include file="/src/style/main.css" %>
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
        <div class="head" style="margin-top: 10px;">
            <div style="clear: both"></div>
            <div style="float: left">
                <img src="src/img/logo.png" style="margin-right: 10px"/>
            </div>
            <div style="float: left; margin: 40px 0px 0px 60px; text-align: center">
                <h3>Server Status:&emsp;<font style="color: red">OFF</font></h3>
                <a class ="onlineT">${ifNo} ${pOnline} !</a>
                <p><b>${outDate}</b></p>
            </div>
            <div style="float: right; margin-top: 15px; margin-right: 30px; text-align: center">
                <a href="#" onclick="playAudio();">RUS</a>&emsp;|&emsp;<a href="#">EN</a>&emsp;|&emsp;<a href="#">UA</a><br>
                <div style="margin-top: 25px">
                    <c:if test="${!isLogin}">
                        <form action="login.html">
                            <input type="submit" value="Sign In" onclick="playAudio();"/>
                        </form>
                        <form action="register.html">
                            <input type="submit" value="Sign Up" onclick="playAudio();"/>
                        </form>
                    </c:if>
                    <c:if test="${isLogin}">
                        <form action="account.html">
                            <input type="submit" value="${login}" onclick="playAudio();"/>
                        </form>
                        <form action="classs.html">
                            <input type="submit" value="${classs}" onclick="playAudio();"/>
                        </form>
                        <a href="main.html?exit=true">
                            <input type="submit" value="Exit" onclick="playAudio();"/>
                        </a>
                        <c:if test="${isAdmP}">
                            <form action="admin.html">
                                <input type="submit" value="Admin Panel" onclick="playAudio();"/>
                            </form>
                        </c:if>
                    </c:if>
                </div>
            </div>
            <div style="clear: both"></div>
            <div style="clear: both"></div>
            <div class="mainmenu" style="margin-top: 10px">
                <c:if test="${!isLogin}">
                    <a href="main.html?page=about">About</a>&emsp;|&emsp;<a href="main.html?page=news">News</a>&emsp;|&emsp;<a href="support.html">Suppot</a>
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

                <!-- zdes' -->
                <c:if test="${isM}">
                    <%@include file="../jspf/main.jspf" %>
                </c:if>
                <c:if test="${isA}">
                    <%@include file="../jspf/about.jspf" %>
                </c:if>
                <c:if test="${isN}">
                    <%@include file="../jspf/news.jspf" %>
                </c:if>
                <!-- -->

            </c:if>
            <c:forEach items="${card}" var="c">
                <div class="cardBlocks" title="LVL: ${c.level}; Damage: ${c.damage}; Health: ${c.health}; Name: ${c.name}">
                    <b><div id="name">${c.name}</div>
                        <div id="lvl">${c.level}</div>
                        <div id="dmg">${c.damage}</div>
                        <div id="HP">${c.health}</div>
                    </b>
                    <div id="attribute">
                        <c:if test="${c.taunt}"><img src="src/img/ars/Ability_Physical_Taunt.ico" title="taunt"/></c:if>
                        <c:if test="${c.imun}"><img src="src/img/ars/Spell_Nature_ProtectionformNature.ico" title="imun"/></c:if>
                        <c:if test="${c.shield}"><img src="src/img/ars/Ability_Warrior_ShieldMastery.ico" title="shield"/></c:if>
                        <c:if test="${c.charge}"><img src="src/img/ars/Ability_Warrior_Charge.ico" title="charge"/></c:if>
                        <c:if test="${c.poison}"><img src="src/img/ars/Ability_Creature_Poison_02.ico" title="poison"/></c:if>
                        </div>
                    </div>
            </c:forEach>
            <div style="clear: both"></div>
            <div class="mainButton" style="margin: 0px auto; width: 430px">
                <c:if test="${isLogin}">
                    <form action="card.html" style="float: left">
                        <input id="cD" type="submit" value="Change Deck" style=" margin-top: 100px"/>
                    </form>
                    <c:if test="${card.size()>=10}">
                        <form action="wait.html" style="float: left">
                            <input id="sO" type="submit" value="Search opponent"/>
                        </form>
                    </c:if>
                    <c:if test="${card.size()<10}">
                        <input type="button" id="sO1" value="Search opponent" style="float: left; margin-left: 220px; margin-top: -50px"/>
                    </c:if>
                </c:if>
            </div>
            <div style="clear: both"></div>
        </div>
        <footer>
            <%@include file="../jspf/footer.jspf" %>
        </footer>
    </body>
</html>
