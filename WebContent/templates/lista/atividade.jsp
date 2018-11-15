<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Atividade Fí­sica</title>

    <%@ include file="../header.jsp" %>
    
  </head>
  <body>
    
    <jsp:include page="../menu.jsp">
    	<jsp:param name="menu" value="1"/>
    </jsp:include>

    <div class="container">
      <h1>Registros de Atividade Física</h1>
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
              <td>Caminhada</td>
              <td>Caminhada noturna</td>
              <td>100</td>
              <td>20/02/2018 20:00</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
            <tr>
              <td>Corrida</td>
              <td>Corrida no parque</td>
              <td>350</td>
              <td>18/02/2018 08:00</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
            <tr>
              <td>Caminhada</td>
              <td>Caminhada noturna</td>
              <td>120</td>
              <td>15/02/2018 20:00</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
          </tbody>
        </table>
      </div>
      <a href="../cadastro/atividade.jsp"><button type="button" class="btn btn-primary">Adicionar atividade fí­sica</button></a>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
