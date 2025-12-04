package dao;

import model.Movie;
import service.MovieService;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MovieDAO {
    public static void movieDao(Movie movie){
        String sqlQuery="insert into cinema_db.movies(title,language,duration,certification) values(?,?,?,?);";
        try {
            Connection con= DBConnection.getConnection();
            PreparedStatement pstm=con.prepareStatement(sqlQuery);
            pstm.setString(1,movie.getTitle());
            pstm.setString(2, movie.getLanguage());
            pstm.setInt(3,movie.getDuration());
            pstm.setString(4, movie.getCertification());
            pstm.executeUpdate();
            System.out.println(" movie inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public static boolean movieExists(String title,String language){
        String selectsql="select * from cinema_db.movies where title=? and language=?;";

        try (  Connection con= DBConnection.getConnection();
                PreparedStatement pstm1 = con.prepareStatement(selectsql)) {
            pstm1.setString(1, title);
            pstm1.setString(2, language);

            ResultSet rs=pstm1.executeQuery();

                return  rs.next();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean movieExistsById(int movieId) {
        String sql = "SELECT id FROM movies WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, movieId);
            ResultSet rs = ps.executeQuery();
            return rs.next();

        } catch (Exception e) {
            return false;
        }
    }

}
