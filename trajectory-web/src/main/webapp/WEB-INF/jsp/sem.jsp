<%--
  Created by IntelliJ IDEA.
  User: lichunting
  Date: 2018/4/18
  Time: 8:57
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
    <title>语义停留点</title>
</head>
<body>
<a href="${cp}">返回首页</a><br>
停留点1的GPS点的编号
<c:forEach var="p" items="${points1}" varStatus="status">


    ${p.name}
</c:forEach>
<br>
停留点2的GPS点的编号
<c:forEach var="p" items="${points2}" varStatus="status">


    ${p.name}
</c:forEach>
<br>
停留点3的GPS点的编号
<c:forEach var="p" items="${points3}" varStatus="status">


    ${p.name}
</c:forEach>
<br>
停留点4的GPS点的编号
<c:forEach var="p" items="${points4}" varStatus="status">


    ${p.name}
</c:forEach>

<br>
<br>
<br>
<br>
<br>
地图标注：通过高德地图的API的获取<br>
停留时间：GPS点集合中最后一个点的时间减去第一个点的时间<br>
日期类型：根据日期函数计算得到（0为工作日，1为非工作日）<br>
<table>
    <tr><td>停留点</td><td>地图标注</td><td>停留时间</td><td>日期类型（0为工作日，1为非工作日）</td></tr>
    <tr><td>停留点1</td><td>饮食广场</td><td>0.5h</td><td>0</td></tr>
    <tr><td>停留点2</td><td>清华大学生物医学馆B座</td><td>2.5h</td><td>0</td></tr>
    <tr><td>停留点3</td><td>21号宿舍楼</td><td>1.1h</td><td>0</td></tr>
    <tr><td>停留点4</td><td>清华大学精密测试技术及仪器国家重点实验室-二室</td><td>3.2h</td><td>0</td></tr>

</table>
<br>
<br>
<br>
<br>
<br>
<br>
<br>

语义停留点格式为为
<br>
{GPS点集合，地图标注，停留时间，日期类型}


</body>
</html>
