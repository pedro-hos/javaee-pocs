<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<htmL>
    <head>
    </head>

    <body>
        <div>
            Logged-in user: <%= request.getParameter("username")%>
            
            <p>
            	<a href="login.jsp">Back</a>
            </p>
        </div>
    </body>
</html>