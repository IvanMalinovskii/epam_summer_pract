package by.gstu.edu.theatre.entities.categories;

import by.gstu.edu.theatre.dao.daos.jdbc.daos.implementations.DateDao;
import by.gstu.edu.theatre.entities.Date;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HallRepository {
    private static HallRepository repository;
    private final Map<Long, Hall> halls;

    private HallRepository() {
        DateDao dateDao = new DateDao();
        List<Date> dates = dateDao.getAll().orElse(Collections.emptyList());
        halls = new HashMap<>();
        dates.forEach(date -> halls.put(date.getId(), new Hall(date, new SmallHallConstructor())));
    }

    public synchronized static HallRepository getInstance() {
        if (repository == null)
            repository = new HallRepository();
        return repository;
    }

    public Hall getHallByDateId(long dateId) {
        return halls.get(dateId);
    }

    public void changeStates(long dateId, Category category, boolean state) {
        Hall hall = halls.get(dateId);
        if (hall == null) return;
        List<Seat> hallSeats = hall.getByName(category.getName()).getSeats();

        hallSeats.stream()
                .filter(category.getSeats()::contains)
                .forEach(seat -> seat.setAvailable(state));
    }
}
