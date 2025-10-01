package com.university.bookstore.visitor;

import com.university.bookstore.model.AudioBook;
import com.university.bookstore.model.EBook;
import com.university.bookstore.model.Magazine;
import com.university.bookstore.model.Material;
import com.university.bookstore.model.Media;
import com.university.bookstore.model.PrintedBook;
import com.university.bookstore.model.VideoMaterial;

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
    
    private static final double PHYSICAL_ITEM_RATE = 0.50; // per 100g
    private static final double MAGAZINE_FLAT_RATE = 2.00;
    private static final double DIGITAL_ITEM_RATE = 0.00;
    
    private double totalShippingCost = 0.0;

}
