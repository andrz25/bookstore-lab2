package com.university.bookstore.model;

import java.util.Objects;
import java.util.Set;

/**
 * Represents an electronic book (e-book) in the bookstore system.
 * Extends Material and implements Media interface to demonstrate
 * multiple inheritance through interfaces.
 * 
 * <p>
 * EBooks support various file formats, DRM protection, and
 * provide reading time estimation based on word count.
 * </p>
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

  /**
   * Constructs an EBook instance with the specified attributes.
   * 
   * @param id         Unique identifier for the e-book.
   * @param title      Title of the e-book.
   * @param author     Author of the e-book.
   * @param price      Price of the e-book.
   * @param year       Publication year of the e-book.
   * @param fileFormat File format of the e-book (e.g., PDF, EPUB)
   * @param fileSize   Size of the e-book file in megabytes (MB).
   * @param drmEnabled Indicates if DRM protection is enabled.
   * @param wordCount  Total word count of the e-book.
   * @param quality    Quality of the media (e.g., STANDARD, HIGH).
   */
  public EBook(String id, String title, String author, double price, int year, String fileFormat, double fileSize,
      boolean drmEnabled, int wordCount, MediaQuality quality) {
    super(id, title, price, year, MaterialType.E_BOOK);
    this.author = validateAuthor(author);
    this.fileFormat = validateFileFormat(fileFormat);
    this.fileSize = validateFileSize(fileSize);
    this.drmEnabled = drmEnabled;
    this.wordCount = validateWordCount(wordCount);
    this.quality = Objects.requireNonNull(quality, "Quality cannot be null");
  }

  // Validators
  private String validateAuthor(String author) {
    Objects.requireNonNull(author, "Author cannot be null");
    return author;
  }

  private String validateFileFormat(String fileFormat) {
    Objects.requireNonNull(fileFormat, "File format cannot be null");
    if (!VALID_FORMATS.contains(fileFormat.toUpperCase())) {
      throw new IllegalArgumentException("Invalid file format: " + fileFormat);
    }
    return fileFormat.toUpperCase();
  }

  private double validateFileSize(double fileSize) {
    if (Double.isNaN(fileSize) || Double.isInfinite(fileSize)) {
      throw new IllegalArgumentException("File size must be a number");
    }
    if (fileSize <= 0) {
      throw new IllegalArgumentException("File size must be positive");
    }
    return fileSize;
  }

  private int validateWordCount(int wordCount) {
    if (wordCount < 0) {
      throw new IllegalArgumentException("Word count must be positive");
    }
    return wordCount;
  }

  // Getters
  public double getDiscountRate() {
    double discount = 0.0;
    if (!drmEnabled) {
      discount = DRM_FREE_DISCOUNT;
    }
    return discount;
  }

  public double getReadingTimeMinutes() {
    return wordCount / (double) WORDS_PER_MINUTE;
  }

  public String getCreator() {
    return author;
  }

  public int getDuration() {
    return 0;
  }

  public String getFileFormat() {
    return fileFormat;
  }

  public double getFileSize() {
    return fileSize;
  }

  public boolean isStreamingOnly() {
    return false;
  }

  public MediaQuality getQuality() {
    return quality;
  }

  public boolean isDrmEnabled() {
    return drmEnabled;
  }

  public int getWordCount() {
    return wordCount;
  }

  public String getDisplayInfo() {
    return String.format(
        "EBook: %s by %s, Size: %.1f MB, File format: %s, DRM: %s, Word count: %d words",
        title, author, fileSize, fileFormat, drmEnabled ? "DRM" : "DRM-free", wordCount);
  }

  public String getDescription() {
    return String.format(
        "%s is an Ebook by %s in a %s format, it is %s, with a size of %.1f MB, estimated time of reading is %d minutes.",
        title, author, fileFormat, drmEnabled ? "DRM protected" : "DRM-free", fileSize,
        (int) getReadingTimeMinutes());
  }

  @Override
  public String toString() {
    return String.format(
        "EBook[ID=%s, Title='%s', Author='%s', Price=%.2f, Year=%d, Format=%s, Size=%.1fMB, DRM=%b, Words=%d, Quality=%s]",
        id, title, author, price, year, fileFormat, fileSize, drmEnabled, wordCount, quality);
  }
}
