<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Student</title>
</head>
<body style="background: linear-gradient(0.25turn, #3f87a6, #ebf8e1, #f69d3c);">

<h2>Add Student</h2>

<form action="hello-servlet" method="GET">
    <label for="name">Name:</label>
    <input type="text" name="name" id="name" required><br><br>
<jsp:useBean id="daoSubjectsImpl" class="dao.impl.DaoSubjectsImpl"
scope="session"
/>
    <table>
        <thead>
        <tr>
            <th>Subject</th>
            <th>Mark</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="subject" items="${daoSubjectsImpl.allSubjects}" varStatus="status">
            <tr>
                <td>${subject.name}</td>
                <td><input type="text" name="marks" required></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <br>

    <label>Group:</label><br>
    <input type="radio" name="group" value=1 required> Group 1<br>
    <input type="radio" name="group" value=2 required> Group 2<br>
    <input type="radio" name="group" value=3 required> Group 3<br>
    <input type="radio" name="group" value=4 required> Group 4<br>
    <input type="radio" name="group" value=5 required> Group 5<br><br>


        <input type="hidden" name="page" value="main"/>
        <input type="hidden" name="add" value="add"/>
        <button type="submit"
                class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
            Add
        </button>
</form>

</body>
</html>