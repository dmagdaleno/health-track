<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Atividade Física</title>

    <%@ include file="../header.jsp" %>
    
  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="1"/>
    </jsp:include>

    <div class="container">
      <h1>Cadastro de Atividade Física</h1>
      <form id="atividadeForm">
        <div class="form-group">
          <label for="tipo">Tipo</label>
          <select id="tipo" name="tipo" class="form-control">
            <option selected>Caminhada</option>
            <option>Corrida</option>
            <option>Pedalada</option>
            <option>Musculação</option>
          </select>
        </div>
        <div class="form-group">
          <label for="caloria">Calorias</label>
          <input type="number" id="caloria" name="caloria" class="form-control" placeholder="Calorias">
        </div>
        <div class="form-group">
          <label for="descricao">Descrição</label>
          <textarea id="descricao" name="descricao" class="form-control" rows="3" placeholder="Descrição (opcional)">
          </textarea>
        </div>
        <div class="form-group">
          <label for="data">Data</label>
          <input type="datetime-local" id="data" name="data" class="form-control">
        </div>
        <button type="submit" class="btn btn-primary">Adicionar atividade física</button>
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
