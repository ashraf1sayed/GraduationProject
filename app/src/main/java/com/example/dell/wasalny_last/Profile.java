package com.example.dell.wasalny_last;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profile extends Fragment {

    private View mMainView;
    LinearLayout monthlyDetils, logout, login, unlogin;
    private FirebaseAuth mAuth;
    TextView tvLogin;
    Temp temp;

    public Profile() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_profile, container, false);
        monthlyDetils = mMainView.findViewById(R.id.month_id);
        logout = mMainView.findViewById(R.id.logout_id);
        login = mMainView.findViewById(R.id.logined_id);
        unlogin = mMainView.findViewById(R.id.not_logined_id);
        tvLogin = mMainView.findViewById(R.id.login_prof_id);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        temp = new Temp();

        if (currentUser == null) {
            //  Log.i("use", "user=" + currentUser.getDisplayName());
            unlogin.setVisibility(View.VISIBLE);
            login.setVisibility(View.GONE);


        }
//        monthlyDetils.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Monthly_details details = new Monthly_details();
//                getFragmentManager()
//                        .beginTransaction()
//                        .replace(R.id.content, details)
//                        .commit();
//            }
//        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();

                Daily_booking details = new Daily_booking();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, details)
                        .commit();

            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp.setProf_mark(1);
                Login details = new Login();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, details)
                        .commit();

            }
        });
        return mMainView;

    }
}
