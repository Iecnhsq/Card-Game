<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Account Page</title>
        <style>
            <%@include file="/Style/account.css" %>
        </style>
    </head>
    <body>
        <div style="margin: 100px auto; width: 750px">
            <div style="clear: both"></div>
            <div class="forma_1">
                <p>
                    <c:if test="${dk}">
                        <img src="Image/deathknight_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${dh}">
                        <img src="Image/demonhunter_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${dr}">
                        <img src="Image/druid_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${hu}">
                        <img src="Image/hunter_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${ma}">
                        <img src="Image/mage_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${mo}">
                        <img src="Image/monk_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${pa}">
                        <img src="Image/paladin_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${pr}">
                        <img src="Image/priest_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${ro}">
                        <img src="Image/rogue_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${sh}">
                        <img src="Image/shaman_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${wl}">
                        <img src="Image/warlock_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${wr}">
                        <img src="Image/warrior_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>               
                    <input type="text" size="25" readonly title="Experience x / 1050" value="x / 1050" style="text-align: center"/><br>
                    <input type="text" size="25" readonly value="${u.login}" title="Name" style="text-align: center"/><br>
                    <input type="text" size="25" readonly value="${u.classs}" title="Class" style="text-align: center"/><br>
                <form action="" method="POST">
                    <input type="submit" value="Get Premium" style="padding: 6px 67px 6px 68px"/>
                </form>
                </p>
                <p style="margin-top: 40px; font-size: 14pt">
                    Registered : <b>${u.date}</b><br>
                    LVL : <b>${u.lvl}</b><br>
                    Points : <b>${u.points}</b><br>
                    Money : <b>${u.money}</b><br><br>
                    Frends : <b>{frends}</b>
                </p>
            </div>
            <div class="forma_2">
                <h2 style="text-align: center">Change data</h2><hr>
                <form action="/CardGame/account.html" method="POST">
                    <div class="main">
                        <div class="field"><label>Enter pass:</label><input type="password" name="pass3" required/></div>
                        <div class="field"><label>Enter new pass:</label><input type="password" name="pass" required/></div>
                        <div class="field"><label>Enter pass again:</label><input type="password" name="pass2" required/></div>
                        <div class="field"><label>Enter city:</label><input type="text" placeholder="${u.city}" name="city" required/></div>
                        <div class="field"><label>Enter phone:</label><input type="tel" placeholder="${u.phone}" name="phone" required/></div>
                        <div class="field"><label>Enter e-mail:</label><input type="email" placeholder="${u.email}" name="email" required/></div>
                        <input type="submit" value="Confirm changes" style="margin-left: 100px; margin-top: 15px"/>
                    </div>
                </form>
                <form action="main.html">
                    <input type="submit" value="Main Page" style="margin-left: 120px; margin-top: 15px"/>
                </form>
            </div>
            <div style="clear: both"></div>
        </div>
    </body>
</html>
