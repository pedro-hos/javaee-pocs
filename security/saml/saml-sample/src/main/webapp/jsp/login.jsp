<html>
  <head></head>
  <body>
    <form id="login_form" name="login_form" method="post" action="j_security_check" enctype="application/x-www-form-urlencoded">
      <center> <p>Welcome to the <b>IDP</b></p> <p>Please login to proceed.</p> </center>
      <div style="margin-left: 15px;">
        <p> <label for="username">Username</label> <br /> <input id="username" type="text" name="j_username"/> </p>
        <p> <label for="password">Password</label> <br /> <input id="password" type="password" name="j_password" value=""/> </p>
        <center> <input id="submit" type="submit" name="submit" value="Login"/> </center>
      </div>
    </form>
  </body>
</html>