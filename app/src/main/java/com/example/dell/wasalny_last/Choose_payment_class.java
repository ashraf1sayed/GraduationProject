package com.example.dell.wasalny_last;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.FirebaseDatabase;

public class Choose_payment_class extends Fragment {
    private View mMainView;
    RelativeLayout fawry;

    public Choose_payment_class() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.choose_payment, container, false);
        fawry = mMainView.findViewById(R.id.fawr_id);
        fawry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Finish fin = new Finish();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, fin)
                        .commit();
            }
        });
        return mMainView;
    }

    public void move_() {
        Details details = new Details();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, details)
                .commit();
    }

}
