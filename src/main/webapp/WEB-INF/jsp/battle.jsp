<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Battle Page</title>
        <style><%@include file="/Style/battle.css" %></style>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
    </head>
    <body>
        <c:if test="${p1Logged}">
            <div class="all">
                <div class="hero_1">
                    <div style="clear: both;"></div>
                    <div class="cardset_1"><img src="Image/S_b.png"/></div>
                    <!--Карты соперника если мы плеер 1-->
                    <div class="cards_1">
                        <c:forEach items="${onTableP2}" var="c">
                            <c:if test="${p1turn and p1AttackCardId!=null}"> 
                                <a href="battle.html?id=-${c.id}">
                                </c:if>
                                <!--блок карты-->
                                <div class="cardmodel">
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
                                <c:if test="${p1turn and p1AttackCardId!=null}">
                                </a> 
                            </c:if>
                        </c:forEach>
                    </div>
                    <!-- герой противника если мы плеер 1 -->
                    <div class="opp">
                        <img src="Image/ma_b.jpg"/><br />
                        <b>${class1}</b><br>
                        <c:if test="${p1AttackCardId!=null}">   
                            <a href="battle.html?id=0"><b>${p2}</b></a>
                                </c:if>
                                <c:if test="${p1AttackCardId==null}">   
                            <b>${p2}</b>
                        </c:if>
                        <br><font id="Health">Health: ${Char2Health}</font> 
                        <font id="Mana">Mana: ${Char2Mana}</font>
                    </div>
                    <div style="clear: both;"></div>
                </div>
                <!--кнопка пропуска хода, или рефреш-->
                <div class="turn">
                    <c:if test="${p1turn}">
                        <a href="battle.html?end=1">
                            <input type="button" value="End of turn"/>
                        </a>
                    </c:if>
                    <c:if test="${p2turn}">
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
                    <!-- Наш герой если мы плеер 1 -->
                    <div class="me">
                        <img src="Image/mo_b.jpg"/><br />
                        <b>${class2}</b><br>
                        <b>${p1}</b><br>
                        <font id="Health">Health: ${Char1Health}</font> 
                        <font id="Mana">Mana: ${Char1Mana}</font>
                    </div>
                    <c:if test="${!p1Attacked}">
                        <div class="power">
                            <a href="battle.html?heroAttack=class" title="Hero Power"><img src="Image/hp_b.png"/></a>
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
                                    <div class="cardmodel">
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
                        <div style="clear: both;"></div>
                    </div>
                    <div class="cardset_2" ><img src="Image/S_b.png"/></div>
                </div>
                <div style="clear: both;"></div>
            </div>
        </c:if>

        <c:if test="${p2Logged}">
            <div class="all">
                <div class="hero_1">
                    <div style="clear: both;"></div>
                    <div class="cardset_1"><img src="Image/S_b.png"/></div>
                    <!--Карты соперника если мы плеер 2-->
                    <div class="cards_1">
                        <c:forEach items="${onTableP1}" var="c">
                            <c:if test="${p2turn and p2AttackCardId!=null}">   
                                <a href="battle.html?id=-${c.id}">
                                </c:if>
                                <!--блок карты-->
                                <div class="cardmodel">
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
                                <c:if test="${p2turn and p2AttackCardId!=null}">
                                </a>
                            </c:if>
                        </c:forEach>
                    </div>
                    <!-- герой противника если мы плеер 2 -->
                    <div div class="opp">
                        <img src="Image/mo_b.jpg"/><br />
                        <b>${class2}</b><br>
                        <c:if test="${p2AttackCardId!=null}">   
                            <a href="battle.html?id=0"><b>${p1}</b></a>
                                </c:if>
                                <c:if test="${p2AttackCardId==null}">
                            <b>${p1}</b>
                        </c:if>
                        <br><font id="Health">Health: ${Char1Health}</font> 
                        <font id="Mana">Mana: ${Char1Mana}</font>
                    </div>
                    <div style="clear: both;"></div>
                </div>
                <!--кнопка пропуска хода, или рефреш-->
                <div class="turn">
                    <c:if test="${p2turn}">
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
                        <img src="Image/ma_b.jpg"/><br />
                        <b>${class1}</b><br>
                        <b>${p2}</b>
                        <br><font id="Health">Health: ${Char2Health}</font> 
                        <font id="Mana">Mana: ${Char2Mana}</font>
                    </div>
                    <c:if test="${!p2Attacked}">
                        <div class="power">
                            <a href="battle.html?heroAttack=class" title="Hero Power"><img src="Image/hp_b.png"/></a>
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
                                    <div class="cardmodel">
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
                        <div style="clear: both;"></div>
                    </div>
                    <div class="cardset_2" ><img src="Image/S_b.png"/></div>
                </div>
                <div style="clear: both;"></div>
            </div>
        </c:if>
    </body>
</html>
