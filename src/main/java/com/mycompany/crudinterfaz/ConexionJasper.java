/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.crudinterfaz;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author david
 */
public class ConexionJasper {
    
    private static Connection conexion;
    
    static {
        String url="jdbc:mysql://localhost:3306/cruddesayunos?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String user="root";
        String pass="";
        
        try {
            conexion = DriverManager.getConnection(url, user, pass);
            System.out.println("Conexión realizada con éxito");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionJasper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Connection getConexion() {
        return conexion;
    }
    
}
