package infopanel.theappchief.com.infopanel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

@SuppressLint("AppCompatCustomView")
public class MyTextViewSemiBold extends TextView {

    public MyTextViewSemiBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextViewSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewSemiBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "font/Cabin-SemiBold.ttf");
        setTypeface(tf ,1);
    }
}