<%@ page import="com.example.web7.Cake" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="result" uri="/WEB-INF/result_tag.tld" %>
<html>
<head>
  <title>Cake wizard</title>
</head>
<jsp:include page="header.jsp"/>
<body>
<%--<h1>Cake creation</h1>--%>

<%--<h1>Your cake!</h1>--%>
<%--<p>Dough: ${cake.getDough()}</p>--%>
<%--<p>Cream: ${cake.getCream()}</p>--%>
<%--<p>Topping: ${cake.getTopping()}</p>--%>


<result:resultTag
        cake='<%= (Cake) session.getAttribute("cake") %>'
        errorPage="errorPage.jsp">
  <h1>Your cake!!!</h1>
</result:resultTag>

<br/>

<form action="hello-servlet" method="GET">
  <input type="hidden"
         name="page"
         value="dough"/>

  <button type="submit"
          class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
    Create new cake
  </button>
</form>

<br/>

<form action="hello-servlet" method="GET">
  <input type="hidden" name="edit" value="false"/>
  <input type="hidden" name="page" value="topping"/>
  <button type="submit"
          class="py-2.5 px-5 mr-2 text-sm font-medium text-gray-900 focus:outline-none bg-white rounded-lg
                border border-gray-200 hover:bg-gray-100 hover:text-blue-700 focus:z-10 focus:ring-4 focus:ring-gray-200">
    Back
  </button>
</form>

</body>
</html>
