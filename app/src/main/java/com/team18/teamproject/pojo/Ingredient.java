package com.team18.teamproject.pojo;

import java.io.Serializable;

/**
 * Created by Daniel on 21/04/2016.
 */
public class Ingredient implements Serializable {

    private String name;
    private String units;
    private double quantity;

    public Ingredient(String name, String units, double quantity) {
        this.name = name;
        this.units = units;
        this.quantity = quantity;
    }
}
