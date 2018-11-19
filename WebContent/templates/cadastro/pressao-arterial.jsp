<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Pressão Arterial</title>

	<%@ include file="../header.jsp" %>

  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="3"/>
    </jsp:include>

    <div class="container">
      <h1>Cadastro de Pressão Arterial</h1>
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>
	  
      <form id="pressaoForm" action="${pageContext.servletContext.contextPath}/pressao-arterial" method="post">
        <div class="form-group">
          <label for="pressaoMax">Pressão arterial sistólica (máxima)</label>
          <input type="number" id="pressaoMax" name="pressaoMax" class="form-control" placeholder="Pressão arterial em mmHg. Ex: 120">
        </div>
        
        <div class="form-group">
          <label for="pressaoMin">Pressão arterial diastólica (mínima)</label>
          <input type="number" id="pressaoMin" name="pressaoMin" class="form-control" placeholder="Pressão arterial em mmHg. Ex: 80">
        </div>
        
        <div class="form-group">
          <label for="data">Data e Hora</label>
          <input type="datetime-local" id="data" name="data" max="3000-12-31" min="1000-01-01" class="form-control">
        </div>
        
        <input type="hidden" name="acao" value="cadastrar" />
        
		<button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.servletContext.contextPath}/pressao-arterial?acao=listar" class="btn btn-danger">Cancelar</a>

      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
