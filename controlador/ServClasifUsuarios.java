package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import dao.DaoUsuario;

/**
 * Servlet implementation class ServClasifUsuarios
 */
public class ServClasifUsuarios extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServClasifUsuarios() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		

		 /**
	     * Metodo para listar los usuarios.
	     */
		  

		  PrintWriter out = response.getWriter();

		    try {
		        DaoUsuario dao = new DaoUsuario();
		        
		        String id = request.getParameter("id");

		       
				if (id != null && !id.isEmpty()) { // Verificar si el ID no es null ni está vacío
		            int idUsuario = Integer.parseInt(id);
		            Usuario usuario = dao.obtenerPorId(idUsuario);
		            String resultado = new Gson().toJson(usuario);
		            out.print(resultado);
		        } else {
		            ArrayList<Usuario> usuarios = dao.listarPuntuacion();
		            String resultados = new Gson().toJson(usuarios);
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
		
	}

}
