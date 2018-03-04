<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%--
  Created by IntelliJ IDEA.
  User: Serge
  Date: 04.03.2018
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Meals</title>
    <style type="text/css">
        .tg {
            border-collapse: collapse;
            border-spacing: 0;
            border-color: #152c24;
        }

        .tg td {
            font-family: Arial, sans-serif;
            font-size: 14px;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #220507;
            /*color: #333;*/
            /*background-color: #fff;*/
        }

        .tg th {
            font-family: Arial, sans-serif;
            font-size: 14px;
            font-weight: normal;
            padding: 10px 5px;
            border-style: solid;
            border-width: 1px;
            overflow: hidden;
            word-break: normal;
            border-color: #220507;
            /*color: #333;*/
            background-color: #d9ffdb;
        }

        .tg {
            /*background-color: #f9f9f9*/
        }

        #true {
            color: red;
            background-color: #ff8c84;
        }

        #false {
            color: green;
            background-color: #76ffcf;
        }
    </style>
</head>
<body>
<h3><a href="index.html">Home</a></h3>
<h2>Meals</h2>
<jsp:useBean id="meal" scope="request" type="java.util.List"/>

<c:if test="${!empty meal}">
    <table class="tg">
        <tr>
            <th width="160">dateTime</th>
            <th width="120">description</th>
            <th width="120">calories</th>
            <th width="120">exceed</th>
        </tr>

        <c:forEach items="${meal}" var="meal">
            <%--<c:choose>--%>
                <%--<c:when test="${meal.exceed}">--%>
                    <%--<tr ID=true>--%>
                        <%--<td>${meal.getFormatDateTime()}</td>--%>
                        <%--<td>${meal.description}</td>--%>
                        <%--<td>${meal.calories}</td>--%>
                        <%--<td>${meal.exceed}</td>--%>
                    <%--</tr>--%>
                <%--</c:when>--%>
                <%--<c:otherwise>--%>
                    <tr ID="${meal.exceed}">
                        <td>${meal.getFormatDateTime()}</td>
                        <td>${meal.description}</td>
                        <td>${meal.calories}</td>
                        <td>${meal.exceed}</td>
                    </tr>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
        </c:forEach>
    </table>
</c:if>

</body>
</html>
