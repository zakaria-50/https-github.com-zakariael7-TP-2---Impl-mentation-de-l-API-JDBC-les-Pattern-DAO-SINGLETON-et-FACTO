package jdbc;

import java.sql.*;
import java.util.Locale;

public class TestJDBC {


    public static void main (String[] args){

        String url = "jdbc:mysql://localhost:3306/madrasati" ,
                userName = "root" ,
                password = "root" ;

        Connection connection = null ;
        Statement statement   = null ;
        ResultSet resultSet   = null ;
        ResultSetMetaData resultSetMetaData = null ;


        try{
            Class.forName("com.mysql.cj.jdbc.Driver") ;
            System.out.println("Chargement du driver JDBC pour MySQL reussi ^_^");
            connection = DriverManager.getConnection(url , userName , password) ;
            System.out.println("Connexion etablit a la BD madrasati ^_^");


            statement = connection.createStatement() ;
            resultSet = statement.executeQuery(
                    "SELECT * " +
                            " FROM etudiant "

            ) ;
            resultSetMetaData = resultSet.getMetaData() ;


            System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
            while(resultSet.next()){
                for(int i = 1 ; i <= resultSetMetaData.getColumnCount() ; i++)
                    System.out.print("\t"+ resultSetMetaData.getColumnName(i).toUpperCase()
                    +" : "+ resultSet.getObject(i).toString() + "\t |"
                    );
                System.out.println("\n------------------------------------------------------------------------------------------------------------------------------------------------------------------ ");
            }
        }
        catch (ClassNotFoundException e){

            e.printStackTrace();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if(connection != null){
                try {
                    connection.close();
                }
                catch (SQLException e){
                    e.printStackTrace();
                }
                if (statement != null){
                    try {
                        statement.close();
                    }
                    catch (SQLException e){
                        e.printStackTrace();
                    }
                    if(resultSet != null){
                        try {
                            resultSet.close();
                        }catch (SQLException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }



}
