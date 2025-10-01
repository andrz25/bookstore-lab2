package com.university.bookstore.impl;

import java.util.ArrayList;
import java.util.List;

import com.university.bookstore.api.BookstoreAPI;
import com.university.bookstore.model.Book;

/**
 * Implementation of a BookstoreAPI in an Arraylist.
 * 
 * <p>This interface provides the basic methods to operate a bookstore.</p>
 * 
 * <p>
 * Basic methods include:
 * - add: adds a book into the bookstore
 * - remove: removes book from bookstore
 * - findByIsbn: finds book according to unique ISBN string
 * - findByTitle: finds book according to title
 * </p>
 * 
 * @author Jayna Ong and Nina Zhang
 * @version 1.0
 * @since 2025-09-21
 */

public class BookstoreArrayList implements BookstoreAPI {
    
    //internal storage
    private final List<Book> inventory;

    /**
     * Creates an empty arrayList or bookstore
     * */
    //constructor that assigns the inventory to an arrayList
    //new empty array list
    public BookstoreArrayList() {
        this.inventory = new ArrayList<>();
    }


    //methods -> all methods below will override the previous methods
    //CRUD Operations

    //method to add book into the inventory
    
    @Override
    public boolean add(Book book) {

        //checks if the book exists by searching using isbn
        if (book == null || findByIsbn(book.getIsbn().trim()) != null) {
            return false; // returns false if Book is null or already exists
        }

        //adds the book to the inventory
        return inventory.add(book);
    }

    //method to remove a book from the inventory using isbn
    @Override
    public boolean removeByIsbn(String isbn) {
        
        //checks if isbn is null or empty
        if (isbn == null || isbn.trim().isEmpty()) {
            return false; //returns false if null or empty
        }

        //uses a for loop to go through the inventory
        for (Book book : inventory) {
            //goes through each book, checking if the book's isbn matches the one we're looking for
            if (book.getIsbn().equals(isbn.trim())) {
                inventory.remove(book); //removes the book from the inventory
                return true; //returns true if done successfully
            }
        }
        return false;
    }

    //method that helps find the book based on the isbn
    @Override
    public Book findByIsbn(String isbn) {
        
        if (isbn == null || isbn.trim().isEmpty()) {
            return null; //returns false if null or empty
        }
        //for loop that goes through the inventory
        for (Book book : inventory){
            //checks if the book's isbn matches the one we're looking for
            if (book.getIsbn().equals(isbn.trim())){
                return book; //returns book when found
            }
        }
        return null;
    }

    //Search Operations
    //method finds all books by title
    @Override
    public List<Book> findByTitle(String title) {
        
        //creates a list to put all books
        List<Book> sameTitle = new ArrayList<>();

        if (title.trim().isEmpty() || title == null) {
            return sameTitle;
        }

        //for loop that goes through the inventory
        for (Book book : inventory){
            if (book.getTitle().toLowerCase().contains(title.toLowerCase().trim())){
                sameTitle.add(book);
            }
        }
        return sameTitle;
    }

    //method finds all books by an author
    @Override
    public List<Book> findByAuthor(String author) {

        List<Book> sameAuthor = new ArrayList<>();

        if (author.trim().isEmpty() || author == null) {
            return sameAuthor;
        }

        for (Book book : inventory){
            if(book.getAuthor().toLowerCase().contains(author.toLowerCase().trim())){
                sameAuthor.add(book);
            }
        }
        return sameAuthor;
    }

    @Override
    public List<Book> findByPriceRange(double lowValue, double highValue) {
        
        List<Book> priceRange = new ArrayList<>();

        if (lowValue < 0 || highValue < 0) {
            throw new IllegalArgumentException("Prices cannot be negative");
        }

        if (lowValue > highValue) {
            throw new IllegalArgumentException("Low value can't be higher than high value");
        }

        for (Book book : inventory){
            if(book.getPrice() >= lowValue && book.getPrice() <= highValue ){
                priceRange.add(book);
            }
        }
        return priceRange;
    }

    @Override
    public List<Book> findByYear(int year) {
        List<Book> sameYear = new ArrayList<>();
        for (Book book : inventory){
            if(book.getYear() == year){
                sameYear.add(book);
            }
        }
        return sameYear;
    }

    //Analytics Operations
    @Override
    public int size() {
        int count = 0;

        for (Book book : inventory) {
            count++;
        }
        return count;
    }

    @Override
    public double inventoryValue() {
        double value = 0;
        for (Book book : inventory){
            value += book.getPrice();
        }
        return value;
    }

    @Override
    public Book getMostExpensive() {
        double mostExpensive = 0;
        Book mostExpensiveBook = null;

        for (Book book : inventory){
            if (book.getPrice() > mostExpensive){
                mostExpensive = book.getPrice();
                mostExpensiveBook = book;
            }
        }
        return mostExpensiveBook;
    }

    @Override
    public Book getMostRecent() {
        int mostRecent = 1450;
        Book mostRecentBook = null;
        for (Book book : inventory){
            if (book.getYear() > mostRecent){
                mostRecent = book.getYear();
                mostRecentBook = book;
            }
        }
        return mostRecentBook;
    }
    
    //Export Operations
    @Override
    public Book[] snapshotArray(){ //returns a copy of an array of books
        Book[] bookArray = new Book[inventory.size()];
        ArrayList<Book> copyArrayList = new ArrayList<>(inventory);
        return copyArrayList.toArray(bookArray);
    }

    @Override
    public List<Book> getAllBooks(){ //returns a copy of a list of books
        return new ArrayList<>(inventory);
    }
}