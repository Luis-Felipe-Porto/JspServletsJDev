/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import beans.BeansCursoJsp;
import beans.Telefone;
import dao.DAOTelefone;
import dao.DaoUser;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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
public class SalvarTelefone extends HttpServlet {

    private DaoUser usuario = new DaoUser();
    private DAOTelefone telefone = new DAOTelefone();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SalvarTelefone</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SalvarTelefone at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        try {
            String user = request.getParameter("user");
            if (user != null) {
                if (acao.endsWith("addTelefone")) {
                    BeansCursoJsp usuario = this.usuario.consultar(user);
                    request.getSession().setAttribute("userEscolhido", usuario);
                    request.setAttribute("userEscolhido", usuario);
                    RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
                    request.setAttribute("telefones", this.telefone.listar(usuario.getId()));
                    request.setAttribute("sucessfull", "Deletado com sucesso");
                    view.forward(request, response);
                } else if (acao.endsWith("deleteFone")) {
                    String userFone = request.getParameter("userFone");
                    this.telefone.remover(userFone);
                    BeansCursoJsp usuario = (BeansCursoJsp) request.getSession().getAttribute("userEscolhido");
                    request.setAttribute("msg", "Deletado com sucesso");

                    RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
                    //    request.getSession().setAttribute("userEscolhido", usuario);
                    request.setAttribute("telefones", this.telefone.listar(usuario.getId()));
                    view.forward(request, response);

                }
            }else{
                 RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                 request.setAttribute("usuarios", usuario.listar());
                 view.forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(SalvarTelefone.class.getName()).log(Level.SEVERE, null, ex);
        }
//        RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
//        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String tipoTel = request.getParameter("tipo");
            String numeroTel = request.getParameter("telefone");
            BeansCursoJsp usuario = (BeansCursoJsp) request.getSession().getAttribute("userEscolhido");
            Telefone tel = new Telefone();
            tel.setNumero(numeroTel);
            tel.setTipo(tipoTel);
            tel.setUsuario(usuario.getId());
            if (numeroTel != null || numeroTel.isEmpty()) {
                this.telefone.inserir(tel);
            }
            request.getSession().setAttribute("userEscolhido", usuario);
            request.setAttribute("userEscolhido", usuario);
            request.setAttribute("telefones", this.telefone.listar(usuario.getId()));
        } catch (Exception ex) {
            Logger.getLogger(SalvarTelefone.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
        request.setAttribute("msg", "Salvo com sucesso");
        view.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
