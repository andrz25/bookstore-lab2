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

    //visits a printed book
    void visit(PrintedBook book);

    //visits a magazine
    void visit(Magazine magazine);

    //visits an audio book
    void visit(AudioBook audioBook);

    //visits a video
    void visit(VideoMaterial video);

    //visits an ebook
    void visit(EBook ebook);
 
}
