package com.example.dell.wasalny_last;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Finish extends Fragment implements Finisht {

    private View mMainView;
    TextView go_id, ret_id;
    Temp temp;

    public Finish() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_finish, container, false);
        go_id = (TextView) mMainView.findViewById(R.id.go_res_id);
        ret_id = (TextView) mMainView.findViewById(R.id.ret_res_id);
        temp = new Temp();
        go_id.setText(temp.getReservation_id_go());
        ret_id.setText(temp.getReservation_id_ret());


        return mMainView;
    }
    @Override
    public boolean onBackPressed() {
        Daily_booking details = new Daily_booking();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, details)
                .commit();
        return true;
    }
}