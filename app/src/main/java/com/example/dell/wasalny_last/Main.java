package com.example.dell.wasalny_last;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static com.example.dell.wasalny_last.R.menu.navigation;

public class Main extends AppCompatActivity {

    private TextView mTextMessage;
    FrameLayout frame;
    Daily_booking dailyBooking_obj;
    Monthly_booking_fragment monthlyBooking_obj;
    One_way_search oneWay_obj;
    Rounded_search rounded_obj;
    Login loginObj;
    Reg regObj;
Profile pr_obj;
    private FirebaseAuth mAuth;
    private DatabaseReference mUserRef;
    private DatabaseReference mUserRef2;


    //bottom navigation listner
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Setfragment(dailyBooking_obj);
                    return true;
                case R.id.navigation_dashboard:
                    checkLogin();
                    return true;
                case R.id.navigation_notifications:
                    Setfragment(pr_obj);

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        frame = (FrameLayout) findViewById(R.id.content);
        dailyBooking_obj = new Daily_booking();
        pr_obj=new Profile();
        monthlyBooking_obj = new Monthly_booking_fragment();
        oneWay_obj = new One_way_search();
        rounded_obj = new Rounded_search();
        loginObj = new Login();
        regObj = new Reg();
        Setfragment(dailyBooking_obj);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {

            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());
            Log.i("usf", "user=" + mUserRef);


        }
    }

    //moving function between fragments
    private void Setfragment(Fragment fragment) {
        FragmentTransaction fragmenttransaction = getSupportFragmentManager().beginTransaction();
        fragmenttransaction.replace(R.id.content, fragment);
        fragmenttransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Setfragment(dailyBooking_obj);
        // navigation.getMenu().getItem(0).setChecked(true);

    }

    public void checkLogin() {
        FirebaseUser currentUser = mAuth.getCurrentUser();


        if (currentUser == null) {
           //  Log.i("use", "user=" + currentUser.getDisplayName());

            Setfragment(loginObj);
        } else {
            Log.i("us", "user=" + currentUser.getDisplayName());

                Setfragment(monthlyBooking_obj);


        }
    }

}
