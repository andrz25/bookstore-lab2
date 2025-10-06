package com.university.bookstore.visitor;

import com.university.bookstore.model.AudioBook;
import com.university.bookstore.model.EBook;
import com.university.bookstore.model.Magazine;
import com.university.bookstore.model.Material;
import com.university.bookstore.model.Media;
import com.university.bookstore.model.PrintedBook;
import com.university.bookstore.model.VideoMaterial;

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

/**
 * Visitor implementation for calculating shipping costs.
 * Demonstrates the Visitor pattern by providing different shipping
 * cost calculations based on material type.
 * 
 * <p>Shipping costs vary by material type:
 * - Physical items: $0.50 per 100g
 * - Digital items: $0 (instant download)
 * - Magazines: $2 flat rate</p>
 * 
 */


 public class ShippingCostCalculator implements MaterialVisitor {
    
    //physical items 0.50 euros per 100g
    //digital items 0 euros (instant download)
    //magazines 2 euros bought rate

    private static final double PHYSICAL_ITEM_RATE = 0.50; // per 100g
    private static final double MAGAZINE_FLAT_RATE = 2.00;
    private static final double DIGITAL_ITEM_RATE = 0.00;
    
    private double totalShippingCost = 0.0;

    @Override
    public void visit(PrintedBook book) {
        double weightHundredGrams = 5.0;
        double cost = weightHundredGrams * PHYSICAL_ITEM_RATE;
        totalShippingCost += cost;
    }

    @Override
    public void visit(Magazine magazine) {
        totalShippingCost += MAGAZINE_FLAT_RATE;
    }

    @Override
    public void visit(AudioBook audioBook) {
        if (audioBook.getQuality() == Media.MediaQuality.PHYSICAL) {
            double weightHundredGrams = 1.0;
            double cost = weightHundredGrams * PHYSICAL_ITEM_RATE;
            totalShippingCost += cost;
        }
        else {
            totalShippingCost += DIGITAL_ITEM_RATE;
        }
    }

    @Override
    public void visit(VideoMaterial video) {
        if (video.getQuality() == Media.MediaQuality.PHYSICAL) {
            double weightHundredGrams = 1.5;
            double cost = weightHundredGrams * PHYSICAL_ITEM_RATE;
            totalShippingCost += cost;
        }
        else {
            totalShippingCost += DIGITAL_ITEM_RATE;
        }
    }

    @Override
    public void visit(EBook ebook) {
        totalShippingCost += DIGITAL_ITEM_RATE;
    }

    public double getShippingCost() {
        return totalShippingCost;
    }

    public void reset() {
        totalShippingCost = 0.0;
    }

    public double calcualteShippingCost(Material material) {
        reset();

        if (material instanceof PrintedBook) {
            visit((PrintedBook) material);
        } else if (material instanceof Magazine) {
            visit((Magazine) material);
        } else if (material instanceof AudioBook) {
            visit((AudioBook) material);
        } else if (material instanceof VideoMaterial) {
            visit((VideoMaterial) material);
        } else if (material instanceof EBook) {
            visit((EBook) material);
        } else {
            throw new IllegalArgumentException("Unknown material types: " + material.getClass().getSimpleName());
        }
        return totalShippingCost;
    }

}
