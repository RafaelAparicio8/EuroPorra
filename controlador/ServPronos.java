package controlador;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Partido;
import modelo.Pronostico;
import dao.DaoPartido;
import dao.DaoPronostico;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ServPronos extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ServPronos() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
    	 response.setContentType("application/json");
         response.setCharacterEncoding("UTF-8");
         PrintWriter out = response.getWriter();

         try {
             DaoPartido daoPartido = new DaoPartido();
             List<Partido> partidos = daoPartido.listar();
             String json = new Gson().toJson(partidos);
             out.print(json);
         } catch (SQLException e) {
             e.printStackTrace();
             response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
             out.print("{\"status\":\"error\",\"message\":\"Error al obtener la lista de partidos.\"}");
         }
     
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  HttpSession session = request.getSession(false); // Obtener sesión existente sin crear una nueva
          Integer idUsuario = (Integer) session.getAttribute("idUsuario");

          if (session == null || idUsuario == null) {
              response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
              response.getWriter().write("{\"status\":\"error\",\"message\":\"No autorizado.\"}");
              return;
          }

          response.setContentType("application/json");
          response.setCharacterEncoding("UTF-8");
          PrintWriter out = response.getWriter();

          try {
              Gson gson = new Gson();
              List<Map<String, Object>> pronosticosList = gson.fromJson(request.getReader(), List.class);

              DaoPronostico daoPronostico = new DaoPronostico();

              for (Map<String, Object> pronosticoMap : pronosticosList) {
                  int idPartido = Integer.parseInt((String) pronosticoMap.get("idPartido"));
                  int resultadoA = Integer.parseInt((String) pronosticoMap.get("resultadoA"));
                  int resultadoB = Integer.parseInt((String) pronosticoMap.get("resultadoB"));

                  Pronostico pronostico = new Pronostico();
                  pronostico.setIdUsuario(idUsuario);
                  pronostico.setIdPartido(idPartido);
                  pronostico.setResultPronA(resultadoA);
                  pronostico.setResultPronB(resultadoB);

                  daoPronostico.insertarPronostico(pronostico);
              }

              out.print("{\"status\":\"success\"}");
          } catch (SQLException e) {
              e.printStackTrace();
              response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
              out.print("{\"status\":\"error\",\"message\":\"Error al insertar los pronósticos.\"}");
          }
      }
    

}