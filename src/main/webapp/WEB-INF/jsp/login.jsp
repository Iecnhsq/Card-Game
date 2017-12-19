<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<style><%@include file="/src/style/common.css" %></style>
</head>
<body>
    <script type="text/javascript">
        function equalCheck() {
            $.ajax({
                url: "equalscheck.html",
                data: ({login: $('#log').val(), pass: $('#password').val()}),
                success: function (data) {
                    $('#equalResult').html(data);
                }
            });
        }
        function playAudio() {
            var myAudio = new Audio();
            myAudio.src = "src/sound/click.mp3";
            myAudio.autoplay = true;
        }
    </script>
    <div style="margin: 100px auto; width: 350px">
        <div class="form1">
            <h1 style="text-align: center">Authorization</h1>
            <div style="font-size: 22px; text-align: center"><span id="equalResult"/></div>
            <fieldset>
                <form action="login.html" method="POST">
                    <input type="text" placeholder="Login" name="login" id="log" required/><br>
                    <input type="password" placeholder="Password" name="pass" id="password" required/><br>
                    <input type="submit" value="Sign in" onclick="playAudio();" onmousedown="equalCheck()"/>
                </form>
                <p><a href="register.html" onmousedown="playAudio();">Don't have an account? Sign up now.</a><br><br>
                    <a href="forgotpass.html" onmousedown="playAudio();">Forgot password?</a></p>
            </fieldset>
        </div>
    </div>
</body>
</html>
