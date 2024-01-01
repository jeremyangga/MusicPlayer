/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package musicplayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 
 */
public class ConnectDatabase {
    Lagu lagu = new Lagu();
    public static String url = "jdbc:sqlite:database.db";
    
     public static Connection getConnection(){
        Connection con = null;
        try 
        {
            con = DriverManager.getConnection(url);            
        } catch (SQLException ex) 
        {
            System.out.println(ex.getMessage());
        }
        return con;
    }
     
}
