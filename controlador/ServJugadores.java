package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import modelo.Jugador;
import modelo.Usuario;
import dao.DaoJugadores;
import dao.DaoUsuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
/**
 * Servlet implementation class ServJugadores
 */
public class ServJugadores extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServJugadores() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
	     * Metodo para listar los jugadores.
	     */
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");

		    String id = request.getParameter("id");
		    PrintWriter out = response.getWriter();
		    		
		    try {
	            DaoJugadores dao = new DaoJugadores();

	            if (id != null && !id.isEmpty()) { // Verificar si el ID no es null ni está vacío
	                int idJugador = Integer.parseInt(id);
	                Jugador jugador = dao.obtenerPorId(idJugador);
	                String resultado = new Gson().toJson(jugador);
	                out.print(resultado);
	            } else {
	                String resultados = dao.listarJson();
	                out.print(resultados);
	            }

	        } catch (SQLException e) {
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