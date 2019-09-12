package com.example.dell.wasalny_last;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

import static com.example.dell.wasalny_last.R.drawable.log;

public class Daily_booking extends Fragment implements View.OnClickListener {

    Context con;
    LinearLayout btnsearch;
    private TextView mTextMessage;
    FrameLayout mainfram;

    BottomNavigationView navigation;
    private View mMainView;

    LinearLayout go_pressed_lay, ret_press_lay, type_go_lay, type_go_ret_lay;
    TextView date_go, date_return;
    String go_date = "", return_date = "";
    Spinner spinnerFrom;
    Spinner spinnerTo;
    String selectedDate;
    String selectedDate2;
    String selected_string;
    Temp temp;
    public Daily_booking() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mMainView = inflater.inflate(R.layout.daily_booking, container, false);
         temp=new Temp();
        //matching buttons and layouts
        final ImageView img = mMainView.findViewById(R.id.icon);
        type_go_lay = (LinearLayout) mMainView.findViewById(R.id.go_lay);
        type_go_ret_lay = (LinearLayout) mMainView.findViewById(R.id.go_ret_lay);
        go_pressed_lay = (LinearLayout) mMainView.findViewById(R.id.dateDisplay_go);
        ret_press_lay = (LinearLayout) mMainView.findViewById(R.id.dateDisplay_ret);
        btnsearch = (LinearLayout) mMainView.findViewById(R.id.search);
        date_go = (TextView) mMainView.findViewById(R.id.date_id_go);
        date_return = (TextView) mMainView.findViewById(R.id.dat_ret);
        btnsearch = (LinearLayout) mMainView.findViewById(R.id.search);
        spinnerFrom = (Spinner) mMainView.findViewById(R.id.spinner_from);
        spinnerTo = (Spinner) mMainView.findViewById(R.id.spinner_to);

        //......................................
        btnsearch.setOnClickListener(this);
        go_pressed_lay.setOnClickListener(this);
        ret_press_lay.setOnClickListener(this);
        ret_press_lay.setClickable(false);


        type_go_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type_go_lay.setBackgroundResource(R.drawable.dark);
                type_go_ret_lay.setBackgroundResource(0);
                //.................................................
                ret_press_lay.setClickable(false);
                ret_press_lay.setBackgroundResource(0);
                date_return.setTextColor(0);
                date_return.setText("");
                img.setImageResource(0);
            }
        });
        type_go_ret_lay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type_go_ret_lay.setBackgroundResource(R.drawable.dark);
                type_go_lay.setBackgroundResource(0);
                ret_press_lay.setBackgroundResource(R.drawable.moraba3);
                ret_press_lay.setClickable(true);
                date_return.setText("تاريخ العودة");
                date_return.setTextColor(Color.parseColor("#909050"));
                img.setImageResource(R.drawable.ic_action_calendar_month);

            }
        });


        //function returned view
        return mMainView;
    }

    public void checkinput() {
       String go = date_go.getText().toString();
        String re = date_return.getText().toString();
        if(type_go_ret_lay.getBackground()==null)
        {
            if(go.equalsIgnoreCase("تاريخ الذهاب"))
            {
                Toast.makeText(getContext(), "ادخل تاريخ الذهاب", Toast.LENGTH_SHORT).show();
            }
            else
            {
                searchFun();
            }

        }
        else
        {
            if(go.equalsIgnoreCase("تاريخ الذهاب")||re.equalsIgnoreCase("تاريخ العودة"))
            {
                Toast.makeText(getContext(), "ادخل تاريخ الذهاب والعودة", Toast.LENGTH_SHORT).show();
            }
            else
            {
                searchFunT0Rounded();
            }
        }

    }

    public void searchFun()

    {

        One_way_search one_way = new One_way_search();

        Bundle bundle = new Bundle();
        bundle.putString("go_date", date_go.getText().toString());
        bundle.putString("return_date", date_return.getText().toString());
        bundle.putString("textfrom", spinnerFrom.getSelectedItem().toString());
        bundle.putString("textto", spinnerTo.getSelectedItem().toString());
        one_way.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, one_way)
                .commit();
    }
    public void searchFunT0Rounded()

    {

        Rounded_search rounded = new Rounded_search();

        Bundle bundle = new Bundle();
        bundle.putString("go_date", date_go.getText().toString());
        bundle.putString("return_date", date_return.getText().toString());
        bundle.putString("textfrom", spinnerFrom.getSelectedItem().toString());
        bundle.putString("textto", spinnerTo.getSelectedItem().toString());
        rounded.setArguments(bundle);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.content, rounded)
                .commit();
    }
    @Override
    public void onClick(View v) {
        if (v == go_pressed_lay) {
            final Context context = null;
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

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd",locale);

                            String my_date = sdf.format(todayDate);
                            // Toast.makeText(Details.this,my_date ,Toast.LENGTH_LONG).show();
                            selectedDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
                         String   selectedDate3 = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                            Log.e("resrd", "the date="+selectedDate);
                            Date strDate = null;
                            try {
                                strDate = sdf.parse(my_date);
                                todayDate = sdf.parse(selectedDate3);
                                 selected_string = sdf.format(todayDate);

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

//                                DateFormat originalFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//                                DateFormat targetFormat = new SimpleDateFormat(" dd MMM yyyy", Locale.ENGLISH);
//                                Date date = null;
//                                try {
//                                    date = originalFormat.parse("01/04/2018");
//                                } catch (ParseException e) {
//                                    e.printStackTrace();
//                                }
//                                String formattedDate = targetFormat.format(date);

                                Log.e("resd", "the date="+selectedDate);






                                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                                String goal = outFormat.format(todayDate);
                                Log.e("rees","date"+todayDate);

                                date_go.setText(goal);

                                temp.setDigital_date(selectedDate);

                            }


                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }
        if (v == ret_press_lay) {

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

                            //   String my_date = "31-12-2014";
                            Date todayDate = Calendar.getInstance().getTime();

                            // SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                            String my_date = sdf.format(todayDate);
                            // Toast.makeText(Details.this,my_date ,Toast.LENGTH_LONG).show();
                            selectedDate2 =year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;

                            Date strDate = null;
                            try {
                                strDate = sdf.parse(my_date);
                                todayDate = sdf.parse(selectedDate2);
                            } catch (java.text.ParseException e) {
                                e.printStackTrace();
                            }
                            if (strDate.after(todayDate)) {
                                //  your_date_is_outdated = true;
                                Toast.makeText(getContext(), "enter valide date..",
                                        Toast.LENGTH_SHORT).show();

                            } else {
                                //  your_date_is_outdated = false;
                                SimpleDateFormat outFormat = new SimpleDateFormat("EEEE");
                                String goal = outFormat.format(todayDate);
                                Log.e("res",goal);
                                date_return.setText(goal);
                                temp.setDigital_date_for_ret(selectedDate2);

                            }

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (v == btnsearch) {
            checkinput();

        }
    }
}
