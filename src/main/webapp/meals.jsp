<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://topjava.javawebinar.ru/functions" %>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<html>
<head>
    <title>Meal list</title>
    <style>
        .normal {
            color: green;
        }

        .exceeded {
            color: red;
        }
    </style>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <h2>Meals</h2>
    <a href="meals?action=create">Add Meal</a>
    <hr/>

    <div class="row">
        <div class="col-sm-7">
            <div class="panel panel-default">
                <div class="panel-body">
                    <form class="form-horizontal" id="filter">
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="startDate">От даты:</label>

                            <div class="col-sm-3">
                                <input class="form-control" name="startDate" id="startDate">
                            </div>

                            <label class="control-label col-sm-4" for="startTime">От времени:</label>

                            <div class="col-sm-2">
                                <input class="form-control" name="startTime" id="startTime">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-3" for="endDate">До даты:</label>

                            <div class="col-sm-3">
                                <input class="form-control" name="endDate" id="endDate">
                            </div>

                            <label class="control-label col-sm-4" for="endTime">До времени:</label>

                            <div class="col-sm-2">
                                <input class="form-control" name="endTime" id="endTime">
                            </div>
                        </div>
                    </form>
                </div>
                <div class="panel-footer text-right">
                    <a class="btn btn-danger" type="button" onclick="clearFilter()">
                        <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                    </a>
                    <a class="btn btn-primary" type="button" onclick="updateTable()">
                        <span class="glyphicon glyphicon-filter" aria-hidden="true"></span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <table border="1" cellpadding="8" cellspacing="0">
        <thead>
        <tr>
            <th>Date</th>
            <th>Description</th>
            <th>Calories</th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <c:forEach items="${meals}" var="meal">
            <jsp:useBean id="meal" scope="page" type="ru.javawebinar.topjava.to.MealWithExceed"/>
            <tr class="${meal.exceed ? 'exceeded' : 'normal'}">
                <td>
                        <%--${meal.dateTime.toLocalDate()} ${meal.dateTime.toLocalTime()}--%>
                        <%--<%=TimeUtil.toString(meal.getDateTime())%>--%>
                        <%--${fn:replace(meal.dateTime, 'T', ' ')}--%>
                        ${fn:formatDateTime(meal.dateTime)}
                </td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
                <td><a href="meals?action=update&id=${meal.id}">Update</a></td>
                <td><a href="meals?action=delete&id=${meal.id}">Delete</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>