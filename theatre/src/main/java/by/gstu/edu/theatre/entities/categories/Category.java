package by.gstu.edu.theatre.entities.categories;

import java.util.List;

public class Category {

    public enum CategoryType {
        PARTERRE(1),
        BALCONY(2);

        private int index;
        CategoryType(int index) {
            this.index = index;
        }

        public int index() {
            return index;
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    private Byn price;
    private String name;
    private List<Seat> seats;

    public Category(String name, List<Seat> seats, Byn price) {
        this.name = name;
        this.seats = seats;
        this.price = price;
    }

    public Byn getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public Seat getPlaceByNumber(int row, int number) {
        return seats.stream()
                .filter(seat -> seat.getRow() == row && seat.getNumber() == number)
                .findFirst()
                .orElseThrow(() -> new ArrayIndexOutOfBoundsException("there is no such seat: " + row + ":" + number));
    }

    @Override
    public String toString() {
        return "Category{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", seats=" + seats +
                '}';
    }
}
