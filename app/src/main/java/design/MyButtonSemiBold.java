package design;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by mali on 12/7/2016.
 */
public class MyButtonSemiBold extends Button {
    public int MVersion =  Build.VERSION.SDK_INT;

    public MyButtonSemiBold(Context context) {
        super(context);
        init();
    }

    public MyButtonSemiBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyButtonSemiBold(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyButtonSemiBold(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr);
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
