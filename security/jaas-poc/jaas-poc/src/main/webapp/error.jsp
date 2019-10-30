<%
  Exception exception = (Exception)session.getAttribute("j_exception");
  String message = exception.getMessage();
  out.println("Error: "+message);
%>