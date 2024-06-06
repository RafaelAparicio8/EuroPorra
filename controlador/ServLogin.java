package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServLogin
 */
public class ServLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nombre = request.getParameter("nombre");
		String contrasena = request.getParameter("contrasena");
		
		Usuario u = new Usuario();
		u.setNombre(nombre);
		
	
		
		//comprobar si esta logado
		
		try {
	        if (u.logeo(contrasena)) {
	            // Iniciar sesión y redirigir a la página de inicio
	            sesion = request.getSession();
	            sesion.setAttribute("idUsuario", u.getIdUsuario());
	            sesion.setAttribute("permiso", u.getPermiso());
	            
	            if (u.getPermiso() == 20) {
	                response.sendRedirect("webAdmin.html"); // Página de inicio para administradores
	            } else {
	                response.sendRedirect("inicio.html"); // Página de inicio para usuarios normales 
	            
	            }
	        }
	        else {
	            // Si el inicio de sesión falla, redirigir de vuelta a la página de inicio de sesión
	            response.sendRedirect("login.html");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    }		
	}

}
