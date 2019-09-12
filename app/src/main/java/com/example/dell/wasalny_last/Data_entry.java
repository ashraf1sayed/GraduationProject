package com.example.dell.wasalny_last;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

public class Data_entry extends Fragment {
    private View mMainView;
    TextView emailview;
    TextView phoneview;
    EditText seats_numView;
    Button go;
    String id;
    String key_user;
    String key_book;
    String bus_id, date, monthly_id, num_of_seats, user_id;
    DatabaseReference dref_unreg_users;
    DatabaseReference book_ref;
    DatabaseReference dref_booking;
    DatabaseReference dref_discount;
    //go bus
    ArrayList<String> allseats = new ArrayList<>();
    ArrayList<Integer> allseatsInt = new ArrayList<>();
    //return bus
    ArrayList<String> allseats_ret = new ArrayList<>();
    ArrayList<Integer> allseatsInt_ret = new ArrayList<>();
    String bus_id_ret, date_ret, monthly_id_ret, num_of_seats_ret,reservation_id_go,reservation_id_ret;

    Temp temp;
    public Data_entry() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_data_entry, container, false);
        emailview =  mMainView.findViewById(R.id.email_id);
        phoneview = (TextView) mMainView.findViewById(R.id.phone_id);
         seats_numView = (EditText) mMainView.findViewById(R.id.seats_count);
        go = mMainView.findViewById(R.id.go_btn);
        temp = new Temp();
        dref_unreg_users = FirebaseDatabase.getInstance().getReference().child("UnRegisterUser");
        dref_booking = FirebaseDatabase.getInstance().getReference().child("Booking");
        dref_discount = FirebaseDatabase.getInstance().getReference().child("Discount");

        bus_id=temp.getGo_bus_round_id();
        bus_id_ret=temp.getRet_bus_round_id();
        Log.i("ddd", "id=" +bus_id );


        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                booking();
                booking_ret();
                Ticket_rounded data = new Ticket_rounded();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, data)
                        .commit();
//                move_to_payment();
//                update_user_id_in_booking(key_user);

            }
        });
        return mMainView;
    }

    public void createUser() {
        // pr.setMessage("loading....");
        //   pr.show();

        DatabaseReference newPost = dref_unreg_users.push();
        String device_token = FirebaseInstanceId.getInstance().getToken();
        key_user = newPost.getKey();
        final String email = emailview.getText().toString().toLowerCase().trim();
        final String phone = phoneview.getText().toString().toLowerCase().trim();


        //create childs
        newPost.child("email").setValue(email);
        newPost.child("phone").setValue(phone);

        // pr.dismiss();


    }
    //get seats for the bus that go
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
    //get seats for the returned bus
    public void getSeats_ret() {

        Query query = dref_booking.orderByChild("busId").equalTo(bus_id_ret);

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));

                    String res_seats = (String) snapshot.child("numberOfSeats").getValue();
                    allseats_ret.add(res_seats);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    //random seats for go bus
    public void choosing_random_seats() {
        int r;
        String str;
        boolean state ;
        int n= Integer.parseInt(seats_numView.getText().toString().toLowerCase().trim());
        for (int i = 1; i <= n; i++) {
            state=false;
            while (state == false) {


                r = getRandomIntegerBetweenRange(1, 40);
                if (!allseatsInt.contains(r)) {
                    state = true;
                    if (i != n) {

                        num_of_seats = num_of_seats + String.valueOf(r) + "-";

                    } else {
                        num_of_seats = num_of_seats + String.valueOf(r);
                    }
                } else {
                    state=false;
                }
            }
        }

    }
    //return bus
    public void choosing_random_seats_ret() {
        int r;
        String str;
        boolean state ;
        int n= Integer.parseInt(seats_numView.getText().toString().toLowerCase().trim());
        for (int i = 1; i <= n; i++) {
            state=false;
            while (state == false) {


                r = getRandomIntegerBetweenRange(1, 40);
                if (!allseatsInt_ret.contains(r)) {
                    state = true;
                    if (i != n) {

                        num_of_seats_ret = num_of_seats_ret + String.valueOf(r) + "-";

                    } else {
                        num_of_seats_ret = num_of_seats_ret + String.valueOf(r);
                    }
                } else {
                    state=false;
                }
            }
        }

    }
//get random numbers
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
    //split the array for returned buses
    public void split_array_ret() {
        String str;
        for (int i = 0; i < allseats_ret.size(); i++) {
            str = allseats_ret.get(i);
            String[] arr = str.split("-");
            for (String w : arr) {
                allseatsInt_ret.add(Integer.valueOf(w));
            }
        }


    }
    //booking go bus
    public void booking() {
        // pr.setMessage("loading....");
        //   pr.show();

        DatabaseReference newPost = dref_booking.push();
        String device_token = FirebaseInstanceId.getInstance().getToken();
        String key = newPost.getKey();
        Log.i("fbb", "the date" + key);
        date = temp.getDigital_date_for_go();
        temp.setReservation_id_go(key);
        monthly_id = "daily";
        num_of_seats = "";
        discount(key);
        temp.setBooking_id(key);
      //  functions to choose seat
        getSeats();
        split_array();
        choosing_random_seats();


        //create childs
        newPost.child("busId").setValue(bus_id);
        newPost.child("date").setValue(date);
        newPost.child("monthlyId").setValue(monthly_id);
        newPost.child("numberOfSeats").setValue(num_of_seats);
        newPost.child("userId").setValue(key_user);

        // pr.dismiss();


    }
//booking returned bus
    public void booking_ret() {
        // pr.setMessage("loading....");
        //   pr.show();

        DatabaseReference newPost = dref_booking.push();
        String device_token = FirebaseInstanceId.getInstance().getToken();
        String key = newPost.getKey();
        Log.i("fbb", "the date" + key);
        date = temp.getDigital_date_for_ret();
        temp.setReservation_id_ret(key);

        monthly_id_ret = "daily";
        num_of_seats_ret = "";
        discount(key);
        //temp.setBooking_id(key);
        //  functions to choose seat
        getSeats_ret();
        split_array_ret();
        choosing_random_seats_ret();


        //create childs
        newPost.child("busId").setValue(bus_id_ret);
        newPost.child("date").setValue(date);
        newPost.child("monthlyId").setValue(monthly_id);
        newPost.child("numberOfSeats").setValue(num_of_seats_ret);
        newPost.child("userId").setValue(key_user);

        // pr.dismiss();


    }
    public void discount(String id)
    {
        DatabaseReference newPost = dref_discount.push();
        newPost.child("bookId").setValue(id);
        newPost.child("percentage").setValue("5%");
        newPost.child("state").setValue("unpaid");




    }
}
