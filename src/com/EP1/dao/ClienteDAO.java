package com.EP1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.EP1.model.Cliente;


public class ClienteDAO {

	private String url;
	private Connection conexion;
	
	public ClienteDAO() {
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
	
	public Cliente buscarClientexId(int idBuscado) throws SQLException {
		Cliente objCliente = new Cliente();
		CallableStatement cs;
		cs = conexion.prepareCall("{Call SP_buscarClientexId(?)}");
		cs.setInt(1, idBuscado);
		ResultSet rs = cs.executeQuery();
		while (rs.next()) {
			int id = rs.getInt(1);
			String nombre = rs.getString(2);
			String apellido = rs.getString(3);
			String correo = rs.getString(4);
			String telefono = rs.getString(5);
			String direccion = rs.getString(6);
			String dni = rs.getString(7);
			
			objCliente.setNombre(nombre);
			objCliente.setApellido(apellido);
			objCliente.setCorreo(correo);
			objCliente.setDireccion(direccion);
			objCliente.setDni(dni);
			objCliente.setId(id);
			objCliente.setTelefono(telefono);
		}
		return objCliente;
	}
	
	public void eliminarCliente(int id) throws SQLException {
		String sentenciaSQL = "delete from Clientes where id = ?";
		PreparedStatement pstmt;
		pstmt = conexion.prepareStatement(sentenciaSQL);
		pstmt.setInt(1, id);
		pstmt.execute();
	}
	
	public List<Cliente> buscarClientes(String nombreBuscar) throws SQLException {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Statement stmt;
		stmt = conexion.createStatement();
		String sentenciaSQL ="Select * from Clientes where NombreCliente like '%" + nombreBuscar + "%'";
		ResultSet rs = stmt.executeQuery(sentenciaSQL);
		while (rs.next()) {
			int id = rs.getInt(1);
			String nombre = rs.getString(2);
			String apellido = rs.getString(3);
			String correo = rs.getString(4);
			String telefono = rs.getString(5);
			String direccion = rs.getString(6);
			String dni = rs.getString(7);
			
			Cliente objCliente = new Cliente();
			objCliente.setNombre(nombre);
			objCliente.setApellido(apellido);
			objCliente.setCorreo(correo);
			objCliente.setDireccion(direccion);
			objCliente.setDni(dni);
			objCliente.setId(id);
			objCliente.setTelefono(telefono);
			listaClientes.add(objCliente);
		}
		rs.close();
		return listaClientes;
	}
	
	public void actualizarCliente(int id, String nombre,String apellido,String correo,String telefono,String direccion,String dni, String estado) throws SQLException {
		String sentenciaSQL = "update Clientes set NombreCliente=?, ApellidoCliente=?, CorreoCliente=?, TelefonoCliente=?, DireccionCliente=?, Dni=? where id=?";
		PreparedStatement pstmt;
		pstmt = conexion.prepareStatement(sentenciaSQL);
		pstmt.setString(1, nombre);
		pstmt.setString(2, apellido);
		pstmt.setString(3, correo);
		pstmt.setString(4, telefono);
		pstmt.setString(5, direccion);
		pstmt.setString(6, dni);
		pstmt.setInt(7, id);
		pstmt.execute();
	}
	
	public List<Cliente> listarClientes() throws SQLException {
		List<Cliente> listaClientes = new ArrayList<Cliente>();
		Statement stmt;
		stmt = conexion.createStatement();
		String sentenciaSQL ="SELECT * FROM Clientes";
		ResultSet rs = stmt.executeQuery(sentenciaSQL);
		while (rs.next()) {
			int id = rs.getInt(1);
			String nombre = rs.getString(2);
			String apellido = rs.getString(3);
			String correo = rs.getString(4);
			String telefono = rs.getString(5);
			String direccion = rs.getString(6);
			String dni = rs.getString(7);
			
			Cliente objCliente = new Cliente();
			objCliente.setNombre(nombre);
			objCliente.setApellido(apellido);
			objCliente.setCorreo(correo);
			objCliente.setDireccion(direccion);
			objCliente.setDni(dni);
			objCliente.setId(id);
			objCliente.setTelefono(telefono);
			listaClientes.add(objCliente);
		}
		rs.close();
		return listaClientes;
	}
	
	
	public void registrarCliente(String nombre,String apellido,String correo,String telefono,String direccion,String dni) throws SQLException {
		CallableStatement cs;
		cs = conexion.prepareCall("{Call SP_registrarCliente(?,?,?,?,?,?)}");
		cs.setString(1, nombre);
		cs.setString(2, apellido);
		cs.setString(3, correo);
		cs.setString(4, telefono);
		cs.setString(5, direccion);
		cs.setString(6, dni);
		cs.execute();
	}
}
