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
import java.io.PrintWriter;

/**
 * Servlet implementation class ServUsuario
 */

@WebServlet("/ServUsuario")
public class ServUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServUsuario() {
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
		
		sesion = request.getSession();
		
		
		String nombre = request.getParameter("nombre");
		String contrasena = request.getParameter("contrasena");
		
		
		
		Usuario u = new Usuario (nombre , contrasena);
		
		try {
			u.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error en la conexion");
		}
		response.sendRedirect("principal.html");
		System.out.println(u.toString());
		}
		
	    
}
