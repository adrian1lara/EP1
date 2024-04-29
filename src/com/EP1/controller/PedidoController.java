package com.EP1.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.EP1.dao.ClienteDAO;
import com.EP1.dao.PedidoDAO;
import com.EP1.dao.ProductoDAO;
import com.EP1.model.Cliente;
import com.EP1.model.Pedido;
import com.EP1.model.Producto;

/**
 * Servlet implementation class PedidoController
 */
@WebServlet("/Pedido")
public class PedidoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	PedidoDAO objPedidoDAO = new PedidoDAO();
	ClienteDAO objClienteDAO = new ClienteDAO();
	ProductoDAO objProductoDAO = new ProductoDAO();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PedidoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion= request.getParameter("opcionGET");
		switch(opcion) {
			case "listarPedidos": {
				try {
					listarPedidos(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "mostrarRegistrarPedidos":{
				try {
					mostrarRegistrarPedidos(request,response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "eliminarPedido":{
				try {
					eliminarPedido(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "mostrarEditarPedido":{
				try {
					mostrarEditarPedido(request, response);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
		}
	}

	private void mostrarEditarPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		int id =Integer.parseInt(request.getParameter("id"));
		Pedido objPedido = objPedidoDAO.buscarPedidoPorId(id);
		request.setAttribute("objPedido", objPedido);
		
		List<Cliente> clienteList;
		List<Producto> productList;
		
		//obtener la listad de clientes
		clienteList = objClienteDAO.listarClientes();
		request.setAttribute("clienteList", clienteList);
		
		
		//obtener la lista de productos
		productList = objProductoDAO.listarProductos();
		request.setAttribute("productList", productList);
		
		
		String nuevaPagina = "/editarPedido.jsp";
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		
		try {
			dispatcher.forward(request, response);
		}catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void eliminarPedido(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		objPedidoDAO.eliminarPedido(id);
		
		String nuevaPagina = "/gestionPedidos.jsp";
		
		/*redirect my system to a new page*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		
		try {
			dispatcher.forward(request, response);
		}catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void mostrarRegistrarPedidos(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		// TODO Auto-generated method stub
		
		List<Cliente> clienteList;
		List<Producto> productList;
		
		//obtener la listad de clientes
		clienteList = objClienteDAO.listarClientes();
		request.setAttribute("clienteList", clienteList);
		
		
		//obtener la lista de productos
		productList = objProductoDAO.listarProductos();
		request.setAttribute("productList", productList);
		
		String nuevaPagina = "/nuevoPedido.jsp";
		
		
		/*redirect my system to a new page*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		
		try {
			dispatcher.forward(request, response);
		}catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	private void listarPedidos(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		// TODO Auto-generated method stub
		
		List<Pedido> pedidoList;
		
		pedidoList = objPedidoDAO.listarPedidos();
		request.setAttribute("pedidoList", pedidoList);
		System.out.println("Fetched " + pedidoList.size() + " pedidos");
		
		String nuevaPagina = "/vistaPedidos.jsp";
		
		/*redirect my system to a new page*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		
		try {
			dispatcher.forward(request, response);
		}catch (ServletException e) {
			e.printStackTrace();
		}

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcionPOST");
		switch(opcion) {
			case "registrarPedido":{
				try {
					try {
						registrarPedido(request, response);
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			}
			case "editarPedido": {
				try {
					editarPedido(request, response);
				} catch (ParseException | SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	private void editarPedido(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		int idProducto = Integer.parseInt(request.getParameter("producto"));
		int idCliente = Integer.parseInt(request.getParameter("cliente"));
		String estadoPedido = request.getParameter("estado");
		String fechaPedidoStr = request.getParameter("fechaPedido");
		String fechaEntregaStr = request.getParameter("fechaEntrega");
		String tipoPago = request.getParameter("tipoPago");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fechaPedido = sdf.parse(fechaPedidoStr);
		java.util.Date fechaEntrega = sdf.parse(fechaEntregaStr);
		
		Date sqlFechaEntrega = new Date(fechaEntrega.getTime());
		Date sqlFechaPedido = new Date(fechaPedido.getTime());
		
		objPedidoDAO.actualizarPedido(id, idProducto, idCliente, estadoPedido, sqlFechaPedido, sqlFechaEntrega, tipoPago, cantidad);
		
		String nuevaPagina = "/gestionPedidos.jsp";
		
		/*redirect my system to a new page*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		
		try {
			dispatcher.forward(request, response);
		}catch (ServletException e) {
			e.printStackTrace();
		}

	}

	private void registrarPedido(HttpServletRequest request, HttpServletResponse response) throws ParseException, SQLException, IOException {
		// TODO Auto-generated method stub
		int idProducto = Integer.parseInt(request.getParameter("producto"));
		int idCliente = Integer.parseInt(request.getParameter("cliente"));
		String estadoPedido = request.getParameter("estado");
		String fechaPedidoStr = request.getParameter("fechaPedido");
		String fechaEntregaStr = request.getParameter("fechaEntrega");
		String tipoPago = request.getParameter("tipoPago");
		int cantidad = Integer.parseInt(request.getParameter("cantidad"));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date fechaPedido = sdf.parse(fechaPedidoStr);
		java.util.Date fechaEntrega = sdf.parse(fechaEntregaStr);
		
		Date sqlFechaEntrega = new Date(fechaEntrega.getTime());
		Date sqlFechaPedido = new Date(fechaPedido.getTime());
		
		objPedidoDAO.registrarPedido(idProducto, idCliente, estadoPedido, sqlFechaPedido, sqlFechaEntrega, tipoPago, cantidad);
		
		
		String nuevaPagina = "/gestionPedidos.jsp";
		
		/*redirect my system to a new page*/
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nuevaPagina);
		
		try {
			dispatcher.forward(request, response);
		}catch (ServletException e) {
			e.printStackTrace();
		}


	}

}
