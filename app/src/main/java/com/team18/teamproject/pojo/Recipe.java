package com.team18.teamproject.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private List<Ingredient> ingredients = new ArrayList<>();

    private List<String> instructions = new ArrayList<>();

    private List<String> recommendedEssentials = new ArrayList<>();

    private Map<String, Float> nutritionalInfo;

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public Map<String, Float> getNutritionalInfo() {
        return nutritionalInfo;
    }

    public List<String> getRecommendedEssentials() {
        return recommendedEssentials;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public void setNutritionalInfo(Map<String, Float> nutritionalInfo) {
        this.nutritionalInfo = nutritionalInfo;
    }

    public void setRecommendedEssentials(List<String> recommendedEssentials) {
        this.recommendedEssentials = recommendedEssentials;
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
