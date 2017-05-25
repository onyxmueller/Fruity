package net.onyxmueller.android.fruity;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import net.onyxmueller.android.fruity.data.AssetLoader;
import net.onyxmueller.android.fruity.data.Fruit;
import net.onyxmueller.android.fruity.databinding.FruitDetailActivityBinding;

public class FruitDetailsActivity extends AppCompatActivity {

    private static final String EXTRA_FRUIT = "fruit";

    private FruitDetailsActivityViewModel viewModel;

    public static Bundle newExtras(Fruit fruit) {
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_FRUIT, fruit);

        return bundle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FruitDetailActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_fruit_detail);

        Fruit fruit = getIntent().getParcelableExtra(EXTRA_FRUIT);
        viewModel = new FruitDetailsActivityViewModel(this, fruit);
        binding.setViewModel(viewModel);
    }

    @BindingAdapter("imageAsset")
    public static void loadImageAsset(ImageView imageView, String assetName) {
        Context context = imageView.getContext();
        AssetLoader assetLoader = new AssetLoader(context.getAssets());

        Bitmap bitmap = assetLoader.loadAsset(assetName);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
