<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	body{
		margin:0px auto;padding:0px;width:100%;
	}
	
	div.contenedorPrincipal{
		width:700px;margin:0px auto;margin-top:20px;
	}
	
	h2{
		padding:0px;
		margin:0px;
		color:#000;
		font-size:20px;
	}
	
	h3{
		padding:0px;
		margin:0px;
		color:#000;
		font-size:15px;
		margin-bottom:15px;
	}
	
	h4{
		padding:0px;
		margin:0px;
		color:#000;
		font-size:13px;
	}
	
	p {
		padding: 0px;
		font-size: 12px;
		line-height: 12px;
		margin: 0px;
	}
	
	.rojo{
		color:red;
	}
	.left{
		float:left;
	}
	
	.both{
		clear:both;
	}
	
	.cien{
		width:100%;
	}
	
	textarea{
		width:500px;
		height:150px;
		
	}
	
	label{
		font-size:12px;
	}
</style>
<title>Bienvenido ${nombre}</title>
</head>
<body>
	<div class="contenedorPrincipal">
		
		<a href="http://localhost:8080/timelineme/empresas/timelineme.do">
			Time Line - Me.
		</a>
		<br />
		
		
			
		<h3 class="left both cien">Este es el Timeline de las empresas a las que sigues.</h3>
		<br />
		<br />
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
		<br />
		
		
		
	</div>
		
</body>
</html>