package service;
import dao.MovieDAO;
import model.Movie;

import  java.util.Scanner;
public class MovieService {
    static  Scanner s=new Scanner(System.in);
    public static void addMovie() {

        System.out.println("enter movies details");

        System.out.println("enter movie title");
        String title=s.next();

        System.out.println("enter language");
        String language=s.next();



        System.out.println("enter duration");
        int duration=s.nextInt();

        System.out.println("enter certification");
        String certification=s.next();

        Movie movie=new Movie();

        movie.setTitle(title);
        movie.setLanguage(language);
        movie.setDuration(duration);
        movie.setCertification(certification);
        if (MovieDAO.movieExists(title, language)) {
            System.out.println("Movie already exists!");
            return;
        }

        MovieDAO.movieDao(movie);

    }


}
