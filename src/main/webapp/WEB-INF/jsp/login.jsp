<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/headernoislogin.jspf" %>
<div class="forms">
    <div style="clear:both;"></div>
    <div class="form_1">
        <h1>Authorization</h1>
        <div><span id="equalResult"/></div>
        <fieldset>
            <form action="login.html" method="POST">
                <input type="text" placeholder="Login" name="login" id="log" required/><br>
                <input type="password" placeholder="Password" name="pass" id="password" required/><br>
                <input type="submit" value="Sign in" onclick="playAudio();" onmousedown="equalCheck()"/>
            </form>
        </fieldset>
    </div>
    <%@include file="../jspf/noisloginheadnav.jspf" %>
    <%@include file="../jspf/footer.jspf" %>
