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
	<h1>Lista de Pedidos</h1>
	<br>
	<br>
	<table>
			<tr>
				<td>Id</td>
				<td>producto</td>
				<td>cliente</td>
				<td>estado</td>
				<td>fechaPedido</td>
				<td>fechaEntrega</td>
				<td>tipo de pago</td>
				<td>cantidad</td>
				<td>Eliminar</td>
				<td>Editar</td>
			</tr>
			<c:forEach var="pedido" items="${pedidoList}">
				<tr>
				<td>${pedido.id}</td>
				<td>${pedido.idProducto}</td>
				<td>${pedido.idCliente}</td>
				<td>${pedido.estadoPedido}</td>
				<td>${pedido.fechaPedido}</td>
				<td>${pedido.fechaEntrega}</td>
				<td>${pedido.tipoPago}</td>
				<td>${pedido.cantidad}</td>
				<td>
				<a href="Pedido?opcionGET=eliminarPedido&id=${pedido.id}">Eliminar</a>
				</td>
				<td>
				<a href="Pedido?opcionGET=mostrarEditarPedido&id=${pedido.id}">Editar</a>
				</td>
				</tr>
			</c:forEach>
		</table>
		
</body>
</html>