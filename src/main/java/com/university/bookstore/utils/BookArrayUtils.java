package com.university.bookstore.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Stream;

import com.university.bookstore.model.Book;

/**
 * Utility class for array-based operations on Book objects.
 * 
 * <p>
 * This class provides static methods for manipulating and analyzing
 * arrays of books, demonstrating array operations without using ArrayList.
 * </p>
 * 
 * <p>
 * All methods handle null arrays and null elements gracefully.
 * </p>
 * 
 */
public final class BookArrayUtils {

  // Prevents instantiation
  private BookArrayUtils() {
    throw new UnsupportedOperationException("Utility class cannot be instantiated");
  }

  // Counting Operations
  public static int countBeforeYear(Book[] books, int year) {
    if (books == null) {
      return 0;
    }

    int count = 0;

    for (Book book : books) {
      if (book != null && book.getYear() < year) {
        count++;
      }
    }

    return count;
  }

  public static int countByAuthor(Book[] books, String author) {
    if (books == null || author == null) {
      return 0;
    }

    int count = 0;

    for (Book book : books) {
      if (book.getAuthor() != null && book.getAuthor().equalsIgnoreCase(author)) {
        count++;
      }
    }

    return count;
  }

  // Filtering Operations
  public static Book[] filterPriceAtMost(Book[] books, double maxPrice) {
    if (maxPrice < 0) {
      throw new IllegalArgumentException("Max price cannot be negative");
    }

    if (books == null) {
      return new Book[0];
    }

    int count = 0;

    for (Book book : books) {
      if (book != null && book.getPrice() <= maxPrice) {
        count++;
      }
    }

    Book[] filteredBooks = new Book[count];
    int index = 0;

    for (Book book : books) {
      if (book != null && book.getPrice() <= maxPrice) {
        filteredBooks[index++] = book;
      }
    }

    return filteredBooks;
  }

  static Book[] filterByDecade(Book[] books, int decade) {
    if (books == null) {
      return new Book[0];
    }

    int decadeEnd = decade + 10;
    int count = 0;

    for (Book book : books) {
      if (book != null && book.getYear() >= decade && book.getYear() < decadeEnd) {
        count++;
      }
    }

    Book[] filteredBooks = new Book[count];
    int index = 0;

    for (Book book : books) {
      if (book != null && book.getYear() >= decade && book.getYear() < decadeEnd) {
        filteredBooks[index++] = book;
      }
    }

    return filteredBooks;
  }

  // sorting Operations
  public static void sortByPrice(Book[] books) {
    if (books == null || books.length <= 1) {
      return;
    }

    Arrays.sort(books, (book1, book2) -> {
      if (book1 == null && book2 == null)
        return 0;
      if (book1 == null)
        return 1;
      if (book2 == null)
        return -1;
      return Double.compare(book1.getPrice(), book2.getPrice());
    });
  }

  public static void sortByYear(Book[] books) {
    if (books == null || books.length <= 1) {
      return;
    }
    Arrays.sort(books, (book1, book2) -> {
      if (book1 == null && book2 == null)
        return 0;
      if (book1 == null)
        return 1;
      if (book2 == null)
        return -1;
      return Integer.compare(book1.getYear(), book2.getYear());
    });
  }

  // Statistics
  public static double averagePrice(Book[] books) {

    // checks for null input
    if (books == null) {
      return 0.0;
    }

    double sum = 0;
    int count = 0;
    for (Book book : books) {
      // Passes null books
      if (book != null) {
        sum += book.getPrice();
        count++;
      }
    }
    if (count == 0) {
      return 0.0;
    }
    return sum / count;
  }

  public static Book findOldest(Book[] books) {
    // checks for null input
    if (books == null) {
      return null;
    }
    Book oldestBook = null;
    for (Book book : books) {
      // Passes null books
      if (book != null) {
        if (oldestBook == null || book.getYear() < oldestBook.getYear()) {
          oldestBook = book;
        }
      }
    }
    return oldestBook;
  }

  // Array Manipulation
  public static Book[] merge(Book[] arr1, Book[] arr2) {
    int arr1Length;
    int arr2Length;

    if (arr1 == null) {
      arr1Length = 0;
    } else {
      arr1Length = arr1.length;
    }

    if (arr2 == null) {
      arr2Length = 0;
    } else {
      arr2Length = arr2.length;
    }

    Book[] merged = new Book[arr1Length + arr2Length];
    if (arr1 != null) {
      System.arraycopy(arr1, 0, merged, 0, arr1Length);
    }
    if (arr2 != null) {
      System.arraycopy(arr2, 0, merged, arr1Length, arr2Length);
    }
    return merged;
  }

  public static Book[] removeDuplicates(Book[] books) {
    ArrayList<Book> noDuplicates = new ArrayList<>();
    Set<String> isbnsSaved = new HashSet<>();

    if (books == null) {
      return new Book[0];
    }

    for (Book book : books) {
      if (book != null && isbnsSaved.add(book.getIsbn())) {
        noDuplicates.add(book);
      }
    }

    return noDuplicates.toArray(new Book[0]);

  }

  /**
   * Finds books within a year range (inclusive).
   * 
   * @param books     array of books
   * @param startYear start year (inclusive)
   * @param endYear   end year (inclusive)
   * @return compact array of books within the year range
   */
  public static Book[] filterByYearRange(Book[] books, int startYear, int endYear) {
    if (books == null || startYear > endYear) {
      return new Book[0];
    }

    return Stream.of(books)
        .filter(book -> book != null &&
            book.getYear() >= startYear &&
            book.getYear() <= endYear)
        .toArray(Book[]::new);
  }

  /**
   * Groups books by decade and returns a summary.
   * 
   * @param books array of books
   * @return map with decade as key and count as value
   */
  public static Map<Integer, Integer> countByDecade(Book[] books) {
    Map<Integer, Integer> decadeCounts = new TreeMap<>();

    if (books != null) {
      for (Book book : books) {
        if (book != null) {
          int decade = (book.getYear() / 10) * 10;
          decadeCounts.merge(decade, 1, Integer::sum);
        }
      }
    }

    return decadeCounts;
  }

  /**
   * Finds the book with the longest title.
   * 
   * @param books array of books
   * @return book with longest title, null if array is null/empty
   */
  public static Book findLongestTitle(Book[] books) {
    if (books == null) {
      return null;
    }

    Book longest = null;
    int maxLength = 0;

    for (Book book : books) {
      if (book != null && book.getTitle().length() > maxLength) {
        maxLength = book.getTitle().length();
        longest = book;
      }
    }

    return longest;
  }
}
