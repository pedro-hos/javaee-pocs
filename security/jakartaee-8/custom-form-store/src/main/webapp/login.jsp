<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<htmL>
    <head>
    </head>
    
    <body>
        <div>
            Logged-in user: <%= request.getParameter("username")%>
            <form method="POST" action="login">
                <table>
                    <tr>
                        <td align="right">Username:</td>
                        <td>
                            <input type="text" name="username" >
                        </td>
                    </tr>
                    <tr>
                        <td align="right">Password:</td>
                        <td><input type="password" name="password" ></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td align="right"><button type="submit">Log in</button></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>