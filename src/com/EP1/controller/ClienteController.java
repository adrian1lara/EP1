package com.EP1.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EP1.dao.ClienteDAO;
import com.EP1.model.Cliente;

/**
 * Servlet implementation class ClienteController
 */
@WebServlet("/cliente")
public class ClienteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClienteDAO objClienteDAO = new ClienteDAO();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcionGET");
		switch (opcion) {
			case "buscarClientes": {
				try {
					buscarClientes(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "eliminarCliente" : {
				try {
					eliminarCliente(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "mostrarEditarCliente":{
				try {
					mostrarEditarCliente(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	private void mostrarEditarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		Cliente objCliente = objClienteDAO.buscarClientexId(id);
		request.setAttribute("objCliente", objCliente);
		String nuevaPagina = "/editarCliente.jsp";
		/*De esta forma re-dirijo mi sistema a una pagina en particular*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		objClienteDAO.eliminarCliente(id);
		String nuevaPagina = "/gestionClientes.jsp";
		/*De esta forma re-dirijo mi sistema a una pagina en particular*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void buscarClientes(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String nombre = request.getParameter("nombre");
		List<Cliente> listaClientes = objClienteDAO.buscarClientes(nombre);
		request.setAttribute("listaClientes", listaClientes);
		String nuevaPagina = "/gestionClientes.jsp";
		/*De esta forma re-dirijo mi sistema a una pagina en particular*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcionPOST");
		switch (opcion) {
			case "mostrarNuevoCliente": {
				mostrarNuevoCliente(request, response);
				break;
			}
			case "registrarCliente": {
				try {
					registrarCliente(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "editarCliente":{
				try {
					editarCliente(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}
	
	private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String dni = request.getParameter("dni");
		String estado = request.getParameter("estado");
		objClienteDAO.actualizarCliente(id, nombre, apellido, correo, telefono, direccion, dni, estado);
		String nuevaPagina = "/gestionClientes.jsp";
		/*De esta forma re-dirijo mi sistema a una pagina en particular*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void registrarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		String nombre = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String correo = request.getParameter("correo");
		String telefono = request.getParameter("telefono");
		String direccion = request.getParameter("direccion");
		String dni = request.getParameter("dni");
		objClienteDAO.registrarCliente(nombre, apellido, correo, telefono, direccion, dni);
		String nuevaPagina = "/gestionClientes.jsp";
		/*De esta forma re-dirijo mi sistema a una pagina en particular*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void mostrarNuevoCliente(HttpServletRequest request, HttpServletResponse response) {
		String nuevaPagina = "/nuevoCliente.jsp";
		/*De esta forma re-dirijo mi sistema a una pagina en particular*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
