package com.example.dell.wasalny_last;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.function.Function;

/**
 * Created by DELL on 3/12/2019.
 */

public class Monthly_booking_fragment extends Fragment {
    private View mMainView;
    private DatabaseReference mUserRef;
    DatabaseReference dref;
    AdapterMonthly adapterR;
    String date;
    private FirebaseAuth mAuth;
    TextView tvdate;
    LinearLayout dateLay;
    LinearLayout searchbtn, bookMonthlybtn;
    ScrollView sc;

    private RecyclerView recS1, recS2, recH1, recH2, recA1, recA2, recT1, recT2, recAr1, recAr2, recK1, recK2, recG1, recG2;
    ArrayList<Object_Class> objects1 = new ArrayList<>();
    ArrayList<Object_Class> objects2 = new ArrayList<>();
    ArrayList<Object_Class> objects3 = new ArrayList<>();
    ArrayList<Object_Class> objects4 = new ArrayList<>();
    ArrayList<Object_Class> objects5 = new ArrayList<>();
    ArrayList<Object_Class> objects6 = new ArrayList<>();
    ArrayList<Object_Class> objects7 = new ArrayList<>();
    ArrayList<Object_Class> objects8 = new ArrayList<>();
    ArrayList<Object_Class> objects9 = new ArrayList<>();
    ArrayList<Object_Class> objects10 = new ArrayList<>();
    ArrayList<Object_Class> objects11 = new ArrayList<>();
    ArrayList<Object_Class> objects12 = new ArrayList<>();
    ArrayList<Object_Class> objects13 = new ArrayList<>();
    ArrayList<Object_Class> objects14 = new ArrayList<>();

    Spinner sourceSp, destSp;
    String sourceTxt, destText;
    DatabaseReference dref_booking;
    DatabaseReference dref_monthly_booking, dref_discount;
    ArrayList<String> allseats = new ArrayList<>();
    ArrayList<Integer> allseatsInt = new ArrayList<>();
    String key;
    Temp temp;
    int pos;
    String uid;
    int count = 0, count2 = 0, count3 = 0, count4 = 0, count5 = 0, count6 = 0, count7 = 0, count8 = 0, count9 = 0, count10 = 0, count11 = 0, count12 = 0, count13 = 0, count14 = 0;
    private ProgressDialog mRegProgress;
    private ProgressDialog mRegProgresss;
    private ProgressDialog mRegProgressm;

