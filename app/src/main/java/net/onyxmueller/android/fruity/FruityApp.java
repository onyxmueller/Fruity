package net.onyxmueller.android.fruity;


import android.app.Application;
import android.os.StrictMode;

public class FruityApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults();
        }
    }
}
