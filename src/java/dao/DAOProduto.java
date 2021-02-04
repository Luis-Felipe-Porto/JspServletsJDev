/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.BeansCategoria;
import beans.BeansProduto;
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
 * @author LUIS PORTO
 */
public class DAOProduto {

    private Connection connection;

    public DAOProduto() {
        this.connection = SingleConnection.getConnection();
    }

    public void inserirProduto(BeansProduto produto) {

        try {
            String sql = "INSERT INTO produto(nome,quantidade,valor,categoria_id) VALUES(?,?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, produto.getNome());
            insert.setInt(2, produto.getQuantidade());
            insert.setDouble(3, produto.getValor());
            insert.setLong(4, produto.getCategoria_id());
            insert.execute();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public BeansProduto buscarProduto(String id) throws SQLException {

        String sql = "SELECT * FROM produto WHERE id = " + id;
        PreparedStatement consulta = connection.prepareStatement(sql);
        ResultSet sqlExecutada = consulta.executeQuery();
        if (sqlExecutada.next()) {
            BeansProduto produto = new BeansProduto();
            produto.setId(sqlExecutada.getLong("id"));
            produto.setNome(sqlExecutada.getString("nome"));
            produto.setQuantidade(sqlExecutada.getInt("quantidade"));
            produto.setValor(sqlExecutada.getDouble("valor"));
            return produto;
        }
        return null;
    }

    public void removerProduto(String id) {

        try {
            String sql = "DELETE FROM produto WHERE id ='" + id + "'";
            PreparedStatement delete = connection.prepareStatement(sql);
            delete.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<BeansProduto> listarProduto() throws SQLException {
        List<BeansProduto> listaProduto = new ArrayList<>();
        String sql = "SELECT * FROM produto";
        PreparedStatement listar = connection.prepareStatement(sql);
        ResultSet sqlExecutada = listar.executeQuery();

        while (sqlExecutada.next()) {
            BeansProduto produto = new BeansProduto();
            produto.setId(sqlExecutada.getLong("id"));
            produto.setNome(sqlExecutada.getString("nome"));
            produto.setQuantidade(sqlExecutada.getInt("quantidade"));
            produto.setValor(sqlExecutada.getDouble("valor"));
            produto.setCategoria_id(sqlExecutada.getLong("categoria_id"));
            listaProduto.add(produto);
        }
        return listaProduto;
    }
    public List<BeansCategoria> listarCategoria()throws SQLException{
        List <BeansCategoria> listaCategoria = new ArrayList<>();
        String sql = "SELECT * FROM categoria";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        while(resultSet.next()){
            BeansCategoria categoria = new BeansCategoria();
            categoria.setId(resultSet.getLong("id"));
            categoria.setNome(resultSet.getString("nome"));
            listaCategoria.add(categoria);
        }
        return listaCategoria;
    }
    public void atualizarProduto(BeansProduto produto) {

        try {
            String sql = "UPDATE produto SET nome = ? ,quantidade = ?, valor = ?,categoria_id = ? WHERE id ='" + produto.getId() + "'";
            PreparedStatement updateQuery = connection.prepareStatement(sql);
            updateQuery.setString(1, produto.getNome());
            updateQuery.setInt(2, produto.getQuantidade());
            updateQuery.setDouble(3, produto.getValor());
            updateQuery.setLong(4, produto.getCategoria_id());
            updateQuery.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOProduto.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
    }
    public boolean validarNome(String nome) throws SQLException {

        String sql = "SELECT COUNT(1) AS qtd FROM produto WHERE nome = '" + nome + "'";

        PreparedStatement consulta = connection.prepareStatement(sql);
        ResultSet sqlExecutada = consulta.executeQuery();
        if (sqlExecutada.next()) {

            return sqlExecutada.getInt("qtd") <= 0;
        }
        return false;
    }
        public boolean validarProdutoId(String id) throws SQLException {

        String sql = "SELECT COUNT(1) AS qtd FROM produto WHERE id = '" + id+"'";

        PreparedStatement consulta = connection.prepareStatement(sql);

        ResultSet sqlExecutada = consulta.executeQuery();

        if (sqlExecutada.next()) {

            return sqlExecutada.getInt("qtd") <= 0;
        }
        return false;
    }

}
