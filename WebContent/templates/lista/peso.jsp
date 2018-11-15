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
      <h1>Registros de Pesagem</h1>
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
            <tr>
              <td>70 kg</td>
              <td>20/02/2018</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
            <tr>
              <td>72,1 kg</td>
              <td>10/02/2018</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
            <tr>
              <td>73,4 kg</td>
              <td>08/02/2018</td>
              <td class="center"><a href="#" title="Alterar registro"><span class="fas fa-edit"></span></a></td>
              <td class="center"><a href="#" title="Remover registro"><span class="fas fa-eraser"></span></a></td>
            </tr>
          </tbody>
        </table>
      </div>
      <a href="../cadastro/peso.jsp"><button type="button" class="btn btn-primary">Adicionar peso</button></a>
    </div>

    <%@ include file="../footer.jsp" %>
    
  </body>
</html>
