package by.gstu.edu.theatre.services;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.interfaces.Dao;
import by.gstu.edu.theatre.entities.Date;
import by.gstu.edu.theatre.entities.Play;
import by.gstu.edu.theatre.entities.PlayDates;
import by.gstu.edu.theatre.services.parsers.json.PlayDatesJsonParser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PlayService {
    private Dao<Play> playDao;
    private Dao<Date> dateDao;
    private PlayDatesJsonParser parser;

    public PlayService(Dao<Play> playDao, Dao<Date> dateDao, PlayDatesJsonParser parser) {
        this.playDao = playDao;
        this.dateDao = dateDao;
        this.parser = parser;
    }

    public String getAllPlays() {
        List<Play> plays = playDao.getAll().orElse(Collections.emptyList());
        List<Date> dates = dateDao.getAll().orElse(Collections.emptyList());
        List<PlayDates> playDates = plays.stream()
                .map(play -> new PlayDates(play, dates.stream().filter(date -> play.getId() == date.getPlayId()).collect(Collectors.toList())))
                .collect(Collectors.toList());
        return parser.getJsonString(playDates);
    }

    public String getPlay(long id) {
        final String[] result = {"{}"};
        playDao.get(id).ifPresent(p -> {
            List<Date> dates = dateDao.getAll().orElse(Collections.emptyList());
            PlayDates playDates = new PlayDates(p, dates.stream().filter(date -> p.getId() == date.getPlayId()).collect(Collectors.toList()));
            result[0] = parser.getJsonString(playDates);
        });
        return result[0];
    }
}
