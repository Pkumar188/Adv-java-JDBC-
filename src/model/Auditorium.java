package model;

public class Auditorium {
    private String name;
    private int seat_rows;
    private int seat_cols;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSeat_rows() {
        return seat_rows;
    }

    public void setSeat_rows(int seat_rows) {
        this.seat_rows = seat_rows;
    }

    public int getSeat_cols() {
        return seat_cols;
    }

    public void setSeat_cols(int seat_cols) {
        this.seat_cols = seat_cols;
    }
}
