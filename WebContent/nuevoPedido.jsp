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
	<a href="Pedido?opcionGET=listarPedidos">Lista de Pedidos</a>
	<br>
	<h1>Nuevo Pedido</h1>
	
	<form action="Pedido" method="post">
		<input type="hidden" name="opcionPOST" value="registrarPedido">
		<select name="cliente">
			<c:forEach var="cliente" items="${clienteList}">
				<option value="${cliente.id}">${cliente.nombre}</option>
			</c:forEach>
		</select>
		<br>
		<select name="producto">
			<c:forEach var="producto" items="${productList}">
				<option value="${producto.id}">${producto.nombre}</option>
			</c:forEach>
		</select>
		<br>
		
		<select name="estado">
			<option value="solicitado">Solicitado</option>
			<option value="en preparacion">En preparacion</option>
			<option value="entregado">Entregado</option>
		</select>
		<br>
		
		<label for="fechaPedido">Fecha del Pedido:</label>
    	<input type="date" id="fechaPedido" name="fechaPedido" required>
    	<br>
    	<label for="fechaEntrega">Fecha de Entrega</label>
    	<input type="date" id="fechaEntrega" name="fechaEntrega" required>
    	
    	<br>
		
		<select name="tipoPago" >
			<option value="Yape">Yape</option>
			<option value="Plin">Plin</option>
			<option value="Transferencia">Transferencia</option>
		</select>
   
    	<input type="number" name="cantidad" required>
    	
    	<button>enviar</button>
    	
	</form>
</body>
</html>