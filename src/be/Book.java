package be;

/*
@author Bálint Farkas
 */

public class Book {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    private String isbn;
    private String title;
    private boolean rented;
    private boolean ebook;
    private int rating;
    private String releaseDate;

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

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRented() {
        return rented;
    }

    public void setRented(boolean rented) {
        this.rented = rented;
    }

    public boolean isEbook() {
        return ebook;
    }

    public void setEbook(boolean ebook) {
        this.ebook = ebook;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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
