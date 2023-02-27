package be;

/*
@author Bálint Farkas
 */

public class Book {

    public int getId() {
        return id;
    }
    private final int id;
    private final String isbn;
    private final String title;
    private final boolean rented;
    private final boolean ebook;
    private final int rating;
    private final String releaseDate;

    public Book(int id, String isbn, String title, boolean rented, boolean ebook, int rating, String releaseDate) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.rented = rented;
        this.ebook = ebook;
        this.rating = rating;
        this.releaseDate = releaseDate;
    }

    public String getIsbn() {
        return isbn;
    }
    public String getTitle() {
        return title;
    }
    public boolean isRented() {
        return rented;
    }
    public boolean isEbook() {
        return ebook;
    }
    public int getRating() {
        return rating;
    }
    public String getReleaseDate() {
        return releaseDate;
    }

    @Override
    public String toString() {
        return
                id +
                "," + isbn +
                "," + title +
                "," + rented +
                "," + ebook +
                "," + rating +
                "," + releaseDate;
    }
}
