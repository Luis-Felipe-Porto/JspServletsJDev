/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import beans.BeansCursoJsp;
import dao.DaoUser;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NATANIELE
 */
public class PesquisaServelt extends HttpServlet {

    private DaoUser daoUsuario = new DaoUser();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PesquisaServelt</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PesquisaServelt at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String pesquisa = request.getParameter("pesquisar");

        if (pesquisa != null && !pesquisa.trim().isEmpty()) {
            try {
                List<BeansCursoJsp> listaPesquisa = daoUsuario.listar(pesquisa);
                RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                request.setAttribute("usuarios", listaPesquisa);
                view.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(PesquisaServelt.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{  
            try {
                RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            } catch (SQLException ex) {
                Logger.getLogger(PesquisaServelt.class.getName()).log(Level.SEVERE, null, ex);
            }    
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
