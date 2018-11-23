<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/perfil?acao=dashboard">
  	HealthTrack <c:if test="${param.menu == 0}"><span class="sr-only">(atual)</span></c:if>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
      	<c:set var="url" value="${pageContext.servletContext.contextPath}/atividade?acao=listar" />
      	<c:set var="desc" value="Atividade Física" />
      	<c:choose>
	      	<c:when test="${param.menu == 1}">
	      		<a class="nav-link active" href="${url}">${desc}<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${url}">${desc}</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
      <li class="nav-item">
      	<c:set var="url" value="${pageContext.servletContext.contextPath}/peso?acao=listar" />
      	<c:set var="desc" value="Peso" />
      	<c:choose>
	      	<c:when test="${param.menu == 2}">
	      		<a class="nav-link active" href="${url}">${desc}<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${url}">${desc}</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
      <li class="nav-item">
      	<c:set var="url" value="${pageContext.servletContext.contextPath}/pressao-arterial?acao=listar" />
      	<c:set var="desc" value="Pressão Arterial" />
      	<c:choose>
	      	<c:when test="${param.menu == 3}">
	      		<a class="nav-link active" href="${url}">${desc}<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${url}">${desc}</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
      <li class="nav-item">
      	<c:set var="url" value="${pageContext.servletContext.contextPath}/alimentacao?acao=listar" />
      	<c:set var="desc" value="Alimentação" />
      	<c:choose>
	      	<c:when test="${param.menu == 4}">
	      		<a class="nav-link active" href="${url}">${desc}<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${url}">${desc}</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
    </ul>
    <c:if test="${sessionScope.usuarioLogado != null}">
	    <span class="navbar-text nav-button">
	      <a href="${pageContext.servletContext.contextPath}/perfil?acao=perfil">
	      	<span class="fas fa-user"></span> ${sessionScope.usuarioLogado.nome}
      	  </a>
	    </span>
    </c:if>
  </div>
</nav>