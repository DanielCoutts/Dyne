package com.team18.teamproject.extras;

/**
 * Interface containing keys for JSON parsing.
 */
public interface Keys {
    interface Recipes {
        String KEY_ID = "RecipeID";
        String KEY_NAME = "RName";
        String KEY_IMAGEURL = "ImageURL";
        String KEY_SERVES = "Serves";
        String KEY_CATEGORY = "Category";
        String KEY_DIFFICULTY = "Difficulty";
        String KEY_COOKTIME = "CookTime";
    }

    interface Ingredients {
        String KEY_NAME = "IName";
        String KEY_UNITS = "Units";
        String KEY_QUANTITY = "Quantity";
    }

    interface Instructions {
        String KEY_STEP = "Step";
    }

    interface Nutrition {
        String KEY_KCAL = "KCal";
        String KEY_FAT = "Fat";
        String KEY_SUGAR = "Sugar";
        String KEY_PROTEIN = "Protein";
        String KEY_CARBS = "Carbohydrates";
    }

    interface Essentials {
        String KEY_ID = "EssentialID";
    }
}
