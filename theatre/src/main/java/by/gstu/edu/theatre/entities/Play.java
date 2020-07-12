package by.gstu.edu.theatre.entities;

import java.util.Objects;

public class Play {
    private long id;
    private String name;
    private String description;
    private Author author;
    private Genre genre;

    public Play(long id, String name, String description, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public Play(String name, String description, Author author, Genre genre) {
        this(-1, name, description, author, genre);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Author getAuthor() {
        return author;
    }

    public Genre getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Play{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", author=" + author +
                ", genre=" + genre +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Play play = (Play) o;
        return id == play.id &&
                Objects.equals(name, play.name) &&
                Objects.equals(description, play.description) &&
                Objects.equals(author, play.author) &&
                Objects.equals(genre, play.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, author, genre);
    }
}
