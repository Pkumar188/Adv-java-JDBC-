package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private  static  final String dburl="jdbc:mysql://localhost:3306/cinemadb1";
    private  static  final  String user="root";
    private  static final String password="Pavan@12345";

    public static Connection getConnection(){
        try {

            return DriverManager.getConnection(dburl,user,password);
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }


}
