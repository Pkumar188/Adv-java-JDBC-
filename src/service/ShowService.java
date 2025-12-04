package service;


import dao.MovieDAO;
import dao.ShowDAO;
import model.Show;

import java.util.Scanner;

public class ShowService {
    static Scanner s=new Scanner(System.in);
    public static void createShow() {
        System.out.println("enter movie Id");
        int movieid=s.nextInt();

//        if (!MovieDAO.movieExistsById(movieid)) {
//            System.out.println(" Movie not found!");
//            return;
//        }

        System.out.println("enter auditorium Id");
        int auditoriumid=s.nextInt();
        s.nextLine();

        System.out.println("enter Show Time (YYYY-MM-DD HH:MM):");
        String showTime=s.nextLine();


        System.out.println("Enter End Time (YYYY-MM-DD HH:MM):");
        String endTime = s.nextLine();

        if (ShowDAO.auditoriumConflict(auditoriumid, showTime, endTime)) {
            System.out.println(" Time conflict! Auditorium is already booked for this time.");
            return;
        }
        Show show=new Show();

        show.setMovieid(movieid);
        show.setAuditoriumid(auditoriumid);
        show.setShowTime(showTime);
        show.setEndTime(endTime);

        ShowDAO.createShow(show);

    }
}
