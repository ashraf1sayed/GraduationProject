package design ;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by mali on 12/7/2016.
 */
public class MyEditText extends EditText {

    public int MVersion =  Build.VERSION.SDK_INT;


    public MyEditText(Context context) {
        super(context);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
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

    @Override
    public void setOnClickListener(OnClickListener l) {
        super.setOnClickListener(l);
    }

}
