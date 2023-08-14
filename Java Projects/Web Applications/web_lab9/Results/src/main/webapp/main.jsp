<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>
<body style="background: linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c);">
<div class="container">

    <jsp:useBean id="daoStudentsImpl" class="dao.impl.DaoStudentsImpl"
    scope="session"
    />

    <h1>Results</h1>
    <table class="table">
        <thead>
        <tr>
            <th>id</th>
            <th>Student name</th>
            <th>Group number</th>
            <th>Marks</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${empty debtor}">
                <c:set var="items" value="${daoStudentsImpl.allStudents}" />
            </c:when>
            <c:otherwise>
                <c:set var="items" value="${daoStudentsImpl.debtors}" />
            </c:otherwise>
        </c:choose>

        <c:forEach var="student" items="${items}">
            <tr>
                <td>${student.id}</td>
                <td>${student.student_name}</td>
                <td>${student.group_number}</td>
                <td>
                    <form action="hello-servlet" method="POST">
                        <input type="hidden" name="do" value="marks">
                        <input type="hidden" name="studentId" value="${student.id}" />
                        <button type="submit">Marks</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <textarea name="marks" rows="4" cols="40" readonly="readonly" style="padding: 0;">
        ${empty marks ? '' : marks}
    </textarea>
</div>
<div>
    <form action="hello-servlet" method="POST">
        <input type="hidden" name="do" value="add"/>
        <button type="submit"
                class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
            Add student
        </button>
    </form>
    <form action="hello-servlet" method="GET">
        <input type="hidden" name="page" value="debtors"/>
        <button type="submit"
                class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
            View debtors
        </button>
    </form>

    <form action="hello-servlet" method="GET">
        <input type="hidden" name="page" value="main"/>
        <button type="submit"
                class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
            View all
        </button>
    </form>

    <form action="hello-servlet" method="POST">
    <label for="inputField">Enter id of student to delete:</label>
    <input type="hidden"
           name="do"
           value="delete"/>
    <input type="text"
           id="inputField"
           name="student"
           value="Default"/>
    <br/><br/>

    <button type="submit"
            class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
        Delete
    </button>
    </form>



</div>
</body>
</html>
