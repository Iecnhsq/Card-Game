<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/header.jspf" %>
<style>
    <%@include file="/src/style/card.css" %>
    <%@include file="/src/style/classstyle.css" %>
    <%@include file="/src/style/common.css" %>
    <%@include file="/src/style/style.css" %>
</style>
</head>
<body>
    <script type="text/javascript">
        function playAudio() {
            var myAudio = new Audio;
            myAudio.src = "src/sound/click.mp3";
            myAudio.play();
        }
    </script>
    <div class="wrap">
        <div style="clear: both"></div>
        <div style="float: left">
            <c:if test="${isAdmP}">
                <a href="admin.html" onclick="playAudio();" style="color: red">Admin Panel</a><br>
            </c:if>
            Go to account:&emsp;<a href="account.html" onclick="playAudio();">${login}</a><br>
            Read or go to class-list:&emsp;<a href="class.html" onclick="playAudio();">${classs}</a><br>
            Perhaps, You need to improve the deck:&emsp;<a href="card.html" onclick="playAudio();">Change Deck</a><br>
            <c:if test="${card.size()>=10}">
                Join the battle or wait for rivals!&emsp;<a href="wait.html" id="sO" onclick="playAudio();">Search opponent!</a><br>
            </c:if>
            <c:if test="${card.size()<10}">
                Alas, your deck is not complete...&emsp;<a href="#" title="Alas, your deck is not complete... :(" onclick="playAudio();">Search opponent!</a><br>
            </c:if>
            Today is a bad game..?&emsp;<a href="main.html?exit=true" onclick="playAudio();">Exit</a>
        </div>
        <div style="float: right">
            <a>Registered : <b>${rDate}</b></a><br>
            <a>LVL : <b>${lvl}</b></a><br>
            <a>Points: <b>${pts}</b></a><br>
            <a>Money : <b>${mon}</b></a><br>
            <a>Class : <b>${classs}</b></a>
        </div>
        <div style="clear: both"></div>
    </div>
    <div class="wrap">
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
    <div class="wrap">
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
                                    <c:if test="${c.taunt}"><img src="src/img/ars/taunt.ico" title="taunt"/></c:if>
                                    <c:if test="${c.imun}"><img src="src/img/ars/immunity.ico" title="imun"/></c:if>
                                    <c:if test="${c.shield}"><img src="src/img/ars/shield.ico" title="shield"/></c:if>
                                    <c:if test="${c.charge}"><img src="src/img/ars/charge.ico" title="charge"/></c:if>
                                    <c:if test="${c.poison}"><img src="src/img/ars/poison.ico" title="poison"/></c:if>
                                    </div>
                                </form>
                            </div>
                        </form>
                    </a>
            </c:forEach>
            <a href="/CardGame/card.html?getCards=1" style="margin-left: 45px">Get Cards</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <%@include file="../jspf/footer.jspf" %>