package dal;

import be.Author;
import be.Book;
import be.Category;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class DataAccess implements IDataAccess {
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
    public void editBook(int isbn, Book book) {
        //TODO later
    }

    @Override
    public void deleteBook(int isbn) {
        List<Book> allBooks = getAllBooks();
        for (Book b : allBooks) {
            if (b.getIsbn() == isbn)
                allBooks.remove(b);
        }
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
    public List<Book> getAllBooks() {
        List<Book> allBooks = new ArrayList<>();

        try {
            List<String> books = Files.readAllLines(Path.of("data/books.txt"));
            for (String line : books) {
                String[] lineContent = line.split(",");
                allBooks.add(new Book(
                        Integer.parseInt(lineContent[0]),
                        lineContent[1],
                        Boolean.parseBoolean(lineContent[2]),
                        Boolean.parseBoolean(lineContent[3]),
                        Integer.parseInt(lineContent[4]),
                        lineContent[5]
                ));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allBooks;
    }

    @Override
    public List<Author> getAllAuthors() {
        List<Author> allAuthors = new ArrayList<>();

        try {
            List<String> books = Files.readAllLines(Path.of("data/books.txt"));
            for (String line : books) {
                String[] lineContent = line.split(",");
                allAuthors.add(new Author(
                        Integer.parseInt(lineContent[0]),
                        lineContent[1]
                ));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allAuthors;
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> allCategories = new ArrayList<>();

        try {
            List<String> categories = Files.readAllLines(Path.of("data/books.txt"));
            for (String line : categories) {
                String[] lineContent = line.split(",");
                allCategories.add(new Category(
                        Integer.parseInt(lineContent[0]),
                        lineContent[1]
                ));

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return allCategories;
    }

    @Override
    public List<String> getBookAuthConn() {
        return getTableConnections("data/book_authors.txt");
    }

    @Override
    public List<String> getBookCatConn() {
        return getTableConnections("data/book_cat.txt");
    }

    private List<String> getTableConnections(String file) {
        List<String> connections = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(file));
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
