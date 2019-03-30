<%--
  Created by IntelliJ IDEA.
  User: lichunting
  Date: 2017/12/17
  Time: 15:33
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
    <title>上传个人数据</title>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

</head>
<body>
<a href="${cp}">返回首页</a><br>
    <form method="POST" enctype="multipart/form-data" id="form1">
        <table>
            <tr>
                <td>上传文件:</td>
                <td><input id="upfile" type="file" name="upfile"></td>
            </tr>
            <tr>
                <td>用户ID</td>
                <td><input id="uid" name="uid"></td>
            </tr>
            <tr>
                <td><input id="upload" type="button" value="导入数据"/></td>
            </tr>
        </table>
    </form>

    <script src="${cp}/static/js/jquery-3.2.1.min.js"></script>
    <script src="${cp}/static/js/jquery.form.js"></script>
    <script src="${cp}/static/js/upload.js"></script>
</body>
</html>
