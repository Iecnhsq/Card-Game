<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="../jspf/header.jspf" %>
<style>
    <%@include file="/src/style/common.css" %>
    <%@include file="/src/style/style.css" %>
</style>
</head>
<body>
    <script type="text/javascript">
        function playAudio() {
            var myAudio = new Audio();
            myAudio.src = "src/sound/click.mp3";
            myAudio.autoplay = true;
        }
        function refresh() {
            window.location.href = "http://localhost:8084/CardGame/wait.html";
        }
        setInterval("refresh()", 5000);
    </script>
    <div class="wrap">
        <div style="clear: both"></div>
        <div style="float: left">
            <c:if test="${isAdmP}">
                <a href="admin.html" onclick="playAudio();" style="color: red">Admin Panel</a><br>
            </c:if>
            Go to account:&emsp;<a href="account.html" onclick="playAudio();">${login}</a><br>
            Read or go to class-list:&emsp;<a href="class.html" onclick="playAudio();">${classs}</a><br>
            Perhaps, You need to improve the deck:&emsp;<a href="card.html" onclick="playAudio();">Change Deck</a><br>
            <c:if test="${card.size()>=10}">
                Join the battle or wait for rivals!&emsp;<a href="wait.html" id="sO" onclick="playAudio();">Search opponent!</a><br>
            </c:if>
            <c:if test="${card.size()<10}">
                Alas, your deck is not complete...&emsp;<a href="#" title="Alas, your deck is not complete... :(" onclick="playAudio();">Search opponent!</a><br>
            </c:if>
            Today is a bad game..?&emsp;<a href="main.html?exit=true" onclick="playAudio();">Exit</a>
        </div>
        <div style="float: right">
            <a>Registered : <b>${rDate}</b></a><br>
            <a>LVL : <b>${lvl}</b></a><br>
            <a>Points: <b>${pts}</b></a><br>
            <a>Money : <b>${mon}</b></a><br>
            <a>Class : <b>${classs}</b></a>
        </div>
        <div style="clear: both"></div>
    </div>
    <div class="wrap">
        <div class="form1">
            <h1 style="text-align: center">Waiting for battles...</h1>
            <a class="onlineT">${ifNo} ${pOnline} !</a>
            <p>
                Who expects: <b>${login}</b><br>
                Your class: <b>${classs}</b><br>
                Your level: <b>${level}</b>
            <p style="text-align: center">
                <a href="main.html?exit=true" onmousedown="playAudio();">You want to quit?</a>&emsp; | &emsp;
                <a href="main.html" onmousedown="playAudio();">Back to Main</a>
            <p>
            <div id="refresh">
                <form action="wait.html">
                    <input id="refresh" type="submit" value="Refresh" onclick="playAudio();"/>
                </form>
            </div>
            </p>
            </p>
            </p>
        </div>
    </div>                
    <%@include file="../jspf/footer.jspf" %>
