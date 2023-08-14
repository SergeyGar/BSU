<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<html>
<head>
    <title>Cake wizard</title>
</head>
<body>
<h1>Cake creation</h1>

<form action="hello-servlet" method="GET">
    <label for="inputField">Enter topping:</label>
    <input type="hidden"
           name="page"
           value="result"/>
    <input type="text"
           id="inputField"
           name="topping"
           value="${cake.getTopping()}"/>
    <br/><br/>

    <button type="submit"
            class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
        Next
    </button>
</form>

<br/>

<form action="hello-servlet" method="GET">
    <input type="hidden" name="edit" value="false"/>
    <input type="hidden" name="page" value="cream"/>
    <button type="submit"
            class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
        Back
    </button>
</form>

</body>
</html>
