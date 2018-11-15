<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
      <form id="pesoForm">
        <div class="form-group">
          <label for="peso">Peso</label>
          <input type="number" id="peso" name="peso" class="form-control" placeholder="Peso em kilogramas">
        </div>
        <div class="form-group">
          <label for="data">Data</label>
          <input type="date" id="data" name="data" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Adicionar peso</button>
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
