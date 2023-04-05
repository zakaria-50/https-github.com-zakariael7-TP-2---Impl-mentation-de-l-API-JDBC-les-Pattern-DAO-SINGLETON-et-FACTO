package jdbc;

import javax.swing.*;
import java.sql.*;

public class Singleton {

    private static Connection connection ;

    private Singleton(){

        String url          = "jdbc:mysql://localhost:3306/madrasati" ,
                username    = "root" ,
                password    = "root" ,
                driver      = "com.mysql.cj.jdbc.Driver" ;
        try {
            Class.forName(driver) ;
            connection = DriverManager.getConnection(url , username ,password) ;
            System.out.println("Creation de l'instance connexion réussite ^_^(");
            JOptionPane.showMessageDialog(
                    null ,
                    "connexion etablit a la BD madrasati ^_^" ,
                    "SESSION OUVERTE" ,
                    JOptionPane.INFORMATION_MESSAGE
            );
        }
        catch (Exception e){
            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    null ,
                    e.getMessage() ,
                    "ERREUR DE CONNEXION ! " ,
                    JOptionPane.ERROR_MESSAGE
            );
        }



    }

    public static Connection getConnection(){
        if (connection == null)
            new Singleton() ;
        else System.out.println("Appel a l'instance de connexion existante ");
            return connection ;

    }

public static void main (String [] args){
        try {
            PreparedStatement ps = getConnection().prepareStatement(
                    "select * from etudiant where id = ? "
            ) ;
            Statement state   = getConnection().createStatement() ;
                                getConnection().setAutoCommit(false);
            DatabaseMetaData meta = getConnection().getMetaData() ;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
}


}
