<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/headerislogin.jspf" %>
<style type="text/css" media="all">
    .all {
        width: 1024px;
        margin: auto;
        border-radius: 8px;
        background-color: rgba(84,82,71,.5);
        color: white;
        font: 14pt Arial, Helvetica, sans-serif;
        padding: 0px 0px 30px 0px;
        text-align: center;
    }
    fieldset {
        width: 400px;
        text-align: center;
        margin: 20px auto;
        border: 2px #A9A9A9 solid;
        border-radius: 8px;
        background-color: rgba(84,82,71,.5);
    }
    p {
        padding: 10px;
        border-radius: 8px;
    }
    .d{
        width: 400px;
        margin: 0px auto;
    }
</style>
</head>
<body>
    <c:if test="${!isLogAdm}">
        <!--        <script type="text/javascript">
                    function checkRec() {
                        $.ajax({
                            url: "checkrec.html",
                            data: ({login: $('#log').val(), email: $('#email').val()}),
                            success: function (data) {
                                $('#resultRec').html(data);
                            }
                        });
                    }
                </script> -->
        <div class="all">
            <!--            <div class="d" style="margin-bottom: 10px"><span id="resultRec"/></div>-->
            <br><br>
            <p style="text-align: center">
                <b>${passlog}</b>
            </p>
            <form action="admin.html" method="POST">
                <fieldset>
                    <input type="text" placeholder="Login" name="login" id="log" required />
                    <input type="password" placeholder="Password" name="pass" id="password" onblur="checkRec()" required />
                    <input type="text" value="${CA}" name="ca" id="cap" readonly />
                    <input type="text" placeholder="ReEnter Code" name="re" id="rec" maxlength="10" required />
                    <input type="submit" value="Sign In"/>
                </fieldset>
            </form><br><br>
            <a href="main.html">Back to main</a>
        </div>
    </c:if>
    <c:if test="${isLogAdm}">
        <!--        <script type="text/javascript">
        function checkRec() {
            $.ajax({
                url: "checkrec.html",
                data: ({login: $('#log').val(), email: $('#email').val()}),
                success: function (data) {
                    $('#resultRec').html(data);
                }
            });
        }
    </script> -->
        <div class="all">
            <!--            <div class="d" style="margin-bottom: 10px"><span id="resultRec"/></div>-->
            <br><br>
            <form action="newspost.html" method="POST">
                <fieldset>
                    <input type="text" placeholder="Subject" name="subject" required/>
                    <input type="text" value="${login}" name="author" required/>
                    <textarea cols="43" required name="text" maxlength="250" placeholder="Enter Your text here..."></textarea><br>
                    <input type="submit" value="Send"/>                    
                </fieldset>
            </form><br><br>
            <a href="main.html">Back to main</a>
        </div>
    </c:if>
</body>
</html>
