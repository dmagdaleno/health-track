<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

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
      <h1>Registros de Pesagem</h1>
      
      <c:if test="${not empty sucesso }">
		<div class="alert alert-success">${sucesso}</div>
	  </c:if>
	  <c:if test="${not empty erro }">
		<div class="alert alert-danger">${erro}</div>
	  </c:if>
	  
      <div class="table-responsive">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Peso</th>
              <th scope="col">Data</th>
              <th scope="col" colspan="2" class="center">Ações</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${registros}" var="registro">
	          <tr>
	            <td>${registro.peso} kg</td>
	            
	            <td>
	              <javatime:format value="${registro.dataRegistro}" pattern="dd/MM/yyyy" />
	            </td>
                
                <td class="center">
                  <c:url value="peso" var="link">
                    <c:param name="acao" value="editar"/>
                    <c:param name="id" value="${registro.id}"/>
                  </c:url>
                  <a href="${link}" class="btn btn-primary btn-xs" title="Alterar registro">Editar</a>
                </td>
                
                <td class="center">
                  <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" 
                  		data-target="#excluirModal" onclick="idExluir.value = ${registro.id}">
		            Excluir
		          </button>
                </td>
                
	          </tr>
	        </c:forEach>
          </tbody>
        </table>
      </div>
      
      <a href="${pageContext.servletContext.contextPath}/templates/cadastro/peso.jsp">
        <button type="button" class="btn btn-primary">Adicionar</button>
      </a>
    </div>

    <%@ include file="../footer.jsp" %>
    
    <div class="modal fade" id="excluirModal" tabindex="-1" role="dialog" 
    		aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">Confirmação</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">×</span>
	        </button>
	      </div>
	      <div class="modal-body">
	            Deseja realmente excluir o registro?
	      </div>
	      <div class="modal-footer">
	        <form action="peso" method="post">
	          <input type="hidden" name="acao" value="excluir">
	          <input type="hidden" name="id" id="idExluir">
	          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
	          <button type="submit" class="btn btn-danger">Excluir</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
    
  </body>
</html>
