<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/templates/dashboard.jsp">
  	HealthTrack <c:if test="${param.menu == 0}"><span class="sr-only">(atual)</span></c:if>
  </a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarText">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
      	<c:choose>
	      	<c:when test="${param.menu == 1}">
	      		<a class="nav-link active" href="#">Atividade Física<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${pageContext.servletContext.contextPath}/templates/lista/atividade.jsp">Atividade Física</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
      <li class="nav-item">
      	<c:choose>
	      	<c:when test="${param.menu == 2}">
	      		<a class="nav-link active" href="#">Peso<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${pageContext.servletContext.contextPath}/templates/lista/peso.jsp">Peso</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
      <li class="nav-item">
      	<c:choose>
	      	<c:when test="${param.menu == 3}">
	      		<a class="nav-link active" href="#">Pressão Arterial<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${pageContext.servletContext.contextPath}/templates/lista/pressao-arterial.jsp">Pressão Arterial</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
      <li class="nav-item">
      	<c:choose>
	      	<c:when test="${param.menu == 4}">
	      		<a class="nav-link active" href="#">Alimentação<span class="sr-only">(atual)</span></a>
	      	</c:when>
	      	<c:otherwise>
		        <a class="nav-link" href="${pageContext.servletContext.contextPath}/templates/lista/alimentacao.jsp">Alimentação</a>
	      	</c:otherwise>
      	</c:choose>
      </li>
    </ul>
    <span class="navbar-text nav-button">
      <a href="${pageContext.servletContext.contextPath}/templates/perfil.jsp"><span class="fas fa-user"></span> João da Silva</a>
    </span>
  </div>
</nav>