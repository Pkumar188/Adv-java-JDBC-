package service;

//import dao.BookingDAO;
import dao.BookingDAO;
import dao.SeatDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SeatService {
    static Scanner s=new Scanner(System.in);
    public static void viewSeats() {
        System.out.println("Enter show Id:");
        int show_id=s.nextInt();

        List<String> seats = SeatDAO.viewSeats(show_id);
        System.out.println("\n=== Seat Availability ===");
        for (String seat : seats) {
            System.out.println(seat);
        }
        System.out.println("==========================");
    }

    public static void bookSeats() {
        System.out.print("Enter Show ID: ");
        int show_id = s.nextInt();

        s.nextLine();
        System.out.print("Enter Your Name: ");
        String username = s.nextLine();

        System.out.print("Enter Phone: ");
        String phone = s.nextLine();

        System.out.print("Enter Show Seat IDs to book (comma separated): ");
        String show_seat_id = s.nextLine();

        List<Integer> seatIds = new ArrayList<>();
        for (String id : show_seat_id.split(",")) {
            seatIds.add(Integer.parseInt(id.trim()));
        }

        boolean success = BookingDAO.bookSeats(username, phone, show_id, seatIds);

        if (!success) {
            System.out.println("❌ Booking Failed");
        }
    }
}
