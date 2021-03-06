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
		<img alt="logotipo" src="http://localhost:8080/timelineme/imagenes/Logo.png" >
	</div>
	<div id="cuerpo">
		<h2 style="text-align:center;">Bienvenido ${username}</h2>
		<div id="cuerpoizq">
			
			<a href="http://localhost:8080/timelineme/empresas/timeline.do">
				Time Line - Empresas que sigo.
			</a>
			
			
			<h3 >Estas son las empresas que sigo</h3>
			<div class="contenedorempresa">
			
				<ul>
					<c:forEach items="${empresasQueSigo}" var="unaEmpresa">
			  			<li> 
			  				${unaEmpresa.empresa.nombre}
			  				<a href="http://localhost:8080/timelineme/seguir/timelineme_dejardeseguir.do?username=${username}&idempresa=${unaEmpresa.empresa.id}">�Dejar de Seguir!</a>
						</li>
						
					</c:forEach>
				</ul>
			</div>
		<br />
		<br />
		
		
		<h3 >Estas son las empresas que NO sigo</h3>
		<div class="contenedorempresa">
			
				<ul>
					<c:forEach items="${empresasQueNoSigo}" var="unaEmpresaNoseguida">
			  			<li> 
			  				${unaEmpresaNoseguida.empresa.nombre}
			  				
			  				<a href="http://localhost:8080/timelineme/seguir/timelineme_seguir.do?username=${username}&idempresa=${unaEmpresaNoseguida.empresa.id}">�Seguir!</a>
						</li>
					</c:forEach>
				</ul>
			
		
		</div>
		</div>
		<div id="cuerpoder">
			<h3>Este es el Timeline de tu Empresa ${empresa}</h3>
			<div class="contenedorempresa1">
			<c:forEach items="${message}" var="unComentario">
	  			<br/> 
	  			<p class='left both cien'>
	  				${unComentario.agente.nombre} a las <b>(${unComentario.fecha})</b> dijo:
	  				<br>
					<b>${unComentario.comentario}</b>
				</p>
			</c:forEach>
			</div>
			
			<br />
			
			<h4 >Escribe un comentario</h4>
			
			<p>
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
						
						<textarea type="text" class=" left both" cols="40" rows="5" name="comments" id="comments" placeholder="Escriba su comentario"></textarea>
						
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
	<a href="../perfil/inicio.do">Probar la sesi�n</a>
	 -->
	 </div>

	</div>
</body>
</html>