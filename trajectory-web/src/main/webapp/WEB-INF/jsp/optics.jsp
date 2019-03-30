<%--
  Created by IntelliJ IDEA.
  User: lichunting
  Date: 2018/4/5
  Time: 9:20
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
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="UTF-8">
    <title>Optics</title>

    <script src="${cp}/static/js/jquery-3.2.1.min.js"></script>

    <script src="${cp}/static/js/echarts.js"></script>
</head>
<body>
<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" style="width: 600px;height:400px;"></div>
<a href="${cp}/staypoint/getpointsp.do?uid=000&&sdate=2018-10-24">聚类效果展示</a>
<div id="main2" style="width: 600px;height:400px;"></div>

<script type="text/javascript">

    $.ajax({
        url: "/staypoint/showReach.do?uid=000&sdate=2008-10-24",
        type: "POST",
        /**
         *必须false才会自动加上正确的Content-Type
         */
        contentType: false,
        /**
         * 必须false才会避开jQuery对 formdata 的默认处理
         * XMLHttpRequest会对 formdata 进行正确的处理
         */
        processData: false,
        success: function (data) {
            var num=[];
            var reach=[];
            var num2=[];
            var reach2=[];
            var obj = JSON.parse(data);
            var datas = obj.datas;
            var datas2 = obj.datas2;
            for(var i = 0;i<datas.length;i++) {
                num.push(i+1);
                reach.push(datas[i].reachableDistance);
            }
            for(var i = 0;i<datas2.length;i++) {
                num2.push(i+1);
                reach2.push(datas2[i].reachableDistance);
            }
            console.log(num);
            console.log(reach);
            var myChart = echarts.init(document.getElementById('main'));
            var myChart2 = echarts.init(document.getElementById('main2'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    text: 'OPTICS算法'
                },
                tooltip: {},
                legend: {
                    data:['reach']
                },
                xAxis: {
                    data: num
                },
                yAxis: {},
                series: [{
                    name: '可达距离',
                    type: 'bar',
                    data: reach
                }]
            };
            // 指定图表的配置项和数据
            var option2 = {
                title: {
                    text: 'HST-OPTICS算法'
                },
                tooltip: {},
                legend: {
                    data:['reach']
                },
                xAxis: {
                    data: num2
                },
                yAxis: {},
                series: [{
                    name: '销量',
                    type: 'bar',
                    data: reach2,
                    barWidth:2
                }]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
            myChart2.setOption(option2);

        },
        error: function () {

        }
    });
    // 基于准备好的dom，初始化echarts实例

</script>
</body>

</html>