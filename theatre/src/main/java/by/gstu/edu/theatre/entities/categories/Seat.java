package by.gstu.edu.theatre.entities.categories;

import java.util.Objects;

public class Seat {
    private int row;
    private int number;
    private boolean isAvailable;

    public Seat(int row, int number) {
        this.row = row;
        this.number = number;
        this.isAvailable = true;
    }

    public int getRow() {
        return row;
    }

    public int getNumber() {
        return number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Seat{" +
                "row=" + row +
                ", number=" + number +
                ", isAvailable=" + isAvailable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row &&
                number == seat.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, number);
    }
}
