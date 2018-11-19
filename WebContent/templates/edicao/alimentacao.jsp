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
            <option value="1" <c:if test="${registro.tipo.id == 1}">selected</c:if>>Café da manhã</option>
            <option value="2" <c:if test="${registro.tipo.id == 2}">selected</c:if>>Almoço</option>
            <option value="3" <c:if test="${registro.tipo.id == 3}">selected</c:if>>Jantar</option>
            <option value="4" <c:if test="${registro.tipo.id == 4}">selected</c:if>>Lanche leve</option>
            <option value="5" <c:if test="${registro.tipo.id == 5}">selected</c:if>>Fruta</option>
          </select>
        </div>
        
        <div class="form-group">
          <label for="calorias">Calorias</label>
          <input type="number" id="calorias" name="calorias" class="form-control"  
          		 placeholder="Calorias" value="${registro.valorCalorico}">
        </div>
        
        <div class="form-group">
          <label for="descricao">Descrição</label>
          <textarea id="descricao" name="descricao" class="form-control" rows="3" 
          		placeholder="Descrição (opcional)">${registro.descricao}</textarea>
        </div>
        
        <div class="form-group">
          <label for="data">Data</label>
          <input type="datetime-local" id="data" name="data" class="form-control" value="${registro.dataRegistro}">
        </div>
        
        <input type="hidden" name="id" value="${registro.id}" />
        <input type="hidden" name="idUsuario" value="${registro.usuario.id}" />
        <input type="hidden" name="acao" value="editar" />
        
        <button type="submit" class="btn btn-primary">Salvar</button>
        <a href="${pageContext.servletContext.contextPath}/alimentacao?acao=listar" class="btn btn-danger">Cancelar</a>
        
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
