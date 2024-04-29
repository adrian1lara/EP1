package com.EP1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.EP1.model.Producto;

public class ProductoDAO {

	private String url;
	private Connection conexion;
	
	public ProductoDAO() {
		/*recuerden para jdbc hay que establecer la cadena de conexion*/
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
	
	public List<Producto> listarProductos() throws SQLException {
		List<Producto> listProducto = new ArrayList<Producto>();
		Statement stmt;
		stmt = conexion.createStatement();
		String sentenciaSQL ="SELECT * FROM Productos";
		ResultSet rs = stmt.executeQuery(sentenciaSQL);
		while (rs.next()) {
			int id = rs.getInt(1);
			String nombre = rs.getString(2);
			double precio = rs.getDouble(3);
			int stock = rs.getInt(4);
			String categoria = rs.getString(5);
			
			Producto objProducto = new Producto();
			objProducto.setId(id);
			objProducto.setNombre(nombre);
			objProducto.setPrecio(precio);
			objProducto.setStock(stock);
			objProducto.setCategoria(categoria);
			
			listProducto.add(objProducto);
		}
		rs.close();
		return listProducto;
	}
	
}
