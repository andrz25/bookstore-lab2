package com.university.bookstore.model;

import java.util.Objects;

/**
 * Represents an immutable book with an ISBN, title, author, price, and year of publication.
 * <p>
 * This class validates all fields during construction:
 * <ul>
 *   <li>ISBN must be either 10 or 13 digits (numeric only, no hyphens or spaces).</li>
 *   <li>Title and author must be non-null, non-blank strings.</li>
 *   <li>Price must be non-negative (zero allowed).</li>
 *   <li>Year must be between 1450 and the current year + 1.</li>
 * </ul>
 */

public final class Book implements Comparable<Book> {
  private final String isbn;
  private final String title;
  private final String author;
  private final double price;
  private final int year;

  /**
   * Constructs a new book instance with the given properties for validation.
   *
   * @param isbn   the ISBN of the book (must be 10 or 13 digits, numeric only)
   * @param title  the title of the book (non-null, non-blank)
   * @param author the author of the book (non-null, non-blank)
   * @param price  the price of the book (must be ≥ 0.0)
   * @param year   the publication year (1450 ≤ year ≤ current year + 1)
   * @throws NullPointerException     if any parameter is null
   * @throws IllegalArgumentException if any of the arguments fail validation
   */

  public Book(String isbn, String title, String author, double price, int year) {
    this.isbn = validateIsbn(isbn);
    this.title = validateString(title, "Title");
    this.author = validateString(author, "Author");
    this.price = validatePrice(price);
    this.year = validateYear(year);
  }

  // Validators
  private String validateIsbn(String isbn) {

    if (isbn == null) {
      throw new NullPointerException("ISBN cannot be null");
    }
    if (isbn.isEmpty()) {
      throw new IllegalArgumentException("ISBN: cannot be empty");
    }
    if (isbn.length() != 13 && isbn.length() != 10) {
      throw new IllegalArgumentException("ISBN: " + isbn + " must be 10 or 13 digits in length");
    }

    for (char c : isbn.toCharArray()) {
      if (!Character.isDigit(c)) {
        throw new IllegalArgumentException("ISBN: " + isbn + " must only contain digits");
      }
    }

    return isbn;
  }

  private String validateString(String string, String fieldName) {
    /*
     * Title and Author Validation:
     * – Must be non-null and non-blank
     * – Should contain meaningful text (not just spaces)
     * – Consider trimming whitespace from input
     */

    if (string == null) {
      throw new NullPointerException(fieldName + " cannot be null");
    }
    if (string.trim().isEmpty()) {
      throw new IllegalArgumentException(fieldName + ": " + string + " cannot be  empty");
    }

    return string.trim();
  }

  private double validatePrice(double price) {
    /*
     * Price Validation:
     * – Must be non-negative (> 0.0)
     * – 0.0 is allowed for free books, promotional items, etc.
     * – Consider using BigDecimal for precise monetary calculations in production
     */

    if (!(price >= 0.0)) {
      throw new IllegalArgumentException("Price: " + price + " cannot be negative ");
    }

    return price;
  }

  private int validateYear(int year) {
    /*
     * Year Validation:
     * – Must be between 1450 (invention of printing press) and current year + 1
     * – Current year + 1 allows for pre-orders of upcoming books
     * – Prevents obviously invalid dates like year 3000 or year 1000
     */
    if (year < 1450 || year > 2026) {
      throw new IllegalArgumentException("Year: " + year + " must be between 1450 and 2026");
    }

    return year;
  }

  // Getters

  /**
   * Returns the ISBN of the book.
   *
   * @return the ISBN
  */
  public String getIsbn() {
    return isbn;
  }

  /**
   * Returns the title of this book.
   *
   * @return the title
  */
  public String getTitle() {
    return title;
  }

  /**
   * Returns the author of this book.
   *
   * @return the author
  */
  public String getAuthor() {
    return author;
  }

  /**
   * Returns the year of this book.
   *
   * @return the year
  */ 
  public int getYear() {
        return year;
    }

  /**
   * Returns the price of this book in dollars.
   *
   * @return the price
  */
  public Double getPrice() {
    return price;
  }

  // Methods

  /**
   * Indicates whether some other object is equal to this one.
   * Two book objects are considered equal if they have the same ISBN.
   *
   * @param obj the object to compare with
   * @return {@code true} if this book and {@code obj} have the same ISBN, otherwise {@code false}
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!(obj instanceof Book))
      return false;

    Book other = (Book) obj;

    return Objects.equals(isbn, other.isbn);
  }

  /**
   * Returns the hash code value for this book.
   *
   * @return the hash code
   */
  @Override
  public int hashCode() {
    return Objects.hash(isbn);
  }
  
  /**
   * Compares this book to another by title (case-insensitive).
   *
   * @param book other book to compare with
   * @return true if books have the same title, otherwise returns false
   * @throws NullPointerException if {@code book} is null
   */
  @Override
  public int compareTo(Book book) {
    if (book == null) {
      throw new NullPointerException("Cannot compare, book is null");
    }
    return title.compareToIgnoreCase(book.title);
  }

  /**
   * Returns a readable string representation of this book.
   * 
   * @return formatted string with book details
   */
  @Override
  public String toString() {
    return String.format("Book[ISBN=%s, Title='%s', Author='%s', Price=$%.2f, Year=%d]",
        isbn, title, author, price, year);
  }
}
