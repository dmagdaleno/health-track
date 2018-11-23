<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

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
          <h1 class="center"><span class="fas fa-user"></span> ${usuarioLogado.nome}</h1>
          <ul class="atributos">
            <li><span class="atributo-chave">Idade:</span> <span class="atributo-valor">${usuarioLogado.idade} anos</span></li>
            
            <li>
              <span class="atributo-chave">Peso:</span> 
              <span class="atributo-valor">
                ${ultimoRegistroPeso.peso} kg - 
                <javatime:format value="${ultimoRegistroPeso.dataRegistro}" pattern="dd/MM/yyyy" />
              </span>
            </li>
            
            <li><span class="atributo-chave">Altura:</span> <span class="atributo-valor">${usuarioLogado.altura} m</span></li>
            <li><span class="atributo-chave">IMC:</span> <span class="atributo-valor">${imc}</span></li>
            
            <li>
              <span class="atributo-chave">Pressão:</span> 
              <span class="atributo-valor">
                ${ultimoRegistroPressao.pressaoMaxima}/${ultimoRegistroPressao.pressaoMinima} mmHg - 
                <javatime:format value="${ultimoRegistroPressao.dataRegistro}" pattern="dd/MM/yyyy" />
              </span>
            </li>
            
            <li style="margin-top: 20px">
              <button type="button" class="btn btn-danger btn-xs" data-toggle="modal" data-target="#sairModal">Sair</button>
            </li>
            
          </ul>
        </div>
        <div class="col-sm-3"></div>
      </div>
    </div>

    <%@ include file="footer.jsp" %>
    
    <div class="modal fade" id="sairModal" tabindex="-1" role="dialog" 
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
	            Deseja realmente sair do sistema?
	      </div>
	      <div class="modal-footer">
	        <form action="${pageContext.servletContext.contextPath}/login" method="post">
	          <input type="hidden" name="acao" value="sair">
	          <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
	          <button type="submit" class="btn btn-danger">Sair</button>
	        </form>
	      </div>
	    </div>
	  </div>
	</div>
    
  </body>
</html>
