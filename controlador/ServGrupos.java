package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Seleccion;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.DaoSelecciones;

/**
 * Servlet implementation class ServGrupos
 */
public class ServGrupos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServGrupos() {
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
	
		try {
			PrintWriter out = response.getWriter();
			
			DaoSelecciones dao = new DaoSelecciones();
			
			String resultados = dao.listarJson();
			
			System.out.println(resultados);
			
			out.print(resultados);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
		/*
		int idSeleccion = Integer.parseInt(request.getParameter("idSeleccion"));
		String nombre = request.getParameter("nombre");
		String grupo = request.getParameter("grupo");
		int gf = Integer.parseInt(request.getParameter("gf"));
		int gc = Integer.parseInt(request.getParameter("gc"));
		int dif = Integer.parseInt(request.getParameter("dif"));
		int puntos = Integer.parseInt(request.getParameter("puntos"));
		
		Seleccion s1 = new Seleccion (idSeleccion, nombre, grupo, gf, gc, dif, puntos);
		System.out.println(s1.toString());
		
		try {
			DaoSelecciones lista;
			
			PrintWriter out = response.getWriter();
				
			lista = new DaoSelecciones();
			ArrayList<Seleccion> listadoSelecciones = lista.listar();
			
			out.print("<ul>");
			for (Seleccion s : listadoSelecciones) {
				out.print("<li>" + s.getNombre());
		    }
			out.print("<ul>");
			
		}catch (SQLException e) {
			e.printStackTrace();
			
		}*/
		
	
		
	}
	
}
		

