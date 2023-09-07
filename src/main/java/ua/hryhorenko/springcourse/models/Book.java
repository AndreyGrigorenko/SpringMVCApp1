package ua.hryhorenko.springcourse.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {
  private int id;
  @NotEmpty(message = "Title should not be empty")
  @Size(min = 2, max = 100, message = "Title should be from 2 to 100 characters")
  private String title;
  @NotEmpty(message = "Author's name should not be empty")
  @Size(min = 2, max = 100, message = "Author's name should be from 2 to 100 characters")
  private String autor;
  @Min(value = 1500, message = "Year should be more than 1500")
  private int year;

  public Book() {
  }

  public Book(String title, String author, int year) {
    this.title = title;
    this.autor = author;
    this.year = year;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return autor;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }
}
