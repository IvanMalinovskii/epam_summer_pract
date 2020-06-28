package by.gstu.edu.theatre.entities;

public enum Role {
    USER(1),
    COURIER(2),
    ADMIN(3);

    private int index;
    Role(int index) {
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
