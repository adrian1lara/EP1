package com.EP1.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/Home")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String opcion = request.getParameter("opcionGET");
		switch(opcion) {
			case "mostrarGestionarPedidos": {
				mostrarGestionarPedidos(request, response);
				break;
			}
			case "mostrarGestionClientes" : {
				mostrarGestionClientes(request,response);
				break;
			}
		}
	}

	private void mostrarGestionClientes(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
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

	private void mostrarGestionarPedidos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String nuevaPagina = "/gestionPedidos.jsp";
		
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
		doGet(request, response);
	}

}
