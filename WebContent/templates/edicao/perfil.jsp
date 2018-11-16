<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Cadastro</title>

    <%@ include file="../header.jsp" %>

  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="0"/>
    </jsp:include>

    <div class="container">
      <h1>Cadastro</h1>
      <form id="perfilForm">
        <div class="form-group">
          <label for="nome">Nome</label>
          <input type="text" class="form-control" id="nome" name="nome" placeholder="Nome completo">
        </div>
        <div class="form-group">
          <label for="data">Data de nascimento</label>
          <input type="date" id="data" name="data" max="3000-12-31" min="1000-01-01" class="form-control">
        </div>
        <div class="form-group">
          <label for="altura">Altura</label>
          <input type="number" class="form-control" id="altura" name="altura" placeholder="Altura em metros">
        </div>
        <div class="form-group">
          <label for="genero">Gênero (para cálculo do IMC)</label>
          <select id="genero" name="genero" class="form-control">
            <option value="">Selecione</option>
            <option value="F">Feminino</option>
            <option value="M">Masculino</option>
          </select>
        </div>
        <div class="form-group">
          <label for="email">Email</label>
          <input type="email" class="form-control" id="email" name="email" placeholder="Email">
        </div>
        <div class="form-group">
          <label for="senha">Senha</label>
          <input type="password" class="form-control" id="senha" name="senha" placeholder="Senha">
        </div>
        <div class="form-group">
          <label for="confirmaSenha">Confirmação da senha</label>
          <input type="password" class="form-control" id="confirmaSenha" name="confirmaSenha" placeholder="Confirme a senha">
        </div>
        <button type="submit" class="btn btn-primary">Cadastrar</button>
      </form>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
