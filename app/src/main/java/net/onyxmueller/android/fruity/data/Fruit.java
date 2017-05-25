package net.onyxmueller.android.fruity.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import net.onyxmueller.android.fruity.data.FruitContract.FruitEntry;

public final class Fruit implements Parcelable {
    // common name
    public final String commonName;
    // latin scientific name
    public final String scientificName;
    // name of icon resource
    public final String iconResource;
    // path to image resource
    public final String imageAsset;
    // serving size in grams
    public final double servingSizeGrams;
    // carbs in grams
    public final double carbsGrams;
    // fiber in grams
    public final double fiberGrams;
    // sugar in grams
    public final double sugarGrams;
    // protein in grams
    public final double proteinGrams;

    /**
     * Create a new Fruit from discrete values
     */
    public Fruit(String name, String scientificName, String iconResource,  String imageAsset,
                 double servingSizeGrams, double carbsGrams, double fiberGrams, double sugarGrams,
                 double proteinGrams) {
        this.commonName = name;
        this.scientificName = scientificName;
        this.iconResource = iconResource;
        this.imageAsset = imageAsset;
        this.servingSizeGrams = servingSizeGrams;
        this.carbsGrams = carbsGrams;
        this.fiberGrams = fiberGrams;
        this.sugarGrams = sugarGrams;
        this.proteinGrams = proteinGrams;
    }

    /**
     * Create a new Fruit from a database Cursor
     */
    public Fruit(Cursor cursor) {
        int nameIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_COMMON_NAME);
        int scientificNameIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_SCIENTIFIC_NAME);
        int iconResourceIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_ICON_RESOURCE);
        int imageAssetIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_IMAGE_ASSET);
        int servingSizeGramsIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_SERVING_SIZE_GRAMS);
        int carbsGramsIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_CARBS_GRAMS);
        int fiberGramsIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_FIBER_GRAMS);
        int sugarGramsIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_SUGAR_GRAMS);
        int proteinGramsIndex = cursor.getColumnIndexOrThrow(FruitEntry.COLUMN_PROTEIN_GRAMS);
        this.commonName =  cursor.getString(nameIndex);
        this.scientificName = cursor.getString(scientificNameIndex);
        this.iconResource = cursor.getString(iconResourceIndex);
        this.imageAsset = cursor.getString(imageAssetIndex);
        this.servingSizeGrams = cursor.getDouble(servingSizeGramsIndex);
        this.carbsGrams = cursor.getDouble(carbsGramsIndex);
        this.fiberGrams = cursor.getDouble(fiberGramsIndex);
        this.sugarGrams = cursor.getDouble(sugarGramsIndex);
        this.proteinGrams = cursor.getDouble(proteinGramsIndex);
    }

    /**
     * Create a new Fruit from a data Parcel
     */
    protected Fruit(Parcel in) {
        this.commonName = in.readString();
        this.scientificName = in.readString();
        this.iconResource = in.readString();
        this.imageAsset = in.readString();
        this.servingSizeGrams = in.readDouble();
        this.carbsGrams = in.readDouble();
        this.fiberGrams = in.readDouble();
        this.sugarGrams = in.readDouble();
        this.proteinGrams = in.readDouble();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(commonName);
        dest.writeString(scientificName);
        dest.writeString(iconResource);
        dest.writeString(imageAsset);
        dest.writeDouble(servingSizeGrams);
        dest.writeDouble(carbsGrams);
        dest.writeDouble(fiberGrams);
        dest.writeDouble(sugarGrams);
        dest.writeDouble(proteinGrams);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Fruit> CREATOR = new Creator<Fruit>() {
        @Override
        public Fruit createFromParcel(Parcel in) {
            return new Fruit(in);
        }

        @Override
        public Fruit[] newArray(int size) {
            return new Fruit[size];
        }
    };
}
