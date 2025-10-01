package com.university.bookstore.api;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import com.university.bookstore.model.Material;
import com.university.bookstore.model.Media;

/**
 * Interface defining operations for a polymorphic material store.
 * Demonstrates interface segregation and dependency inversion principles.
 * 
 * <p>This interface extends the concept of a bookstore to handle
 * various types of materials using polymorphism.</p>
 * 
 * @author Jayna Ong and Nina Zhang
 * @version 1.0
 * @since 2025-10-01
 */
public interface MaterialStore {

    
    //adds material to store inventory
    boolean addMaterial(Material material);

    //removes material by id
    Optional<Material> removeMaterial(String id);

    //finds material by id
    Optional<Material> findById(String id);

    //searches material by title -> case-insensitive partial match
    List<Material> searchByTitle(String title);

    //searches materials by creator
    List<Material> searchByCreator(String creator);

    //gets all material of a certain type
    List<Material> getMaterialsByType(Material.MaterialType type);

    //gets all material that implement the media interface
    List<Media> getMediaMaterials();

    //filters materials by a custom predicate
    List<Material> filterMaterials(Predicate<Material> predicate);
    
    //find materials published in last n years
    List<Material> findMostRecentMaterials(int years);

    //find materials by multiple creators or condition
    List<Material> findByCreators(String ... creators);

    //find materials with custom filtering using predicate
    List<Material> findWithPredicate(Predicate<Material> condition);

    //get materials sorted by a custom comparator
    List<Material> getSorted(java.util.Comparator<Material> comparator);

    //get materials within a certain price range
    List<Material> getMaterialsByPriceRange(double minPrice, double maxPrice);

    //get materials published/released in a certain year
    List<Material> getMaterialsByYear(int year);

    //gets all materials sorted by title
    List<Material> getAllMaterialsSorted();

    //gets all materials unsorted
    List<Material> getAllMaterials();

    //calculates total inventory
    double getTotalInventoryValue();

    //calculates total discounted inventory value
    double getTotalDiscountedValue();

    //gets inventory statistics
    InventoryStats getInventoryStats();

    //clears all material from the store
    void clearInventory();

    //gets the number of materials in an inventory
    int size();

    //checks if inventory is empty
    boolean isEmpty();

    //statistics class for inventory analysis
    class InventoryStats {
        private final int totalCount;
        private final double averagePrice;
        private final double medianPrice;
        private final int uniqueTypes;
        private final int mediaCount;
        private final int printCount;

        public InventoryStats(int totalCount, double averagePrice, double medianPrice, int uniqueTypes, int mediaCount, int printCount){
            this.totalCount = totalCount;
            this.averagePrice = averagePrice;
            this.medianPrice = medianPrice;
            this.uniqueTypes = uniqueTypes;
            this.mediaCount = mediaCount;
            this.printCount = printCount;
        }

        public int getTotalCount() {
            return totalCount;
        }
        public double getAveragePrice() {
            return averagePrice;
        }
        public double getMedianPrice() {
            return medianPrice;
        }
        public int getUniqueTypes() {
            return uniqueTypes;
        }
        public int getMediaCount() {
            return mediaCount;
        }
        public int getPrintCount() {
            return printCount;
        }
        @Override 
        public String toString() {
            return String.format("InventoryStats[Total=%d, AvgPrice=$%.2f, MedianPrice=$%.2f, Types=%d, Media=%d, Print=%d]", totalCount, averagePrice, medianPrice, uniqueTypes, mediaCount, printCount);
        }
    }

}