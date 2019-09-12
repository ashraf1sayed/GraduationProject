package com.example.dell.wasalny_last;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ticket_monthly extends Fragment {
    private View mMainView;

    public ticket_monthly() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_ticket_monthly, container, false);

        return mMainView;

    }
}
