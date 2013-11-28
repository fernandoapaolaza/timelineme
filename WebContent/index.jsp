<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login</title>
</head>
<body>
<%-- 	<jsp:useBean id="userLogin" scope="request" --%>
<%-- 		class="ar.edu.unlam.talleweb.timelineme.beans.UserBean" /> --%>

	<div class="alert">
		${message}
	</div>
	<form action="login/auth.do" class="form-horizontal" method='POST'>
		<div class="control-group">
			<label class="control-label" for="inputUsername">Nombre de usuario</label>
			<div class="controls">
				<input type="text" name="username" id="inputUsername" placeholder="jack">
				
			</div>
		</div>
		<br />
		<div class="control-group">
			<label class="control-label" for="inputPassword">Contraseņa</label>
			<div class="controls">
				<input type="password" name="password" id="inputPassword" placeholder="Contraseņa">
			
			</div>
		</div>
		<br />
		<br />
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">Ingresar</button>
			</div>
		</div>
	</form>
</body>
</html>