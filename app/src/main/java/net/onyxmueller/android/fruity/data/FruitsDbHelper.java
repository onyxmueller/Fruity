package net.onyxmueller.android.fruity.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.JsonReader;

import net.onyxmueller.android.fruity.R;
import net.onyxmueller.android.fruity.data.FruitContract.FruitEntry;

import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Database helper class to facilitate creating and updating
 * the database from the chosen schema.
 */
public class FruitsDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fruits.db";
    private static final int DATABASE_VERSION = 1;

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FruitEntry.TABLE_NAME + " (" +
                    FruitEntry._ID + " INTEGER PRIMARY KEY," +
                    FruitEntry.COLUMN_COMMON_NAME + " TEXT," +
                    FruitEntry.COLUMN_SCIENTIFIC_NAME + " TEXT," +
                    FruitEntry.COLUMN_ICON_RESOURCE + " TEXT," +
                    FruitEntry.COLUMN_IMAGE_ASSET + " TEXT," +
                    FruitEntry.COLUMN_SERVING_SIZE_GRAMS + " DOUBLE," +
                    FruitEntry.COLUMN_CARBS_GRAMS + " DOUBLE," +
                    FruitEntry.COLUMN_FIBER_GRAMS + " DOUBLE," +
                    FruitEntry.COLUMN_SUGAR_GRAMS + " DOUBLE," +
                    FruitEntry.COLUMN_PROTEIN_GRAMS + " DOUBLE)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FruitEntry.TABLE_NAME;

    //Used to read data from res/ and assets/
    private Resources resources;

    public FruitsDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        resources = context.getResources();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        try {
            readFruitsFromResources(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    /**
     * Streams the JSON data from fruits.json, parses it, and inserts it into the
     * provided {@link SQLiteDatabase}.
     *
     * @param db Database where objects should be inserted.
     * @throws IOException
     * @throws JSONException
     */
    private void readFruitsFromResources(SQLiteDatabase db) throws IOException, JSONException {
        InputStream in = resources.openRawResource(R.raw.fruits);

        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equalsIgnoreCase("fruits")) {
                    List<Fruit> fruits = readFruitsJsonArray(reader);
                    for (Fruit fruit : fruits) {
                        ContentValues values = new ContentValues();
                        values.put(FruitEntry.COLUMN_COMMON_NAME, fruit.commonName);
                        values.put(FruitEntry.COLUMN_SCIENTIFIC_NAME, fruit.scientificName);
                        values.put(FruitEntry.COLUMN_ICON_RESOURCE, fruit.iconResource);
                        values.put(FruitEntry.COLUMN_IMAGE_ASSET, fruit.imageAsset);
                        values.put(FruitEntry.COLUMN_SERVING_SIZE_GRAMS, fruit.servingSizeGrams);
                        values.put(FruitEntry.COLUMN_CARBS_GRAMS, fruit.carbsGrams);
                        values.put(FruitEntry.COLUMN_FIBER_GRAMS, fruit.fiberGrams);
                        values.put(FruitEntry.COLUMN_SUGAR_GRAMS, fruit.sugarGrams);
                        values.put(FruitEntry.COLUMN_PROTEIN_GRAMS, fruit.proteinGrams);
                        db.insert(FruitEntry.TABLE_NAME, null, values);
                    }
                }
            }
            reader.endObject();
        } finally {
            reader.close();
            in.close();
        }
    }

    private List<Fruit> readFruitsJsonArray(JsonReader reader) throws IOException {
        List<Fruit> fruits = new ArrayList<>();
        reader.beginArray();
        while (reader.hasNext()) {
            fruits.add(readFruit(reader));
        }
        reader.endArray();
        return fruits;
    }

    private Fruit readFruit(JsonReader reader) throws IOException {
        String commonName = "";
        String scientificName = "";
        String iconResource = "";
        String imageAsset = "";
        double servingSizeGrams = -1D;
        double carbsGrams = -1D;
        double fiberGrams = -1D;
        double sugarGrams = -1D;
        double proteinGrams = -1D;

        reader.beginObject();
        while (reader.hasNext()) {
            String name = reader.nextName();
            if (name.equalsIgnoreCase("commonName")) {
                commonName = reader.nextString();
            } else if (name.equalsIgnoreCase("scientificName")) {
                scientificName = reader.nextString();
            } else if (name.equalsIgnoreCase("iconResource")) {
                iconResource = reader.nextString();
            } else if (name.equalsIgnoreCase("imageAsset")) {
                imageAsset = reader.nextString();
            } else if (name.equalsIgnoreCase("servingSizeGrams")) {
                servingSizeGrams = reader.nextDouble();
            } else if (name.equalsIgnoreCase("carbsGrams")) {
                carbsGrams = reader.nextDouble();
            } else if (name.equalsIgnoreCase("fiberGrams")) {
                fiberGrams = reader.nextDouble();
            } else if (name.equalsIgnoreCase("sugarGrams")) {
                sugarGrams = reader.nextDouble();
            } else if (name.equalsIgnoreCase("proteinGrams")) {
                proteinGrams = reader.nextDouble();
            } else {
                reader.skipValue();
            }
        }
        reader.endObject();
        return new Fruit(commonName, scientificName, iconResource, imageAsset, servingSizeGrams,
                carbsGrams, fiberGrams, sugarGrams, proteinGrams);
    }
}
