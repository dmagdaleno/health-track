<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<!Doctype html>
<html lang="pt">
  <head>
    <title>HealthTrack - Dashboard</title>

    <%@ include file="header.jsp" %>
    
  </head>
  <body>
  
    <jsp:include page="menu.jsp">
    	<jsp:param name="menu" value="0"/>
    </jsp:include>

    <div class="container text-center">
      <div class="row">
        <div class="col-sm-4 well">
          <div class="well content-box">
            
            <c:if test="${not empty erro }">
			  <div class="alert alert-danger">${erro}</div>
		    </c:if>
          
            <h1 class="center">
              <a href="${pageContext.servletContext.contextPath}/perfil?acao=perfil">
                <span class="fas fa-user"></span> ${usuarioLogado.nome}
              </a>
            </h1>
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
            
            </ul>
          </div>
          <div class="well content-box">
            <h1 class="center"><a href="${pageContext.servletContext.contextPath}/alimentacao?acao=listar">Alimentação</a></h1>

            <c:forEach items="${alimentacoes}" var="alimentacao">
	          
	          <div class="card w-75 mb-3">
                <div class="card-body">
                  <span class="card-hd-title">${alimentacao.tipo.descricao}</span>
                  <span class="card-hd-text">${alimentacao.valorCalorico} kcal</span>
                </div>
              </div>
              
	        </c:forEach>
            
            <p class="center link"><a href="${pageContext.servletContext.contextPath}/alimentacao?acao=listar">Ver mais...</a></p>
          </div>
        </div>
        <div class="col-sm-8">
          <div class="row">
            <div class="col-sm-12">
              <div class="well content-box">
                <h1 class="center"><a href="${pageContext.servletContext.contextPath}/atividade?acao=listar">Últimas atividades</a></h1>

                <c:forEach items="${atividades}" var="atividade">
	            
                  <div class="card w-75 mb-3">
                    <div class="card-header">
                      <span class="card-hd-title">${atividade.descricao}</span>
                      <span class="card-hd-text">
                        <javatime:format value="${atividade.dataRegistro}" pattern="dd/MM/yyyy - HH:mm" />
                      </span>
                    </div>
                    <div class="card-body">
                      <p class="card-text">Tipo: ${atividade.tipo.descricao}</p>
                      <p class="card-text">${atividade.gastoCalorico} kcal</p>
                    </div>
                  </div>
                  
	            </c:forEach>

                <p class="center link">
                  <a href="${pageContext.servletContext.contextPath}/atividade?acao=listar">Ver mais...</a>
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <%@ include file="footer.jsp" %>
    
  </body>
</html>
