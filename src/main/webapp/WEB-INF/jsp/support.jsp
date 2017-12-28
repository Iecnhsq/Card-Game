<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/headernoislogin.jspf" %>
<div class="forms">
    <div style="clear:both;"></div>
    <div class="form_1">
        <h1>Write a message to the support team</h1>
        <p style="text-align: justify">
            If you have any problems with the game, write to us. Specify the name 
            of the problem and briefly describe its essence.<br>
            We will try to solve it in the shortest possible time.
        </p>
        <fieldset>
            <div><span id="logResultSupp"/></div>
            <form action="support.html" method="POST">
                <input type="text" placeholder="Your problem" required name="problem"/><br>
                <input type="text" placeholder="Subject" required name="subject"/><br>
                <input type="text" placeholder="Your Login" name="loginsupport" id="login" onblur="loginCheckSupp()" required/><br>
                <textarea required name="message" maxlength="250" placeholder="Enter Your message..."></textarea><br>             
                <input type="submit" value="Send" onclick="playAudio();"/>
            </form>
        </fieldset>
    </div>
    <%@include file="../jspf/noisloginheadnav.jspf" %>
    <%@include file="../jspf/footer.jspf" %>