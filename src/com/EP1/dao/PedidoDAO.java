package com.EP1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.EP1.model.Pedido;

public class PedidoDAO {
	
	private String url;
	private Connection conexion;
	
	public PedidoDAO() {
		// This is the connection to the local database
				this.url = "jdbc:sqlserver://localhost:1433;databaseName=isilEP1;user=sa;password=sauser;encrypt=true;trustServerCertificate=true;";
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
					this.conexion = DriverManager.getConnection(url);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
	}
	
	public void actualizarPedido(int id, int idProducto, int idCliente, String estadoPedido, Date fechaPedido, Date fechaEntrega, String tipoPago, int cantidad) throws SQLException {
		CallableStatement cs;
		cs = conexion.prepareCall("{CALL SP_actualizarPedido(?, ?, ?, ?, ?, ?, ?, ?)}");
		cs.setInt(1, id);
		cs.setInt(2, idProducto);
	    cs.setInt(3, idCliente);
	    cs.setString(4, estadoPedido);
	    cs.setDate(5, fechaPedido);
	    cs.setDate(6, fechaEntrega);
	    cs.setString(7, tipoPago);
	    cs.setInt(8, cantidad);
	    cs.execute();
	}
	
	public Pedido buscarPedidoPorId (int idBuscado) throws SQLException {
		Pedido objPedido = new Pedido();
		CallableStatement cs;
		cs = conexion.prepareCall("{CALL SP_buscarPedidoPorId(?)}");
		cs.setInt(1, idBuscado);
		ResultSet rs = cs.executeQuery();
		while(rs.next()) {
			int id = rs.getInt(1);
			int idProducto = rs.getInt(2);
			int idCliente = rs.getInt(3);
			String estadoPedido = rs.getString(4);
			Date fechaPedido = rs.getDate(5);
			Date fechaEntrega = rs.getDate(6);
			String tipoPago = rs.getString(7);
			int cantidad = rs.getInt(8);
			
			objPedido.setId(id);
			objPedido.setIdProducto(idProducto);
			objPedido.setIdCliente(idCliente);
			objPedido.setEstadoPedido(estadoPedido);
			objPedido.setFechaPedido(fechaPedido);
			objPedido.setFechaEntrega(fechaEntrega);
			objPedido.setTipoPago(tipoPago);
			objPedido.setCantidad(cantidad);
		}
		return objPedido;
	}
	
	public void eliminarPedido(int id) throws SQLException {
		String query = "DELETE FROM Pedidos WHERE id = ?";
		PreparedStatement pstmt;
		pstmt = conexion.prepareStatement(query);
		pstmt.setInt(1, id);
		pstmt.execute();
	}
	
	public void registrarPedido(int idProducto, int idCliente, String estadoPedido, Date fechaPedido, Date fechaEntrega, String tipoPago, int cantidad) throws SQLException {
		CallableStatement cs;
		cs = conexion.prepareCall("{CALL SP_registrarPedido(?, ? , ? , ? , ?, ?, ?)}");
		cs.setInt(1, idProducto);
	    cs.setInt(2, idCliente);
	    cs.setString(3, estadoPedido);
	    cs.setDate(4, fechaPedido);
	    cs.setDate(5, fechaEntrega);
	    cs.setString(6, tipoPago);
	    cs.setInt(7, cantidad);
	    cs.execute();
	}
	
	public List<Pedido> listarPedidos() throws SQLException {
		List<Pedido> pedidoList = new ArrayList<Pedido>();
		Statement stmt;
		stmt = conexion.createStatement();
		String query = "SELECT * FROM Pedidos";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			int id = rs.getInt(1);
			int idProducto = rs.getInt(2);
			int idCliente = rs.getInt(3);
			String estadoPedido = rs.getString(4);
			Date fechaPedido = rs.getDate(5);
			Date fechaEntrega = rs.getDate(6);
			String tipoPago = rs.getString(7);
			int cantidad = rs.getInt(8);
			
			Pedido objPedido = new Pedido();
			objPedido.setId(id);
			objPedido.setIdProducto(idProducto);
			objPedido.setIdCliente(idCliente);
			objPedido.setEstadoPedido(estadoPedido);
			objPedido.setFechaPedido(fechaPedido);
			objPedido.setFechaEntrega(fechaEntrega);
			objPedido.setTipoPago(tipoPago);
			objPedido.setCantidad(cantidad);
			
			pedidoList.add(objPedido);
		}
		rs.close();
		return pedidoList;
	}
}
