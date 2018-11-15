<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Login</title>

    <%@ include file="header.jsp" %>

  </head>
  <body>
    
    <jsp:include page="menu.jsp">
    	<jsp:param name="menu" value="0"/>
    </jsp:include>

    <div class="container">
      <h1>Login</h1>

      <form id="loginForm" action="dashboard.jsp" method="POST">
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" class="form-control" id="email" name="email">
        </div>
        <div class="form-group">
          <label for="password">Senha:</label>
          <input type="password" class="form-control" id="password" name="password">
        </div>
        <div class="checkbox">
          <label><input type="checkbox"> Lembrar</label>
        </div>
        <button type="submit" class="btn btn-primary" id="btnLogar">Entrar</button>
      </form>
    </div>

    <%@ include file="footer.jsp" %>
    
  </body>
</html>