    public Monthly_booking_fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.monthly_booking, container, false);
        mRegProgress = new ProgressDialog(getContext());
        mRegProgresss = new ProgressDialog(getContext());
        mRegProgressm = new ProgressDialog(getContext());
        temp.dates.clear();

        temp.monthly_booked_ids.clear();
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null) {

            mUserRef = FirebaseDatabase.getInstance().getReference().child("Users").child(mAuth.getCurrentUser().getUid());

        }
        dref_discount = FirebaseDatabase.getInstance().getReference().child("Discount");

        uid = mAuth.getCurrentUser().getUid();
        temp = new Temp();
        searchbtn = mMainView.findViewById(R.id.search);
        dateLay = mMainView.findViewById(R.id.dateDisplay);
        sc = mMainView.findViewById(R.id.scrol_id);

        bookMonthlybtn = mMainView.findViewById(R.id.btn_monthly_id);
        tvdate = mMainView.findViewById(R.id.date_id_);
        searchbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                objects1.clear();
                objects2.clear();
                objects3.clear();
                objects4.clear();
                objects5.clear();
                objects6.clear();
                objects7.clear();
                objects8.clear();
                objects9.clear();
                objects10.clear();
                objects11.clear();
                objects12.clear();
                objects13.clear();
                objects14.clear();


                temp.ids_s1.clear();
                temp.ids_s2.clear();

                temp.ids_h1.clear();

                temp.ids_h2.clear();

                temp.ids_a1.clear();

                temp.ids_a2.clear();

                temp.ids_t1.clear();

                temp.ids_t2.clear();

                temp.ids_ar1.clear();

                temp.ids_ar2.clear();

                temp.ids_k1.clear();

                temp.ids_k2.clear();

                temp.ids_g1.clear();

                temp.ids_g2.clear();


                sc.setVisibility(View.VISIBLE);
                searchOneWayMonthly("السبت", recS1, objects1, temp.ids_s1);
                searchOneWayMonthly_ret("السبت", recS2, objects2, temp.ids_s2);
                searchOneWayMonthly("الأحد", recH1, objects3, temp.ids_h1);
                searchOneWayMonthly_ret("الأحد", recH2, objects4, temp.ids_h2);
                searchOneWayMonthly("الاثنين", recA1, objects5, temp.ids_a1);
                searchOneWayMonthly_ret("الاثنين", recA2, objects6, temp.ids_a2);
                searchOneWayMonthly("الثلاثاء", recT1, objects7, temp.ids_t1);
                searchOneWayMonthly_ret("الثلاثاء", recT2, objects8, temp.ids_t2);
                searchOneWayMonthly("الأربعاء", recAr1, objects9, temp.ids_ar1);
                searchOneWayMonthly_ret("الأربعاء", recAr2, objects10, temp.ids_ar2);
                searchOneWayMonthly("الخميس", recK1, objects11, temp.ids_k1);
                searchOneWayMonthly_ret("الخميس", recK2, objects12, temp.ids_k2);
                searchOneWayMonthly("الجمعة", recG1, objects13, temp.ids_g1);
                searchOneWayMonthly_ret("الجمعة", recG2, objects14, temp.ids_g2);


            }
        });
        //spinners
        sourceSp = mMainView.findViewById(R.id.spinner_from_m);
        destSp = mMainView.findViewById(R.id.spinner_to_m);
        //get data of spinners

        Log.i("fffggg", "id= " + sourceTxt);

        dref = FirebaseDatabase.getInstance().getReference().child("BusSchedule");
        dref_booking = FirebaseDatabase.getInstance().getReference().child("Booking");
        dref_monthly_booking = FirebaseDatabase.getInstance().getReference().child("MonthlyBooking");
        //list one
        recS1 = (RecyclerView) mMainView.findViewById(R.id.r1);
        recS1.setLayoutManager(new LinearLayoutManager(getContext()));
        recS1.setItemViewCacheSize(9);
        recS1.setHasFixedSize(true);

        recS1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recS1, new RecyclerItemClickListenerb.OnItemClickListener() {
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
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_s1.get(position));
                                    //  temp.setTime_s1();
                                    //   gettime(temp.ids_s1.get(position),temp.setTime_s1());
                                    pos = position;
                                    DatabaseReference gata = dref.child(temp.ids_s1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_s1(time);
                                            Log.i("cid22", "id=" + temp.getTime_s1());

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count = 1;
                                    Log.i("cid", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_s1.get(position));

                                Log.i("vvvvvvv", "id=" + temp.getTime_s1());
                                count = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //----------------------
        recS2 = (RecyclerView) mMainView.findViewById(R.id.r2);
        recS2.setLayoutManager(new LinearLayoutManager(getContext()));
        recS2.setItemViewCacheSize(9);
        recS2.setHasFixedSize(true);


        recS2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recS2, new RecyclerItemClickListenerb.OnItemClickListener() {
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
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_s2.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_s2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_s2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count2 = 1;
                                    pos = position;

                                    Log.i("pos2", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_s2.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);
                                count2 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //--------------------------------------
        recH1 = (RecyclerView) mMainView.findViewById(R.id.r3);
        recH1.setLayoutManager(new LinearLayoutManager(getContext()));
        recH1.setItemViewCacheSize(9);
        recH1.setHasFixedSize(true);

        recH1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recH1, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count3 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_h1.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_h1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_h1(time);
                                            Log.i("cid22", "id=" + temp.getTime_h1());

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count3 = 1;
                                    // Log.i("cid", "id=" +  temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_h1.get(position));

                                count3 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //---------------------------------------------
        recH2 = (RecyclerView) mMainView.findViewById(R.id.r4);
        recH2.setLayoutManager(new LinearLayoutManager(getContext()));
        recH2.setItemViewCacheSize(9);
        recH2.setHasFixedSize(true);

        recH2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recH2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count4 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_h2.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_h2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_h2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count4 = 1;
                                    //Log.i("cid", "id=" +  temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_h2.get(position));

                                //  Log.i("cid2", "id=" +  temp.monthly_booked_ids);
                                count4 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //--------------------------------------------
        recA1 = (RecyclerView) mMainView.findViewById(R.id.r5);
        recA1.setLayoutManager(new LinearLayoutManager(getContext()));
        recA1.setItemViewCacheSize(9);
        recA1.setHasFixedSize(true);

        recA1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recA1, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count5 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_a1.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_a1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_a1(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count5 = 1;
                                    // Log.i("cid", "id=" +  temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_a1.get(position));

                                //    Log.i("cid2", "id=" +  temp.monthly_booked_ids);
                                count5 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //-----------------------------------
        recA2 = (RecyclerView) mMainView.findViewById(R.id.r6);
        recA2.setLayoutManager(new LinearLayoutManager(getContext()));
        recA2.setItemViewCacheSize(9);
        recA2.setHasFixedSize(true);
        recA2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recA2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count6 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_a2.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_a2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_a2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count6 = 1;
                                    // Log.i("cid", "id=" +  temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_a2.get(position));

                                //  Log.i("cid2", "id=" +  temp.monthly_booked_ids);
                                count6 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //----------------------------------------
        recT1 = (RecyclerView) mMainView.findViewById(R.id.r7);
        recT1.setLayoutManager(new LinearLayoutManager(getContext()));
        recT1.setItemViewCacheSize(9);
        recT1.setHasFixedSize(true);

        recT1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recT1, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count7 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_t1.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_t1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_t1(time);
                                            Log.i("ttt", "id=" + temp.getTime_t1());

                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count7 = 1;
                                    Log.i("cid", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_t1.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);

                                count7 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //----------------------------------------
        recT2 = (RecyclerView) mMainView.findViewById(R.id.r8);
        recT2.setLayoutManager(new LinearLayoutManager(getContext()));
        recT2.setItemViewCacheSize(9);
        recT2.setHasFixedSize(true);
        recT2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recT2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count8 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_t2.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_t2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_t2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count8 = 1;
                                    // Log.i("cid", "id=" +  temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_t2.get(position));

                                // Log.i("cid2", "id=" +  temp.monthly_booked_ids);
                                count8 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //---------------------------------------------
        recAr1 = (RecyclerView) mMainView.findViewById(R.id.r9);
        recAr1.setLayoutManager(new LinearLayoutManager(getContext()));
        recAr1.setItemViewCacheSize(9);
        recAr1.setHasFixedSize(true);
        recAr1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recAr1, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count9 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_ar1.get(position));

                                    DatabaseReference gata = dref.child(temp.ids_ar1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_ar1(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });
                                    count9 = 1;
                                    //    Log.i("cid", "id=" +  temp.monthly_booked_ids);

                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_ar1.get(position));

                                //  Log.i("cid2", "id=" +  temp.monthly_booked_ids);
                                count9 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //-------------------------------------------
        recAr2 = (RecyclerView) mMainView.findViewById(R.id.r10);
        recAr2.setLayoutManager(new LinearLayoutManager(getContext()));
        recAr2.setItemViewCacheSize(9);
        recAr2.setHasFixedSize(true);
        recAr2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recAr2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count10 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_ar2.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_ar2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_ar2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count10 = 1;
                                    // Log.i("cid", "id=" +  temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_ar2.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);
                                count10 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //----------------------------------------------
        recK1 = (RecyclerView) mMainView.findViewById(R.id.r11);
        recK1.setLayoutManager(new LinearLayoutManager(getContext()));
        recK1.setItemViewCacheSize(9);
        recK1.setHasFixedSize(true);
        recK1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recK1, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count11 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_k1.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_k1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_k1(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count11 = 1;
                                    Log.i("cid", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_k1.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);
                                count11 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //-----------------------------------------------
        recK2 = (RecyclerView) mMainView.findViewById(R.id.r12);
        recK2.setLayoutManager(new LinearLayoutManager(getContext()));
        recK2.setItemViewCacheSize(9);
        recK2.setHasFixedSize(true);
        recK2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recK2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count12 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_k2.get(position));

                                    DatabaseReference gata = dref.child(temp.ids_k2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_k2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count12 = 1;
                                    Log.i("cid", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_k2.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);
                                count12 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //-------------------------------------------------
        recG1 = (RecyclerView) mMainView.findViewById(R.id.r13);
        recG1.setLayoutManager(new LinearLayoutManager(getContext()));
        recG1.setItemViewCacheSize(9);
        recG1.setHasFixedSize(true);
        recG1.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recG1, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count13 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_g1.get(position));


                                    DatabaseReference gata = dref.child(temp.ids_g1.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_g1(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count13 = 1;
                                    Log.i("cid", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_g1.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);
                                count13 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );


        //-------------------------------------------------------
        recG2 = (RecyclerView) mMainView.findViewById(R.id.r14);
        recG2.setLayoutManager(new LinearLayoutManager(getContext()));
        recG2.setItemViewCacheSize(9);
        recG2.setHasFixedSize(true);
        recG2.addOnItemTouchListener(
                new RecyclerItemClickListenerb(getContext(), recG2, new RecyclerItemClickListenerb.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {
                        final Button select = view.findViewById(R.id.boolking);
                        final Button cancel = view.findViewById(R.id.cancel_booking_id);
                        final RelativeLayout line = view.findViewById(R.id.boolking_lay);


                        select.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                if (count14 == 0) {
                                    select.setVisibility(View.GONE);
                                    cancel.setVisibility(View.VISIBLE);
                                    //  temp.setGo_bus_round_id(temp.keys_go_list.get(position));
                                    temp.monthly_booked_ids.add(temp.ids_g2.get(position));

                                    DatabaseReference gata = dref.child(temp.ids_g2.get(position));
                                    gata.addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(DataSnapshot dataSnapshot) {

                                            String time = (String) dataSnapshot.child("time_for_go").getValue();
                                            temp.setTime_g2(time);
                                        }

                                        @Override
                                        public void onCancelled(DatabaseError databaseError) {

                                        }
                                    });


                                    count14 = 1;
                                    Log.i("cid", "id=" + temp.monthly_booked_ids);


                                }

                                return true;

                            }
                        });
                        cancel.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {


                                cancel.setVisibility(View.GONE);
                                select.setVisibility(View.VISIBLE);
                                temp.monthly_booked_ids.remove(temp.ids_g2.get(position));

                                Log.i("cid2", "id=" + temp.monthly_booked_ids);
                                count14 = 0;
                                return true;

                            }
                        });

                    }


                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        //---------------------------------

        //-----------------------------------------
        bookMonthlybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("vvv", "id=" + temp.monthly_booked_ids.get(1));
                if (temp.monthly_booked_ids.size() == 14&&date!=null) {
                    monthlyBooking();


                    for (int i = 0; i < 14; i++) {

                        booking(i);
                    }
                    Monthly_details details = new Monthly_details();
                    getFragmentManager()
                            .beginTransaction()
                            .replace(R.id.content, details)
                            .commit();
                } else {

                    Toast.makeText(getContext(), "يجب اختيار اتوبيسين لكل يوم!!", Toast.LENGTH_LONG).show();


                }
            }
        });

        dateLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getdate();
                Log.i("ffff", "id=" + date);

            }
        });
        return mMainView;
    }

    public void getdate() {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        Locale locale = new Locale("ar");

                        //   String my_date = "31-12-2014";
                        Date todayDate = Calendar.getInstance().getTime();

                        // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", locale);

                        String my_date = sdf.format(todayDate);
                        // Toast.makeText(Details.this,my_date ,Toast.LENGTH_LONG).show();
                        date = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                        Date strDate = null;
                        try {
                            strDate = sdf.parse(my_date);
                            todayDate = sdf.parse(date);

                        } catch (java.text.ParseException e) {
                            e.printStackTrace();
                        }
                        if (strDate.after(todayDate)) {
                            //  your_date_is_outdated = true;
                            Toast.makeText(getContext(), "enter valide date..", Toast.LENGTH_SHORT).show();
                        } else {
                            //  your_date_is_outdated = false;
                            // c.setTime(todayDate);

//                                String[] days = new String[] {"السبت", "الاحد", "الاثنين", "الثلاثاء", "الاربعاء", "الخميس", "الجمعة" };
//                               String day = days[c.get(Calendar.DAY_OF_WEEK)];
//                                Log.e("res", String.valueOf(Calendar.DAY_OF_WEEK));
//                               date_go.setText(day);
                            SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                            String goal = outFormat.format(todayDate);
                            Log.e("res", goal);
                            temp.dates.add(date);
                            for(int i=2;i<=14;i++)
                            {

                                if(dayOfMonth==30)
                                {
                                    monthOfYear=monthOfYear+1;
                                    dayOfMonth=1;

                                }
                                else
                                {
                                    dayOfMonth=dayOfMonth+1;

                                }
                              String  date2=year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                                temp.dates.add(date2);



                            }
                            temp.setDigital_date_mon(date);
                            tvdate.setText(date);

                            Log.i("cidyy2", "id=" + temp.dates);

                        }


                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    public void move()

    {
        Details details = new Details();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, details)
                .commit();
    }
