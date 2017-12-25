<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<div class="wrap">
    <div style="clear: both"></div>
    <div class="forma_1">
        <p>             
            <input type="text" readonly value="${u.points} / ${u.lvl}" style="text-align: center"/><br>
            <input type="text" readonly value="${u.login}" style="text-align: center"/><br>
            <input type="text" readonly value="${u.classs}" style="text-align: center"/><br>
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
        <form action="premium.html" method="POST" id="p">
            <input type="submit" value="Get Premium" form="p"/>
            <input type="submit" value="Main Page" form="m"/>
        </form>
        <form action="main.html" id="m"></form>
    </div>
</div>
<%@include file="../jspf/footer.jspf" %>>
