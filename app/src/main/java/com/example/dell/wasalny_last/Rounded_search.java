package com.example.dell.wasalny_last;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DELL on 3/26/2019.
 */

public class Rounded_search extends Fragment {
    private ProgressDialog pr;
    private TextView tvFrom, tvTo, tvDateGo, tvDateRet;
    ImageView img;
    private View mMainView;
    DatabaseReference dref;
    private RecyclerView rec, rec2;
    ArrayList<Object_Class> objects = new ArrayList<>();
    ArrayList<Object_Class> objectsR = new ArrayList<>();

    AdapterRounded adapterR;
    String go_date, return_date = "", textfrom, textto;
    Temp temp;
    Boolean color = false;
    int count = 0;
    int count2 = 0;
    String selected_go_bus, selected_ret_bus;


    public Rounded_search() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.activity_rounded_search, container, false);
        temp = new Temp();
        tvFrom = mMainView.findViewById(R.id.from_id_bar_R);
        tvTo = mMainView.findViewById(R.id.to_id_bar_R);
        tvDateGo = mMainView.findViewById(R.id.date_id_bar_r);
        img = mMainView.findViewById(R.id.book_img_id);
        pr = new ProgressDialog(getContext());
        pr.getWindow().setGravity(Gravity.CENTER);
        temp.keys_go_list.clear();
        temp.keys_ret_list.clear();

        dref = FirebaseDatabase.getInstance().getReference().child("BusSchedule");


        Bundle bundle = this.getArguments();

        if (bundle != null) {
            go_date = bundle.getString("go_date", ""); // Key, default value
            return_date = bundle.getString("return_date", "");
            textfrom = bundle.getString("textfrom", "");
            textto = bundle.getString("textto", "");

            tvFrom.setText(textfrom);
            tvTo.setText(textto);
            tvDateGo.setText(return_date + " / " + go_date);
//            Log.i("df", "text from" + temp.getDigital_date_for_ret());
//            Log.i("t", textto);

        }
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (temp.getGo_bus_round_id() != null && temp.getRet_bus_round_id() != null) {
                    Data_entry data = new Data_entry();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, data)
                            .commit();
                } else

                {
                    Toast.makeText(getContext(), "يجب اختيار اتوبس الذهاب والعودة", Toast.LENGTH_SHORT).show();

                }
            }

        });

        rec = (RecyclerView) mMainView.findViewById(R.id.mainDataGo);
        rec2 = (RecyclerView) mMainView.findViewById(R.id.mainDataRet);

        rec.setLayoutManager(new LinearLayoutManager(getContext()));
        rec.setItemViewCacheSize(9);
        rec.setHasFixedSize(true);

        rec2.setLayoutManager(new LinearLayoutManager(getContext()));
        rec2.setItemViewCacheSize(9);
        rec2.setHasFixedSize(true);


        pr.setTitle("Loading your buses...");
        pr.setMessage("Please wait....");
        pr.setCanceledOnTouchOutside(false);
        pr.show();
        searchRounded();
        searchOneWay();
        Log.i("id", "id=" + temp.keys);
        Log.i("fff", "count=" + count);

        //handle on click
        rec.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), rec, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    count = 1;
                                    Log.i("cid", "id=" + selected_go_bus);

                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                selected_go_bus = "";
                                temp.setGo_bus_round_id(null);

                                Log.i("cid2", "id=" + selected_go_bus);
                                count = 0;
                                return true;

                            }
                        });
                        Log.i("cid2", "id=" + selected_go_bus);

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        rec2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), rec2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count2 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.setRet_bus_round_id(temp.keys_ret_list.get(position));
                                    count2 = 1;
                                    Log.i("cid", "id=" + selected_go_bus);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                selected_go_bus = "";
                                temp.setRet_bus_round_id(null);

                                Log.i("cid2", "id=" + selected_go_bus);
                                count2 = 0;
                                return true;

                            }
                        });
                        Log.i("cid2", "id=" + selected_go_bus);

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        Log.e("out", "val" + count);
        return mMainView;
    }

    public void searchOneWay() {
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
                    String locationName = (String) snapshot.child("destination").getValue();
                    //   Toast.makeText(Buses.this,snapshot.child("fromch").toString()+"kkkjj" ,Toast.LENGTH_SHORT).show();
                    String dateValue = (String) snapshot.child("day").getValue();

                    if (locationName.equals(textto) && dateValue.equals(go_date)) {
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
                        temp.keys_go_list.add(k);
                        Log.i("ffg", "id= " + temp.keys_go_list);

                        Object_Class model = new Object_Class(from, to, bus_num, dat_go, seatss_, driver_name, duration, time, price);
                        objects.add(model);


                    }


                }


                if (objects.size() != 0) {

                    rec.setVisibility(View.VISIBLE);
                    adapterR = new AdapterRounded(objects, getContext());

                    rec.setAdapter(adapterR);

                } else {
                    rec.setVisibility(View.GONE);
                    //searchResult.setVisibility(View.VISIBLE);

                    // show no one
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void searchRounded() {
        Intent intent = getActivity().getIntent();
        // ID = intent.getStringExtra("ID");
        String fromt = intent.getStringExtra("textfrom");

        Query query = dref.orderByChild("source").equalTo(textto);
        //   Toast.makeText(Buses.this,query.toString()+"jk" ,Toast.LENGTH_SHORT).show();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));
                    String locationName = (String) snapshot.child("destination").getValue();
                    //   Toast.makeText(Buses.this,snapshot.child("fromch").toString()+"kkkjj" ,Toast.LENGTH_SHORT).show();
                    String dateValue = (String) snapshot.child("day").getValue();

                    if (locationName.equals(textfrom) && dateValue.equals(return_date)) {
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
                        temp.keys_ret_list.add(k);

                        pr.dismiss();
                        Log.i("rr", "text to  " + to);

                        Object_Class modelR = new Object_Class(from, to, bus_num, dat_go, seatss_, driver_name, duration, time, price);
                        objectsR.add(modelR);

                    }


                }


                if (objectsR.size() != 0) {

                    rec2.setVisibility(View.VISIBLE);
                    adapterR = new AdapterRounded(objectsR, getContext());
                    rec2.setAdapter(adapterR);

                } else {
                    rec.setVisibility(View.GONE);
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
