<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../jspf/header.jspf" %>
<%@include file="../jspf/noIsLoginHeadNav.jspf" %>
<c:if test="${isM}"><%@include file="../jspf/main.jspf" %></c:if>
<c:if test="${isA}"><%@include file="../jspf/about.jspf" %></c:if>
<c:if test="${isN}"><%@include file="../jspf/news.jspf" %></c:if>
<%@include file="../jspf/footer.jspf" %>