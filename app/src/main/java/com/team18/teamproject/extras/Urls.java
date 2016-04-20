package com.team18.teamproject.extras;

/**
 * Interface containing keys for JSON parsing.
 */
public interface Urls {
    /** Returns JSON array of recipes. Takes 3 boolean POST arguments: Vegetarian, Vegan, GlutenFree. */
    public static final String GET_RECIPES ="http://danielcoutts.me/projects/dyne/scripts/getRecipes.php";
    /** Returns JSON array of featured recipes. Takes 3 boolean POST arguments: Vegetarian, Vegan, GlutenFree. */
    public static final String GET_FEATURED ="http://danielcoutts.me/projects/dyne/scripts/getFeatured.php";
    /** Returns JSON array of ingredients. Takes 1 String parameter: RecipeID. */
    public static final String GET_INGREDIENTS ="http://http://danielcoutts.me/projects/dyne/scripts/getIngredients.php";
    /** Returns Nutrition JSON object. Takes 1 String parameter: RecipeID. */
    public static final String GET_NUTRITION ="http://http://danielcoutts.me/projects/dyne/scripts/getNutrition.php";
    /** Returns JSON array of instructions. Takes 1 String parameter: RecipeID. */
    public static final String GET_INSTRUCTIONS ="http://danielcoutts.me/projects/dyne/scripts/getInstructions.php";
    /** Returns a JSON array of recommended tutorials. Takes 1 String parameter: RecipeID. */
    public static final String GET_RECOMMENDED_ESSENTIALS ="http://danielcoutts.me/projects/dyne/scripts/getRecommendedEssentials.php";
}
