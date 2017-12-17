<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Battle Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <style>
            <%@include file="/src/style/battle.css" %>
            <%@include file="/src/style/common.css" %>
        </style>
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <audio src="src/sound/legends_of_azeroth.mp3" autoplay loop></audio>
            <c:if test="${p1Logged}">
            <div class="all">
                <div class="hero_1">
                    <div style="clear: both;"></div>
                    <div class="cardset_1"><img src="src/img/S_b.png" title="${deckP2.size()}"/></div>
                    <!--Карты соперника если мы плеер 1-->
                    <div class="cards_1">
                        <c:if test="${!p2HasTaunt or p1HeroPowerSelected}">
                            <c:forEach items="${onTableP2}" var="c">
                                <c:if test="${p1turn and (p1AttackCardId!=null or p1HeroPowerSelected)}"> 
                                    <a href="battle.html?id=-${c.id}">
                                    </c:if>
                                    <%@include file="../jspf/cardModel.jspf" %>
                                    <c:if test="${p1turn and (p1AttackCardId!=null or p1HeroPowerSelected)}">
                                    </a> 
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${p2HasTaunt and !p1HeroPowerSelected}">
                            <c:forEach items="${onTableP2}" var="c">
                                <c:if test="${p1turn and p1AttackCardId!=null and c.taunt}"> 
                                    <a href="battle.html?id=-${c.id}">
                                    </c:if>
                                    <%@include file="../jspf/cardModel.jspf" %>
                                    <c:if test="${p1turn and p1AttackCardId!=null and c.taunt}">
                                    </a> 
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>
                    <!-- герой противника если мы плеер 1 -->
                    <c:if test="${(p1AttackCardId!=null and !p2HasTaunt) or p1HeroPowerSelected}">   
                        <a href="battle.html?id=0">
                        </c:if>
                        <div div class="opp">
                            <img src="src/img/ma_b.jpg"/><br />
                            <b>${class1}</b><br>
                            <b>${p2}</b>
                            <br><font id="Health">Health: ${Char2Health}</font> 
                            <font id="Mana">Mana: ${Char2Mana}</font>
                        </div>
                        <c:if test="${(p1AttackCardId!=null and !p2HasTaunt) or p1HeroPowerSelected}">
                        </a>
                    </c:if>
                    <div style="clear: both;"></div>
                </div>
                <!--кнопка пропуска хода, или рефреш-->
                <div class="turn">
                    <c:if test="${p1turn}">
                        <div>
                            <a href="battle.html?putCard=1">
                                <input type="button" value="Put Card"/>
                            </a>
                            <audio id="player" src="src/sound/end_new_turn.mp3"></audio>
                            <a href="battle.html?end=1" onclick="document.getElementById('player').play()">
                                <input type="button" value="End of turn" onclick="document.getElementById('player').play()"/>
                            </a>
                        </div>
                    </c:if>
                    <c:if test="${p2turn}">
                        <audio src="src/sound/metronom.mp3"></audio>
                        <input type="button" value="Opponent turn"/>
                        <script type='text/javascript'>
                            function refresh() {
                                window.location.href = "http://localhost:8084/CardGame/battle.html";
                            }
                            setInterval("refresh()", 2000);
                        </script>
                    </c:if>
                </div>
                <div class="hero_2">
                    <div style="clear: both;"></div>
                    <!-- Наш герой если мы плеер 1 -->
                    <div class="me">
                        <img src="src/img/mo_b.jpg"/><br />
                        <b>${class2}</b><br>
                        <b>${p1}</b><br>
                        <font id="Health">Health: ${Char1Health}</font> 
                        <font id="Mana">Mana: ${Char1Mana}</font>
                    </div>
                    <c:if test="${!p1Attacked}">
                        <div class="power">
                            <a href="battle.html?heroAttack=class" title="Hero Power"><img src="src/img/hp_b.png"/></a>
                        </div>
                    </c:if>
                    <!--Наши карты на столе если мы плеер 1-->
                    <div class="cards_2">
                        <div style="clear: both;"></div>
                        <div>
                            <c:forEach items="${onTableP1}" var="c">
                                <c:if test="${p1turn}">   
                                    <a href="battle.html?id=${c.id}">
                                    </c:if>
                                    <!--блок карты, 
                                    добавлен id карты на случий если карта выбрана по id нужно добавить карте крассную рамку-->
                                    <div class="cardmodel" <c:if test="${c.id == p1AttackCardId}">id="redPadd" title="Discription card"</c:if>>
                                            <audio src="src/sound/click.mp3"></audio>
                                            <div title="Name" id="name">${c.name}</div>
                                        <div title="Level" id="lvl">${c.level}</div>
                                        <div title="Damage" id="dmg">${c.damage}</div>
                                        <div title="Health Point" id="HP">${c.health}</div>
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
                                    </div>
                                    <c:if test="${p1turn}">   
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                        <!--Наши карты на в руке если мы плеер 1-->
                        <div class="me_on_hend" title="Cards on hend">
                            <c:forEach items="${onHandP1}" var="c">
                                <a href="battle.html?id=${c.id}">
                                    <%@include file="../jspf/cardModel.jspf" %>
                                </a>
                            </c:forEach>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="cardset_2" ><img src="src/img/S_b.png" title="${deckP1.size()}"/></div>
                </div>
                <div style="clear: both;"></div>
            </div>
        </c:if>



        <c:if test="${p2Logged}">
            <div class="all">
                <div class="hero_1">
                    <div style="clear: both;"></div>
                    <div class="cardset_1"><img src="src/img/S_b.png" title="${deckP1.size()}"/></div>
                    <!--Карты соперника если мы плеер 2-->
                    <div class="cards_1">
                        <c:if test="${!p1HasTaunt or p2HeroPowerSelected}">
                            <c:forEach items="${onTableP1}" var="c">
                                <c:if test="${p2turn and (p2AttackCardId!=null or p2HeroPowerSelected)}">   
                                    <a href="battle.html?id=-${c.id}">
                                    </c:if>
                                    <%@include file="../jspf/cardModel.jspf" %>
                                    <c:if test="${p2turn and (p2AttackCardId!=null or p2HeroPowerSelected)}">
                                    </a>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${p1HasTaunt and !p2HeroPowerSelected}">
                            <c:forEach items="${onTableP1}" var="c">
                                <c:if test="${p2turn and p2AttackCardId!=null and c.taunt}">   
                                    <a href="battle.html?id=-${c.id}">
                                    </c:if>
                                    <%@include file="../jspf/cardModel.jspf" %>
                                    <c:if test="${p2turn and p2AttackCardId!=null and c.taunt}">
                                    </a>
                                </c:if>
                            </c:forEach>
                        </c:if>
                    </div>
                    <!-- герой противника если мы плеер 2 -->
                    <c:if test="${(p2AttackCardId!=null and !p1HasTaunt) or p2HeroPowerSelected}">   
                        <a href="battle.html?id=0">
                        </c:if>
                        <div div class="opp">
                            <img src="src/img/mo_b.jpg"/><br />
                            <b>${class2}</b><br>
                            <b>${p1}</b>
                            <br><font id="Health">Health: ${Char1Health}</font> 
                            <font id="Mana">Mana: ${Char1Mana}</font>
                        </div>
                        <c:if test="${(p2AttackCardId!=null and !p1HasTaunt) or p2HeroPowerSelected}">   
                        </a>
                    </c:if>
                    <div style="clear: both;"></div>
                </div>
                <!--кнопка пропуска хода, или рефреш-->
                <div class="turn">
                    <c:if test="${p2turn}">
                        <a href="battle.html?putCard=1">
                            <input type="button" value="Put Card"/>
                        </a>
                        <a href="battle.html?end=1">
                            <input type="button" value="End of turn"/>
                        </a>
                    </c:if>
                    <c:if test="${p1turn}">
                        <input type="button" value="Opponent turn"/>
                        <script type='text/javascript'>
                            function refresh() {
                                window.location.href = "http://localhost:8084/CardGame/battle.html";
                            }
                            setInterval("refresh()", 1000);
                        </script>
                    </c:if>
                </div>
                <div class="hero_2">
                    <div style="clear: both;"></div>
                    <!-- Наш герой если мы плеер 2-->
                    <div class="me">
                        <img src="src/img/ma_b.jpg"/><br />
                        <b>${class1}</b><br>
                        <b>${p2}</b>
                        <br><font id="Health">Health: ${Char2Health}</font> 
                        <font id="Mana">Mana: ${Char2Mana}</font>
                    </div>
                    <c:if test="${!p2Attacked}">
                        <div class="power">
                            <a href="battle.html?heroAttack=class" title="Hero Power"><img src="src/img/hp_b.png"/></a>
                        </div>
                    </c:if>
                    <!--Наши карты на столе если мы плеер 2-->
                    <div class="cards_2">
                        <div style="clear: both;"></div>
                        <div>
                            <c:forEach items="${onTableP2}" var="c"> 
                                <a href="battle.html?id=${c.id}">
                                    <!--блок карты, 
                                    добавлен id карты на случий если карта выбрана по id нужно добавить карте крассную рамку-->
                                    <div class="cardmodel" <c:if test="${c.id == p2AttackCardId}">id="redPadd" title="Discription card"</c:if>>
                                            <audio src="src/sound/click.mp3"></audio>
                                            <div title="Name" id="name">${c.name}</div>
                                        <div title="Level" id="lvl">${c.level}</div>
                                        <div title="Damage" id="dmg">${c.damage}</div>
                                        <div title="Health Point" id="HP">${c.health}</div>
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
                                    </div>
                                </a>
                            </c:forEach>
                        </div>
                        <!--Наши карты на в руке если мы плеер 2-->
                        <div class="me_on_hend" title="Cards on hend">
                            <c:forEach items="${onHandP2}" var="c">
                                <a href="battle.html?id=${c.id}">
                                    <%@include file="../jspf/cardModel.jspf" %>
                                </a>
                            </c:forEach>
                        </div>
                        <div style="clear: both;"></div>
                    </div>
                    <div class="cardset_2" ><img src="src/img/S_b.png" title="${deckP2.size()}"/></div>
                </div>
                <div style="clear: both;"></div>
            </div>
        </c:if>
    </body>
</html>