//    public String[] gettime(String id) {
//        DatabaseReference gata = dref.child(id);
//        gata.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//
//                 String time = (String) dataSnapshot.child("time_for_go").getValue();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//    }


    public void booking(int i) {
        // pr.setMessage("loading....");
        //   pr.show();

        DatabaseReference newPost = dref_booking.push();
        String device_token = FirebaseInstanceId.getInstance().getToken();
        String key2 = newPost.getKey();
        String busId = temp.monthly_booked_ids.get(i);
        ArrayList<String> alls = getSeats(i);
        ArrayList<Integer> allInt = split_array(alls);
        String seats = choosing_random_seats(allInt);
     Log.e("refs", "the ids="+temp.monthly_booked_ids);

        //create childs
        newPost.child("busId").setValue(busId);
        newPost.child("date").setValue(temp.dates.get(i));
        newPost.child("monthlyId").setValue(key);
        newPost.child("numberOfSeats").setValue(seats);
        newPost.child("userId").setValue(uid);
        // pr.dismiss();


    }

    public void monthlyBooking() {

        DatabaseReference newPost = dref_monthly_booking.push();
        //
        key = newPost.getKey();
        temp.setReservation_id_go(key);
        newPost.child("percentage").setValue("5%");
        newPost.child("state").setValue("un paid");
        newPost.child("userId").setValue(uid);
        discount(key);


    }

    public ArrayList<String> getSeats(int c) {

        final ArrayList<String> allseatsm = new ArrayList<>();
        Query query = dref_booking.orderByChild("busId").equalTo(temp.monthly_booked_ids.get(c));

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));

                    String res_seats = (String) snapshot.child("numberOfSeats").getValue();

                    allseatsm.add(res_seats);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return allseatsm;
    }

    public String choosing_random_seats(final ArrayList<Integer> allseatsInt) {
        int r;
        String str, num_of_seats = "";

        boolean state;

        state = false;
        while (state == false) {


            r = getRandomIntegerBetweenRange(1, 40);
            if (!allseatsInt.contains(r)) {
                state = true;
                num_of_seats = num_of_seats + String.valueOf(r);
            } else {
                state = false;
            }
        }

        return num_of_seats;
    }

    public static int getRandomIntegerBetweenRange(int min, int max) {
        int x = (int) ((Math.random() * ((max - min) + 1)) + min);
        return x;
    }

    public ArrayList<Integer> split_array(final ArrayList<String> allseatsm) {
        String str;
        ArrayList<Integer> allseatsIntr = new ArrayList<>();
        for (int i = 0; i < allseatsm.size(); i++) {
            str = allseatsm.get(i);
            String[] arr = str.split("-");
            for (String w : arr) {
                allseatsIntr.add(Integer.valueOf(w));
            }
        }

        return allseatsIntr;
    }


    public void searchOneWayMonthly(final String dayd, final RecyclerView recycler, final ArrayList<Object_Class> objects, final ArrayList<String> loaded) {


        mRegProgress.setTitle("جاري التسجيل....");
        mRegProgress.setCanceledOnTouchOutside(false);
        mRegProgress.show();

        sourceTxt = sourceSp.getSelectedItem().toString();
        destText = destSp.getSelectedItem().toString();
        Query query = dref.orderByChild("source").equalTo(sourceTxt);
        Log.i("ffgg", "id= " + sourceTxt);

        //   Toast.makeText(Buses.this,query.toString()+"jk" ,Toast.LENGTH_SHORT).show();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));
                    String locationName = (String) snapshot.child("destination").getValue();
                    //   Toast.makeText(Buses.this,snapshot.child("fromch").toString()+"kkkjj" ,Toast.LENGTH_SHORT).show();
                    String dateValue = (String) snapshot.child("day").getValue();

                    if (locationName.equals(destText) && dateValue.equals(dayd)) {
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
                        Log.i("ffgg", "id= " + time);
                        loaded.add(k);

                        Object_Class model = new Object_Class(from, to, bus_num, dat_go, seatss_, driver_name, duration, time, price);
                        objects.add(model);


                    }


                }


                if (objects.size() != 0) {
                    mRegProgress.dismiss();
                    recycler.setVisibility(View.VISIBLE);
                    adapterR = new AdapterMonthly(objects, getContext());

                    recycler.setAdapter(adapterR);

                } else {
                    mRegProgress.dismiss();

                    recycler.setVisibility(View.GONE);
                    //searchResult.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "لا يوجد", Toast.LENGTH_LONG).show();

                    // show no one
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void searchOneWayMonthly_ret(final String dayd, final RecyclerView recycler, final ArrayList<Object_Class> objects, final ArrayList<String> loaded) {
        mRegProgresss.setTitle("جاري التسجيل....");
        mRegProgresss.setCanceledOnTouchOutside(false);
        mRegProgresss.show();
        sourceTxt = sourceSp.getSelectedItem().toString();
        destText = destSp.getSelectedItem().toString();

        temp.setSource(sourceTxt);
        temp.setDestination(destText);
        Query query = dref.orderByChild("source").equalTo(destText);
        Log.i("ffgg", "id= " + sourceTxt);

        //   Toast.makeText(Buses.this,query.toString()+"jk" ,Toast.LENGTH_SHORT).show();

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    //   Log.e(TAG + "out", String.valueOf(snapshot.child("job")) + "" + String.valueOf(snapshot.child("name").getValue()));
                    String locationName = (String) snapshot.child("destination").getValue();
                    //   Toast.makeText(Buses.this,snapshot.child("fromch").toString()+"kkkjj" ,Toast.LENGTH_SHORT).show();
                    String dateValue = (String) snapshot.child("day").getValue();

                    if (locationName.equals(sourceTxt) && dateValue.equals(dayd)) {
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
                        Log.i("ffgg", "id= " + time);
                        loaded.add(k);

                        Object_Class modelr = new Object_Class(from, to, bus_num, dat_go, seatss_, driver_name, duration, time, price);
                        objects.add(modelr);


                    }


                }


                if (objects.size() != 0) {
                    mRegProgresss.dismiss();

                    recycler.setVisibility(View.VISIBLE);
                    adapterR = new AdapterMonthly(objects, getContext());

                    recycler.setAdapter(adapterR);

                } else {
                    mRegProgresss.dismiss();

                    recycler.setVisibility(View.GONE);
                    //searchResult.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "لا يوجد", Toast.LENGTH_LONG).show();

                    // show no one
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void discount(String id) {
        DatabaseReference newPost = dref_discount.push();
        newPost.child("bookId").setValue(id);
        newPost.child("percentage").setValue("5%");
        newPost.child("state").setValue("unpaid");


    }

}
