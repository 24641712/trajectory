

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<c:set var="cp" value="<%=basePath%>"/>
<html>
<body>
<a href="${cp}/index/plt.do">用户数据上传</a>
<a href="${cp}/data/data.do">用户数据展示</a>
<a href="${cp}/data/oneday.do">单天数据展示</a>
<a href="${cp}/staypoint/getpoints.do"> 语义停留点获取分析</a>
<a href="${cp}/item/all.do">模型构建</a>
</body>
</html>
