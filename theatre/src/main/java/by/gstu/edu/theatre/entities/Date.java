package by.gstu.edu.theatre.entities;

import java.time.LocalDate;
import java.util.Objects;

public class Date {
    private long id;
    private LocalDate date;
    private long playId;


    public Date(long id, LocalDate date, long playId) {
        this.id = id;
        this.date = date;
        this.playId = playId;
    }

    public Date(LocalDate date, long playId) {
        this(-1, date, playId);
    }

    public long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public long getPlayId() {
        return playId;
    }

    @Override
    public String toString() {
        return "Date{" +
                "id=" + id +
                ", date=" + date +
                ", playId=" + playId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Date date1 = (Date) o;
        return id == date1.id &&
                playId == date1.playId &&
                Objects.equals(date, date1.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, playId);
    }
}
