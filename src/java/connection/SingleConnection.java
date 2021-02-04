/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Fazer a conexão com o banco de dados 
 */
package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Luis Porto
 */

public class SingleConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/curso-jsp";
    private static final String PASSWORD = "admin";
    private static final String USER = "postgres";
    private static Connection conection = null;
    
    static{
        conectar();
    }
    public SingleConnection(){
        conectar();
    }
    private static void conectar(){
        try{
            if(conection == null){
                 Class.forName("org.postgresql.Driver");
                conection = DriverManager.getConnection(URL,USER,PASSWORD);
                conection.setAutoCommit(false);
            }
        }catch(Exception e){
            throw new RuntimeException("Erro ao conectar com o banco de dados");
        }
    }
    public static Connection getConnection(){
        return conection;
    }
}
