package ru.nikita.library.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "nameOfBook")
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(min=2, max = 50, message="Название не меньше 2, и не больше 50")
    private String nameOfBook;

    @NotEmpty(message = "Имя автора не должно быть пустым")
    @Column(name = "author")
    @Size(min=2, max = 50, message="Имя автора не меньше 2, и не больше 50")
    private String author;
    
    @Min(value=0, message="Дата должна быть больше 0")
    @Column(name = "years")
    private int years;

    @Min(value=0, message="Страниц должно быть больше 0")
    @Column(name = "numberOfPages")
    private int numberOfPages;

    @Min(value=0, message="Цена должна быть больше 0")
    @Column(name = "price")
    private int price;

    public Book() {

    }

    public Book(String author, String nameOfBook, int numberOfPages, int price, int years) {
        this.author = author;
        this.nameOfBook = nameOfBook;
        this.numberOfPages = numberOfPages;
        this.price = price;
        this.years = years;
    }

    public int getId() {
        return id;
    }

    public String getNameOfBook() {
        return nameOfBook;
    }

    public void setNameOfBook(String nameOfBook) {
        this.nameOfBook = nameOfBook;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", nameOfBook=" + nameOfBook + ", author=" + author + ", years=" + years
                + ", numberOfPages=" + numberOfPages + ", price=" + price + "]";
    }

}
