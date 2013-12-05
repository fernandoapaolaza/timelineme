<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bienvenido ${nombre}</title>
<link href="http://localhost:8080/timelineme/css/estilos.css" type="text/css" rel="stylesheet">
</head>
<body>
	<div id="contenedor">
		<div id="encabezado">
			<img alt="logotipo" src="http://localhost:8080/timelineme/imagenes/Logo.png" >
		</div>
		<div id="cuerpo">
		<a href="http://localhost:8080/timelineme/empresas/timelineme.do">
			Time Line - Me.
		</a>
		<br />
		<div style="width:100% margin: 0 auto;">
		<div id="cuerpoizq" style="width: 90%;margin: 0 auto; ">
		
		
			
			<h3 class="left both cien">Este es el Timeline de las empresas a las que sigues.</h3>
			<br />
			<br />
			<div class="contenedorempresa1" style="width: 750px;">
			<div class="comentarios left both  cien">
			<c:forEach items="${message}" var="unComentario">
	  			<br/> 
	  			<p class='left both cien'>
	  				${unComentario.agente.nombre} a las <b>(${unComentario.fecha})</b> dijo:
	  				<br>
					<b>${unComentario.comentario}</b>
				</p>
			</c:forEach>
			</div>
			</div>
		</div>
		<br />
		
		
		</div>
		</div>
	</div>
		
</body>
</html>