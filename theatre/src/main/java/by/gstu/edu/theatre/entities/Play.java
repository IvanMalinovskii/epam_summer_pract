package by.gstu.edu.theatre.entities;

import java.util.Objects;

public class Play {
    private long id;
    private String name;
    private String imgUrl;
    private String description;
    private Author author;
    private Genre genre;

    public Play(long id, String name, String imgUrl, String description, Author author, Genre genre) {
        this.id = id;
        this.name = name;
        this.imgUrl = imgUrl;
        this.description = description;
        this.author = author;
        this.genre = genre;
    }

    public Play(String name, String imgUrl, String description, Author author, Genre genre) {
        this(-1, name, imgUrl, description, author, genre);
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

    public String getImgUrl() {
        return imgUrl;
    }

    @Override
    public String toString() {
        return "Play{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
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
                Objects.equals(imgUrl, play.imgUrl) &&
                Objects.equals(description, play.description) &&
                Objects.equals(author, play.author) &&
                Objects.equals(genre, play.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, imgUrl, description, author, genre);
    }
}
