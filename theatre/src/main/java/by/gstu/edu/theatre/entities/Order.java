package by.gstu.edu.theatre.entities;

import by.gstu.edu.theatre.entities.categories.Category;

public class Order {
    private long id;
    private Category.CategoryType category;
    private long dateId;
    private long userId;
    private int quantity;

    public Order(long id, Category.CategoryType category, int quantity, long dateId, long userId) {
        this.id = id;
        this.category = category;
        this.dateId = dateId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Order(Category.CategoryType category, int quantity, long dateId, long userId) {
        this(-1, category, quantity, dateId, userId);
    }

    public long getId() {
        return id;
    }

    public Category.CategoryType getCategory() {
        return category;
    }

    public long getDate() {
        return dateId;
    }

    public long getUser() {
        return userId;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", category=" + category +
                ", dateId=" + dateId +
                ", userId=" + userId +
                '}';
    }
}
