<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>email.jsp</title>
	</head>
	
 	<body>
 		
 		<p>
        	<label style="color: red; width: 100%;text-align: left;">${message}</label>
		</p>
 		
 		<form id="email" action="email.do" method="POST">
 			<table>
 				
 				<tr>
 					<td>From:</td>
 					<td>
 						<input type="text" id=from name="from" value="${novoEmail.from}" />
 					</td>
 				</tr>
 				
 				<tr>
 					<td>To:</td>
 					<td><input type="text" id=to name="to" value="${novoEmail.to}" /></td>
 				</tr>
 				
 				<tr>
 					<td>Subject:</td>
 					<td><input type="text" id=subject name="subject" value="${novoEmail.subject}" /></td>
 				</tr>
 				
 				<tr>
 					<td>Content:</td>
 					<td><textarea rows="5" cols="5" id=content name=content value="${novoEmail.content}"></textarea> </td>
 				</tr>
 				
 			</table>
 			
 			<p>
            	<input id="register" type="submit" value="Enviar" /> 
			</p>
			
 		</form>
	</body>
</html>