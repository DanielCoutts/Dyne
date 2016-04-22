package com.team18.teamproject.extras;

/**
 * Interface containing keys for JSON parsing.
 */
public interface Urls {
    /** Returns JSON array of recipes. Takes 3 boolean POST arguments: Vegetarian, Vegan, GlutenFree. */
    public static final String GET_RECIPES ="http://danielcoutts.me/projects/dyne/scripts/getRecipes.php";
    /** Returns JSON array of featured recipes. Takes 3 boolean POST arguments: Vegetarian, Vegan, GlutenFree. */
    public static final String GET_FEATURED ="http://danielcoutts.me/projects/dyne/scripts/getFeatured.php";
    /** Returns JSON array of ingredients. Takes 1 String POST argument: RecipeID. */
    public static final String GET_INGREDIENTS ="http://danielcoutts.me/projects/dyne/scripts/getIngredients.php";
    /** Returns Nutrition JSON object. Takes 1 String POST argument: RecipeID. */
    public static final String GET_NUTRITION ="http://danielcoutts.me/projects/dyne/scripts/getNutrition.php";
    /** Returns JSON array of instructions. Takes 1 String POST argument: RecipeID. */
    public static final String GET_INSTRUCTIONS ="http://danielcoutts.me/projects/dyne/scripts/getInstructions.php";
    /** Returns a JSON array of recommended tutorials. Takes 1 String POST argument: RecipeID. */
    public static final String GET_RECOMMENDED_ESSENTIALS ="http://danielcoutts.me/projects/dyne/scripts/getRecommendedEssentials.php";
    /** Returns a JSON array of recipes of a specified category. Takes 1 String POST argument: Category. Takes 3 boolean POST arguments: Vegetarian, Vegan, GlutenFree. */
    public static final String GET_CATEGORY = "http://danielcoutts.me/projects/dyne/scripts/getCategory.php";
    /** Returns a JSON array of recipes matching a search query. Takes 1 String POST argument: Search. Takes 3 boolean POST arguments: Vegetarian, Vegan, GlutenFree. */
    public static final String SEARCH = "http://danielcoutts.me/projects/dyne/scripts/search.php";
}
