package com.university.bookstore.api;

import java.util.List;

import com.university.bookstore.model.Book;

/**
 * <p>This interface provides the basic methods to operate a bookstore.</p>
 * 
 * <p>Includes CRUD, search, analytic, and export operations</p>
 * 
 * @author Jayna Ong and Nina Zhang
 * @version 1.0
 * @since 2025-09-21
 */

public interface BookstoreAPI {
    //CRUD Operations

    /**
     * Adds a book object into the inventory
     * @param book the book that will be added
     * @return true if the book is added, false if the ISBN exists or the book is null
     */
    boolean add(Book book); 

    /**
     * Removes a book with the given ISBN 
     * @param isbn the ISBN to remove
     * @return true if the book was removed, false if the ISBN doesn't exist or ISBN is null
     */
    boolean removeByIsbn(String isbn); 

    /**
     * Finds and returns a book with the given ISBN
     * @param isbn the ISBN to search for
     * @return the book with the ISBN, null otherwise
     */
    Book findByIsbn(String isbn); 

    //Search Operations
    /**
     * Finds books based on the title(case-insensitive, partial match)
     * @param title the title or partial title to search for
     * @return a list of books containing the title name(may be empty)
     */
    List<Book> findByTitle(String title); 

    /**
     * Finds books with the same author(case-insensitive, partial match)
     * @param author the author's name or partial name to search for
     * @return a list of books with author's name(may be empty)
     */
    List<Book> findByAuthor(String author); 

    /**
     * Finds all books within a specific price range
     * @param lowValue the minimum value to search for
     * @param highValue the maximum value to search for
     * @return a list of books within the price range(may be empty)
     */
    List<Book> findByPriceRange(double lowValue, double highValue); 

    /**
     * Finds all books published within a specific year
     * @param year the year that the books were published
     * @return a list of books all published within the same year
     */
    List<Book> findByYear(int year); 

    //Analytics Operations

    /**
     * Gets total number of books in inventory
     * @return The total number of books
     * */
    int size(); 

    /**
     * Gets total price of all books in inventory
     * @return The sum of all book prices
     * */
    double inventoryValue(); 

    /**
     * Gets most expensive book in inventory
     * @return The most expensive book
     * */
    Book getMostExpensive(); 

    /**
     * Gets most recent book in inventory
     * @return The most recent book
     * */
    Book getMostRecent(); 

    //Export Operations

    /**
     * Creates defensive copy of inventory into an array
     * @return The copy of all books in the inventory in array form
     * */
    Book[] snapshotArray(); 

    /**
     * Creates defensive copy of inventory into another list
     * @return The copy of all books in the inventory in a list form
     */
    List<Book> getAllBooks(); 
}