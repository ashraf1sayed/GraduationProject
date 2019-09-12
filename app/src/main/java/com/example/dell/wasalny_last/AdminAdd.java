package com.example.dell.wasalny_last;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class AdminAdd extends AppCompatActivity {

    EditText txtfrom,txtto,txttime,txtprice,txtseats,txtdate;
    String strFrom,StrTo,strTime;
    Button btadd;
    ProgressDialog pr;
    ImageView imageView;
    private DatabaseReference mDatabase;

    DatabaseReference dref;

    private int mYear, mMonth, mDay, mHour, mMinute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);
        txtfrom= (EditText) findViewById(R.id.from);
        txtto= (EditText) findViewById(R.id.to);
        txttime= (EditText) findViewById(R.id.time);
        txtprice= (EditText) findViewById(R.id.price_edit);
        txtseats= (EditText) findViewById(R.id.seats_edit);
        txtdate= (EditText) findViewById(R.id.date_for_go_id);
        btadd= (Button) findViewById(R.id.add);

        dref = FirebaseDatabase.getInstance().getReference().child("BUSES");
        pr = new ProgressDialog(this);

        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddBus();
            }
        });
    }

    private void AddBus() {

        pr.setMessage("loading....");
        final String from = txtfrom.getText().toString().toLowerCase().trim();
        final String to = txtto.getText().toString().toLowerCase().trim();
        final String time = txttime.getText().toString().toLowerCase().trim();
        final String price_str = txtprice.getText().toString().toLowerCase().trim();
        final String seats_str = txtseats.getText().toString().toLowerCase().trim();
        final String date_str = txtdate.getText().toString().toLowerCase().trim();

        if (!TextUtils.isEmpty(from) && !TextUtils.isEmpty(to)  && !TextUtils.isEmpty(time)&& !TextUtils.isEmpty(price_str)&& !TextUtils.isEmpty(seats_str)) {

            pr.show();

            DatabaseReference newPost = dref.push();
            mDatabase = FirebaseDatabase.getInstance().getReference().child("users");
            String device_token = FirebaseInstanceId.getInstance().getToken();


            newPost.child("source").setValue(from);
            newPost.child("destination").setValue(to);
            newPost.child("buse_number").setValue("#22222");
            newPost.child("date_for_go").setValue(date_str);
            newPost.child("seats").setValue(seats_str);
            newPost.child("Driver_name").setValue("null");
            newPost.child("duration").setValue("ساعتان");
            newPost.child("time_for_go").setValue(time+" مساءا");
            newPost.child("price").setValue(price_str);
            pr.dismiss();
        }
        else{
                Toast.makeText(AdminAdd.this, "هتعبك املا كل البيانات المطلوبة", Toast.LENGTH_LONG).show();
                pr.dismiss();

            }


    }
}
