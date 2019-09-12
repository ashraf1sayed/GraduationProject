package com.example.dell.wasalny_last;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Monthly_details extends Fragment {
    private View mMainView;
    TextView t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14,fromtv,totv,datetv;
    Temp temp;
    LinearLayout btnGo;

    public Monthly_details() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.activity_monthly_details, container, false);
        temp = new Temp();

        btnGo = mMainView.findViewById(R.id.btn_go_pay_id);
        fromtv = mMainView.findViewById(R.id.from_id);
        totv = mMainView.findViewById(R.id.to_id);
        datetv = mMainView.findViewById(R.id.start_date);

        fromtv.setText(temp.getSource());
        totv.setText(temp.getDestination());
        datetv.setText(temp.getDigital_date_mon());

        t1 = mMainView.findViewById(R.id.s1);
        t2 = mMainView.findViewById(R.id.s2);

        t3 = mMainView.findViewById(R.id.h1);
        t4 = mMainView.findViewById(R.id.h2);
        t5 = mMainView.findViewById(R.id.a1);
        t6 = mMainView.findViewById(R.id.a2);
        t7 = mMainView.findViewById(R.id.tt1);
        t8 = mMainView.findViewById(R.id.tt2);
        t9 = mMainView.findViewById(R.id.ar1);
        t10 = mMainView.findViewById(R.id.ar2);
        t11 = mMainView.findViewById(R.id.k1);
        t12 = mMainView.findViewById(R.id.k2);
        t13 = mMainView.findViewById(R.id.g1);
        t14 = mMainView.findViewById(R.id.g2);
        t1.setText(temp.getTime_s1());
        t1.setText(temp.getTime_s1());
        t2.setText(temp.getTime_s2());
        t3.setText(temp.getTime_h1());
        t4.setText(temp.getTime_h2());
        t5.setText(temp.getTime_a1());
        t6.setText(temp.getTime_a2());
        t7.setText(temp.getTime_t1());
        t8.setText(temp.getTime_t2());
        t9.setText(temp.getTime_ar1());
        t10.setText(temp.getTime_ar2());
        t11.setText(temp.getTime_k1());
        t12.setText(temp.getTime_k2());
        t13.setText(temp.getTime_g1());
        t14.setText(temp.getTime_g2());
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose_payment_class details = new Choose_payment_class();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, details)
                        .commit();
            }
        });

        return mMainView;

    }
}
