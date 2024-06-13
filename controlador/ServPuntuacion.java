package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import dao.DaoPronostico;

/**
 * Servlet implementation class ServPuntuacion
 */
public class ServPuntuacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoPronostico daoPronostico;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServPuntuacion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 try {
	            daoPronostico.puntuacion();
	            response.getWriter().println("Puntuaciones actualizadas correctamente.");
	        } catch (SQLException e) {
	            throw new ServletException("Error al calcular puntuaciones: " + e.getMessage());
	        }
	    }

}
