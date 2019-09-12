package com.example.dell.wasalny_last;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class One_way_search extends Fragment {
    private ProgressDialog pr;
    private View mMainView;
    TextView tvFrom, tvTo, tvDate;
    DatabaseReference dref;
    private RecyclerView rec;
    ArrayList<Object_Class> objects = new ArrayList<>();
    AdapterItems adapter;
    String go_date, return_date = "", textfrom, textto;
    Temp temp;

    public One_way_search() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_one_way_search, container, false);
        tvFrom = mMainView.findViewById(R.id.from_id_bar);
        tvTo = mMainView.findViewById(R.id.to_id_bar);
        tvDate = mMainView.findViewById(R.id.date_bar);
        temp.daily_booking.clear();
        pr = new ProgressDialog(getContext());
        pr.getWindow().setGravity(Gravity.CENTER);
        temp = new Temp();
        dref = FirebaseDatabase.getInstance().getReference().child("BusSchedule");

        Bundle bundle = this.getArguments();

        if (bundle != null) {
            go_date = bundle.getString("go_date", ""); // Key, default value
            Log.i("ffb", "the date" + go_date);
            return_date = bundle.getString("return_date", "");
            textfrom = bundle.getString("textfrom", "");
            textto = bundle.getString("textto", "");
            tvFrom.setText(textfrom);
            tvTo.setText(textto);
            tvDate.setText(go_date);
            Log.i("f", "the date" + go_date);
            Log.i("t", textto);


        }
        rec = (RecyclerView) mMainView.findViewById(R.id.mainData);
        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setItemViewCacheSize(9);
        rec.setHasFixedSize(true);
        pr.setTitle("Loading your buses...");
        pr.setMessage("Please wait....");
        pr.setCanceledOnTouchOutside(false);
        pr.show();
        search();
        Log.i("idt", "id=" + temp.keys);


        return mMainView;
    }


    public void search() {
        Intent intent = getActivity().getIntent();
        // ID = intent.getStringExtra("ID");
        String fromt = intent.getStringExtra("textfrom");

        Query query = dref.orderByChild("source").equalTo(textfrom);
        //   Toast.makeText(Buses.this,query.toString()+"jk" ,Toast.LENGTH_SHORT).show();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));
                    String destinationValue = (String) snapshot.child("destination").getValue();
                    String dateValue = (String) snapshot.child("day").getValue();

                    if (destinationValue.equals(textto) && dateValue.equals(go_date)) {
                        String k = snapshot.getKey();

                        String from = (String) snapshot.child("source").getValue();
                        String to = (String) snapshot.child("destination").getValue();
                        String bus_num = (String) snapshot.child("buse_number").getValue();
                        String dat_go = (String) snapshot.child("day").getValue();
                        String seatss_ = (String) snapshot.child("seats").getValue();
                        String driver_name = (String) snapshot.child("Driver_name").getValue();
                        String duration = (String) snapshot.child("duration").getValue();
                        String time = (String) snapshot.child("time_for_go").getValue();
                        String price = (String) snapshot.child("price").getValue();
                        pr.dismiss();
                        temp.add(k);
                        temp.daily_booking.add(k);
                        Log.i("fff", "text to  " + temp.getKeys());
                        Object_Class model = new Object_Class(from, to, bus_num, dat_go, seatss_, driver_name, duration, time, price);
                        objects.add(model);

                    }


                }


                if (objects.size() != 0) {

                    rec.setVisibility(View.VISIBLE);
                    adapter = new AdapterItems(objects, getContext());
                    rec.setAdapter(adapter);

                } else {
                    rec.setVisibility(View.GONE);
                    pr.dismiss();

                    //searchResult.setVisibility(View.VISIBLE);

                    // show no one
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
