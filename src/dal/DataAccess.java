package dal;

import be.Author;
import be.Book;
import be.Category;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DataAccess implements IDataAccess {


    private List<Book> allBooks = new ArrayList<>();
    private List<Author> allAuthors = new ArrayList<>();
    private List<Category> allCategories = new ArrayList<>();


    public DataAccess() {
        fillAllBooks();
        fillAllAuthors();
        fillAllCategories();
    }

    @Override
    public void addBook(Book book) {
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/books.txt", true));
            outStream.write(book.toString());
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editBook(int id, Book book) {
        //TODO later
    }

    @Override
    public void deleteBook(int bookID) {
        List<Book> allBooks = getAllBooks();
        allBooks.removeIf(b -> b.getId() == bookID);
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/books.txt", false));
            for (Book b : allBooks) {
                outStream.write(b.toString());
            }
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCategory(Category category) {
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/category.txt", true));
            outStream.write(category.toString());
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategory(int categoryID) {
        List<Category> allCategories = getAllCategories();
        allCategories.removeIf(b -> b.getId() == categoryID);
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/category.txt", false));
            for (Category b : allCategories) {
                outStream.write(b.toString());
            }
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAuthor(Author author) {
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/author.txt", true));
            outStream.write(author.toString());
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteAuthor(int authorID) {
        List<Author> allAuthors = getAllAuthors();
        allAuthors.removeIf(a -> a.getId() == authorID);
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/author.txt", false));
            for (Author a : allAuthors) {
                outStream.write(a.toString());
            }
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return allBooks;
    }

    private void fillAllBooks() {
        try {
            List<String> books = Files.readAllLines(Paths.get("data/books.txt"));
            for (String line : books) {
                String[] lineContent = line.split(",");
                allBooks.add(new Book(
                        Integer.parseInt(lineContent[0]), //id
                        lineContent[1], //isbn
                        lineContent[2], //title
                        Boolean.parseBoolean(lineContent[3]), //rented
                        Boolean.parseBoolean(lineContent[4]), //ebook
                        Integer.parseInt(lineContent[5]), //rating
                        lineContent[6] //release date
                ));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    private void fillAllAuthors() {
        try {
            List<String> books = Files.readAllLines(Paths.get("data/authors.txt"));
            for (String line : books) {
                String[] lineContent = line.split(",");
                allAuthors.add(new Author(
                        Integer.parseInt(lineContent[0]), //id
                        lineContent[1] //name
                ));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return this.allCategories;
    }

    private void fillAllCategories() {
        try {
            List<String> categories = Files.readAllLines(Paths.get("data/category.txt"));
            for (String line : categories) {
                String[] lineContent = line.split(",");
                allCategories.add(new Category(
                        Integer.parseInt(lineContent[0]), //id
                        lineContent[1] //name
                ));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> getBookAuthConn() {
        return getConnectorContent("data/book_authors.txt");
    }

    @Override
    public List<String> getBookCatConn() {
        return getConnectorContent("data/book_cat.txt");
    }

    /**
     * Returns the content of the connector tables.
     *
     * @param file File location.
     * @return Returns a String list separated by ','. The first data is the bookID, the second is the foreignID.
     */
    private List<String> getConnectorContent(String file) {
        List<String> connections = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Paths.get(file));
            for (String line : lines) {
                String[] lineContent = line.split(",");
                connections.add(lineContent[1] + "," + lineContent[2]);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return connections;
    }
}
