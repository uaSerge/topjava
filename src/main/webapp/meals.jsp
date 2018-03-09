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
        .tb {
            font-family: Arial, sans-serif;
            font-size: 16px;
            border-spacing: 5px;
            border-color: #152c24;
            padding: 10px 5px;
        }
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
            font-weight: bold;
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
            <th width="60">ID</th>
            <th width="160">dateTime</th>
            <th width="120">description</th>
            <th width="120">calories</th>
            <th width="120">exceed</th>
        </tr>

        <c:forEach items="${meal}" var="meal1">
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
                    <tr ID="${meal1.exceed}">
                        <td>${meal1.getID()}</td>
                        <td>${meal1.getFormatDateTime()}</td>
                        <td>${meal1.description}</td>
                        <td>${meal1.calories}</td>
                        <td>${meal1.exceed}</td>
                    </tr>
                <%--</c:otherwise>--%>
            <%--</c:choose>--%>
        </c:forEach>
    </table>
</c:if>
<p/>
<form method="post">
    <label class="tb">Date & Time:
        <input type="datetime-local" name="date" value="2018-12-19T16:39:00"><br />
    </label>
    <%--<label class="tb">Time:--%>
        <%--<input type="time" name="time"><br />--%>
    <%--</label>--%>
    <p/>
    <label class="tb">Description:
        <input type="text" name="description"><br />
    </label>
    <p/>
    <label class="tb">Calories:
        <input type="number" name="calories"><br />
    </label>
    <p/>
    <button type="submit">Submit</button>
</form>
</body>
</html>
