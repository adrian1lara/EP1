<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Nuevo Cliente</h1>
		<br>
		<br>
		<form action="cliente" method="POST">
			<input type="hidden" name="opcionPOST" value="registrarCliente">
			Nombre: <input type="text" name="nombre"><br><br>
			Apellido: <input type="text" name="apellido"><br><br>
			Correo: <input type="text" name="correo"><br><br>
			Telefono: <input type="text" name="telefono"><br><br>
			Direccion: <input type="text" name="direccion"><br><br>
			Dni: <input type="text" name="dni"><br><br>
			<br>
			<br>
			<button>Grabar</button>
		</form>

</body>
</html>