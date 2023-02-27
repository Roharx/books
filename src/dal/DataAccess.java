package dal;

import be.Author;
import be.Book;
import be.Category;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/*
@author Bálint Farkas
 */

public class DataAccess implements IDataAccess {

    DbConnector dbConnector = new DbConnector();

    private List<Book> allBooks = new ArrayList<>();
    private List<Author> allAuthors = new ArrayList<>();
    private List<Category> allCategories = new ArrayList<>();
    private List<String> allBookNotes = new ArrayList<>();


    public DataAccess() {
        fillAllBooks();
        fillAllAuthors();
        fillAllCategories();
        fillAllBookNotes();
    }

    @Override
    public void addBook(Book book) {
        try {
            String psql = "INSERT INTO books (isbn, title, rented, ebook, rating, releaseDate) VALUES (?,?,?,?,?,?)";

            PreparedStatement pst = dbConnector.createConnection().prepareStatement(psql);
            pst.setString(1, book.getIsbn());
            pst.setString(2, book.getTitle());
            pst.setBoolean(3, book.isRented());
            pst.setBoolean(4, book.isEbook());
            pst.setInt(5, book.getRating());
            pst.setString(6, book.getReleaseDate());
            pst.execute();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editBook(int id, Book book) {
        //TODO later
    }

    @Override
    public void deleteBook(int bookID) {
        try {
            Connection con = dbConnector.createConnection();
            PreparedStatement pstCatMovie = con.prepareStatement("DELETE FROM books WHERE id = ?;");
            pstCatMovie.setInt(1, bookID);
            pstCatMovie.executeUpdate();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addCategory(Category category) {
        try {
            String psql = "INSERT INTO category (name) VALUES (?)";

            PreparedStatement pst = dbConnector.createConnection().prepareStatement(psql);
            pst.setString(1, category.getName());
            pst.execute();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteCategory(int categoryID) {
        try {
            Connection con = dbConnector.createConnection();
            PreparedStatement pstCatMovie = con.prepareStatement("DELETE FROM category WHERE id = ?;");
            pstCatMovie.setInt(1, categoryID);
            pstCatMovie.executeUpdate();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addAuthor(Author author) {
        try {
            String psql = "INSERT INTO authors (name) VALUES (?)";

            PreparedStatement pst = dbConnector.createConnection().prepareStatement(psql);
            pst.setString(1, author.getName());
            pst.execute();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteAuthor(int authorID) {
        try {
            Connection con = dbConnector.createConnection();
            PreparedStatement pstCatMovie = con.prepareStatement("DELETE FROM authors WHERE id = ?;");
            pstCatMovie.setInt(1, authorID);
            pstCatMovie.executeUpdate();

        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveBookNote(int bookID, String note) {
        boolean foundLine = false;
        for (String line : allBookNotes) {
            String[] lineContent = line.split(",");
            if (Integer.parseInt(lineContent[0]) == bookID) {
                allBookNotes.remove(line);
                allBookNotes.add(bookID + "," + note);
                foundLine = true;
            }
        }
        if(!foundLine)
            allBookNotes.add(bookID + "," + note);
        try {
            BufferedWriter outStream = new BufferedWriter(new FileWriter("data/book_notes.txt", false));

            for (String line : allBookNotes) {
                outStream.write(line);
                outStream.newLine();
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
        allBooks.clear();
        List<Book> allBooks = new ArrayList<>();

        try (Connection con = dbConnector.createConnection()) {
            String sql = "SELECT * FROM books;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    Book book = new Book(
                            resultSet.getInt("id"),
                            resultSet.getString("isbn"),
                            resultSet.getString("title"),
                            resultSet.getBoolean("rented"),
                            resultSet.getBoolean("ebook"),
                            resultSet.getInt("rating"),
                            resultSet.getString("releaseDate")
                    );
                    allBooks.add(book);
                }
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return allAuthors;
    }

    private void fillAllAuthors() {
        allAuthors.clear();
        List<Author> allAuthors = new ArrayList<>();

        try (Connection con = dbConnector.createConnection()) {
            String sql = "SELECT * FROM authors;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    Author author = new Author(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                    allAuthors.add(author);
                }
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Category> getAllCategories() {
        return this.allCategories;
    }

    private void fillAllCategories() {
        allCategories.clear();
        List<Category> allCategories = new ArrayList<>();

        try (Connection con = dbConnector.createConnection()) {
            String sql = "SELECT * FROM category;";
            Statement statement = con.createStatement();

            if (statement.execute(sql)) {
                ResultSet resultSet = statement.getResultSet();

                while (resultSet.next()) {
                    Category category = new Category(
                            resultSet.getInt("id"),
                            resultSet.getString("name")
                    );
                    allCategories.add(category);
                }
            }
        } catch (SQLServerException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void fillAllBookNotes() {
        allBookNotes.clear();
        try {
            List<String> bookNotes = Files.readAllLines(Paths.get("data/book_notes.txt"));
            allBookNotes.addAll(bookNotes);

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

    @Override
    public List<String> getAllBookNotes() {
        return allBookNotes;
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
