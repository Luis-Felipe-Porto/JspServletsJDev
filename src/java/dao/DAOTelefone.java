/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import beans.BeansProduto;
import beans.Telefone;
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
public class DAOTelefone {

    private Connection connection;

    public DAOTelefone() {
        this.connection = SingleConnection.getConnection();
    }

    public void inserir(Telefone telefone) {

        try {
            String sql = "INSERT INTO telefone(numero,tipo,usuario) VALUES(?,?,?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, telefone.getNumero());
            insert.setString(2, telefone.getTipo());
            insert.setLong(3, telefone.getUsuario());
            insert.execute();
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(DAOTelefone.class.getName()).log(Level.SEVERE, null, ex1);
            }
            Logger.getLogger(DAOTelefone.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void remover(String id) {

        try {
            String sql = "DELETE FROM telefone WHERE id ='" + id + "'";
            PreparedStatement delete = connection.prepareStatement(sql);
            delete.execute();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DAOTelefone.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public List<Telefone> listar(Long user) throws SQLException {
        List<Telefone> listaTelefone = new ArrayList<>();
        String sql = "SELECT * FROM telefone WHERE usuario ="+ user;
        PreparedStatement listar = connection.prepareStatement(sql);
        ResultSet sqlExecutada = listar.executeQuery();

        while (sqlExecutada.next()) {
            Telefone telefone = new Telefone();
            telefone.setId(sqlExecutada.getLong("id"));
            telefone.setNumero(sqlExecutada.getString("numero"));
            telefone.setTipo(sqlExecutada.getString("tipo"));
            telefone.setUsuario(sqlExecutada.getLong("usuario"));
            listaTelefone.add(telefone);
        }
        return listaTelefone;
    }

  

}
