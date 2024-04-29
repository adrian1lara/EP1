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
		<h1>Gestion de Clientes</h1>
		<br>
		<br>
		<h2>Criterios de Busqueda</h2>
		<br>
		<br>
		<form action="cliente" method="GET">
		<input type="hidden" name="opcionGET" value="buscarClientes">
			nombre: <input type="text" name="nombre">
			<button>Buscar</button>
		</form>
		<br>
		<br>
		<h2>Resultado de busqueda</h2>
		<br>
		<br>
		<table>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>Apellido</td>
				<td>Correo</td>
				<td>Telefono</td>
				<td>Direccion</td>
				<td>Dni</td>
				<td>Acciones</td>
			</tr>
			<c:forEach var="cliente" items="${listaClientes}">
				<tr>
					<td>${usuario.id}</td>
					<td>${cliente.nombre}</td>
					<td>${cliente.apellido}</td>
					<td>${cliente.correo}</td>
					<td>${cliente.telefono}</td>
					<td>${cliente.direccion}</td>
					<td>${cliente.dni}</td>
					<td>
					     <a href="cliente?opcionGET=mostrarEditarCliente&id=${cliente.id }">Editar</a>
						 <a href="cliente?opcionGET=eliminarCliente&id=${cliente.id}">Eliminar</a>
					 </td>
				</tr>
			</c:forEach>
		</table>
		<br>
		<br>
		<form action="cliente" method="POST">
			<input type="hidden" name="opcionPOST" value="mostrarNuevoCliente">
			<button>Nuevo</button>
		</form>
	
	</body>
</html>