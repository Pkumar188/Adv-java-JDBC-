package dao;

import model.Auditorium;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AuditoriumDAO {
    public static  void auditoriumDao(Auditorium auditorium){
        String sqlQuery="insert into cinema_db.auditorium(name,seat_rows,seat_cols) values(?,?,?);";

        try {
            Connection con= DBConnection.getConnection();
            PreparedStatement pstm=con.prepareStatement(sqlQuery);
            pstm.setString(1,auditorium.getName());
            pstm.setInt(2, auditorium.getSeat_rows());
            pstm.setInt(3,auditorium.getSeat_cols());

            pstm.executeUpdate();
            System.out.println(" auditorium  inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
