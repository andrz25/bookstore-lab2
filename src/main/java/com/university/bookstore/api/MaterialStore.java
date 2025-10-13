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
    
    /**
     * Adds valid material to store inventory
     * @param material the material that will be added
     * @return true if the material is added, false if the material is not valid
     */

    boolean addMaterial(Material material);

    /**
     * Removes material by unique id
     * @param id the id that will be used to remove materials
     * @return material object if it was removed, an empty optional if not
     * @throws NullPointerException is thrown if material is null
     */
    
    Optional<Material> removeMaterial(String id);

    /**
     * Finds material by unique id
     * @param id the id that will be used to find materials
     * @return material object if it was found, an empty optional if not
     */
    
    Optional<Material> findById(String id);

    /**
     * Searches material by title using case-insensitive and partial match
     * @param title the title that will be used to search for materials
     * @return list of materials that contain the title
     */
    
    List<Material> searchByTitle(String title);

    /**
     * Searches material by creator using case-insensitive and partial match
     * @param creator the creator that will be used to search for materials
     * @return list of materials that contain the creator
     */

    List<Material> searchByCreator(String creator);

    /**
     * Gets all material of a certain type
     * @param type the type of material that is being looked for
     * @return list of materials that are that given type
     */

    List<Material> getMaterialsByType(Material.MaterialType type);

    /**
     * Gets all material that implement the media interface
     * @return list of media objects that implement media interface
     */

    List<Media> getMediaMaterials();

    /**
     * Filters materials by a custom predicate
     * @param predicate the predicate that helps filter out media objects
     * @return list of media objects that implement media interface
     * @throws NullPointerException is thrown if predicate is null
     */
    
    //filters materials by a custom predicate
    List<Material> filterMaterials(Predicate<Material> predicate);
    
    /**
     * Find materials published in last n years
     * @param years the year the materials need to be
     * @return a list of materials that are the same as the given year
     * @throws IllegalArgumentException if years is a negative value
     */
    
    List<Material> findRecentMaterials(int years);

    /**
     * Find materials by multiple creators or condition
     * @param creators the creators or condition that help filter out materials
     * @return list of materials that contain the creators, or match the conditions
     */
    
    List<Material> findByCreators(String ... creators);

    /**
     * Find materials with custom filtering using predicate
     * @param condition the condition used to find materials
     * @return list of materials that match the predicate
     * @throws NullPointerException is thrown if condition is null
     */
    
    List<Material> findWithPredicate(Predicate<Material> condition);

    /**
     * Gets materials sorted by a custom comparator
     * @param comparator the comparator used to sort materials
     * @return list of materials that were sorted using the comparator
     * @throws NullPointerException is thrown if comparator is null
     */
    
    List<Material> getSorted(java.util.Comparator<Material> comparator);

    /**
     * Gets materials within a certain price range
     * @param minPrice the minimum price the material can be
     * @param maxPrice the maximum price the material can be
     * @return list of materials that are within the given price range
     */
    
    List<Material> getMaterialsByPriceRange(double minPrice, double maxPrice);

    /**
     * Gets materials published/released in a certain year
     * @param year the year the required materials should be
     * @return list of materials that match the given year
     * @throws IllegalArgumentException is thrown if minPrice or maxPrice is less than zero, or minPrice is larger than maxPrice
     */

    List<Material> getMaterialsByYear(int year);

    /**
     * Gets all materials sorted by title
     * @return list of materials that are sorted in the inventory
     */
    
    List<Material> getAllMaterialsSorted();

    /**
     * Gets all materials unsorted
     * @return list of material that was copied from the original inventory
     */

    List<Material> getAllMaterials();

    /**
     * Calculates total inventory value
     * @return total inventory value
     */

    double getTotalInventoryValue();

    /**
     * Calculates total discounted inventory value
     * @return the total discounted inventory value calculated
     */
    
    double getTotalDiscountedValue();

    /**
     * Gets all inventory statistics
     * @return all information about the inventory
     */
    
    InventoryStats getInventoryStats();

    /**
     * Clears all material from the store
     */
    
    void clearInventory();

    /**
     * Gets the number of materials in an inventory
     * @return the amount of materials in the inventory
     */
    
    int size();

    /**
     * Checks if inventory is empty
     * @return true if inventory is empty, false if not
     */

    boolean isEmpty();

    /**
     * A class that contains all statistics for our current inventory
     * Allows inventory analysis to be done
     */

    class InventoryStats {
        private final int totalCount;
        private final double averagePrice;
        private final double medianPrice;
        private final int uniqueTypes;
        private final int mediaCount;
        private final int printCount;

        /**
         * Constructor for InventoryStats with required information
         * @param totalCount the total number of items in inventory
         * @param averagePrice the average price of all items in inventory
         * @param medianPrice the median price of all items in inventory
         * @param uniqueTypes the number of unique types in inventory
         * @param mediaCount the number of media types in inventory
         * @param printCount the number of print types in inventory
         */

        public InventoryStats(int totalCount, double averagePrice, double medianPrice, int uniqueTypes, int mediaCount, int printCount){
            this.totalCount = totalCount;
            this.averagePrice = averagePrice;
            this.medianPrice = medianPrice;
            this.uniqueTypes = uniqueTypes;
            this.mediaCount = mediaCount;
            this.printCount = printCount;
        }

        /**
         * Returns total number of items in inventory
         * @return the total number of items in inventory
         */

        public int getTotalCount() {
            return totalCount;
        }

        /**
         * Returns average price of all inventory items
         * @return the average price of all items in inventory
         */

        public double getAveragePrice() {
            return averagePrice;
        }

        /**
         * Returns median price of all inventory items
         * @return the median price of all inventory items
         */

        public double getMedianPrice() {
            return medianPrice;
        }

        /**
         * Returns the number of unique types in inventory
         * @return the number of unique types in inventory
         */

        public int getUniqueTypes() {
            return uniqueTypes;
        }

        /**
         * Returns the number of media types in inventory
         * @return the number of media types in inventory
         */

        public int getMediaCount() {
            return mediaCount;
        }

        /**
         * Returns the number of print types in inventory
         * @return the number of print types in inventory
         */

        public int getPrintCount() {
            return printCount;
        }

        @Override 
        public String toString() {
            return String.format("InventoryStats[Total=%d, AvgPrice=$%.2f, MedianPrice=$%.2f, Types=%d, Media=%d, Print=%d]", totalCount, averagePrice, medianPrice, uniqueTypes, mediaCount, printCount);
        }
    }

}