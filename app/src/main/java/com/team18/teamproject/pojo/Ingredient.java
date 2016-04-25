package com.team18.teamproject.pojo;

import java.io.Serializable;

/**
 * Class representation of an Ingredient.
 *
 * Created by Daniel.
 */
public class Ingredient implements Serializable {

    private String name;
    private String units;
    private String quantity;

    public Ingredient(String name, String units, String quantity) {
        this.name = name;
        this.units = units;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getUnits() {
        return units;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
