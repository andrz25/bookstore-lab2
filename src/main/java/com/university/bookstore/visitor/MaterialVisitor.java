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
 * @author Navid Mohaghegh
 * @version 2.0
 * @since 2024-09-15
 */
public interface MaterialVisitor {
 
}
