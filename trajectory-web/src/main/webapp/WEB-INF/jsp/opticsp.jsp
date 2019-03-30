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
<a href="${cp}/staypoint/sem.do">语义停留点</a>
<div id="main2" style="width: 600px;height:400px;"></div>

<script type="text/javascript">

    $.ajax({
        url: "/staypoint/showReachp.do?uid=000&sdate=2008-10-24",
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
            var num2=[]
            var obj = JSON.parse(data);
            var datas = obj.datas;
            var datas2 = obj.datas2;
            console.log(datas2)
            for(var i = 0;i<datas.length;i++) {
                var temp =[];
                temp[0]=(i+1);
                temp[1]=(datas[i].reachableDistance);
                num.push(temp);
            }
            for(var j = 0;j<datas2.length;j++) {
                if(datas2[j].reachableDistance!=undefined) {
                    var temp = [];
                    temp[0] = (j + 1);
                    temp[1] = (datas2[j].reachableDistance);
                    num2.push(temp);
                }
            }
            console.log(num);
            console.log(num2);
            var myChart = echarts.init(document.getElementById('main'));
            var myChart2 = echarts.init(document.getElementById('main2'));
            var  option = {
                title:{
                    text: 'HST-OPTICS算法'
                },
                xAxis: {},
                yAxis: {},
                series: [{
                    symbolSize: 10,
                    data: num,
                    type: 'scatter'
                }]
            };
            var  option2 = {
                title:{
                    text: 'OPTICS算法'
                },
                xAxis: {},
                yAxis: {},
                series: [{
                    symbolSize: 10,
                    data: num2,
                    type: 'scatter'
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