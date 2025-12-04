package service;

import dao.AuditoriumDAO;
import model.Auditorium;

import java.util.Scanner;

public class AuditoriumService {
    static Scanner s=new Scanner(System.in);
    public static void addAuditorium() {
        System.out.println("Enter Auditorium details");

        System.out.println("Enter Auditorium Name:");
        String name=s.next();

        System.out.println("Enter no.of seats in a row:");
        int seat_rows=s.nextInt();

        System.out.println("Enter no.of seats in a cols:");
        int seat_cols=s.nextInt();

        Auditorium auditorium=new Auditorium();

        auditorium.setName(name);
        auditorium.setSeat_rows(seat_rows);
        auditorium.setSeat_cols(seat_cols);

        AuditoriumDAO.auditoriumDao(auditorium);
    }

}
