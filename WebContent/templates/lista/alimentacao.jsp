<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
      <h1>Registros de Alimentos Consumidos</h1>
      <div class="table-responsive">
        <table class="table">
          <thead>
            <tr>
              <th scope="col">Tipo</th>
              <th scope="col">Descrição</th>
              <th scope="col">Calorias</th>
              <th scope="col">Data</th>
              <th scope="col" colspan="2" class="center">Ações</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <td>Jantar</td>
              <td>Salada</td>
              <td>535</td>
              <td>20/02/2018 19:00</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
            <tr>
              <td>Almoço</td>
              <td>Arroz, feijão, salada e carne</td>
              <td>650</td>
              <td>20/02/2018 12:00</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
            <tr>
              <td>Café da manhã</td>
              <td>Pão de queijo e café</td>
              <td>320</td>
              <td>20/02/2018 08:00</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
          </tbody>
        </table>
      </div>
      <a href="../cadastro/alimentacao.jsp">
      	<button type="button" class="btn btn-primary">Adicionar alimentação</button>
      </a>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
