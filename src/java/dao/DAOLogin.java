/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connection.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author NATANIELE
 */
public class DAOLogin {
    
    private Connection connection;
    
    public DAOLogin(){
        connection = SingleConnection.getConnection();
    }
    public boolean validarLogin(String login,String senha)throws Exception{
        String slq = "SELECT * FROM usuario WHERE login ='"+login+"'and senha ='"+senha+"'";
        PreparedStatement statement = connection.prepareStatement(slq);
        ResultSet resultSet = statement.executeQuery();
        if(resultSet.next()){
            return true;
        }else{
            return false;
        }
    }
}
