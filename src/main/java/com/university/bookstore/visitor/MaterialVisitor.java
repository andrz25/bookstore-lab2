package com.university.bookstore.visitor;

import com.university.bookstore.model.AudioBook;
import com.university.bookstore.model.EBook;
import com.university.bookstore.model.Magazine;
import com.university.bookstore.model.PrintedBook;
import com.university.bookstore.model.VideoMaterial;

/**
 * Visitor interface for implementing the Visitor pattern.
 * Allows adding new operations to the Material hierarchy without
 * modifying existing classes.
 * 
 * <p>This pattern is particularly useful for operations that need
 * to behave differently based on the concrete type of Material.</p>
 * 
 * @author Jayna Ong and Nina Zhang
 * @version 1.0
 * @since 2025-10-01
 */

public interface MaterialVisitor {

    /**
     * Visits a printed book
     * @param book the book that will be added
     */

    //visits a printed book
    void visit(PrintedBook book);

    /**
     * Visits a magazine
     * @param book the book that will be added
     */

    //visits a magazine
    void visit(Magazine magazine);

    /**
     * Visits an audio book
     * @param book the book that will be added
     */

    //visits an audio book
    void visit(AudioBook audioBook);

    /**
     * Visits a video
     * @param book the book that will be added
     */

    //visits a video
    void visit(VideoMaterial video);

    /**
     * Visits an ebook
     * @param book the book that will be added
     */

    //visits an ebook
    void visit(EBook ebook);
 
}
