/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fill
 */
public class Connection {
    
    private String usuario = "postgres";
    private String senha = "my";
    private String ip = "localhost";
    private String porta = "5432";
    private String banco = "estocar";
    public java.sql.Connection connection;
    public java.sql.ResultSet resultSet;
    public java.sql.Statement statement;
        
    public void open(){
        try{
            System.setProperty("jdbc.Driver", "org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://"+ip+":"+porta+"/"+banco, usuario, senha);
            System.out.println("Conectado com sucesso!");
        }catch(java.sql.SQLException erro){
            System.out.println("Erro ao conectar: \n"+erro.getMessage());
        }
        
    }
    
    public void close(){
        
        try {
            connection.close();
            System.out.println("Desconectado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
     
    public void exec(String sql){
        try {
            statement = connection.createStatement(ResultSet.CONCUR_READ_ONLY, ResultSet.TYPE_SCROLL_INSENSITIVE);
            resultSet = statement.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
