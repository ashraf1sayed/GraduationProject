package com.example.dell.wasalny_last;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

/**
 * Created by DELL on 12/5/2018.
 */

public class Ticket extends Fragment {

    private View mMainView;

    private DatabaseReference mDatabase;

    TextView tvfrom;
    TextView tvto;
    TextView tvprice;
    TextView tvdate;
    Button confirm;
    DatabaseReference dref;
    DatabaseReference dref_booking;
    ArrayList<String> allseats = new ArrayList<>();
    ArrayList<Integer> allseatsInt = new ArrayList<>();

    Object_Class model;
    String bund_price, bund_from, bund_to, bund_day;
    String bus_id, date, monthly_id, num_of_seats, user_id;
    int position;
    Temp temp;
    DatabaseReference dref_discount;

    public Ticket() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainView = inflater.inflate(R.layout.ticket, container, false);
        tvfrom = (TextView) mMainView.findViewById(R.id.from_t);
        tvto = (TextView) mMainView.findViewById(R.id.to_t);
        tvprice = (TextView) mMainView.findViewById(R.id.price_view);
        tvdate = (TextView) mMainView.findViewById(R.id.date_t);
        confirm = (Button) mMainView.findViewById(R.id.confirm_button);
        dref = FirebaseDatabase.getInstance().getReference().child("BUSES");
        dref_discount = FirebaseDatabase.getInstance().getReference().child("Discount");

        model = new Object_Class();
        temp = new Temp();


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            bund_price = bundle.getString("price", ""); // Key, default value
            bund_from = bundle.getString("from", "");
            bund_to = bundle.getString("to", "");
            bund_day = bundle.getString("day", "");
            position = bundle.getInt("pos", 0);

            //set values to the views
            tvprice.setText(bund_price.toString());
            tvfrom.setText(bund_from.toString());
            tvto.setText(bund_to.toString());
            tvdate.setText(bund_day);
            //   bus_id=temp.getKeys().get(position);

             Log.i("fbg", "the date" + bund_from);


        }


       // dref = FirebaseDatabase.getInstance().getReference().child("BUSES");
        // pr = new ProgressDialog(this);
        dref_booking = FirebaseDatabase.getInstance().getReference().child("Booking");

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking();
                move();
            }
        });
        return mMainView;

    }

    public void move()

    {
        Details details = new Details();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, details)
                .commit();
    }

    public void getSeats() {


        Query query = dref_booking.orderByChild("busId").equalTo(bus_id);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));

                    String res_seats = (String) snapshot.child("numberOfSeats").getValue();

                    allseats.add(res_seats);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void choosing_random_seats() {
        int r;
        String str;
        boolean state;
        for (int i = 1; i <= 2; i++) {
            state = false;
            while (state == false) {


                r = getRandomIntegerBetweenRange(1, 40);
                if (!allseatsInt.contains(r)) {
                    state = true;
                    if (i != 2) {

                        num_of_seats = num_of_seats + String.valueOf(r) + "-";

                    } else {
                        num_of_seats = num_of_seats + String.valueOf(r);
                    }
                } else {
                    state = false;
                }
            }
        }

    }

    public static int getRandomIntegerBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    public void split_array() {
        String str;
        for (int i = 0; i < allseats.size(); i++) {
            str = allseats.get(i);
            String[] arr = str.split("-");
            for (String w : arr) {
                allseatsInt.add(Integer.valueOf(w));
            }
        }


    }

    public void booking() {
        // pr.setMessage("loading....");
        //   pr.show();

        DatabaseReference newPost = dref_booking.push();
        String device_token = FirebaseInstanceId.getInstance().getToken();
        String key = newPost.getKey();
        date = temp.getDigital_date_for_go();


        bus_id = temp.daily_booking.get(position);

        monthly_id = "daily";
        num_of_seats = "";
        temp.setReservation_id_go(key);
        temp.setBooking_id(key);
        //functions to choose seat
        getSeats();
        split_array();
        choosing_random_seats();
        discount(key);
        Log.i("fbbn", "the date" + bus_id);

        //create childs
        newPost.child("busId").setValue(bus_id);
        newPost.child("date").setValue(date);
        newPost.child("monthlyId").setValue(monthly_id);
        newPost.child("numberOfSeats").setValue(num_of_seats);
        newPost.child("userId").setValue("omda");

        // pr.dismiss();


    }

    public void discount(String id) {
        DatabaseReference newPost = dref_discount.push();
        newPost.child("bookId").setValue(id);
        newPost.child("percentage").setValue("5%");
        newPost.child("state").setValue("unpaid");


    }
}


