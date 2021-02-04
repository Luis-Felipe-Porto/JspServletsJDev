/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import beans.BeansProduto;
import dao.DAOProduto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NATANIELE
 */
public class ProdutoServelt extends HttpServlet {
    
    private DAOProduto DaoProduto = new DAOProduto();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProdutoServelt</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProdutoServelt at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        
        String produto = request.getParameter("produto");
        
        try {
            if (acao != null) {
                if (acao.equalsIgnoreCase("delete")) {
                    this.DaoProduto.removerProduto(produto);
                } else if (acao.equalsIgnoreCase("editar")) {
                    BeansProduto produtoCadastrado = this.DaoProduto.buscarProduto(produto);
                    request.setAttribute("prd", produtoCadastrado);
                }
            }
            request.setAttribute("categorias", DaoProduto.listarCategoria());
            request.setAttribute("produtos", DaoProduto.listarProduto());
            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            view.forward(request, response);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        
        if (acao != null && acao.equalsIgnoreCase("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
                request.setAttribute("produtos", this.DaoProduto.listarProduto());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String id = request.getParameter("id");
        String nome = request.getParameter("nome");
        String quantidade = request.getParameter("quantidade");
        String valor = request.getParameter("valor");
        String categoria = request.getParameter("categoria_id");
        
        BeansProduto produto = new BeansProduto();
        
        produto.setId(!id.isEmpty() ? Long.parseLong(id) : null);
        produto.setNome(nome);
        produto.setQuantidade(Integer.parseInt(quantidade));
        String valorParse = valor.replaceAll("\\.", "");//10.500,20
        valorParse = valorParse.replaceAll("\\,", ".");
        produto.setValor(Double.parseDouble(valorParse));
        produto.setCategoria_id(Long.parseLong(categoria));
        
        try {
            if (id == null || id.isEmpty()) {
                if (this.DaoProduto.validarNome(nome)) {
                    this.DaoProduto.inserirProduto(produto);
                    request.setAttribute("sucess", "Produto Cadastrado com Sucesso!");
                }else if (!this.DaoProduto.validarNome(nome)) {
                    request.setAttribute("msg", "Existe um produto com mesmo nome já cadastrado\nAdicone algum complemento ao nome!");  
                }
            } else if (id != null || !id.isEmpty()) {
                request.setAttribute("prd", produto);
                
                if (this.DaoProduto.validarNome(nome)) {
                    this.DaoProduto.atualizarProduto(produto);
                    request.setAttribute("sucess", "Produto Atualizado com Sucesso!");
                }else{
                    request.setAttribute("msg", "Existe um produto com mesmo nome cadastrado!");
                }
            }
            RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
            request.setAttribute("produtos", this.DaoProduto.listarProduto());
            request.setAttribute("categorias", this.DaoProduto.listarCategoria());
            view.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
