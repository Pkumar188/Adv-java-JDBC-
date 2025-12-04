package dao;

import util.DBConnection;

import java.sql.*;
import java.util.List;

public class BookingDAO {
    public static boolean bookSeats(String username, String phone, int showId, List<Integer> seatIds) {

        String createBookingSql="insert into bookings(user_name,user_phone,show_id,total_amount) values(?,?,?,?);";
        String lockSeatCheckSql="select status,price from show_seats where show_seats_id=? for update;";
        String updateSeatSql="update show_seats set status='BOOKED' where show_seats_id=?;";
        String insertBookingItemSql="insert into booking_items(booking_id,show_seats_id,price) values(?,?,?);";

        Connection con=null;
        try{
            con= DBConnection.getConnection();
            con.setAutoCommit(false);

            double totalAmount=0.0;

            for (int seat_id : seatIds){
                PreparedStatement checkpstm=con.prepareStatement(lockSeatCheckSql);
                checkpstm.setInt(1,seat_id);
                ResultSet rs=checkpstm.executeQuery();

                if(!rs.next() || !rs.getString("status").equals("AVAILABLE"))
                {
                    con.rollback();
                    System.out.println("Seat "+ seat_id+ "  not availabel!");
                    return  false;
                }
                totalAmount+=rs.getDouble("price");

            }
            PreparedStatement pst=con.prepareStatement(createBookingSql);
            pst.setString(1,username);
            pst.setString(2,phone);
            pst.setInt(3,showId);
            pst.setDouble(4,totalAmount);


            pst.executeUpdate();
//            rs.next();
            for (int seatId : seatIds) {
                PreparedStatement updateSeat = con.prepareStatement(updateSeatSql);
                updateSeat.setInt(1, seatId);
                updateSeat.executeUpdate();

                PreparedStatement insertItem = con.prepareStatement(insertBookingItemSql);
                insertItem.setInt(1, booking_id);
                insertItem.setInt(2, seatId);
                insertItem.setDouble(3, totalAmount / showId);
                insertItem.executeUpdate();
            }
            con.commit();
            System.out.println("Booking Successfully Booking ID ="+booking_id);
            return true;

        } catch (Exception e) {
            try {
                if(con!=null)
                    con.rollback();
            }
            catch (SQLException ex)
            {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        }
        finally {
            try{
                if(con!=null)
                    con.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
////        return false;
//        try (Connection conn = DBConnection.getConnection()) {
//
//            conn.setAutoCommit(false);
//
//            // Step 1 — check availability
//            String checkSQL = "SELECT status FROM show_seats WHERE id=? FOR UPDATE";
//            String updateSQL = "UPDATE show_seats SET status='BOOKED' WHERE id=?";
//            PreparedStatement checkStmt = conn.prepareStatement(checkSQL);
//            PreparedStatement updateStmt = conn.prepareStatement(updateSQL);
//
//            double totalAmount = 0.0;
//
//            for (int sid : seatIds) {
//                checkStmt.setInt(1, sid);
//                ResultSet rs = checkStmt.executeQuery();
//
//                if (!rs.next() || !rs.getString("status").equals("AVAILABLE")) {
//                    conn.rollback();
//                    System.out.println("❌ Seat " + sid + " not available.");
//                    return false;
//                }
//
//                // Fetch price
//                PreparedStatement priceStmt = conn.prepareStatement("SELECT price FROM show_seats WHERE id=?");
//                priceStmt.setInt(1, sid);
//                ResultSet priceRS = priceStmt.executeQuery();
//                if (priceRS.next()) totalAmount += priceRS.getDouble("price");
//            }
//
//            // Step 2 — Insert booking master record
//            String bookingSQL = "INSERT INTO bookings(user_name, user_phone, show_id, total_amount, status) VALUES (?, ?, ?, ?, 'BOOKED')";
//            PreparedStatement bookingStmt = conn.prepareStatement(bookingSQL, Statement.RETURN_GENERATED_KEYS);
//
//            bookingStmt.setString(1, username);
//            bookingStmt.setString(2, phone);
//            bookingStmt.setInt(3, showId);
//            bookingStmt.setDouble(4, totalAmount);
//            bookingStmt.executeUpdate();
//
//            ResultSet generatedKeys = bookingStmt.getGeneratedKeys();
//            generatedKeys.next();
//            int bookingId = generatedKeys.getInt(1);
//
//            // Step 3 — update seats & insert booking_items
//            String bookingItemSQL = "INSERT INTO booking_items(booking_id, show_seat_id, price) VALUES (?, ?, ?)";
//            PreparedStatement bookingItemStmt = conn.prepareStatement(bookingItemSQL);
//
//            for (int sid : seatIds) {
//                updateStmt.setInt(1, sid);
//                updateStmt.executeUpdate();
//
//                PreparedStatement priceStmt = conn.prepareStatement("SELECT price FROM show_seats WHERE id=?");
//                priceStmt.setInt(1, sid);
//                ResultSet priceRS = priceStmt.executeQuery();
//                priceRS.next();
//
//                bookingItemStmt.setInt(1, bookingId);
//                bookingItemStmt.setInt(2, sid);
//                bookingItemStmt.setDouble(3, priceRS.getDouble("price"));
//                bookingItemStmt.executeUpdate();
//            }
//
//            conn.commit();
//            System.out.println("🎉 Booking Successful! Total Amount: " + totalAmount);
//            return true;
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("❌ Booking failed. Transaction rolled back.");
//            return false;
//        }
//    }
}
