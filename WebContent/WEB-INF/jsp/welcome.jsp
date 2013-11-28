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
		<h2 class="left both cien">Bienvenido ${nombre}</h2>
		
		<a href="http://localhost:8080/timelineme/empresas/timeline.do">
			Time Line - Empresas que sigo.
		</a>
		<br>
		<a href="http://localhost:8080/timelineme/empresas/timelinegeneral.do">
			Time Line - General.
		</a>
		
		<h3 class="left both cien">Estas son las empresas que sigo</h3>
		<div class="empresasALasQueSigo left both">
			${empresasquesigo}
		</div>
		
		<h3 class="left both cien">Este es el Timeline de tu Empresa ${empresa}</h3>
		<br />
		<br />
		<div class="comentarios left both  cien">
		${message}
		</div>
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
					<input type="hidden" name="username" id="username" value="${nombre}">
					
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
	<a href="../perfil/inicio.do">Probar la sesi�n</a>
	 -->
	
</body>
</html>