import service.AuditoriumService;
import service.MovieService;
import service.SeatService;
import service.ShowService;

import java.util.Scanner;

class Main{
    public static  void main(String[] args){

        Scanner sc=new Scanner(System.in);


        System.out.println("Select user type:");
        System.out.println("1. Admin");
        System.out.println("2. Customer");
        System.out.println("3. Exit");
        System.out.println("Barath");

        System.out.print("Choose: ");
        int ch = sc.nextInt();

        switch (ch) {
            case 1:
                System.out.println("you selected Admin Role");
                System.out.println("1. Add Movie");
                System.out.println("2. Set Auditorium");

                System.out.println("3. Create Show");
                System.out.println("4. Exit");

                System.out.print("Choose: ");
                int ch1 = sc.nextInt();
                switch (ch1){

                    case 1:
                        MovieService.addMovie();
                        break;
                    case 2:
                        AuditoriumService.addAuditorium();
                    case 3:
                        ShowService.createShow();
                        break;
                }
                break;

            case 2:
                System.out.println("you selected Customer Role");
                System.out.println("1. View Seats");
                System.out.println("2. Boot Tickets");
                System.out.println("3. Confirm Payments");
                System.out.println("4. CancelBooking");
                System.out.println("5. Exit");
                System.out.print("Choose: ");
                int ch2 = sc.nextInt();
                switch (ch2){
                    case 1:
                        System.out.println("view seats");
                        SeatService.bookSeats();
                        SeatService.viewSeats();
                        break;

                    case 2:
                        System.out.println("Now your booking seats");
//                        SeatService.bookSeats();
                }
                break;

            default:
                System.out.println("InValidUser number");
        }
        System.out.println("hello pavan");
    }
}