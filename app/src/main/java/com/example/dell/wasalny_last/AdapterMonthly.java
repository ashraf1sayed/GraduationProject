package com.example.dell.wasalny_last;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by DELL on 6/16/2019.
 */

public class AdapterMonthly extends RecyclerView.Adapter<AdapterMonthly.MyViewHolder3>  {

    List<Object_Class> models;
    Context context;
    DatabaseReference dref;

    private LayoutInflater mInflater;

    public AdapterMonthly(List<Object_Class> models, Context context) {
        this.models = models;
        this.context = context;
        dref = FirebaseDatabase.getInstance().getReference().child("BusSchedule");

    }

    @Override
    public MyViewHolder3 onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_asr, parent, false);

        return new MyViewHolder3(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder3 holder, final int position) {
        holder.txttime.setText(models.get(position).getTime_for_go());
        holder.txtprice.setText(models.get(position).getPrice()+"جنيه");
        holder.txtbuse_number.setText(models.get(position).getBuse_number());


    }


    @Override
    public int getItemCount() {
        return models.size();
    }


    public class MyViewHolder3 extends RecyclerView.ViewHolder implements  View.OnClickListener {

        TextView txttime;
        TextView txtprice;
        TextView txtbuse_number;
        CheckBox ch;


        public MyViewHolder3(View itemView) {
            super(itemView);
            txttime = (TextView) itemView.findViewById(R.id.timeid_m);
            txtprice = (TextView) itemView.findViewById(R.id.price_id_m);
            txtbuse_number = (TextView) itemView.findViewById(R.id.bus_number_m);
            //   ch = itemView.findViewById(R.id.select_id_ch);





        }

        @Override
        public void onClick(View view) {
            if (ch.isChecked()) {
                Temp temp = new Temp();

                String ks = temp.keys.get(getLayoutPosition());
                DatabaseReference dref = FirebaseDatabase.getInstance().getReference("BUSES").child(ks);
                String pr=  models.get(getLayoutPosition()).getseats();

                int result=Integer.parseInt(pr);
                result-=1;
                String str1 = Integer.toString(result);
                Log.i("rr", "text from" + str1);
                dref.child("seats").setValue(str1);


            } else {


            }

        }
    }



}
