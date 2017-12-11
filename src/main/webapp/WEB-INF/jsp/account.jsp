<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Account Page</title>
        <link rel="shortcut icon" href="src/img/favicon.png" type="image/png">
        <style><%@include file="/src/style/common.css" %></style>
        <script src="src/js/jquery-3.2.1.min.js" type="text/javascript"></script>
    </head>
    <body>
        <script type="text/javascript">
            function playAudio() {
                var myAudio = new Audio();
                myAudio.src = "src/sound/click.mp3";
                myAudio.autoplay = true;
            }
        </script>
        <div style="margin: 100px auto; width: 900px; background-color: rgba(84,82,71,.5)">
            <div style="clear: both"></div>
            <div class="forma_1">
                <p>
                    <c:if test="${hu}">
                        <img src="src/img/hunter_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${ma}">
                        <img src="src/img/mage_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${pr}">
                        <img src="src/img/priest_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${ro}">
                        <img src="src/img/rogue_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${sh}">
                        <img src="src/img/shaman_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>
                    <c:if test="${wr}">
                        <img src="src/img/warrior_a.png" style="float: left; margin: 0px 20px 0px 0px"/>
                    </c:if>               
                    <input type="text" size="25" readonly value="${u.points} / ${u.lvl}" style="text-align: center"/><br>
                    <input type="text" size="25" readonly value="${u.login}" title="Name" style="text-align: center"/><br>
                    <input type="text" size="25" readonly value="${u.classs}" title="Class" style="text-align: center"/><br>
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
                <form action="account.html" method="POST">
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
            </div>
            <div style="clear: both"></div>
            <div style="text-align: center">
                <form action="" method="POST" id="p">
                    <input type="submit" value="Get Premium" form="p"/>
                    <input type="submit" value="Main Page" form="m"/>
                </form>
                <form action="main.html" id="m"></form>
            </div>
        </div>
    </body>
</html>
