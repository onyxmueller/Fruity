package net.onyxmueller.android.fruity;


import android.content.Context;
import android.databinding.BaseObservable;

import net.onyxmueller.android.fruity.data.Fruit;

public class FruitDetailsActivityViewModel extends BaseObservable {

    private final Context context;
    private final Fruit fruit;

    public FruitDetailsActivityViewModel(Context context, Fruit fruit) {
        this.context = context;
        this.fruit = fruit;
    }

    public String getName() {
        return fruit.commonName;
    }

    public String getScientificName() {
        return fruit.scientificName;
    }

    public String getImageAssetName() {
        return fruit.imageAsset;
    }

    public String getServingSizeDescriptionAndValue() {
        return context.getString(R.string.serving_size_description_format, fruit.servingSizeGrams);
    }

    public String getCarbsDescriptionAndValue() {
        return context.getString(R.string.carbs_description_format, fruit.carbsGrams);
    }

    public String getFiberDescriptionAndValue() {
        return context.getString(R.string.fiber_description_format, fruit.fiberGrams);
    }

    public String getSugarDescriptionAndValue() {
        return context.getString(R.string.sugar_description_format, fruit.sugarGrams);
    }

    public String getProteinDescriptionAndValue() {
        return context.getString(R.string.protein_description_format, fruit.proteinGrams);
    }
}
