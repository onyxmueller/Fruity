package net.onyxmueller.android.fruity.data;


import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class AssetLoader {

    private final AssetManager assetManager;

    private static final String TAG = AssetLoader.class.getSimpleName();

    public AssetLoader(AssetManager assetManager) {
        this.assetManager = assetManager;
    }

    @Nullable
    public Bitmap loadAsset(String assetName) {
        if (TextUtils.isEmpty(assetName)) {
            return null;
        }
        try {
            InputStream inputStream = assetManager.open(assetName);
            return BitmapFactory.decodeStream(inputStream);
        } catch (IOException e) {
            Log.e(TAG, String.format("unable to load %s", assetName), e);
        }

        return null;
    }
}
