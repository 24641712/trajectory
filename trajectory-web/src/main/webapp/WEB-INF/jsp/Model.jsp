<%--
  Created by IntelliJ IDEA.
  User: lichunting
  Date: 2018/4/18
  Time: 20:59
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
    <title>Model</title>
</head>
<body>
<table border="1">
    <tr>
        <td >用户</td>
        <td>行为</td>
        <td>行为特征项</td>
    </tr>
    <tr>
        <td rowspan="11">000</td>
        <td rowspan="5">运动=0.162140574</td>
        <td>打羽毛球=0.016008297</td>
    </tr>
    <tr>

        <td>打乒乓球=0.04337466</td>
    </tr>
    <tr>

        <td>打排球=0.04337466</td>
    </tr>
    <tr>

        <td>打篮球=0.016008297</td>
    </tr>
    <tr>

        <td>跑步=0.04337466</td>
    </tr>
    <tr>
        <td>饮食=0.016008297</td>
        <td>餐厅吃饭=0.016008297</td>
    </tr>
    <tr>
        <td>学习=0.016008297</td>
        <td>读书=0.016008297</td>
    </tr>
    <tr>
        <td rowspan="4">生活=0.146132277</td>
        <td>理发=0.04337466</td>
    </tr>

    <tr>


        <td>看病=0.04337466</td>
    </tr>
    <tr>


        <td> 洗澡=0.016008297</td>
    </tr>
    <tr>


        <td>饭店打工=0.04337466</td>
    </tr>
</table>
<b>Model(001)=({运动，0.162140574},{饮食,0.016008297},{学习,0.016008297},{生活,0.146132277})=({(打羽毛球,0.016008297)...})</b>
<hr>
<table border="1">
    <tr>
        <td >用户</td>
        <td>行为</td>
        <td>行为特征项</td>
    </tr>
    <tr>
        <td rowspan="10">001</td>
        <td rowspan="4">运动=0.13064251</td>
        <td>踢足球=0.04771213</td>
    </tr>
    <tr>

        <td>打篮球=0.017609125</td>
    </tr>
    <tr>

        <td>健身=0.04771213</td>
    </tr>
    <tr>

        <td>打羽毛球=0.017609125</td>
    </tr>
    <tr>
        <td rowspan="3">娱乐=0.14313639</td>
        <td>K歌=0.04771213</td>
    </tr>
    <tr>

        <td>上网=0.04771213</td>
    </tr>
    <tr>

        <td>游玩=0.04771213</td>
    </tr>
    <tr>

        <td>学习=0.017609125</td>
        <td>读书=0.017609125</td>
    </tr>
    <tr>

        <td>饮食=0.017609125</td>
        <td>餐厅吃饭=0.017609125</td>
    </tr>
    <tr>

        <td>生活=0.017609125</td>
        <td>洗澡=0.017609125</td>
    </tr>
</table>
<b>Model(001)=({运动，0.13064251},{饮食,0.017609125	7},{学习,0.017609125},{生活,0.017609125},{娱乐,0.14313639})=({(打羽毛球,0.017609125)...})</b>
<a href="${cp}/model/end.do">后续工作</a>
</body>
</html>
