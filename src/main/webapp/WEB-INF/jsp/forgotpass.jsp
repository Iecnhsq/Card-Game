<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<div class="forms">
    <div style="clear:both;"></div>
    <div class="form_1">
        <h1>Password Recovery</h1>
        <p style="text-align: justify">If you have forgotten your password, enter the information below and you will receive a message 
            in the course of 5 minutes with the data to restore access to your account</p>
        <fieldset>
            <div><span id="resultRec"/></div>
            <form action="forgotpass.html" method="POST">
                <input type="text" placeholder="Login" name="login" id="log" required /><br>
                <input type="email" placeholder="Enter Your E-mail" name="mail" id="email" onblur="checkRec()" required /><br>
                <input type="submit" value="Recovery" onclick="playAudio();"/>     
            </form>
        </fieldset>
    </div>
    <%@include file="../jspf/noisloginheadnav.jspf" %>
    <%@include file="../jspf/footer.jspf" %>