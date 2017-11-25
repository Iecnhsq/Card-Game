<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Card Page</title>
        <style>
            <%@include file="/Style/card.css" %>
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
            <form action="/CardGame/account.html">
                <input id="bl1" class="topButton" type="submit" value="${login}" style="float: left; margin-top: 10px"/>
            </form>
            <form action="/CardGame/classs.html">
                <input id="bl2" class="topButton" type="submit" value="${user.getClasss()}" style="float: left; margin-top: 10px;margin-left: 10px"/>
            </form>
            <a href='main.html?exit=true'>
                <input  id="blEx" class="topButton" type='submit' value='Exit' style="float: left; margin-top: 10px;margin-left: 10px"/>
            </a>
            <div style="clear: both"></div>
        </div>
        <div class="head" style="margin-bottom: 10px; margin-top: 10px">
            <p>Select a deck of cards and a hero. Maximum of cards in a deck of 10. Carefully choose the cards in order not to lose =)</p>
            <div class="class">
                <div style="text-align: center">
                    <div style="clear: both"></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=dk"><img src="Image/icon-deathknight.png"/><br>Death Knight</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=dh"><img src="Image/icon-demonhunter.png"/><br>Demon Hunter</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=dr"><img src="Image/icon-druid.png"/><br>Druid</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=hu"><img src="Image/icon-hunter.png"/><br>Hunter</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=ma"><img src="Image/icon-mage.png"/><br>Mage</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=mo"><img src="Image/icon-monk.png"/><br>Monk</a></div>
                    <div style="clear: both"></div>
                </div>
                <div style="text-align: center">
                    <div style="clear: both"></div>
                    <div style="float: left; margin: 5px 10px 5px 20px"><a href="class.html?idc=pa"><img src="Image/icon-paladin.png"/><br>Paladin</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=pr"><img src="Image/icon-priest.png"/><br>Priest</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=ro"><img src="Image/icon-rogue.png"/><br>Rogue</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=sh"><img src="Image/icon-shaman.png"/><br>Shaman</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=wl"><img src="Image/icon-warlock.png"/><br>Warlock</a></div>
                    <div style="float: left; margin: 5px 10px 5px 10px"><a href="class.html?idc=wr"><img src="Image/icon-warrior.png"/><br>Warrior</a></div>
                    <div style="clear: both"></div>
                </div>
            </div>
        </div>
        <div class="card" style="margin-bottom: 10px; margin-top: 10px">
            <div style="clear: both"></div>
            <div class="allCards">
                <c:forEach items="${cards}" var="c">
                    <a href="card.html?id=${c.id}">
                        <div class="t1"  >
                            <form action="http://localhost:8084/CardGame/">
                                <b><div title="Name" id="name">Name</div>
                                <div title="Level" id="lvl">${c.level}</div>
                                <div title="Damage" id="dmg">${c.damage}</div>
                                <div title="Health Point" id="HP">${c.health}</div></b>
                                <div title="Attributs" id="attribute">
                                    <c:if test="${c.taunt}">
                                        <span> taunt </span>
                                    </c:if>
                                    <c:if test="${c.imun}">
                                        <span> imun </span>
                                    </c:if>
                                    <c:if test="${c.shield} ">
                                        <span> shield </span>
                                    </c:if>
                                    <c:if test="${c.charge}">
                                        <span> charge </span>
                                    </c:if>
                                    <c:if test="${c.poison}">
                                        <span> poison </span>
                                    </c:if>
                                </div>
                            </form>
                        </div>
                    </a>
                </c:forEach>
            </div>
            <div class="deckCardscard">
                <c:forEach items="${card}" var="c">
                    <a href="card.html?id=-${c.id}">
                        <form action="http://localhost:8084/CardGame/">
                            <div class="t2"  >
                                <form action="http://localhost:8084/CardGame/">
                                    <b><div title="Name" id="name">Name</div>
                                    <div title="Level" id="lvl">${c.level}</div>
                                    <div title="Damage" id="dmg">${c.damage}</div>
                                    <div title="Health Point" id="HP">${c.health}</div></b>
                                    <div title="Attributs" id="attribute">
                                        <c:if test="${c.taunt}">
                                            <span> taunt </span>
                                        </c:if>
                                        <c:if test="${c.imun}">
                                            <span> imun </span>
                                        </c:if>
                                        <c:if test="${c.shield} ">
                                            <span> shield </span>
                                        </c:if>
                                        <c:if test="${c.charge}">
                                            <span> charge </span>
                                        </c:if>
                                        <c:if test="${c.poison}">
                                            <span> poison </span>
                                        </c:if>
                                    </div>
                                </form>
                            </div>
                        </form>
                    </a>
                </c:forEach>
                <form action="/CardGame/main.html" style="margin-left: 45px">
                    <input class="topButton" type="submit" value="Get Cards">
                </form>
            </div>
            <div style="clear: both"></div>
        </div>
    </body>
</html>
