/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import beans.BeansCursoJsp;
import com.oracle.jrockit.jfr.DataType;
import com.sun.javafx.image.impl.ByteArgb;
import dao.DaoUser;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

/**
 *
 * @author NATANIELE
 */
@MultipartConfig
public class Usuario extends HttpServlet {

    private DaoUser daoUsuario = new DaoUser();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Usuario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Usuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        String user = request.getParameter("user");
        try {
            if (acao != null) {
                if (acao.equalsIgnoreCase("delete")) {
                    this.daoUsuario.delete(user);
                    RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                    request.setAttribute("usuarios", daoUsuario.listar());
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("editar")) {
                    RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                    BeansCursoJsp usuarioConsultado = this.daoUsuario.consultar(user);
                    request.setAttribute("user", usuarioConsultado);
                    request.setAttribute("usuarios", daoUsuario.listar());
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("listarTodos")) {
                    request.setAttribute("usuarios", daoUsuario.listar());
                    RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                    view.forward(request, response);
                } else if (acao.equalsIgnoreCase("download")) {
                    BeansCursoJsp usuario = this.daoUsuario.consultar(user);
                    if (usuario != null) {
                        String contentType = "";
                        byte[] fileBytes = null;
                        String tipo = request.getParameter("tipo");
                        if (tipo.equalsIgnoreCase("imagem")) {
                            contentType = usuario.getContentType();
                            /*Converte em base 64 da imagem do banco para bayte array*/
                            fileBytes = new Base64().decodeBase64(usuario.getFotoBase64());
                        } else if (tipo.equalsIgnoreCase("curriculo")) {
                            contentType = usuario.getContentTypeCurriculo();
                            /*Converte em base 64 da imagem do banco para bayte array*/
                            fileBytes = new Base64().decodeBase64(usuario.getCurriculoBase64());
                        }
                        response.setHeader("Content-Disposition", "attachment;filename=arquivo."
                                + contentType.split("\\/")[1]);

                        /*Coloca os bytes em um objeto de netrada para processas*/
                        InputStream is = new ByteArrayInputStream(fileBytes);

                        /*Inicio da Resposta para o navegador*/
                        int read = 0;
                        byte[] bytes = new byte[124];
                        OutputStream os = response.getOutputStream();
                        while ((read = is.read(bytes)) != -1) {
                            os.write(bytes, 0, read);
                        }
                        os.flush();
                        os.close();
                    }
                }
            } else {
                RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");
        if (acao != null && acao.equalsIgnoreCase("reset")) {
            try {
                RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            String id = request.getParameter("id");
            String login = request.getParameter("login");
            String senha = request.getParameter("senha");
            String nome = request.getParameter("nome");
            String cep = request.getParameter("cep");
            String rua = request.getParameter("rua");
            String bairro = request.getParameter("bairro");
            String cidade = request.getParameter("cidade");
            String estado = request.getParameter("uf");
            String ibge = request.getParameter("ibge");
            String ativo =  request.getParameter("ativo");//on //null
            String sexo = request.getParameter("sexo");
            String perfil  = request.getParameter("perfil");
            
            BeansCursoJsp usuario = new BeansCursoJsp();

            usuario.setId((!id.isEmpty()) ? Long.parseLong(id) : null);
            usuario.setLogin(login);
            usuario.setSenha(senha);
            usuario.setNome(nome);
            usuario.setCep(cep);
            usuario.setRua(rua);
            usuario.setBairro(bairro);
            usuario.setCidade(cidade);
            usuario.setEstado(estado);
            usuario.setIbge(ibge);
            usuario.setSexo(sexo);
            usuario.setPerfil(perfil);
            if(ativo != null && ativo.equalsIgnoreCase("on")){
                usuario.setAtivo(true);
            }else{
                usuario.setAtivo(false);
            }
            try {
                //upload de imagens
                if (ServletFileUpload.isMultipartContent(request)) {

                    Part imagemFoto = request.getPart("foto");

                    if (imagemFoto != null && imagemFoto.getInputStream().available() > 0) {
                        byte[] bytesImagem = covertStremParabyte(imagemFoto.getInputStream());
                        String fotoBase64 = new Base64()
                                .encodeBase64String(bytesImagem);
                        usuario.setFotoBase64(fotoBase64);
                        usuario.setContentType(imagemFoto.getContentType());
                        /*Miniatura imagens*/
                        /*traformar em um buffered imagem*/
                        byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
                        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
                        /*Pega o tipo da imagem*/
                        int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB:bufferedImage.getType();                        
                        /*cria Dimensão da Imagem*/
                        BufferedImage resizideImage = new BufferedImage(100,100,type);
                        Graphics2D gr = resizideImage.createGraphics();
                        gr.drawImage(bufferedImage, 0, 0,100,100,null);
                        gr.dispose();
                        /*Escrever Imagem novamente*/
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ImageIO.write(resizideImage,"png", baos);
                        
                        String miniaturaBase64 = "data:image/png;base64,"+
                                DatatypeConverter.printBase64Binary(baos.toByteArray());
                        usuario.setFotoBase64Miniatura(miniaturaBase64 );
                        /*Escrever Imagem novamente*/
                        
                    }else{
                      usuario.setAtualizarImage(false);
                    }

                    /*Processa pdf*/
                    Part curriculoPdf = request.getPart("curriculo");
                    if (curriculoPdf != null && curriculoPdf.getInputStream().available() > 0) {
                        String curriculoBase64 = new Base64()
                                .encodeBase64String(covertStremParabyte(curriculoPdf.getInputStream()));
                        usuario.setCurriculoBase64(curriculoBase64);
                        usuario.setContentTypeCurriculo(curriculoPdf.getContentType());
                    } else {
                        usuario.setAtualizarPdf(false);
                    }

                }

                if (id == null || id.isEmpty() && !this.daoUsuario.validarSenhaDuplicada(senha)) {
                    request.setAttribute("msg", "Senha Inválida para Esse Usuario, Tente outra senha!");
                    request.setAttribute("user", usuario);
                } else if (id == null || id.isEmpty() && !this.daoUsuario.validarLogin(login)) {
                    request.setAttribute("msg", "Usuario já existente com o mesmo login!");
                    request.setAttribute("user", usuario);
                } else if (id == null || id.isEmpty() && this.daoUsuario.validarLogin(login) && this.daoUsuario.validarSenhaDuplicada(senha)) {
                    this.daoUsuario.salvar(usuario);
                    request.setAttribute("sucess", "Usuario Cadastrado com Sucesso!");
                } else if (id != null || id.isEmpty()) {

                    if (this.daoUsuario.validarLoginUpdate(login, id)) {
                        this.daoUsuario.atualizar(usuario);
                        request.setAttribute("sucess", "Alteracão Realizada com Sucesso!");
                    } else {
                        if (!this.daoUsuario.validarLoginUpdate(login, id)) {
                            request.setAttribute("msg", "Usuario já existente com o mesmo login!");
                        }
                        request.setAttribute("user", usuario);
                    }
                }
                RequestDispatcher view = request.getRequestDispatcher("/CadastroUsuario.jsp");
                request.setAttribute("usuarios", daoUsuario.listar());
                view.forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private byte[] covertStremParabyte(InputStream imagem) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int reads = imagem.read();
        while (reads != -1) {
            baos.write(reads);
            reads = imagem.read();
        }
        return baos.toByteArray();
    }
}
