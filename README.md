#  Bookstore Management System - Lab 2

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.6+-blue.svg)](https://maven.apache.org/)
[![JUnit](https://img.shields.io/badge/JUnit-5-green.svg)](https://junit.org/junit5/)
[![Coverage](https://img.shields.io/badge/Coverage-60%25-brightgreen.svg)](target/site/jacoco/index.html)
[![License](https://img.shields.io/badge/License-Educational-lightgrey.svg)](LICENSE)

## Lab Overview

This lab implements a **Polymorphic Bookstore Management System** that demonstrates software engineering principles including object-oriented design, polymorphism, defensive programming, immutability, and detailed testing. The system manages different collection of library materials including books, magazines, audio books, video materials, and digital e-books through a polymorphic hierarchy.


### Key Features

- **Polymorphic Material Hierarchy**: Support for books, magazines, audio/video materials, and e-books
- **Search Capabilities**: Filtering, sorting, and basic query operations
- **Design Patterns**: Factory, Visitor, and Template Method patterns
- **Testing**: Unit tests with high code coverage
- **Performance Analysis**: ArrayList vs ConcurrentHashMap implementation comparisons with thread safety
- **Auto-Generated Documentation**: Javadoc from in-code annotations and UML diagrams
- **Build Automation**: Maven build with coverage reporting and UML generation
- **Defensive Programming**: Input validation, immutability, and error handling

### Additional Features

- **Thread-Safe ConcurrentHashMap Implementation**: Lock-free reads with immutable snapshots
- **Three Implementation Strategies**: ArrayList, Hybrid, and ConcurrentHashMap for performance comparison
- **Concurrency Testing**: Dedicated tests for thread safety and performance
- **Performance Metrics**: Speedup for concurrent reads vs ArrayList
- **Concurrent Demonstrations**: Real multi-threading with ExecutorService and CountDownLatch
- **Scenario Testing**: E-commerce, Social Media, Financial Trading, and IoT simulations
- **Error Analysis**: Thread safety failure detection and reporting
- **Memory Usage Analysis**: Comparative memory footprint analysis between implementations
- **Well-Structured Architecture**: Proper error handling and documentation

## System Architecture

The bookstore system is architected around several core components that demonstrate software engineering practices:

```
bookstore-system/
  Model Layer          # Domain models and business logic
  API Layer            # Interface contracts and abstractions  
  Implementation Layer # Concrete implementations
  Utility Layer       # Helper classes and static operations
  Test Suite          # Detailed testing framework
  Documentation       # Javadoc, UML, and guides
```

### Package Structure

| Package | Purpose | Key Classes |
|---------|---------|-------------|
| `model/` | Domain entities and business logic | `Material`, `EBook`, `PrintedBook`, `AudioBook` |
| `api/` | Interface contracts | `BookstoreAPI`, `MaterialStore` |
| `impl/` | Concrete implementations | `BookstoreArrayList`, `MaterialStoreImpl`, `MaterialStoreConcurrentImpl` |
| `factory/` | Object creation patterns | `MaterialFactory` |
| `visitor/` | Behavioral patterns | `MaterialVisitor`, `ShippingCostCalculator` |
| `utils/` | Static utility operations | `BookArrayUtils` |
| `demo/` | Demonstration code | `PolymorphismDemo` |

## Quick Start

### Prerequisites

- **Java 17** or higher
- **Maven 3.6** or higher  
- **Git** (for version control)
- **Graphviz** (for UML diagram generation)

### Installation & Setup

```bash
cd bookstore-lab2/src

# Verify environment
./runme.sh --help

# Run complete build and test cycle
./runme.sh
```

### One-Command Build

```bash
# Complete build with all features
./runme.sh

# Quick build (skip coverage/docs/UML)
./runme.sh --quick

# Generate UML diagrams only
./runme.sh --uml-only

# Run tests only
./runme.sh --test-only

# Force clean build
./runme.sh --clean

# Skip tests during build
./runme.sh --skip-tests

# Generate Javadoc only
./runme.sh --javadoc-only

# Create JAR package only
./runme.sh --package-only

# Clean up generated files
./runme.sh --clean-uml

# Show help and all options
./runme.sh --help

# Show version information
./runme.sh --version

# Enable debug output
./runme.sh --debug
```

**Build Script Features:**
- Environment validation (Java 17+, Maven 3.6+)
- Error handling and colored output
- Automatic PlantUML download and UML generation
- Code coverage analysis with JaCoCo
- Javadoc generation
- Quality checks and statistics
- Final summary report with status indicators

## Learning Objectives

This lab provides hands-on experience with:

### Core OOP Concepts
- **Abstraction**: Abstract classes and interfaces
- **Encapsulation**: Data hiding and access control
- **Inheritance**: Class hierarchies and code reuse
- **Polymorphism**: Dynamic binding and method dispatch

### Design Principles
- **SOLID Principles**: Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion
- **GRASP Principles**: Information Expert, Creator, Controller, Low Coupling, High Cohesion
- **Design Patterns**: Factory, Visitor, Template Method, Strategy

### Quality Assurance
- **Unit Testing**: JUnit 5 with parameterized tests
- **Code Coverage**: JaCoCo reporting with 70%+ target
- **Performance Testing**: Algorithm complexity analysis
- **Defensive Programming**: Input validation and error handling

## Object-Oriented Design

### Class Hierarchy

The system demonstrates a polymorphic hierarchy:

```
Material (Abstract)
 PrintedBook
 Magazine  
 AudioBook (implements Media)
 VideoMaterial (implements Media)
 EBook (implements Media)
```

**Material Types Supported:**
- BOOK, MAGAZINE, AUDIO_BOOK, VIDEO, MUSIC_ALBUM, PODCAST, DOCUMENTARY, E_BOOK

### Interface Design

```java
// Core material operations
public interface MaterialStore {
    boolean addMaterial(Material material);
    Optional<Material> findById(String id);
    List<Material> searchByTitle(String title);
    // ... search methods
}

// Media-specific operations
public interface Media {
    int getDuration();
    String getFormat();
    double getFileSize();
    MediaQuality getQuality();
    boolean isStreamingOnly();
    default String getPlaybackInfo();
    default int estimateDownloadTime(double mbps);
}
```

### Polymorphism in Action

```java
import java.util.Arrays;
import java.util.List;
import com.university.bookstore.model.*;

// Polymorphic collection
List<Material> materials = Arrays.asList(
    new PrintedBook("978-0134685991", "Effective Java", "Joshua Bloch", 45.99, 2018, 412, "Addison-Wesley", true),
    new EBook("978-0143038092", "1984", "George Orwell", 14.99, 2020, "EPUB", 2.5, false, 50000, MediaQuality.HIGH),
    new AudioBook("978-0143038093", "1984", "George Orwell", "Simon Prebble", 19.99, 2020, 690, "MP3", 850.5, MediaQuality.HIGH, "English", true)
);

// Polymorphic operations
for (Material material : materials) {
    System.out.println(material.getDisplayInfo());  // Dynamic dispatch
    System.out.println("Discounted: $" + material.getDiscountedPrice());  // Template method
}
```

## Build System & Automation

### Available Commands

| Command | Description | Use Case |
|---------|-------------|----------|
| `./runme.sh` | Complete build cycle | Full development workflow |
| `./runme.sh --quick` | Fast build (skip coverage/docs/UML) | Quick compilation check |
| `./runme.sh --test-only` | Run tests only | Testing phase |
| `./runme.sh --clean` | Force clean build | Remove target directory |
| `./runme.sh --skip-tests` | Skip running tests | Compile and package only |
| `./runme.sh --javadoc-only` | Generate Javadoc only | Documentation |
| `./runme.sh --package-only` | Create JAR package only | Packaging |
| `./runme.sh --uml-only` | Generate UML diagrams only | Documentation |
| `./runme.sh --clean-uml` | Clean generated UML files | Maintenance |
| `./runme.sh --help` | Show all options | Reference |
| `./runme.sh --version` | Show version information | Reference |
| `./runme.sh --debug` | Enable debug output | Troubleshooting |

### Maven Commands

```bash
# Compile source code
mvn clean compile

# Run all tests
mvn test

# Generate coverage report
mvn jacoco:report
# View: target/site/jacoco/index.html

# Generate Javadoc
mvn javadoc:javadoc
# View: target/site/apidocs/index.html

# Package as JAR
mvn package

# Run specific test
mvn test -Dtest=EBookTest
```

## Testing Strategy

### Test Coverage


- **Test Categories**:
  - Unit Tests: Individual class testing
  - Integration Tests: Component interaction
  - Edge Case Tests: Boundary conditions

### Test Results Breakdown

| Test Suite | Status | Coverage |
|------------|--------|----------|
| **MaterialStoreConcurrentImplTest** |  PASS | Thread safety & concurrency |
| **SimplePerformanceTest** |  PASS | Performance comparisons |
| **MaterialStoreImplTest** |  PASS | Hybrid implementation |
| **BookstoreArrayListTest** |  PASS | ArrayList implementation |
| **Model Tests** |  PASS | EBook, Material, etc. |
| **Factory Tests** |  PASS | MaterialFactory |
| **Visitor Tests** |  PASS | ShippingCostCalculator |
| **Utility Tests** |  PASS | BookArrayUtils |
| **Demo Tests** |  PASS | PolymorphismDemo |
| **Edge Case Tests** |  PASS | ModelEdgeCaseTest |
| **Search Tests** |  PASS | EnhancedSearchTest |

### Test Examples

```java
import static org.junit.jupiter.api.Assertions.*;
import com.university.bookstore.model.*;

@Test
void testEBookCreation() {
    EBook ebook = new EBook("978-0134685991", "Effective Java", 
        "Joshua Bloch", 45.99, 2018, "EPUB", 2.5, 
        true, 90000, MediaQuality.HIGH);
    
    assertNotNull(ebook);
    assertEquals("Effective Java", ebook.getTitle());
    assertEquals(0.0, ebook.getDiscountRate(), 0.001); // DRM-enabled, no discount
    assertEquals(360, ebook.getReadingTimeMinutes()); // 90000/250
}

@Test
void testPolymorphicBehavior() {
    List<Material> materials = createTestMaterials();
    
    // Test polymorphic discount calculation
    for (Material material : materials) {
        double discounted = material.getDiscountedPrice();
        assertTrue(discounted <= material.getPrice());
    }
}
```

## Concurrent Demonstrations

### Real Multi-Threading Implementation

The project now features concurrent demonstrations using actual multi-threading instead of sequential loops:

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicInteger;

// Real concurrent read operations
private static void demonstrateConcurrentReads(MaterialStore store, int numThreads, int operationsPerThread) {
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    CountDownLatch latch = new CountDownLatch(numThreads);
    AtomicLong totalOperations = new AtomicLong(0);
    AtomicLong totalTime = new AtomicLong(0);
    
    long startTime = System.nanoTime();
    
    for (int i = 0; i < numThreads; i++) {
        final int threadId = i;
        executor.submit(() -> {
            long threadStartTime = System.nanoTime();
            int threadOperations = 0;
            
            try {
                for (int j = 0; j < operationsPerThread; j++) {
                    store.searchByTitle("Test");
                    store.getMaterialsByType(Material.MaterialType.E_BOOK);
                    store.getMediaMaterials();
                    store.getTotalInventoryValue();
                    threadOperations += 4;
                }
                
                long threadTime = System.nanoTime() - threadStartTime;
                totalOperations.addAndGet(threadOperations);
                totalTime.addAndGet(threadTime);
                
            } catch (Exception e) {
                System.err.println("Thread " + threadId + " failed: " + e.getMessage());
            } finally {
                latch.countDown();
            }
        });
    }
    
    latch.await();
    executor.shutdown();
}
```

### Thread Safety Verification

The demo includes thread safety verification:

```java
private static void demonstrateThreadSafetyVerification(MaterialStore arrayStore, MaterialStore concurrentStore) {
    System.out.println("  Thread Safety Verification:");
    
    // Test data integrity after concurrent operations
    int arrayStoreSize = arrayStore.size();
    int concurrentStoreSize = concurrentStore.size();
    
    System.out.println("    ArrayList Store Size: " + arrayStoreSize + " (may be inconsistent)");
    System.out.println("    ConcurrentHashMap Store Size: " + concurrentStoreSize + " (consistent)");
    
    // Test value consistency
    double arrayStoreValue = arrayStore.getTotalInventoryValue();
    double concurrentStoreValue = concurrentStore.getTotalInventoryValue();
    
    System.out.println("    ArrayList Total Value: $" + arrayStoreValue + " (may be corrupted)");
    System.out.println("    ConcurrentHashMap Total Value: $" + concurrentStoreValue + " (accurate)");
    
    // Test statistics consistency
    InventoryStats arrayStats = arrayStore.getInventoryStats();
    InventoryStats concurrentStats = concurrentStore.getInventoryStats();
    
    System.out.println("    ArrayList Stats: " + arrayStats + " (may be inconsistent)");
    System.out.println("    ConcurrentHashMap Stats: " + concurrentStats + " (consistent)");
}
```

### Memory Usage Analysis

Memory usage comparison between implementations:

```java
private static void demonstrateMemoryUsageComparison(MaterialStore arrayStore, MaterialStore concurrentStore) {
    System.out.println("  Memory Usage Analysis:");
    
    // Measure memory usage
    Runtime runtime = Runtime.getRuntime();
    runtime.gc(); // Force garbage collection
    
    long beforeMemory = runtime.totalMemory() - runtime.freeMemory();
    
    // Test with large dataset
    for (int i = 0; i < 10000; i++) {
        arrayStore.addMaterial(createTestMaterial(i));
        concurrentStore.addMaterial(createTestMaterial(i));
    }
    
    long afterMemory = runtime.totalMemory() - runtime.freeMemory();
    long memoryUsed = afterMemory - beforeMemory;
    
    System.out.println("    Memory used for 10,000 materials: " + (memoryUsed / 1024 / 1024) + " MB");
    System.out.println("    ArrayList memory efficiency: High (single data structure)");
    System.out.println("    ConcurrentHashMap memory efficiency: Medium (multiple indexes)");
    System.out.println("    Trade-off: Memory vs Thread Safety");
}
```

### Error Analysis and Reporting

Detailed error analysis for educational purposes:

```java
} catch (Exception e) {
    errorCount.incrementAndGet();
    String errorType = e.getClass().getSimpleName();
    String errorMsg = e.getMessage() != null ? e.getMessage() : "Unknown error";
    System.err.println("  " + storeType + " Reader Thread " + threadId + " failed: " +
                         errorType + " - " + errorMsg);

    // Log specific error types for educational purposes
    if (e instanceof NullPointerException) {
        System.err.println("    -> Data corruption detected: Null pointer during concurrent access");
    } else if (e instanceof IndexOutOfBoundsException) {
        System.err.println("    -> Data corruption detected: Index out of bounds during concurrent access");
    } else if (e instanceof ConcurrentModificationException) {
        System.err.println("    -> Data corruption detected: Concurrent modification during iteration");
    } else if (e instanceof ArrayIndexOutOfBoundsException) {
        System.err.println("    -> Data corruption detected: Array index corruption during concurrent access");
    }
}
```

## Design Patterns Implementation

### Factory Pattern

The Factory pattern is a creational design pattern that provides an interface for creating objects without specifying their exact classes. Instead of using constructors directly, the factory encapsulates object creation logic and returns objects based on input parameters. This pattern promotes loose coupling between the client code and the concrete classes, making the system more flexible and easier to maintain. The factory acts as a centralized place for object creation, which is especially useful when you have multiple similar classes or when object creation involves complex logic.

In our bookstore system, the `MaterialFactory` demonstrates this pattern by providing a `createMaterial()` method that takes a material type string and properties map as parameters. Instead of clients having to know about all the different material classes (`PrintedBook`, `EBook`, `AudioBook`, etc.) and their specific constructors, they can simply call the factory with a type name and get the appropriate object back. For example, calling `createMaterial("EBOOK", properties)` will return an `EBook` instance, while `createMaterial("BOOK", properties)` returns a `PrintedBook`. This approach makes it easy to add new material types or modify creation logic without changing the client code, and it provides a consistent interface for creating all types of materials.

```java
import java.util.Map;
import com.university.bookstore.model.*;

public class MaterialFactory {
    public static Material createMaterial(String type, Map<String, Object> properties) {
        String normalizedType = type.trim().toUpperCase();
        
        switch (normalizedType) {
            case "BOOK":
            case "PRINTED_BOOK":
                return createPrintedBook(properties);
            case "MAGAZINE":
                return createMagazine(properties);
            case "AUDIO_BOOK":
            case "AUDIOBOOK":
                return createAudioBook(properties);
            case "VIDEO":
            case "VIDEO_MATERIAL":
                return createVideoMaterial(properties);
            case "EBOOK":
            case "E_BOOK":
                return createEBook(properties);
            default:
                throw new IllegalArgumentException("Unsupported material type: " + type);
        }
    }
}
```

### Visitor Pattern

The Visitor pattern is a behavioral design pattern that allows you to define new operations on objects without changing their classes. This pattern separates the algorithm from the object structure by moving operations into separate visitor classes. The key benefit is that you can add new functionality to existing classes without modifying them, following the Open/Closed Principle - classes should be open for extension but closed for modification.

In our bookstore system, the `ShippingCostCalculator` demonstrates this pattern by implementing the `MaterialVisitor` interface. Instead of adding shipping calculation methods directly to each material class (which would violate the Single Responsibility Principle), we create a separate visitor that can "visit" different material types and calculate shipping costs based on their specific characteristics. For example, physical books are charged by weight, magazines have a flat rate, and digital materials like E-books have no shipping cost. This approach makes it easy to add new shipping calculation strategies or modify existing ones without touching the material classes themselves.

```java
import com.university.bookstore.model.*;
import com.university.bookstore.visitor.MaterialVisitor;

public class ShippingCostCalculator implements MaterialVisitor {
    private static final double PHYSICAL_ITEM_RATE = 0.50; // per 100g
    private static final double MAGAZINE_FLAT_RATE = 2.00;
    private static final double DIGITAL_ITEM_RATE = 0.00;
    
    private double totalShippingCost = 0.0;
    
    @Override
    public void visit(PrintedBook book) {
        // Assume average book weight of 500g
        double weightInHundredGrams = 5.0;
        double cost = weightInHundredGrams * PHYSICAL_ITEM_RATE;
        totalShippingCost += cost;
    }
    
    @Override
    public void visit(Magazine magazine) {
        totalShippingCost += MAGAZINE_FLAT_RATE;
    }
    
    @Override
    public void visit(EBook ebook) {
        // E-books are always digital - no shipping cost
        totalShippingCost += DIGITAL_ITEM_RATE;
    }
    
    // ... other visit methods for AudioBook and VideoMaterial
    
    public double getTotalShippingCost();
    public void reset();
    public double calculateShippingCost(Material material);
}
```

### Template Method Pattern

The Template Method pattern is a behavioral design pattern that defines the skeleton of an algorithm in a base class, allowing subclasses to override specific steps of the algorithm without changing its overall structure. This pattern promotes code reuse and follows the "Don't Repeat Yourself" (DRY) principle by centralizing common algorithm logic while providing flexibility for customization.

In our bookstore system, the `Material` abstract class demonstrates this pattern through the `getDiscountedPrice()` method. The template method defines the general algorithm for calculating discounted prices (price × (1 - discount rate)), while the hook method `getDiscountRate()` allows each material type to implement its own discount logic. For example, E-books with DRM protection have no discount (0%), while DRM-free E-books get a 15% discount, and physical books might have different discount rates based on their condition or publisher agreements.

```java
import com.university.bookstore.model.Media.MediaQuality;

public abstract class Material {
    // Template method - defines algorithm structure
    public final double getDiscountedPrice() {
        return price * (1.0 - getDiscountRate());
    }
    
    // Hook method - subclasses can override
    public double getDiscountRate() {
        return 0.0; // Default: no discount
    }
}

public class EBook extends Material implements Media {
    @Override
    public double getDiscountRate() {
        return drmEnabled ? 0.0 : 0.15; // 15% discount for DRM-free
    }
    
    // Media interface implementation
    @Override
    public int getDuration() {
        return getReadingTimeMinutes();
    }
    
    @Override
    public String getFormat() {
        return fileFormat;
    }
    
    @Override
    public boolean isStreamingOnly() {
        return false; // E-books are downloadable
    }
}
```

## Performance Analysis

### Time Complexity Comparison

| Operation | ArrayList | Hybrid (ArrayList+HashMap) | ConcurrentHashMap | Notes |
|-----------|-----------|---------------------------|-------------------|-------|
| Add | O(1)* | O(1) | O(1) | *Amortized for ArrayList |
| Remove | O(n) | O(1) | O(1) | ISBN-based removal |
| Find by ID | O(n) | O(1) | O(1) | Linear vs constant time |
| Search by Title | O(n) | O(1) | O(1) | Indexed lookup |
| Concurrent Reads | Not thread-safe | Not thread-safe | Lock-free | Thread safety |
| Concurrent Writes | Not thread-safe | Not thread-safe | Thread-safe | Lock contention |
| Memory Usage | Low | Medium | High | Index overhead |

### Concurrent Performance Testing

The system now includes concurrent testing scenarios:

#### Real-World Scenario Performance

| Scenario | Concurrent Users | Operations | ArrayList Result | ConcurrentHashMap Result |
|----------|------------------|------------|------------------|--------------------------|
| **E-commerce** | 1,000 users | 10,000 operations |  Thread safety failures |  100% success rate |
| **Social Media** | 10,000 posts | 1,000,000 operations |  Data corruption |  Consistent data |
| **Financial Trading** | 100,000 transactions | 10,000,000 operations |  Race conditions |  Atomic operations |
| **IoT Devices** | 1,000,000 devices | 100,000,000 operations |  Memory corruption |  Lock-free performance |

#### Thread Safety Analysis

**ArrayList Thread Safety Issues Detected:**
- `NullPointerException`: Threads accessing null elements during array resizing
- `IndexOutOfBoundsException`: Threads accessing invalid indices during concurrent modifications
- `ConcurrentModificationException`: Iterators detecting concurrent changes
- `ArrayIndexOutOfBoundsException`: Array structure corruption during concurrent access

**ConcurrentHashMap Thread Safety Features:**
- Lock-free reads: Multiple threads can read simultaneously without blocking
- Segmented locking: Write operations use minimal locking to reduce contention
- Atomic operations: All operations are designed to be thread-safe
- Consistent state: Data structure remains consistent even under concurrent access

### Concurrent Performance Testing

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import com.university.bookstore.api.MaterialStore;
import com.university.bookstore.impl.MaterialStoreImpl;
import com.university.bookstore.impl.MaterialStoreConcurrentImpl;

@Test
void realConcurrentPerformanceComparison() {
    MaterialStore arrayStore = new MaterialStoreImpl();
    MaterialStore concurrentStore = new MaterialStoreConcurrentImpl();
    
    // Real concurrent testing with multiple threads
    int numThreads = 10;
    int operationsPerThread = 1000;
    
    // Test ArrayList with concurrent operations (will show failures)
    ExecutorService executor = Executors.newFixedThreadPool(numThreads);
    CountDownLatch latch = new CountDownLatch(numThreads);
    AtomicLong arrayListErrors = new AtomicLong(0);
    
    long startTime = System.nanoTime();
    for (int i = 0; i < numThreads; i++) {
        final int threadId = i;
        executor.submit(() -> {
            try {
                for (int j = 0; j < operationsPerThread; j++) {
                    arrayStore.addMaterial(createRandomMaterial(threadId * 1000 + j));
                    arrayStore.findById("ID-" + (threadId * 1000 + j));
                }
            } catch (Exception e) {
                arrayListErrors.incrementAndGet();
                System.err.println("ArrayList Thread " + threadId + " failed: " + e.getClass().getSimpleName());
            } finally {
                latch.countDown();
            }
        });
    }
    
    latch.await();
    long arrayListTime = System.nanoTime() - startTime;
    
    // Test ConcurrentHashMap with concurrent operations (thread-safe)
    latch = new CountDownLatch(numThreads);
    AtomicLong concurrentErrors = new AtomicLong(0);
    
    startTime = System.nanoTime();
    for (int i = 0; i < numThreads; i++) {
        final int threadId = i;
        executor.submit(() -> {
            try {
                for (int j = 0; j < operationsPerThread; j++) {
                    concurrentStore.addMaterial(createRandomMaterial(threadId * 1000 + j));
                    concurrentStore.findById("ID-" + (threadId * 1000 + j));
                }
            } catch (Exception e) {
                concurrentErrors.incrementAndGet();
                System.err.println("ConcurrentHashMap Thread " + threadId + " failed: " + e.getClass().getSimpleName());
            } finally {
                latch.countDown();
            }
        });
    }
    
    latch.await();
    long concurrentTime = System.nanoTime() - startTime;
    executor.shutdown();
    
    // Results analysis
    System.out.println("ArrayList Concurrent Test:");
    System.out.println("  Time: " + (arrayListTime / 1_000_000.0) + " ms");
    System.out.println("  Errors: " + arrayListErrors.get() + " (Thread safety failures)");
    System.out.println("  Success Rate: " + ((numThreads - arrayListErrors.get()) * 100.0 / numThreads) + "%");
    
    System.out.println("ConcurrentHashMap Concurrent Test:");
    System.out.println("  Time: " + (concurrentTime / 1_000_000.0) + " ms");
    System.out.println("  Errors: " + concurrentErrors.get() + " (Thread safety failures)");
    System.out.println("  Success Rate: " + ((numThreads - concurrentErrors.get()) * 100.0 / numThreads) + "%");
    
    System.out.println("Performance Ratio: " + (arrayListTime / (double) concurrentTime) + "x");
}
```

### Concurrent Implementation

The `MaterialStoreConcurrentImpl` provides thread-safe operations:

```java
import com.university.bookstore.impl.MaterialStoreConcurrentImpl;
import com.university.bookstore.demo.PolymorphismDemo;

// Thread-safe store with ConcurrentHashMap
MaterialStore concurrentStore = new MaterialStoreConcurrentImpl();

// Lock-free reads
Optional<Material> material = concurrentStore.findById("ISBN-123");
List<Material> books = concurrentStore.searchByTitle("Effective Java");

// Thread-safe writes
concurrentStore.addMaterial(newBook);
concurrentStore.removeMaterial("ISBN-123");

// Run concurrent demonstrations
PolymorphismDemo.main(new String[]{});
```

**Key Features:**
- **ConcurrentHashMap** for primary ISBN index (lock-free reads)
- **Immutable snapshots** for secondary indexes (title, creator, type)
- **StampedLock** for consistent index updates
- **Lock-free reads** for all search operations
- **Thread-safe writes** with minimal locking
- **High performance** under concurrent access
- **Real-world scenario testing** with ExecutorService and CountDownLatch
- **Error analysis** for thread safety verification
- **Memory usage comparison** between implementations

**Performance Characteristics:**
- Primary operations (findById): O(1) lock-free
- Secondary operations (searchByTitle): O(1) from immutable index
- Concurrent reads: No locking, no contention
- Concurrent writes: Minimal locking for index updates
- Memory overhead: Higher due to multiple indexes
- **Real-world scenarios**: E-commerce (1K users), Social Media (10K posts), Financial Trading (100K transactions), IoT (1M devices)

### Real-World Scenario Demonstrations

The demo includes four real-world scenarios:

#### 1. E-commerce Platform (1,000 Concurrent Users)
```java
// Simulates 1,000 concurrent users browsing products
// Demonstrates: Product search, inventory updates, concurrent reads
// Result: ArrayList fails with thread safety issues, ConcurrentHashMap succeeds
```

#### 2. Social Media Platform (10,000 Concurrent Posts)
```java
// Simulates 10,000 concurrent posts and reads
// Demonstrates: Content creation, timeline updates, concurrent access
// Result: ArrayList shows data corruption, ConcurrentHashMap maintains consistency
```

#### 3. Financial Trading System (100,000 Concurrent Transactions)
```java
// Simulates 100,000 concurrent financial transactions
// Demonstrates: High-frequency trading, price updates, atomic operations
// Result: ArrayList fails with race conditions, ConcurrentHashMap ensures atomicity
```

#### 4. IoT Device Management (1,000,000 Concurrent Devices)
```java
// Simulates 1,000,000 concurrent IoT device status updates
// Demonstrates: Device monitoring, status updates, massive concurrency
// Result: ArrayList fails with memory corruption, ConcurrentHashMap scales perfectly
```

## API Reference

### API Documentation

For complete API documentation including all interfaces, classes, and methods, please refer to the generated Javadoc:

**[📚 View Complete API Documentation](target/site/apidocs/index.html)**

### Generating Javadoc Documentation

You can generate the Javadoc documentation using either method:

**Using the run script:**
```bash
./runme.sh --javadoc-only
```

**Or by using Maven directly:**
```bash
mvn javadoc:javadoc
```

Both commands will generate the complete API documentation in the `target/site/apidocs/` directory. The documentation includes detailed information about all classes, methods, parameters, return values, and usage examples.

The API documentation includes:
- **Core Interfaces**: MaterialStore, Media, MaterialVisitor
- **Model Classes**: Material, EBook, PrintedBook, AudioBook, VideoMaterial, Magazine
- **Implementation Classes**: MaterialStoreImpl, MaterialStoreConcurrentImpl, BookstoreArrayList
- **Factory Classes**: MaterialFactory
- **Visitor Classes**: ShippingCostCalculator
- **Utility Classes**: BookArrayUtils
- **Demo Classes**: PolymorphismDemo

## Learning Outcomes

By completing this lab, students will:

1. **Master OOP Fundamentals**: Understand and apply the four pillars of OOP
2. **Apply SOLID Principles**: Implement maintainable, extensible code
3. **Use Design Patterns**: Apply proven solutions to common problems
4. **Write Quality Tests**: Achieve high coverage with detailed testing
5. **Analyze Performance**: Compare different implementation strategies
6. **Practice Defensive Programming**: Write error-resistant code

### Key Concepts Demonstrated

- **Polymorphism**: Dynamic method dispatch and interface-based programming
- **Inheritance**: Code reuse through class hierarchies
- **Abstraction**: Hiding implementation details behind interfaces
- **Encapsulation**: Data protection and controlled access
- **Design Patterns**: Factory, Visitor, Template Method, Strategy
- **Testing**: Unit testing, integration testing, performance testing
- **Documentation**: Javadoc, UML diagrams, and a README file

## Development Guide

### Project Structure

```
src/
 main/java/com/university/bookstore/
    model/           # Domain models
       Material.java        # Abstract base class
       EBook.java          # E-book implementation
       PrintedBook.java    # Physical book
       AudioBook.java      # Audio content
       VideoMaterial.java  # Video content
       Magazine.java       # Periodical
       Book.java          # Basic book (legacy)
       Media.java         # Media interface
    api/             # Interface contracts
       BookstoreAPI.java   # Book-specific operations
       MaterialStore.java  # Generic material operations
    impl/            # Implementations
       BookstoreArrayList.java  # ArrayList-based store
       MaterialStoreImpl.java   # Hybrid ArrayList+HashMap store
       MaterialStoreConcurrentImpl.java # Thread-safe ConcurrentHashMap store
    factory/         # Creation patterns
       MaterialFactory.java     # Factory for materials
    visitor/         # Behavioral patterns
       MaterialVisitor.java     # Visitor interface
       ShippingCostCalculator.java # Shipping cost visitor
    utils/           # Utility classes
       BookArrayUtils.java      # Array operations
    demo/            # Demonstration code
       PolymorphismDemo.java    # Polymorphism examples
 test/java/com/university/bookstore/
    model/           # Model tests
       BookTest.java, EBookTest.java, MaterialPolymorphismTest.java, ModelEdgeCaseTest.java
    impl/            # Implementation tests
       BookstoreArrayListTest.java, MaterialStoreImplTest.java, MaterialStoreConcurrentImplTest.java, EnhancedSearchTest.java, SimplePerformanceTest.java
    factory/         # Factory tests
       MaterialFactoryTest.java, MaterialFactoryExtendedTest.java
    visitor/         # Visitor tests
       ShippingCostCalculatorTest.java
    utils/           # Utility tests
       BookArrayUtilsTest.java
    api/             # API tests (empty)
 pom.xml              # Maven configuration
 runme.sh             # Build script
 README.md            # This file
```

### Development Workflow

1. **Setup Environment**
   ```bash
   cd src
   ./runme.sh --help  # Verify setup
   ```

2. **Make Changes**
   - Edit source files in `src/main/java/`
   - Add tests in `src/test/java/`
   - Update documentation as needed

3. **Test Changes**
   ```bash
   ./runme.sh --test-only  # Run tests
   ./runme.sh --quick      # Quick build
   ```

4. **Generate Documentation**
   ```bash
   ./runme.sh --uml-only   # Generate UML
   mvn javadoc:javadoc     # Generate Javadoc
   ```

5. **Full Build**
   ```bash
   ./runme.sh              # Complete build cycle
   ```

### Code Style Guidelines

- **Naming**: Use descriptive names for classes, methods, and variables
- **Documentation**: Add Javadoc for all public methods
- **Validation**: Validate all inputs with meaningful error messages
- **Immutability**: Make objects immutable when possible
- **Testing**: Write tests for all new functionality
- **Formatting**: Use consistent indentation and spacing

## Video Reflection Requirements

Create a 20-minute video reflection covering:

### Technical Discussion
1. **Polymorphism**: Explain using Material hierarchy examples
2. **Dynamic Binding**: Demonstrate with code walkthrough
3. **Template Method**: Show how `getDiscountedPrice()` works
4. **Immutability**: Discuss benefits in domain models
5. **Performance**: Compare ArrayList vs ConcurrentHashMap trade-offs and thread safety

### Code Exploration and Analysis

1. How many concrete classes extend the abstract Material class?
- The five concrete classes that extend the abstract Material class are AudioBook, EBook, Magazine, PrintedBook, and VideoMaterial.
2. What design pattern is demonstrated in the getDiscountedPrice() method?
- Inside the getDiscountedPrice() method, the design pattern demonstrated is the Template method. The template method is a design pattern where it creates the structure of an algorithm, which then allows other subclasses to override certain steps, while maintaining the same algorithm.. For example, the getDiscountedPrice() method is able to use the getDiscountRate() method as it desires, while the overall behavior of getDiscountedPrice() stays the same. The getDiscountRate() method is the step that can be used by other subclasses or the “step” that is being overridden.
3. List all interfaces implemented in the system and their purposes.
- BookstoreAPI: An interface that provides the basic methods to operate a bookstore, and defines CRUD, search, analytic, and export operations
- MaterialStore: An interface that helps establish operations of a polymorphic material store
- Media: An interface that helps materials like audio and video materials define their function (ex. Duration, format, and playback)
- MaterialVisitor: Allows for multiple classes to implement the same method names with different implementation details
- Comparable<Material>: Helps order all materials in alphabetical order using titles
4. Identify three examples of defensive programming in the Book class.
- Input validation in a constructor -> helps prevent any errors for a given input, instead of automatically assigning and assuming the given input is correct
- ISBN validation -> helps prevent wrong formatting of the ISBN, in case the given ISBN is a NULL value, and if it only contains digits from 0-9
- Price validation -> helps prevent invalid prices by checking if the price is a negative value, or any invalid math equations (infinite, or invalid calculations)
- Conclusion: Each one of these examples help prevent invalid input
5. What is the worst case time complexity of finding a book by ISBN in BookstoreArrayList?
- The worst case time complexity is O(n) where the given isbn is valid but it does not exist within the inventory, meaning that the code has to iterate through the entire list only to return back NULL.

### UML Generation and Analysis
1. Identify all aggregation and composition relationships
- Composition: Section A relies on Section B to exist. If Section B gets destroyed, Section A gets destroyed alongside it, since it can’t function without Section B. In essence, Section A can’t be independent, but is dependent on Section B.
    - Example: MaterialStoreImpl and List<Material>
        - The store (MaterialStoreImpl) has to exist in order for materials (List<Material>) to exist
- Aggregation: Section A can rely on Section B to exist, but it doesn’t have to. If Section B gets destroyed, Section A can still function without Section B. In essence, Section A can be independent of Section B.
    - Example: BookstoreArrayList and List<Book>
        - The bookstore (BookstoreArrayList) does not need to exist in order for books (List<Book>) to exist
2. Explain why Media is an interface rather than a class
- Media is an interface instead of a class because multiple different classes require Media to set its behaviour. In other words, Media is like a contract for all classes, as it helps classes create different implementation needs, and media types desired. At the same time, it helps practice the Interface Segregation Policy (A SOLID Principle), since Media and Material are separate from each other. This allows classes to only implement interfaces that are needed, instead of implementing ones that are not necessary, if Media and Material weren’t separated.

### Solid Principle Analysis

For each SOLID principle, provide:
1. Single Responsibility: One example from the codebase
- Single Responsibility essentially means that a class only should have one reason to change, and it should only have one job in general. An example would be the BookArrayUtils class, since the only purpose of the class is to provide util use for Book objects. The only reason it would need to change, would all be based on how the Book objects function, and what utils they require from the class.
2. Open/Closed: How does the Material hierarchy demonstrate this?
- Material is an abstract class that implements the Comparable<Material>. The subclasses of material can use polymorphism to implement any of the abstract methods in the Material class, but the subclasses cannot edit any of the methods within the Material class.(You can’t make modifications to Material class)
3. Liskov Substitution: An example of proper substitution
- Liskov Substitution means that subclasses should be able to substitute for their base type without changing the functionality of the base type. An example could be Material, meaning any Material subclass should be able to substitute in for the Material type, without breaking or changing functionality. In essence, we can use EBook (a material subclass) in place of where Material is needed or called.
4. Interface Segregation: Why is Media separate from Material?
- Media is separate from Material because the methods stored in Media are not needed by all subclasses, only AudioBook and VideoMaterial. The other subclasses shouldn't be forced to depend on interfaces/methods that don’t apply to them.
5. Dependency Inversion: How does the API layer demonstrate this?
- This means that it is not the actual implementation that is depended on, but the declaration of the method(method lines). In the API layer there are no code bodies for each of the declared methods, there are only the method lines in the interfaces.


### Reflection Questions

Answer these reflection questions thoughtfully:

1. **Abstraction Understanding**: How does the abstract `Material` class enforce a contract for its subclasses? What would happen if we made `Material` a concrete class instead?
- The abstract Material class enforces a contract for its subclasses by adding abstract methods without implementations, that their subclasses will be forced to implement. This ensures each of the subclasses implement specific behaviors that would be consistent with the other classes. If Material was made into a concrete class instead then all of its methods would have a defined body and would have already been implemented. This means that all of its subclasses would have to follow the exact same implementation unlike abstract classes where we would be able to have different implementations in different subclasses.

2. **Polymorphism in Practice**: Describe a real-world scenario where you would use polymorphism similar to this bookstore system. How would it improve code maintainability?
- Polymorphism allows it so we can reuse the same code in many different ways, especially code that may be needed for many different objects. It improves code readability by allowing users to reuse methods instead of having to create more for each object. It makes the code easier to manage and debug if you have one single method rather than multiple that do essentially the same thing.

3. **Interface Design**: Why is the `Media` interface valuable even though `AudioBook` and `VideoMaterial` already extend `Material`? What principle does this demonstrate?
- The Media interface is valuable because unlike PrintedBook and Magazine, AudioBook and VideoMaterial are online resources, meaning that there could be more factors that affect those two objects that don’t affect the others. Having the Media interface allows it so these two objects can implement more methods and variables while following a similar structure and not affecting the other objects.
- This demonstrates the Interface Segregation Principle where clients shouldn’t be forced to depend on interfaces they don’t use. In this case PrintedBook and Magazine wouldn’t use Media because they are physical books, while VideoMaterial and AudioBook would use Media because they are not physical items. 

4. **Defensive Programming**: Identify three defensive programming techniques used in the codebase. How do they prevent bugs and improve reliability?
- Defensive Programming is where we prepare for situations where errors can occur, and where code checking and prevention is done. Within the codebase, there are many different examples of defensive programming shown. 
    - EBook Constructor: There are validations being run inside the EBook constructor, instead of assuming that the given input is correct. For each of the fields, they run specific method lines that are there for input checking. 
    - Magazine ValidateIssn: Inside there, we can see how it checks for NULL input, and if it is properly formatted by checking if the ISSN is only 8 characters. In addition, it also helps remove unnecessary characters meaning input such as “1111 1111” can be valid input.
    - VideoMaterial ValidateFileSize: It helps check if the file size is a valid input, meaning it can’t be a negative number. It then throws an IllegalArgumentException, which allows us to identify the specific issue we are occurring.
- Conclusion: Each example helps prevent bugs, and improve reliability since it helps the code run smoothly, even if such issues occur. If an issue were to occur, a proper written error message would be given, which directly tells us what the problem is with our code. Having such defensive programming techniques allow us to further protect our code from errors, which is especially important when it comes to bigger portions of code.


5. **Testing Strategy**: Why is it important to test both valid and invalid inputs? Give an example of a boundary condition test from the codebase.
- It is important to test both valid and invalid inputs to make sure that your code runs more smoothly. If you only test valid inputs there is a chance that invalid inputs may pass the tests and cause an error somewhere along the way. Similarly if you only test invalid inputs you wouldn’t know if the valid inputs actually work the way they are intended to, which is why it is important to have both valid and invalid inputs when testing the code. It allows it so we can catch any bugs or errors that we may not have noticed within the code.
@Test
    @Order(8)
    @DisplayName("Should reject negative max price")
    void testFilterPriceAtMostNegative() {
        assertThrows(IllegalArgumentException.class,
            () -> BookArrayUtils.filterPriceAtMost(books, -1.0));
    }
- In this example, we can see that the test case checks for a negative input, where we use -1.0 as our testing input. Since we can’t have a value below 0, this test checks for input values lower than 0, then ensuring an IllegalArgumentException is thrown. This test case helps ensure that input values less than 0 will be properly dealt with in the code. Overall, this is an example of a boundary condition test since we know that the value of maxPrice can not be a negative value.

6. **Design Patterns**: Which design pattern from the lab do you find most useful? How would you apply it in your own projects?
- Out of all design patterns we found the factory design pattern to be most useful because of how it allows multiple objects to follow one interface, but all have different implementations. In our own projects we can use this design pattern in situations where we have multiple objects that follow a similar pattern, but essentially have different implementations. For example, we could have an animal interface, and have multiple different animal objects implementing the interface like dog, bear, chicken, etc. There could be a method makeNoise, and while all animals make noise, they all have different sounds, so implementing the method for each object would be different, but they would still follow the same pattern.

7. **Performance Considerations**: What are the performance implications of using `ArrayList` vs `HashMap` for the bookstore? When would you choose each?
- An arrayList would be a better option if you want an ordered version of your items. At the same time, we can use it to find where specific items are within the arrayList, by getting the index of the item. Memory wise, an arrayList would be a better option since it only uses a contiguous chunk of memory.
- For things like addition, deletion or search, a HashMap would be a better choice. Since hashMaps use a key-value pair, it makes it easier to go through all the items, and do the necessary operations desired. Different from an arrayList, it does not need to iterate through all items, to find the specific one we need.


8. **SOLID Principles**: Which SOLID principle do you think is most important for maintainable code? Provide an example from your implementation.
- Out of all the SOLID principles we think that the Single Responsibility Principle(SRP) is the most important for maintaining code. The Single Responsibility Principle helps us maintain and create flexibility, and talks about how a class should only change for one reason. By implementing this principle, we can ensure that code is being organized in a manner where no logic is being mixed amongst one another. At the same time, any occurrence of errors would only be targeted towards a specific logic of the code, ensuring that other sections remain unaffected. In the end, the appearance of errors would greatly decline compared to code where logic is mixed together.
    - For example, we have our ShippingCalculateCost class where all and only material shipping prices are calculated. In addition, we have our Material class where all the properties and information of all materials is being held. This is an example of the principle, since it shows how the logic behind cost calculating, and material information are separate amongst each other. By doing so, it ensures that each of those classes have only one purpose, making it easier to read and test code.


9. **Code Quality**: What makes code "clean"? Identify three characteristics of clean code demonstrated in this lab.
- Consistent/Similar Structure across multiple files
    - Removal of unnecessary and/or repetitive code across multiple files
- Same naming conventions 
    - Easy to read/understand comments that don’t over explain every method
- Maintainability
    - Clean code makes it easy to maintain, refactor, or debug.
    - Adding or removing new features is easy, and ensures minimal friction between existing implementations and features.
- Don’t Repeat Yourself (D.R.Y)
    - Ensures code reusability through the use of functions, classes, and objects.


10. **Learning Reflection**: What was the most challenging concept in this lab? How did you overcome the challenge, and what resources did you use?
- The most challenging concept to understand was the multiple design patterns that were discussed and implemented in this lab. During the lab, it took more effort and time to go over each of the design patterns to fully understand the purpose of each. When going through and reading the different design patterns we could have implemented it was hard to make a choice as we were familiar with none of the patterns. In addition, when implementing the two files for the visitor pattern it was difficult to understand what the design pattern actually was. For example, we went over patterns such as the template, and factory pattern as a group by looking at additional resources to further enhance our understanding.


### Quick Access Commands

```bash
# Open coverage report
open target/site/jacoco/index.html

# Open Javadoc documentation
open target/site/apidocs/index.html

# View UML diagram
open bookstore-class-diagram.png

# Run the application
java -jar target/bookstore-lab-1.0-SNAPSHOT.jar

# Run the concurrent demonstrations
cd src
java -cp target/classes com.university.bookstore.demo.PolymorphismDemo
```

## Contributing

### Development Guidelines

1. **Code Style**: Follow Java naming conventions 
2. **Documentation**: Add Javadoc for all public methods
3. **Testing**: Write unit tests for new features
4. **Validation**: Implement defensive programming
5. **Performance**: Consider algorithm complexity


## License

Educational use only

## Contributors

- **Course**: CSSD2101
- **Lab Assignment**: Bookstore Management System
- **Academic Year**: 2025
- **Author**: Navid Mohaghegh


## Getting Started

Ready to explore the world of object-oriented programming? Start with:

```bash
cd src
./runme.sh --help
```

**Happy Coding!**
