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
 * Servlet implementation class ServEditarUsuario
 */
public class ServEditarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServEditarUsuario() {
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
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");

		    String id = request.getParameter("id");
		    PrintWriter out = response.getWriter();
		    		
		    try {
	            DaoUsuario dao = new DaoUsuario();

	            if (id != null && !id.isEmpty()) { // Verificar si el ID no es null ni está vacío
	                int idUsuario = Integer.parseInt(id);
	                Usuario usuario = dao.obtenerPorId(idUsuario);
	                String resultado = new Gson().toJson(usuario);
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
		System.out.println("Se ha llamado al método doPost de ServEditarUsuario.java");
	
		  response.setContentType("application/json");
		  response.setCharacterEncoding("UTF-8");

		/**
		 *  metodo para editar usuarios.(solo por parte de administrador)
		 */
		 
      

        try {
            Gson gson = new Gson();
            Usuario usuario = gson.fromJson(request.getReader(), Usuario.class);
            System.out.println("Datos recibidos para la edición:");
            System.out.println("ID Usuario: " + usuario.getIdUsuario());
            System.out.println("Nombre: " + usuario.getNombre());
            System.out.println("Permiso: " + usuario.getPermiso());

            DaoUsuario dao = new DaoUsuario();
            dao.editar(usuario);

            response.getWriter().print(gson.toJson(usuario));

        } catch (SQLException e) {
            e.printStackTrace();
        }

}
}
