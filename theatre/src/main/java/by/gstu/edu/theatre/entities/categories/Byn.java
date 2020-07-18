package by.gstu.edu.theatre.entities.categories;

public class Byn {
    private int coins;

    public Byn(String coins) {
        String[] parts = coins.split("[.]");
        this.coins = Integer.parseInt(parts[0]) * 100 + Integer.parseInt(parts[1]);
    }

    public int getCoins() {
        return coins;
    }

    @Override
    public String toString() {
        int kopecks = coins % 100;
        return coins / 100 + "." + kopecks / 10 + kopecks % 10;
    }
}
