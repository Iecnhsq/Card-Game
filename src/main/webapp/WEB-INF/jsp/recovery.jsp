<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/headernoislogin.jspf" %>
<div class="forms">
    <div style="clear:both;"></div>
    <div class="form_1">
        <h1>Password Recovery</b></h1>
        <p style="text-align: justify">We just sent you to E-mail letter with validation code. 
            Please enter it in the box below.</p>
        <fieldset>
            <div><span id="resultRec1"/></div>
            <form action="recovery.html" method="POST">
                <input type="text" placeholder="Code from E-mail" name="answer" onblur="checkRec1()" required /><br>
                <input type="submit" value="Apply" onclick="playAudio();"/>
            </form>
        </fieldset>
    </div>
    <%@include file="../jspf/noisloginheadnav.jspf" %>
    <%@include file="../jspf/footer.jspf" %>