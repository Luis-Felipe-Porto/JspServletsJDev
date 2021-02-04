/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.BeansCursoJsp;
import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NATANIELE
 */
public class DaoUser {

    private Connection connection;

    public DaoUser() {
        connection = SingleConnection.getConnection();
    }

    public void salvar(BeansCursoJsp usuario) {
        try {
            String sql = "INSERT INTO usuario(login,senha,nome,rua,bairro,"
                    + "cidade,estado,ibge,cep,fotobase64,contenttype,curriculobase64"
                    + ",curriculocontenttype,fotominiatura,ativo,sexo,perfil)"
                    + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, usuario.getLogin());
            insert.setString(2, usuario.getSenha());
            insert.setString(3, usuario.getNome());
            insert.setString(4, usuario.getRua());
            insert.setString(5, usuario.getBairro());
            insert.setString(6, usuario.getCidade());
            insert.setString(7, usuario.getEstado());
            insert.setString(8, usuario.getIbge());
            insert.setString(9, usuario.getCep());
            insert.setString(10, usuario.getFotoBase64());
            insert.setString(11, usuario.getContentType());
            insert.setString(12, usuario.getCurriculoBase64());
            insert.setString(13, usuario.getContentTypeCurriculo());
            insert.setString(14, usuario.getFotoBase64Miniatura());
            insert.setBoolean(15, usuario.isAtivo());
            insert.setString(16, usuario.getSexo());
            insert.setString(17,usuario.getPerfil());
            insert.execute();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
            e.printStackTrace();
        }
    }
    public List<BeansCursoJsp> listar(String pesquisa)throws SQLException{
        String sql = "SELECT * FROM usuario Where login <> 'admin' and nome like '%"+pesquisa+"%'";
        return consultarUsuarios(sql);
    }
    public List<BeansCursoJsp> listar() throws SQLException {
        String sql = "SELECT * FROM usuario Where login <> 'admin'";
        return consultarUsuarios(sql);
    }
    private List<BeansCursoJsp> consultarUsuarios(String sql)throws SQLException{
        List<BeansCursoJsp> listaDeUsuario = new ArrayList<BeansCursoJsp>();
        PreparedStatement selecao = connection.prepareStatement(sql);
        ResultSet resultadoDaQuery = selecao.executeQuery();

        while (resultadoDaQuery.next()) {
            BeansCursoJsp usuario = new BeansCursoJsp();
            usuario.setId(resultadoDaQuery.getLong("id"));
            usuario.setLogin(resultadoDaQuery.getString("login"));
            usuario.setSenha(resultadoDaQuery.getString("senha"));
            usuario.setNome(resultadoDaQuery.getString("nome"));
            usuario.setCep(resultadoDaQuery.getString("cep"));
            usuario.setBairro(resultadoDaQuery.getString("bairro"));
            usuario.setRua(resultadoDaQuery.getString("rua"));
            usuario.setCidade(resultadoDaQuery.getString("cidade"));
            usuario.setEstado(resultadoDaQuery.getString("estado"));
            usuario.setIbge(resultadoDaQuery.getString("ibge"));
            usuario.setContentType(resultadoDaQuery.getString("contenttype"));
            usuario.setCurriculoBase64(resultadoDaQuery.getString("curriculobase64"));
            usuario.setContentTypeCurriculo(resultadoDaQuery.getString("curriculocontenttype"));
            usuario.setFotoBase64Miniatura(resultadoDaQuery.getString("fotominiatura"));
            usuario.setAtivo(resultadoDaQuery.getBoolean("ativo"));
            usuario.setSexo(resultadoDaQuery.getString("sexo"));
            usuario.setPerfil(resultadoDaQuery.getString("perfil"));
            listaDeUsuario.add(usuario);  
        }
        return listaDeUsuario;
        
    }

    public void delete(String id) {

        try {
            String sql = "DELETE FROM usuario WHERE id='" + id + "'and login <> 'admin'";
            PreparedStatement delete = connection.prepareStatement(sql);
            delete.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }

    public BeansCursoJsp consultar(String id) throws SQLException {

        String sql = "SELECT * FROM usuario WHERE id = '" + id + "' and login <> 'admin'";

        PreparedStatement consulta = connection.prepareStatement(sql);

        ResultSet sqlExecutada = consulta.executeQuery();

        if (sqlExecutada.next()) {
            BeansCursoJsp usuario = new BeansCursoJsp();
            usuario.setId(sqlExecutada.getLong("id"));
            usuario.setNome(sqlExecutada.getString("nome"));
            usuario.setLogin(sqlExecutada.getString("login"));
            usuario.setSenha(sqlExecutada.getString("senha"));
            usuario.setCep(sqlExecutada.getString("cep"));
            usuario.setBairro(sqlExecutada.getString("bairro"));
            usuario.setCidade(sqlExecutada.getString("cidade"));
            usuario.setEstado(sqlExecutada.getString("estado"));
            usuario.setIbge(sqlExecutada.getString("ibge"));
            usuario.setFotoBase64(sqlExecutada.getString("fotobase64"));
            usuario.setContentType(sqlExecutada.getString("contenttype"));
            usuario.setCurriculoBase64(sqlExecutada.getString("curriculobase64"));
            usuario.setContentTypeCurriculo(sqlExecutada.getString("curriculocontenttype"));
            usuario.setFotoBase64Miniatura(sqlExecutada.getString("fotominiatura"));
            usuario.setAtivo(sqlExecutada.getBoolean("ativo"));
            usuario.setSexo(sqlExecutada.getString("sexo"));
            usuario.setPerfil(sqlExecutada.getString("perfil"));
            return usuario;
        }
        return null;
    }

    public void atualizar(BeansCursoJsp usuario) {
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE usuario SET login = ?,senha = ?,nome = ?");
            sql.append(",rua = ?,bairro = ?,cidade = ?");
            sql.append(",estado = ?, ibge = ?, cep = ?,ativo = ?,sexo = ?,perfil = ?");
            if (usuario.isAtualizarImage()) {
                sql.append(",fotobase64 = ?,contenttype = ?");
            }
            if (usuario.isAtualizarPdf()) {
                sql.append(",curriculobase64 = ?,curriculocontenttype = ?");
            }
            if (usuario.isAtualizarImage()) {
                sql.append(",fotominiatura = ?");
            }
            sql.append(" WHERE id =" + usuario.getId());
            PreparedStatement update = connection.prepareStatement(sql.toString());
            update.setString(1, usuario.getLogin());
            update.setString(2, usuario.getSenha());
            update.setString(3, usuario.getNome());
            update.setString(4, usuario.getRua());
            update.setString(5, usuario.getBairro());
            update.setString(6, usuario.getCidade());
            update.setString(7, usuario.getEstado());
            update.setString(8, usuario.getIbge());
            update.setString(9, usuario.getCep());
            update.setBoolean(10, usuario.isAtivo());
            update.setString(11, usuario.getSexo());
            update.setString(12, usuario.getPerfil());
            if (usuario.isAtualizarImage()) {
                update.setString(13, usuario.getFotoBase64());
                update.setString(14, usuario.getContentType());
            }
            if (usuario.isAtualizarPdf()) {
                if (usuario.isAtualizarPdf() && !usuario.isAtualizarImage()) {
                    update.setString(13, usuario.getCurriculoBase64());
                    update.setString(14, usuario.getContentTypeCurriculo());
                } else {
                    update.setString(15, usuario.getCurriculoBase64());
                    update.setString(16, usuario.getContentTypeCurriculo());
                }
            } else {
                if (usuario.isAtualizarImage()) {
                    update.setString(15, usuario.getFotoBase64Miniatura());
                }
            }
            if (usuario.isAtualizarImage() && usuario.isAtualizarPdf()) {
                update.setString(17,
                        usuario.getFotoBase64Miniatura());
            }
            update.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public boolean validarLogin(String login) throws SQLException {

        String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "'";

        PreparedStatement consulta = connection.prepareStatement(sql);

        ResultSet sqlExecutada = consulta.executeQuery();

        if (sqlExecutada.next()) {

            return sqlExecutada.getInt("qtd") <= 0;
        }
        return false;
    }

    public boolean validarLoginUpdate(String login, String id) throws SQLException {

        String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE login = '" + login + "' and id <> " + id;

        PreparedStatement consulta = connection.prepareStatement(sql);

        ResultSet sqlExecutada = consulta.executeQuery();

        if (sqlExecutada.next()) {

            return sqlExecutada.getInt("qtd") <= 0;
        }
        return false;
    }

    public boolean validarSenhaDuplicada(String senha) throws SQLException {
        String sql = "SELECT COUNT(1) AS qtd FROM usuario WHERE senha = '" + senha + "'";

        PreparedStatement consulta = connection.prepareStatement(sql);
        ResultSet sqlExecutada = consulta.executeQuery();

        if (sqlExecutada.next()) {

            return sqlExecutada.getInt("qtd") <= 0;
        }
        return false;

    }
}
