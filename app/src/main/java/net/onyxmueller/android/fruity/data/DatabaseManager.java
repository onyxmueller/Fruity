package net.onyxmueller.android.fruity.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import net.onyxmueller.android.fruity.data.FruitContract.FruitEntry;

/**
 * Singleton that controls access to the SQLiteDatabase instance
 * for this application.
 */
public class DatabaseManager {
    private static DatabaseManager INSTANCE;

    private final SQLiteDatabase writableDatabase;

    public static synchronized DatabaseManager getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new DatabaseManager(context.getApplicationContext());
        }

        return INSTANCE;
    }

    private FruitsDbHelper fruitsDbHelper;

    private DatabaseManager(Context context) {
        fruitsDbHelper = new FruitsDbHelper(context);
        writableDatabase = fruitsDbHelper.getWritableDatabase();

    }

    /**
     * Return a {@link Cursor} that contains every fruit in the database.
     *
     * @param sortType Sort column for the query. Set to SortType.NONE for no sorting.
     * @return {@link Cursor} containing all fruit results.
     */
    public Cursor queryAllFruits(SortType sortType) {
        String[] projection = {
                FruitEntry.COLUMN_COMMON_NAME,
                FruitEntry.COLUMN_SCIENTIFIC_NAME,
                FruitEntry.COLUMN_ICON_RESOURCE,
                FruitEntry.COLUMN_IMAGE_ASSET,
                FruitEntry.COLUMN_SERVING_SIZE_GRAMS,
                FruitEntry.COLUMN_CARBS_GRAMS,
                FruitEntry.COLUMN_FIBER_GRAMS,
                FruitEntry.COLUMN_SUGAR_GRAMS,
                FruitEntry.COLUMN_PROTEIN_GRAMS
        };

        final String orderBy = sortType == SortType.NONE ? null :
                String.format("%s %s", sortType.getColumnName(), sortType.getOrderBy().getOrder());

        Cursor cursor = writableDatabase.query(
                FruitEntry.TABLE_NAME,                     // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                orderBy                                 // The sort order
        );
        return cursor;
    }
}
