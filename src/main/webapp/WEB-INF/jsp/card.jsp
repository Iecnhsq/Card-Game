<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/headerislogin.jspf" %>
<%@include file="../jspf/isloginheadnav.jspf" %>
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