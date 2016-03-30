package com.team18.teamproject;

/**
 * Created by Daniel on 30/03/2016.
 */
public class Recipe {

    private int id;
    private String name;
    private int serves;
    private String category;
    private String difficulty;
    private String cookTime;

    private String imageUrl;

    public Recipe(int id) {
        this.id = id;
        name = "testName";
        serves = 1;
        category = "testCat";
        difficulty = "testDifficulty";
        cookTime = "10 Minutes";
        imageUrl = "http://homepages.cs.ncl.ac.uk/2015-16/csc2022_team18/images/1.jpg";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getServes() {
        return serves;
    }

    public String getCategory() {
        return category;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getCookTime() {
        return cookTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
