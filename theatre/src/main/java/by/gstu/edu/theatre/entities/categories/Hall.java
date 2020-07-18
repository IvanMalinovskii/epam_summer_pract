package by.gstu.edu.theatre.entities.categories;

import by.gstu.edu.theatre.entities.Date;

import java.time.LocalDate;
import java.util.List;

public class Hall {
    private Date date;
    private List<Category> categories;

    public Hall(Date date, HallConstructor constructor) {
        this.date = date;
        this.categories = constructor.create();
    }

    public Date getDate() {
        return date;
    }

    public Category getByName(String categoryName) {
        return categories.stream()
                .filter(category -> category.getName().equals(categoryName))
                .findAny().orElseThrow(() -> new IllegalArgumentException("sth"));
    }

    public List<Category> getCategories() {
        return categories;
    }
}
