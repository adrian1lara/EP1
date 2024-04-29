<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Editar Cliente</h1>
		<br>
		<br>
		<form action="cliente" method="POST">
			<input type="hidden" name="opcionPOST" value="editarCliente">
			<input type="hidden" name="id" value="${objCliente.id }">
			Nombre: <input type="text" name="nombre" value="${objCliente.nombre}"><br><br>
			Apellido: <input type="text" name="apellido" value="${objCliente.apellido}"><br><br>
			Correo: <input type="text" name="correo" value="${objCliente.correo}"><br><br>
			Telefono: <input type="text" name="telefono" value="${objCliente.telefono}"><br><br>
			Direccion: <input type="text" name="direccion" value="${objCliente.direccion}"><br><br>
			Dni: <input type="text" name="dni" value="${objCliente.dni}"><br><br>
			<br>
			<br>
			<button>Grabar</button>
		</form>

</body>
</html>