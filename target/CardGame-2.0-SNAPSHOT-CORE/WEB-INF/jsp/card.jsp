<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Card Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <style>
            <%@include file="/src/style/card.css" %>
            <%@include file="/src/style/classstyle.css" %>
            <%@include file="/src/style/common.css" %>
        </style>
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
                    <form action="account.html">
                        <input type="submit" value="${login}" onclick="playAudio();"/>
                    </form>
                    <form action="classs.html">
                        <input type="submit" value="${user.getClasss()}" onclick="playAudio();"/>
                    </form>
                    <a href='main.html?exit=true'>
                        <input  type='submit' value='Exit' onclick="playAudio();"/>
                    </a>
                </div>
            </div>
            <div style="clear: both"></div>
            <div style="clear: both"></div>
        </div>
        <div class="class_head" style="margin-bottom: 10px; margin-top: 10px">
            <p>Select a deck of cards and a hero. Maximum of cards in a deck of 10. Carefully 
                choose the cards in order not to lose =)
            </p>
            <div style="margin: 0px auto; width: 700px; font-size: 14pt">
                <div style="clear: both"></div>
                <div class="class_set" <c:if test="${heroClass.equals("Hunter")}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="card.html?idclass=Hunter"><img src="src/img/icon-hunter.png"/><br>Hunter</a></div>
                <div class="class_set" <c:if test="${heroClass.equals("Mage")}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="card.html?idclass=Mage"><img src="src/img/icon-mage.png"/><br>Mage</a></div>         
                <div class="class_set" <c:if test="${heroClass.equals("Priest")}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="card.html?idclass=Priest"><img src="src/img/icon-priest.png"/><br>Priest</a></div>
                <div class="class_set" <c:if test="${heroClass.equals("Thief")}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="card.html?idclass=Thief"><img src="src/img/icon-rogue.png"/><br>Thief</a></div>
                <div class="class_set" <c:if test="${heroClass.equals("Shaman")}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="card.html?idclass=Shaman"><img src="src/img/icon-shaman.png"/><br>Shaman</a></div> 
                <div class="class_set" <c:if test="${heroClass.equals("Warrior")}">style="border: 2px solid firebrick; border-radius: 10px"</c:if>><a href="card.html?idclass=Warrior"><img src="src/img/icon-warrior.png"/><br>Warrior</a></div>
                    <div style="clear: both"></div>
                </div>
            <%@include file="../jspf/classCardSet.jspf" %>
        </div>
        <div class="card" style="margin-bottom: 10px; margin-top: 10px">
            <div style="clear: both"></div>
            <div class="allCards">
                <div style="clear: both"></div>
                <div>
                    <c:forEach items="${level0Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level1Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level2Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level3Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level4Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level5Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level6Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level7Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level8Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level9Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
                <div style="clear: both"></div><hr>
                <div>
                    <c:forEach items="${level10Cards}" var="c">
                        <%@include file="../jspf/cardLevelSet.jspf" %>
                    </c:forEach>
                </div>
            </div>
            <div class="deckCardscard">
                <c:forEach items="${deckCards}" var="c">
                    <a href="card.html?id=-${c.id}">
                        <form action="http://localhost:8084/CardGame/">
                            <div class="t2"  >
                                <form action="http://localhost:8084/CardGame/">
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
                                    </form>
                                </div>
                            </form>
                        </a>
                </c:forEach>
                <a href="/CardGame/card.html?getCards=1" style="margin-left: 45px">
                    <input type="submit" value="Get Cards">
                </a>
            </div>
            <div style="clear: both"></div>
        </div>
    </body>
</html>
