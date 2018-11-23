<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

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
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>

      <form id="loginForm" action="${pageContext.servletContext.contextPath}/login" method="POST">
        <div class="form-group">
          <label for="email">Email:</label>
          <input type="email" class="form-control" id="email" name="email">
        </div>
        
        <div class="form-group">
          <label for="senha">Senha:</label>
          <input type="password" class="form-control" id="senha" name="senha">
        </div>
        
        <input type="hidden" name="acao" value="entrar">
         
        <button type="submit" class="btn btn-primary">Entrar</button>
        <a href="${pageContext.servletContext.contextPath}" class="btn btn-danger">
          Cancelar
        </a>
        
      </form>
    </div>

    <%@ include file="footer.jsp" %>
    
  </body>
</html>
