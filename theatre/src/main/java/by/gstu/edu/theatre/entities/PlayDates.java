package by.gstu.edu.theatre.entities;

import java.util.List;
import java.util.Objects;

public class PlayDates {
    private Play play;
    private List<Date> dates;

    public PlayDates(Play play, List<Date> dates) {
        this.play = play;
        this.dates = dates;
    }

    public Play getPlay() {
        return play;
    }

    public List<Date> getDates() {
        return dates;
    }

    @Override
    public String toString() {
        return "PlayDates{" +
                "play=" + play +
                ", dates=" + dates +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayDates playDates = (PlayDates) o;
        return Objects.equals(play, playDates.play) &&
                Objects.equals(dates, playDates.dates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(play, dates);
    }
}
