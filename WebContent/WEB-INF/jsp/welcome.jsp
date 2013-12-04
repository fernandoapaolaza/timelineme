<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<title>Bienvenido ${username}</title>
<link href="http://localhost:8080/timelineme/css/estilos.css" type="text/css" rel="stylesheet">

</head>
<body>
	<div id="contenedor">
	<div id="encabezado">
		<h1>TIMELINE-ME</h1>
		<h2 class="left both cien">Bienvenido ${username}</h2>
	</div>
	<div id="cuerpo">
		
		<div id="cuerpoizq">
		
		
		<a href="http://localhost:8080/timelineme/empresas/timeline.do">
			Time Line - Empresas que sigo.
		</a>
		<br>
		
		<h3 class="left both cien">Estas son las empresas que sigo</h3>
		
		<div class="empresasALasQueSigo left both">
			<ul>
				<c:forEach items="${empresasQueSigo}" var="unaEmpresa">
		  			<li> 
		  				${unaEmpresa.empresa.nombre}
		  				<a href="http://localhost:8080/timelineme/seguir/dejardeseguir.do?username=${username}&idempresa=${unaEmpresa.empresa.id}">¡Dejar de Seguir!</a>
					</li>
					
				</c:forEach>
			</ul>
		</div>
		
		<h3 class="left both cien">Estas son las empresas que NO sigo</h3>
		
		<div class="empresasALasQueSigo left both">
			<ul>
				<c:forEach items="${empresasQueNoSigo}" var="unaEmpresaNoseguida">
		  			<li> 
		  				${unaEmpresaNoseguida.empresa.nombre}
		  				
		  				<a href="http://localhost:8080/timelineme/seguir/seguir.do?username=${username}&idempresa=${unaEmpresaNoseguida.empresa.id}">¡Seguir!</a>
					</li>
				</c:forEach>
			</ul>
		</div>
		
		<div id="cuerpoder">
		<h3 class="left both cien">Este es el Timeline de tu Empresa ${empresa}</h3>
		
		<c:forEach items="${message}" var="unComentario">
  			<br/> 
  			<p class='left both cien'>
  				${unComentario.agente.nombre} a las <b>(${unComentario.fecha})</b> dijo:
  				<br>
				<b>${unComentario.comentario}</b>
			</p>
		</c:forEach>
		
		<br />
		<br />
		<br />
		
		<h4 class="left both  cien">Escribe un comentario</h4>
		<br />
		
		<p class="left both  cien">
			<i>
				<span class="rojo">
					<b>${nombre}</b>
				</span> dice:
			</i>
		</p>
		
		<form action="http://localhost:8080/timelineme/comments/comment.do" class="form-horizontal" method='POST'>
			<div class="control-group  left both  cien">
				<label class="control-label  cien left both" for="inputComentario">Comentario</label>
				<div class="controls  left both  cien">
					<input type="hidden" name="username" id="username" value="${username}">
					
					<textarea type="text" class=" left both" name="comments" id="comments" placeholder="Escriba su comentario"></textarea>
					
				</div>
			</div>
			<div class="control-group  left both">
				<div class="controls  left both">
					<button type="submit" class="btn  left both">Ingresar</button>
				</div>
			</div>
		</form>
	</div>
	<!-- 
	<a href="../perfil/inicio.do">Probar la sesión</a>
	 -->
	 </div>
	 </div>
	</div>
</body>
</html>