package dal;

import be.Author;
import be.Book;
import be.Category;

import java.io.BufferedReader;
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
            BufferedWriter outStream= new BufferedWriter(new FileWriter("data/books.txt", true));
            outStream.write(book.toString());
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
    public void editBook(int bookID, Book book) {
        //TODO later
    }

    @Override
    public void deleteBook(int isbn) {
        Book bookToDelete = getBookByISBN(isbn);
        List<Book> allBooks = getAllBooks();
        for (Book b :
                allBooks) {
            if (b.getIsbn() == isbn)
                allBooks.remove(b);
        }
        try {
            BufferedWriter outStream= new BufferedWriter(new FileWriter("data/books.txt", false));
            for (Book b :
                    allBooks) {
                outStream.write(b.toString());
            }
            outStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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
    public Author getAuthorByID(int id) {

        List<Author> allAuthors = getAllAuthors();
        for (Author a :
                allAuthors) {
            if (a.getId() == id)
                return a;
        }

        return null;
    }


    @Override
    public List<Book> getBooksByCategory(Category category) {
        return null;
    }

    @Override
    public List<Book> getAllBooksByAuthor(int authorID) {
        List<Book> booksForAuthor = new ArrayList<>();
        List<Integer> bookIds = new ArrayList<>();

        try {
            List<String> authors = Files.readAllLines(Path.of("data/book_authors.txt"));
            for (String line : authors) {
                String[] lineContent = line.split(",");
                if(Integer.parseInt(lineContent[2]) == authorID)
                    bookIds.add(Integer.parseInt(lineContent[2]));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    @Override
    public Book getBookByISBN(int isbn) {

        List<Book> allBooks = getAllBooks();
        for (Book b :
                allBooks) {
            if (b.getIsbn() == isbn)
                return b;
        }

        return null;
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
    public Category getCategoryByID(int id) {

        List<Category> allCategories = getAllCategories();
        for (Category c :
                allCategories) {
            if (c.getId() == id)
                return c;
        }

        return null;
    }
}
