<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<style>
    <%@include file="/src/style/common.css" %>
    <%@include file="/src/style/style.css" %>
</style>
</head>
<body>
    <script type="text/javascript">
        function checkRec() {
            $.ajax({
                url: "checkrec1.html",
                data: ({login: $('#log').val(), email: $('#email').val()}),
                success: function (data) {
                    $('#resultRec1').html(data);
                }
            });
        }
        function playAudio() {
            var myAudio = new Audio();
            myAudio.src = "src/sound/click.mp3";
            myAudio.autoplay = true;
        }
    </script>
    <div class="wrap">
        <div style="clear: both"></div>
        <div style="text-align: center; font-size: 60pt; float: left">
            <a href="index.html" onclick="playAudio();"><b>Lord Of<br>Magic Cards</b></a>
            <div style="font-size: 14pt !important">
                Server Status:&emsp;<c:if test="${isAlive}"><font style="color: green">ON</font><br></c:if>
                <c:if test="${!isAlive}"><font style="color: red">OFF</font><br></c:if>
                <a class="onlineT">${pOnline} !</a><br>
                <b>${outDate}</b>
            </div>
        </div>
        <div>
            <form action="login.html" method="POST">
                <fieldset>
                    <div><span id="equalResult"/></div>
                    <input type="text" placeholder="Login" name="login" id="log" required><br>
                    <input type="password" placeholder="Password" name="pass" id="password" required><br>
                    <input type="submit" value="Sign in" onclick="playAudio();" onmousedown="equalCheck()">
                    <p><a href="forgotpass.html" onclick="playAudio();">Forgot Password?</a></p>
                </fieldset>
            </form>
        </div>
        <div style="text-align: center; float: right;margin-top: -150px;margin-right: 20px">
            <a href="#" onclick="playAudio();">UA</a><br><br>
            <a href="#" onclick="playAudio();">EN</a><br><br>
            <a href="#" onclick="playAudio();">RUS</a>
        </div>
        <div style="clear: both"></div>
    </div>
    <div class="wrap">
        <div style="text-align: center">
            <a href="index.html?page=about" onclick="playAudio();">About</a>&emsp;||&emsp;
            <a href="index.html?page=news" onclick="playAudio();">News</a>&emsp;||&emsp;
            <a href="support.html" onclick="playAudio();">Support</a>
            <a href="register.html" style="float: right; margin-right: 100px" onclick="playAudio();">Registration</a>
        </div>
    </div>
    <div class="wrap">
        <div class="form1">
            <h1 style="text-align: center">Password Recovery</h1>
            <p style="text-align: justify">We just sent you to E-mail letter with validation code. Please enter it in the box below.</p>
            <div><span id="resultRec1"/></div>
            <form action="recovery.html" method="POST">
                <fieldset>
                    <input type="text" placeholder="Code from E-mail" name="answer" required /><br>
                    <input type="submit" value="Apply" onclick="playAudio();"/>
                </fieldset>
            </form>
        </div>
    </div>
    <%@include file="../jspf/footer.jspf" %>
