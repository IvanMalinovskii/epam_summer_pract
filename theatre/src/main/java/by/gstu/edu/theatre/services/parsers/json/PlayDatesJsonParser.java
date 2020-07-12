package by.gstu.edu.theatre.services.parsers.json;

import by.gstu.edu.theatre.entities.PlayDates;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDate;
import java.util.List;

public class PlayDatesJsonParser {
    private Gson gson;

    public PlayDatesJsonParser() {
        gson = new GsonBuilder()
        .setPrettyPrinting()
        .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
        .create();
    }

    public String getJsonString(PlayDates playDates) {
        return gson.toJson(playDates);
    }

    public String getJsonString(List<PlayDates> playDates) {
        return gson.toJson(playDates);
    }

    public PlayDates getPlayDates(String jsonString) {
        return gson.fromJson(jsonString, PlayDates.class);
    }
}
