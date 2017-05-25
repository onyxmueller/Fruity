package net.onyxmueller.android.fruity.data;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.CursorLoader;

public class FruitLoader extends CursorLoader {

    private final DatabaseManager databaseManager;
    private final SortType sortType;

    public FruitLoader(Context context, SortType sortType) {
        super(context);

        this.databaseManager = DatabaseManager.getInstance(context.getApplicationContext());
        this.sortType = sortType;
    }

    @Override
    public Cursor loadInBackground() {
        return databaseManager.queryAllFruits(sortType);
    }
}
