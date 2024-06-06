package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet implementation class ServSesion
 */
public class ServSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession sesion;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServSesion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/** Verificar si ya hay una sesión activa
		 * verificar permisos antes de redirigir a cada pagina
		 * Redirigir a la página de inicio si ya hay una sesión activa
		 */
	    if (sesion.getAttribute("id") != null) {
	    	 int permiso = (int) sesion.getAttribute("permiso");
	    	    if (permiso == 20) {
	    	        response.sendRedirect("webAdmin.jsp"); // Página de inicio para administradores
	    	    } else {
	    	        response.sendRedirect("inicio.jsp"); // Página de inicio para usuarios normales 
	    	    }
	        
	    } else {
	        // Obtener los parámetros de la solicitud y configurar la sesión
	        String nombre = request.getParameter("nombre");
	        int id = Integer.parseInt(request.getParameter("id")); 
	        
	        sesion.setAttribute("nombre", nombre);
	        sesion.setAttribute("id", id);
	        
	        // Redirigir a la página de inicio
	        response.sendRedirect("inicio.html");
	    }
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}