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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Ticket_rounded extends Fragment {
    private View mMainView;

    private DatabaseReference dref;

    TextView tvfrom;
    TextView tvto;
    TextView tvprice;
    TextView tvdateGo;
    TextView tvdateRet;
    TextView timeGo;
    TextView timeRet;
    DatabaseReference dref_discount;

    TextView goBusNum;
    TextView retBusNum;
    Temp temp;
    Button confirm;
    String price_go_value, price_ret_value;
 int total_price;
    public Ticket_rounded() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.ticket_rounded, container, false);
        tvfrom = (TextView) mMainView.findViewById(R.id.from_t);
        tvto = (TextView) mMainView.findViewById(R.id.to_t);

        tvprice = (TextView) mMainView.findViewById(R.id.price_view);

        tvdateGo = (TextView) mMainView.findViewById(R.id.date_t);
        tvdateRet = (TextView) mMainView.findViewById(R.id.date_rett);

        timeGo = (TextView) mMainView.findViewById(R.id.time_go);
        timeRet = (TextView) mMainView.findViewById(R.id.time_rt);

        goBusNum = (TextView) mMainView.findViewById(R.id.go_bus_num);
        retBusNum = (TextView) mMainView.findViewById(R.id.return_bus_num);

        confirm = (Button) mMainView.findViewById(R.id.confirm_button);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choose_payment_class payment = new Choose_payment_class();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content, payment)
                        .commit();
            }
        });
        dref = FirebaseDatabase.getInstance().getReference().child("BusSchedule");
        temp = new Temp();
        getDataForGoBud();
        getDataForRetBus();
      //  calcPrice();
        return mMainView;
    }


    public void getDataForGoBud() {

        String id = temp.getGo_bus_round_id();
        DatabaseReference gata = dref.child(id);
        gata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String from = (String) dataSnapshot.child("source").getValue();
                String to = (String) dataSnapshot.child("destination").getValue();
                String time = (String) dataSnapshot.child("time_for_go").getValue();
                String busNum = (String) dataSnapshot.child("buse_number").getValue();
                String date = temp.getDigital_date_for_go();
                price_go_value = (String) dataSnapshot.child("price").getValue();
                //set texts to the viewa
                tvfrom.setText(from);
                tvto.setText(to);
                timeGo.setText(time);
                goBusNum.setText(busNum);
                tvdateGo.setText(date);
                tvprice.setText(price_go_value);
                Log.i("frt", "the dsss=" + from);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void getDataForRetBus() {

        String id = temp.getRet_bus_round_id();
        DatabaseReference gata = dref.child(id);
        gata.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String time = (String) dataSnapshot.child("time_for_go").getValue();
                String busNum = (String) dataSnapshot.child("buse_number").getValue();
                String date = temp.getDigital_date_for_ret();
                price_ret_value = (String) dataSnapshot.child("price").getValue();
                //set texts to the viewa

                timeRet.setText(time);
                retBusNum.setText(busNum);
                tvdateRet.setText(date);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void calcPrice()
    {
        int gop= Integer.parseInt(price_go_value);
        int retp= Integer.parseInt(price_ret_value);
        int sum=gop+retp;
        total_price=sum-(5/100)*sum;


    }


}
