package net.onyxmueller.android.fruity.data;


import android.provider.BaseColumns;

public class FruitContract {
    private FruitContract() { }

    public static class FruitEntry implements BaseColumns {
        public static final String TABLE_NAME = "fruit";
        public static final String COLUMN_COMMON_NAME = "common_name";
        public static final String COLUMN_SCIENTIFIC_NAME = "scientific_name";
        public static final String COLUMN_ICON_RESOURCE = "icon_resource";
        public static final String COLUMN_IMAGE_ASSET = "image_asset";
        public static final String COLUMN_SERVING_SIZE_GRAMS = "serving_size_grams";
        public static final String COLUMN_CARBS_GRAMS = "carbs_grams";
        public static final String COLUMN_FIBER_GRAMS = "fiber_grams";
        public static final String COLUMN_SUGAR_GRAMS = "sugar_level";
        public static final String COLUMN_PROTEIN_GRAMS = "protein_grams";
    }
}
