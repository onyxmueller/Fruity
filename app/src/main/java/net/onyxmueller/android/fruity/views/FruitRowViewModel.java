package net.onyxmueller.android.fruity.views;


import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.view.View;

import net.onyxmueller.android.fruity.FruitDetailsActivity;
import net.onyxmueller.android.fruity.data.Fruit;

public class FruitRowViewModel extends BaseObservable implements View.OnClickListener {

    private Fruit fruit;

    public FruitRowViewModel() {

    }

    public void update(Fruit fruit) {
        this.fruit = fruit;
        notifyChange();
    }

    public String getCommonName() {
        return fruit.commonName;
    }

    public String getScientificName() {
        return fruit.scientificName;
    }

    public String getIconResource() {
        return fruit.iconResource;
    }

    @Override
    public void onClick(View v) {
        Context context = v.getContext();
        Intent intent = new Intent(context, FruitDetailsActivity.class);
        intent.putExtras(FruitDetailsActivity.newExtras(fruit));

        context.startActivity(intent);
    }
}
