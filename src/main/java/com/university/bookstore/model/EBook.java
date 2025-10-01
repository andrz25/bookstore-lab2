package com.university.bookstore.model;

import java.util.Objects;
import java.util.Set;

/**
 * Represents an electronic book (e-book) in the bookstore system.
 * Extends Material and implements Media interface to demonstrate
 * multiple inheritance through interfaces.
 * 
 * <p>EBooks support various file formats, DRM protection, and
 * provide reading time estimation based on word count.</p>
 * 
 */
public class EBook extends Material implements Media {
    private static final Set<String> VALID_FORMATS = Set.of("PDF", "EPUB", "MOBI");
    private static final int WORDS_PER_MINUTE = 250;
    private static final double DRM_FREE_DISCOUNT = 0.15;
    
    private final String author;
    private final String fileFormat;
    private final double fileSize; // in MB
    private final boolean drmEnabled;
    private final int wordCount;
    private final MediaQuality quality;
}
