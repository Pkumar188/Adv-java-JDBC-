package model;

import java.time.LocalDateTime;

public class Show {
    private int movieid;
    private int auditoriumid;
    private String showTime;
    private String endTime;

    public int getMovieid() {
        return movieid;
    }

    public void setMovieid(int movieid) {
        this.movieid = movieid;
    }

    public int getAuditoriumid() {
        return auditoriumid;
    }

    public void setAuditoriumid(int auditoriumid) {
        this.auditoriumid = auditoriumid;
    }

    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
