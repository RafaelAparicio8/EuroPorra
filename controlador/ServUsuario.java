package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Partido;
import modelo.Usuario;

import java.io.IOException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.DaoAdminPartido;
import dao.DaoUsuario;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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
		
		String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int idUsuario = Integer.parseInt(request.getParameter("id"));
            try {
                DaoUsuario dao = new DaoUsuario();
                dao.eliminar(idUsuario);
                response.getWriter().print("{\"status\":\"success\"}");
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().print("{\"status\":\"error\"}");
            }
        }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		/**
		 * A continuacion se crea el metodo para insertar usuarios.
		 */
		
		sesion = request.getSession();
		
		
		String nombre = request.getParameter("nombre");
		String contrasena = getMd5(request.getParameter("contrasena")); // Se utilizara Md5 para cifrar la contraseña
		
		
		
		Usuario u = new Usuario (nombre , contrasena);
		
		try {
			u.insertar();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("error en la conexion");
		}
		response.sendRedirect("inicio.html");
		System.out.println(u.toString());
		}
	
	/**
	 * METODO PARA ENCRIPTAR CONTRASENAS
	 * @param input
	 * @return
	 */
	
	public static String getMd5 (String input) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger number = new BigInteger(1, messageDigest);
			String hashtext = number.toString(16);
			
			while (hashtext.length() < 32) { 
				hashtext = "0" + hashtext;
			}
			 
		return hashtext; } catch (NoSuchAlgorithmException e) { throw new RuntimeException(e); } }
  
}
