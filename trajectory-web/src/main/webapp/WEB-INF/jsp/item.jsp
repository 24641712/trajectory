<%--
  Created by IntelliJ IDEA.
  User: lichunting
  Date: 2018/4/18
  Time: 20:13
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
    <title>Title</title>
</head>
<body>
<table>
    <th>用户</th>
    <th>用户行为特征项</th>
    <c:forEach var="p" items="${actitem}" varStatus="status">
        <tr>
            <td>${p.uid}</td>
            <td>${p.sem}</td>
        </tr>
    </c:forEach>
    <tr>
        <td>...</td>
        <td>...</td>
    </tr>
</table>
<a href="${cp}/item/tfidf.do" >权重计算</a>
</body>
</html>
