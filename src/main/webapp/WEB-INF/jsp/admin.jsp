<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Card Game - Admin Panel Page</title>
        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js" type="text/javascript"></script>
        <style type="text/css" media="all">
            body {
                background-image: url(Image/fon.png);
                background-repeat: no-repeat;
                background-attachment: fixed;
                background-size: 100% 100%;
            }
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
            a {
                text-decoration: none;
                color: white;
                padding: 10px;
                background-color: #545247;
                border-radius: 4px;
            }
            a:hover {
                color: gainsboro;
            }
            fieldset {
                width: 400px;
                text-align: center;
                margin: 20px auto;
                border: 2px #A9A9A9 solid;
                border-radius: 8px;
                background-color: rgba(84,82,71,.5);
            }
            input {
                padding: 5px 5px 5px 10px;
                border: 1px #52500d solid;
                margin: 2px;
            }
            .b1 {
                padding: 10px;
                border-radius: 4px;             
            }
            p {
                padding: 10px;
                border-radius: 8px;
            }
            .d{
                width: 400px;
                margin: 0px auto;
            }
            textarea {
                resize: none;
                margin: 2px;
                border: 1px #52500d solid;
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
                <form action="/CardGame/admin.html" method="POST">
                    <fieldset>
                        <input type="text" size="40" placeholder="Login" name="login" id="log" required />
                        <input type="password" size="40" placeholder="Password" name="pass" id="password" onblur="checkRec()" required />
                        <input type="text" size="40" value="${CA}" name="ca" id="cap" readonly />
                        <input type="text" size="40" placeholder="ReEnter Code" name="re" id="rec" maxlength="10" required />
                        <input type="submit" value="Sign In" class="b1"/>
                    </fieldset>
                </form><br><br>
                <a href="/CardGame/main.html">Back to main</a>
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
                <form action="/CardGame/newspost.html" method="POST">
                    <fieldset>
                        <input type="text" size="40" placeholder="Subject" name="subject" required/>
                        <input type="text" size="40" value="${login}" name="author" required/>
                        <textarea cols="43" rows="10" required name="text" maxlength="250" placeholder="Enter Your text here..."></textarea><br>
                        <input type="submit" value="Send" class="b1"/>                    
                    </fieldset>
                </form><br><br>
                <a href="/CardGame/main.html">Back to main</a>
            </div>
        </c:if>
    </body>
</html>
