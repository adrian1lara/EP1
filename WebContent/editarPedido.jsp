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
	<h1>Actualizar Pedido</h1>
	<br>
	<form action="Pedido" method="POST">
		<input type="hidden" name="opcionPOST" value="editarPedido">
		<input type="hidden" name="id" value="${objPedido.id}">
		
		<select name="cliente">
			<c:forEach var="cliente" items="${clienteList}">
				<option value="${cliente.id}" ${cliente.id == objPedido.idCliente ? "selected" : ""}>${cliente.nombre}</option>
			</c:forEach>
		</select>
		<br>
		<select name="producto">
			<c:forEach var="producto" items="${productList}">
				<option value="${producto.id}" ${producto.id == objPedido.idProducto ? "selected" : ""}>${producto.nombre}</option>
			</c:forEach>
		</select>
		<br>
		
		<select name="estado">
			<option value="solicitado" ${objPedido.estadoPedido.equals("solicitado") ? "selected" : "" }>Solicitado</option>
			<option value="en preparacion" ${objPedido.estadoPedido.equals("en preparacion") ? "selected" : "" }>En preparacion</option>
			<option value="entregado" ${objPedido.estadoPedido.equals("entregado") ? "selected" : "" }>Entregado</option>
		</select>
		
		<br>
		
		<label for="fechaPedido">Fecha del Pedido:</label>
    	<input type="date" id="fechaPedido" name="fechaPedido" value="${objPedido.fechaPedido}" required>
    	<br>
    	<label for="fechaEntrega">Fecha de Entrega</label>
    	<input type="date" id="fechaEntrega" name="fechaEntrega"  value="${objPedido.fechaEntrega}" required>
    	
    	<br>
		
		<select name="tipoPago" >
			<option value="Yape"  ${objPedido.tipoPago.equals("Yape") ? "selected" : ""}>Yape</option>
			<option value="Plin" ${objPedido.tipoPago.equals("Plin") ? "selected" : ""} >Plin</option>
			<option value="Transferencia" ${objPedido.tipoPago.equals("Transferencia") ? "selected" : ""}>Transferencia</option>
		</select>
   
    	<input type="number" name="cantidad" value="${objPedido.cantidad}" required>
    	
    	<button>guardar</button>
	</form>

</body>
</html>