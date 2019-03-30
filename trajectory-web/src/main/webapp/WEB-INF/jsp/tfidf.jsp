<%--
  Created by IntelliJ IDEA.
  User: lichunting
  Date: 2018/4/18
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="cp" value="<%=basePath%>"/>
<html>
<head>
    <title>TFIDF</title>
</head>
<body>
<c:forEach var="p" items="${tfidf}" varStatus="status">
    ${p}<br>
</c:forEach>

<a href="${cp}/model/show.do">模型构建</a>
</body>
</html>
