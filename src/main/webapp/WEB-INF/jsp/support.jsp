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
        function loginCheckSupp() {
            $.ajax({
                url: "logchecksupp.html",
                data: ({login: $('#login').val()}),
                success: function (data) {
                    $('#logResultSupp').html(data);
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
            <h1 style="text-align: center"><b>Write a message to the support team</b></h1>
            <p style="text-align: justify">
                If you have any problems with the game, write to us. Specify the name 
                of the problem and briefly describe its essence.<br>
                We will try to solve it in the shortest possible time.
            </p>
            <div><span id="logResultSupp"/></div>
            <form action="support.html" method="POST">
                <fieldset>
                    <input type="text" placeholder="Your problem" required name="err"/><br>
                    <input type="text" placeholder="Subject" required name="sub"/><br>
                    <input type="text" placeholder="Your Login" name="log" id="login" onblur="loginCheckSupp()" required/><br>
                    <textarea required name="mess" maxlength="250" placeholder="Enter Your message..."></textarea><br>             
                    <input type="submit" value="Send" onclick="playAudio();"/>
                </fieldset>
            </form>
        </div>
    </div>
    <%@include file="../jspf/footer.jspf" %>
