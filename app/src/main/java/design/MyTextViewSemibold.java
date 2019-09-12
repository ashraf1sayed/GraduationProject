package design;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;


public class MyTextViewSemibold extends TextView {

    public int MVersion =  Build.VERSION.SDK_INT;

    public MyTextViewSemibold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyTextViewSemibold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyTextViewSemibold(Context context) {
        super(context);
        init();
    }

    private void init() {

        if ( MVersion > 18 )
        {
            Typeface tf = Typeface.createFromAsset(getContext().getAssets(),  "fonts/Cairo-Regular.ttf");
            setTypeface(tf);
        }


    }
}
