<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Pesos</title>

    <%@ include file="../header.jsp" %>
    
  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="2"/>
    </jsp:include>

    <div class="container">
      <h1>Cadastro de Peso</h1>
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>
	  
      <form id="pesoForm" action="${pageContext.servletContext.contextPath}/peso" method="post">
        <div class="form-group">
          <label for="peso">Peso</label>
          <input type="number" id="peso" name="peso" class="form-control" placeholder="Peso em kilogramas">
        </div>
        
        <div class="form-group">
          <label for="data">Data e Hora</label>
          <input type="datetime-local" id="data" name="data" class="form-control">
        </div>
        
        <input type="hidden" name="acao" value="cadastrar" />
        
        <button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.servletContext.contextPath}/peso?acao=listar" class="btn btn-danger">Cancelar</a>
        
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
