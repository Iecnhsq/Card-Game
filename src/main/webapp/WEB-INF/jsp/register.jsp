<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<%@include file="../jspf/noIsLoginHeadNav.jspf" %>
<div class="wrap">
    <h1 style="text-align: center"><b>Registration</b></h1>
    <fieldset>
        <div><span id="logResult"/></div>
        <div><span id="passResult"/></div>
        <div><span id="equalResult"/></div>
        <div><span id="mailResult"/></div>
        <form action="register.html" method="POST">
            <input type="text" placeholder="Login" name="login" id="log" onblur="loginCheck()" required/><br>
            <input type="password" placeholder="Password" name="pass" id="password" onkeyup="passCheck()" required/><br>
            <input type="password" placeholder="Repeat Password" name="pass2" id="password2" onkeyup="equalCheck()" required/><br>
            <input type="text" placeholder="City" name="city"/><br>
            <input type="tel" placeholder="Phone" name="phone"/><br>
            <input type="email" placeholder="E-Mail" name="email" id="mail" onblur="mailCheck()" required/><br>
            <input type="submit" value="Sign Up" onclick="playAudio();" />
        </form>
    </fieldset>
</div>
<%@include file="../jspf/footer.jspf" %>