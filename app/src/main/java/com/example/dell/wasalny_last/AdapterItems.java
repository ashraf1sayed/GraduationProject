package com.example.dell.wasalny_last;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.util.List;

public class AdapterItems extends RecyclerView.Adapter<AdapterItems.MyViewHolder> {
    List<Object_Class> models;
    Context context;
    private LayoutInflater mInflater;

    public AdapterItems(List<Object_Class> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_bus_item, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
//        holder.Name.setText(models.get(position).getName());
//        holder.job.setText(models.get(position).getJob());
        holder.txttime.setText(models.get(position).getTime_for_go());
        holder.txtprice.setText(models.get(position).getPrice()+" جنيه");
        holder.txtseats.setText(models.get(position).getseats()+" مقعد متاح ");
        holder.txtbuse_number.setText(models.get(position).getBuse_number());
        holder.txtprice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("ff", "you pressed " + holder.txtprice.getText().toString());
            }
        });


    }

    @Override
    public int getItemCount() {
        return models.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txttime;
        TextView txtprice;
        TextView txtseats;
        TextView txtbuse_number;


        public MyViewHolder(View itemView) {
            super(itemView);
            txttime = (TextView) itemView.findViewById(R.id.timeid);
            txtprice = (TextView) itemView.findViewById(R.id.price_id);
            txtseats = (TextView) itemView.findViewById(R.id.seates_id);
            txtbuse_number = (TextView) itemView.findViewById(R.id.bus_number);

            //  Name = itemView.findViewById(R.id.rv_user_name);
            // job = itemView.findViewById(R.id.rv_user_job);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Object_Class model = models.get(getAdapterPosition());
                    Temp tm = new Temp();
                    tm.modelOb = model;
                    Ticket tic = new Ticket();
                    FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();

                    //  Toast.makeText(context, tm.modelOb.getDate_for_go(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putInt("pos",getAdapterPosition());
                    bundle.putString("from", model.getSource());
                    bundle.putString("to", model.getDestination().toString());
                    bundle.putString("day", model.getDate_for_go().toString());
                    bundle.putString("price", model.getPrice().toString());
                    tic.setArguments(bundle);
                    manager
                            .beginTransaction()
                            .replace(R.id.content, tic)
                            .commit();


                }
            });
        }

    }
}
