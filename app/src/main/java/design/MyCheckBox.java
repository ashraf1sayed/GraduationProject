package design;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by work on 06/09/2017.
 */

public class MyCheckBox extends CheckBox {
    public int MVersion =  Build.VERSION.SDK_INT;


    public MyCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCheckBox(Context context) {
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
