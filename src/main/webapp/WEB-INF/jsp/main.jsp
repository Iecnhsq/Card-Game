<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<%@include file="../jspf/isLoginHeadNav.jspf" %>
<div class="wrap">
    <c:if test="${card.size()==0}">
        <h1 style="text-align: center">Alas, your deck is not complete... :(</h1>
    </c:if>
    <c:forEach items="${card}" var="c">
        <%@include file="../jspf/cardModel.jspf" %>
    </c:forEach>
</div>
<%@include file="../jspf/footer.jspf" %>