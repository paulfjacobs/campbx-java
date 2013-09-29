package com.cbx.response;

/**
 * Price and Quantity tuple.
 * @author  pjacobs
 */
public class PriceQuantity {
    private double price;
    private double quantity;

    public PriceQuantity(double price, double quantity) {
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Price:"+this.price+" Quantity:"+this.quantity;
    }
}
