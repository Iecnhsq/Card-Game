<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<style><%@include file="/src/style/common.css" %></style>
</head>
<body>
    <script type="text/javascript">
        function checkRec() {
            $.ajax({
                url: "checkrec.html",
                data: ({login: $('#log').val(), email: $('#email').val()}),
                success: function (data) {
                    $('#resultRec').html(data);
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
            <h1 style="text-align: center">Password Recovery</h1>
            <div style="font-size: 22px; text-align: center"><span id="resultRec"/></div>
            <form action="forgotpass.html" method="POST">
                <fieldset>
                    <input type="text" placeholder="Login" name="login" id="log" required />
                    <input type="email" placeholder="Enter Your E-mail" name="mail" id="email" onblur="checkRec()" required /><br>
                    <input type="submit" value="Recovery" onclick="playAudio();"/>
                </fieldset>
            </form>
            <p style="text-align: center"><a href="main.html" onmousedown="playAudio();">Back to main</a></p>
        </div>
    </div>
</body>
</html>
