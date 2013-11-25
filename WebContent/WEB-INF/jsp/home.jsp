<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Bienvenido al home - Session</title>
</head>
<body>
	<h2>Algún String: </h2>
		<p>${sValue}</p>
	<h2>Id de Sesión del Usuario: </h2>
		<p>${sid}</p>
	<h2>Variable de Sesión name: </h2>
			<p>${name}</p>
	<h2>Datos de la Persona: </h2>
			<p>${miUsuario.apellido}</p>
			<p>${miUsuario.nombre}</p>
			<p>${miUsuario.edad}</p>			
	<h2>Listado de las Personas en la Base de Datos: </h2>
		<c:forEach items="${misUsuarios}" var="unUsuario">
  			<li>${unUsuario.apellido}, ${unUsuario.nombre}</li>
  			<br/>  				
  		</c:forEach>
		<a href="${pageContext.request.contextPath}/">Login page</a>			
</body>
</html>