package com.example.dell.wasalny_last;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class Details extends Fragment {

    private View mMainView;
    TextView emailview;
    TextView phoneview;
    Button go;
    String id;
    String key_user;
    String key_book;
    DatabaseReference dref_unreg_users;
    DatabaseReference book_ref;
    Temp temp;
    Ticket tic = new Ticket();

    public Details() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_details, container, false);
        emailview = (TextView) mMainView.findViewById(R.id.email_id);
        phoneview = (TextView) mMainView.findViewById(R.id.phone_id);
        go = mMainView.findViewById(R.id.go_btn);
        temp = new Temp();
        key_book=temp.getBooking_id();
        Log.i("fbf", "the date" + key_book);
        dref_unreg_users = FirebaseDatabase.getInstance().getReference().child("UnRegisterUser");
        book_ref = FirebaseDatabase.getInstance().getReference().child("Booking").child(key_book);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
                move_to_payment();
                update_user_id_in_booking(key_user);

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

    public void update_user_id_in_booking(String s) {

        book_ref.child("userId").setValue(s);
    }

    public void move_to_payment()

    {
        Choose_payment_class pay = new Choose_payment_class();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, pay)
                .commit();
    }
}
