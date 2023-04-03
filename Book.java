/**
 * @author Yasmine Mouatif 40249967 Hanine Tydrini 40226729
 * COMP249
 * Assignment #3
 * March 29th, 2023
 */
import java.util.Objects;
import java.io.Serializable;

/**
 * Book class, has 6 attributes : title, isbn, authors, price, genre and year
 */

public class Book implements Serializable{
    private String title;
    private String authors;
    private double price;
    private long isbn;
    private String genre;
    private int year;

    /**
     * Default constructor
     */
    public Book() {
        title = "";
        authors = "";
        price = 0;
        isbn = 0;
        genre = "";
        year = 0;
    }

    /**
     * Parameterized constructor
     * @param title
     * @param authors
     * @param price
     * @param isbn
     * @param genre
     * @param year
     */

    public Book(String title, String authors, double price, long isbn, String genre, int year) {
        this.title = title;
        this.authors = authors;
        this.price = price;
        this.isbn = isbn;
        this.genre = genre;
        this.year = year;
    }

    //Getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    /**
     * Equals method
     * @param o object
     * @return boolean true if objects are equal, false is not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return getPrice() == book.getPrice() && getIsbn() == book.getIsbn() && getYear() == book.getYear() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthors(), book.getAuthors()) && Objects.equals(getGenre(), book.getGenre());
    }

    /**
     * toString method
     * @return String of the object attrbutes 
     */
    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", authors='" + authors + '\'' +
                ", price=" + price +
                ", isbn=" + isbn +
                ", genre='" + genre + '\'' +
                ", year=" + year +
                '}';
    }
}