<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<style><%@include file="/src/style/common.css" %></style>
</head>
<body>
    <script type="text/javascript">
        function playAudio() {
            var myAudio = new Audio;
            myAudio.src = "src/sound/click.mp3";
            myAudio.play();
        }
    </script>
    <h1>Hello World!</h1>
</body>
</html>
