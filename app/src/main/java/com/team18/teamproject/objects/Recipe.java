package com.team18.teamproject.objects;

import java.io.Serializable;

/**
 * Created by Daniel on 30/03/2016.
 */
public class Recipe implements Serializable {

    private int id;
    private String name;
    private int serves;
    private String category;
    private String difficulty;
    private String cookTime;
    private String imageUrl;

    public Recipe(int id, String name, int serves, String category, String difficulty, String cookTime, String imageUrl) {
        this.id = id;
        this.name = name;
        this.serves = serves;
        this.category = category;
        this.difficulty = difficulty;
        this.cookTime = cookTime;
        this.imageUrl = imageUrl;
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

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
