package net.onyxmueller.android.fruity.views;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;

public class FruitIconView extends android.support.v7.widget.AppCompatImageView {

    private double sugarLevel = -1D;

    public FruitIconView(Context context) {
        super(context);
    }

    public FruitIconView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FruitIconView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
//
//    public void setSugarLevel(double sugarLevel) {
//        this.sugarLevel = sugarLevel;
//
//        String dangerLevelText = String.valueOf(sugarLevel);
//        setText(dangerLevelText);fruit_
//        final String[] stringArray = getContext().getResources().getStringArray(R.array.dangerColors);
//        String colorString = stringArray[0];//(int) (sugarLevel - 1)];
//
//        final Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.background_danger);
//        drawable.setColorFilter(Color.parseColor(colorString), PorterDuff.Mode.MULTIPLY);
//        setBackground(drawable);
//    }

    public void setFruitIconName(String fruitIconName) {
        final Context context = getContext();
        final Resources resources = context.getResources();
        final int resourceId = resources.getIdentifier(fruitIconName, "drawable",
                context.getPackageName());
        setImageResource(resourceId);
    }

//    @Override
//    public void setImageResource(int resId) {
//        super.setImageResource(resId);
//        BitmapDrawable drawable = (BitmapDrawable) getDrawable();
//        Bitmap bitmap = drawable.getBitmap();
//        createPaletteAsync(bitmap);
//    }
//
//    // Generate palette asynchronously and use it on a different
//    // thread using onGenerated()
//    private void createPaletteAsync(Bitmap bitmap) {
//        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
//            public void onGenerated(Palette p) {
//                setBackgroundColor(p.getVibrantColor(ContextCompat.getColor(getContext(), R.color.detail_hero_background_color)));
//            }
//        });
//    }
}
