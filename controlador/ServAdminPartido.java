
package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Partido;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdminPartido;
import dao.DaoPartido;

/**
 * Servlet implementation class ServAdminPartido
 */
public class ServAdminPartido extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServAdminPartido() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    
	    
	    /**
	     * Metodo para listar los partidos.
	     */
	    String id = request.getParameter("id");
	    PrintWriter out = response.getWriter();
	    		
	    try {
            DaoAdminPartido dao = new DaoAdminPartido();

            if (id != null) {
                int idPartido = Integer.parseInt(id);
                Partido partido = dao.obtenerPorId(idPartido);
                String resultado = new Gson().toJson(partido);
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
		
		    response.setContentType("application/json");
	        response.setCharacterEncoding("UTF-8");
	        
	        /**
		     * Metodo para editar los partidos.
		     */

	        try {
	            Gson gson = new Gson();
	            Partido partido = gson.fromJson(request.getReader(), Partido.class);

	            DaoAdminPartido dao = new DaoAdminPartido();
	            dao.editar(partido);

	            response.getWriter().print(gson.toJson(partido));

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	
	}

}