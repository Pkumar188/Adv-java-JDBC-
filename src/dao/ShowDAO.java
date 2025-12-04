package dao;

import model.Show;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowDAO {

    public static void createShow(Show show){
        String sqlQuery="insert into shows(movie_id,auditorium_id,show_time,end_time) values(?,?,?,?);";

        try{
            Connection con= DBConnection.getConnection();
            PreparedStatement pstm=con.prepareStatement(sqlQuery);
            pstm.setInt(1,show.getMovieid());
            pstm.setInt(2,show.getAuditoriumid());
            pstm.setString(3,show.getShowTime());
            pstm.setString(4,show.getEndTime());

            pstm.executeUpdate();
            System.out.println("show created successfully");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean auditoriumConflict(int auditoriumid,String showTime,String endTime)
    {
        String sql="select * from shows where auditorium_id=? and (showTime=? and endTime =?);";
        try{
            Connection con=DBConnection.getConnection();
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,auditoriumid);
            ps.setString(2,endTime);
            ps.setString(3,showTime);
            ResultSet rs= ps.executeQuery();
            return rs.next();

        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return true;
        }
    }



}


