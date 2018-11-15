<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Perfil</title>

    <%@ include file="header.jsp" %>
    
  </head>
  <body>
    
	<jsp:include page="menu.jsp">
    	<jsp:param name="menu" value="0"/>
    </jsp:include>

    <div class="container text-center">
      <div class="row">
        <div class="col-sm-3"></div>
        <div class="content-box col-sm-6">
          <h1 class="center"><a href="perfil.jsp"><span class="fas fa-user"></span> João da Silva</a></h1>
          <ul class="atributos">
            <li><span class="atributo-chave">Idade:</span> <span class="atributo-valor">22 anos</span></li>
            <li><span class="atributo-chave">Peso:</span> <span class="atributo-valor">70 kg - 20/02/2018</span></li>
            <li><span class="atributo-chave">Altura:</span> <span class="atributo-valor">1,70 m</span></li>
            <li><span class="atributo-chave">IMC:</span> <span class="atributo-valor">24,2</span></li>
            <li>
              <span class="atributo-chave">Pressão:</span> 
              <span class="atributo-valor">120/80 mmHg - 20/02/2018</span>
            </li>
            <li style="margin-top: 20px">
              <a href="${pageContext.servletContext.contextPath}">
                <span class="fas fa-sign-out-alt"></span> Sair
              </a>
            </li>
          </ul>
        </div>
        <div class="col-sm-3"></div>
      </div>
    </div>

    <%@ include file="footer.jsp" %>
    
  </body>
</html>
