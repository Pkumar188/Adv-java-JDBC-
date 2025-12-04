package dao;

import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class SeatDAO {

    public static List<String> viewSeats(int showId) {
        String query = """
                SELECT ss.id, s.row_label, s.seat_no, ss.status, ss.price
                FROM show_seats ss
                JOIN seats s ON ss.seat_id = s.id
                WHERE ss.show_id = ?;
                """;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, showId);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n---- Seat Availability ----");
            System.out.printf("%-10s %-10s %-10s %-12s %-8s\n",
                    "SeatID", "Row", "SeatNo", "Status", "Price");

            while (rs.next()) {
                System.out.printf("%-10d %-10s %-10d %-12s %-8.2f\n",
                        rs.getInt("id"),
                        rs.getString("row_label"),
                        rs.getInt("seat_no"),
                        rs.getString("status"),
                        rs.getDouble("price"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

