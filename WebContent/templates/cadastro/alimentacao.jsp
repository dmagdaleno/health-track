<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Alimentação</title>

    <%@ include file="../header.jsp" %>
    
  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="4"/>
    </jsp:include>

    <div class="container">
      <h1>Cadastro de Alimentos Consumidos</h1>
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>
      
      <form id="alimentacaoForm" action="${pageContext.servletContext.contextPath}/alimentacao" method="post">
        <div class="form-group">
          <label for="tipo">Tipo</label>
          <select id="tipo" name="tipo" class="form-control">
            <option value="1" selected>Café da manhã</option>
            <option value="2">Almoço</option>
            <option value="3">Jantar</option>
            <option value="4">Lanche leve</option>
            <option value="5">Fruta</option>
          </select>
        </div>
        
        <div class="form-group">
          <label for="calorias">Calorias</label>
          <input type="number" id="calorias" name="calorias" class="form-control" placeholder="Calorias">
        </div>
        
        <div class="form-group">
          <label for="descricao">Descrição</label>
          <textarea id="descricao" name="descricao" class="form-control" rows="3" 
          		placeholder="Descrição (opcional)"></textarea>
        </div>
        
        <div class="form-group">
          <label for="data">Data</label>
          <input type="datetime-local" id="data" name="data" class="form-control">
        </div>
        
        <input type="hidden" name="acao" value="cadastrar" />
        
        <button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.servletContext.contextPath}/alimentacao?acao=listar" class="btn btn-danger">
          Cancelar
        </a>
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
