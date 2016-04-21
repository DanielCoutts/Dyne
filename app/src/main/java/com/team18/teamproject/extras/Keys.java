package com.team18.teamproject.extras;

/**
 * Interface containing keys for JSON parsing.
 */
public interface Keys {
    interface Recipes {
        public static final String KEY_ID="RecipeID";
        public static final String KEY_NAME="RName";
        public static final String KEY_IMAGEURL="ImageURL";
        public static final String KEY_SERVES="Serves";
        public static final String KEY_CATEGORY="Category";
        public static final String KEY_DIFFICULTY="Difficulty";
        public static final String KEY_COOKTIME="CookTime";
    }

    interface Ingredients {
        public static final String KEY_NAME="IName";
        public static final String KEY_UNITS="Units";
        public static final String KEY_QUANTITY="Quantity";
    }

    interface Instructions {
        public static final String KEY_STEP="Step";
    }

    interface Nutrition {
        public static final String KEY_KCAL="KCal";
        public static final String KEY_FAT="Fat";
        public static final String KEY_SUGAR="Sugar";
        public static final String KEY_PROTEIN="Protein";
        public static final String KEY_CARBS="Carbohydrates";
    }

    interface Essentials {
        public static final String KEY_ID="EssentialID";
    }
}
