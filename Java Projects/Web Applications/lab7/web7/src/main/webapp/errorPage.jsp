<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isErrorPage="true" %>
<html>
<head>
    <title>Error!</title>
</head>
<body>
<div class="container dark">
    <div class="app">
        <h5 class="text-center" style="font-family: 'Droid serif', serif; font-size: 100px; color: red">
            Error!
        </h5>
        <form action="hello-servlet" method="GET">
            <button class="button-34"
                    type="submit"
                    name="page"
                    value="dough">
                Back</button>
        </form>
    </div>
</div>
</body>
</html>
