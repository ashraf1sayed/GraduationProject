package com.example.dell.wasalny_last;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by DELL on 4/2/2019.
 */

public class Temp {

    public static ArrayList<String> keys = new ArrayList<>();
    Object_Class modelOb=new Object_Class();
    public static ArrayList<String> keys2 = new ArrayList<>();
    public static ArrayList<String> keys_go_list = new ArrayList<>();

    public static ArrayList<String> keys_ret_list = new ArrayList<>();

    public static ArrayList<String> monthly_booked_ids = new ArrayList<>();
    public static ArrayList<String> dates = new ArrayList<>();
    public static ArrayList<String> ids_s1 = new ArrayList<>();

    public static ArrayList<String> ids_s2 = new ArrayList<>();

    public static ArrayList<String> ids_h1 = new ArrayList<>();

    public static ArrayList<String> ids_h2 = new ArrayList<>();

    public static ArrayList<String> ids_a1 = new ArrayList<>();

    public static ArrayList<String> ids_a2 = new ArrayList<>();

    public static ArrayList<String> ids_t1 = new ArrayList<>();

    public static ArrayList<String> ids_t2 = new ArrayList<>();

    public static ArrayList<String> ids_ar1 = new ArrayList<>();

    public static ArrayList<String> ids_ar2 = new ArrayList<>();

    public static ArrayList<String> ids_k1 = new ArrayList<>();

    public static ArrayList<String> ids_k2 = new ArrayList<>();

    public static ArrayList<String> ids_g1 = new ArrayList<>();

    public static ArrayList<String> ids_g2 = new ArrayList<>();
    public static ArrayList<String> daily_booking = new ArrayList<>();


















    public static String digital_date_for_go;
    public static String digital_date_for_ret;
    public  static String booking_id;
    public static String go_bus_round_id;
    public static String ret_bus_round_id;
    public static String reservation_id_go;
    public static String reservation_id_ret;
    public static String digital_date_mon;
    public static String time_s1;
    public static String time_s2;

    public static String getSource() {
        return source;
    }

    public static void setSource(String source) {
        Temp.source = source;
    }

    public static String source;

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        Temp.destination = destination;
    }

    public static String destination;


    public static String getMonthly_id_final() {
        return monthly_id_final;
    }

    public static void setMonthly_id_final(String monthly_id_final) {
        Temp.monthly_id_final = monthly_id_final;
    }

    public static String monthly_id_final;

    public static int getProf_mark() {
        return prof_mark;
    }

    public static void setProf_mark(int prof_mark) {
        Temp.prof_mark = prof_mark;
    }

    public static int prof_mark=0;


    public static String getTime_s1() {
        return time_s1;
    }

    public static void setTime_s1(String time_s1) {
        Temp.time_s1 = time_s1;
    }

    public static String getTime_s2() {
        return time_s2;
    }

    public static void setTime_s2(String time_s2) {
        Temp.time_s2 = time_s2;
    }

    public static String getTime_h1() {
        return time_h1;
    }

    public static void setTime_h1(String time_h1) {
        Temp.time_h1 = time_h1;
    }

    public static String time_h1;

    public static String getTime_h2() {
        return time_h2;
    }

    public static void setTime_h2(String time_h2) {
        Temp.time_h2 = time_h2;
    }

    public static String time_h2;

    public static String getTime_a1() {
        return time_a1;
    }

    public static void setTime_a1(String time_a1) {
        Temp.time_a1 = time_a1;
    }

    public static String time_a1;

    public static String getTime_a2() {
        return time_a2;
    }

    public static void setTime_a2(String time_a2) {
        Temp.time_a2 = time_a2;
    }

    public static String time_a2;

    public static String getTime_t1() {
        return time_t1;
    }

    public static void setTime_t1(String time_t1) {
        Temp.time_t1 = time_t1;
    }

    public static String time_t1;

    public static String getTime_t2() {
        return time_t2;
    }

    public static void setTime_t2(String time_t2) {
        Temp.time_t2 = time_t2;
    }

    public static String time_t2;

    public static String getTime_ar1() {
        return time_ar1;
    }

    public static void setTime_ar1(String time_ar1) {
        Temp.time_ar1 = time_ar1;
    }

    public static String time_ar1;

    public static String getTime_ar2() {
        return time_ar2;
    }

    public static void setTime_ar2(String time_ar2) {
        Temp.time_ar2 = time_ar2;
    }

    public static String time_ar2;

    public static String getTime_k1() {
        return time_k1;
    }

    public static void setTime_k1(String time_k1) {
        Temp.time_k1 = time_k1;
    }

    public static String time_k1;

    public static String getTime_k2() {
        return time_k2;
    }

    public static void setTime_k2(String time_k2) {
        Temp.time_k2 = time_k2;
    }

    public static String time_k2;

    public static String getTime_g1() {
        return time_g1;
    }

    public static void setTime_g1(String time_g1) {
        Temp.time_g1 = time_g1;
    }

    public static String time_g1;

    public static String getTime_g2() {
        return time_g2;
    }

    public static void setTime_g2(String time_g2) {
        Temp.time_g2 = time_g2;
    }

    public static String time_g2;


    public static String getDigital_date_mon() {
        return digital_date_mon;
    }













    public static String getReservation_id_ret() {
        return reservation_id_ret;
    }

    public static void setReservation_id_ret(String reservation_id_ret) {
        Temp.reservation_id_ret = reservation_id_ret;
    }


    public static String getReservation_id_go() {
        return reservation_id_go;
    }

    public static void setReservation_id_go(String reservation_id_go) {
        Temp.reservation_id_go = reservation_id_go;
    }


    public static String getRet_bus_round_id() {
        return ret_bus_round_id;
    }

    public static void setRet_bus_round_id(String ret_bus_round_id) {
        Temp.ret_bus_round_id = ret_bus_round_id;
    }


    public static String getGo_bus_round_id() {
        return go_bus_round_id;
    }

    public static void setGo_bus_round_id(String go_bus_round_id) {
        Temp.go_bus_round_id = go_bus_round_id;
    }

    public static void setKeys(ArrayList<String> keys) {
        Temp.keys = keys;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }


    public String getDigital_date_for_ret() {
        return digital_date_for_ret;
    }

    public void setDigital_date_for_ret(String digital_date_for_ret) {
        this.digital_date_for_ret = digital_date_for_ret;
    }


    public String getDigital_date_for_go() {
        return digital_date_for_go;
    }

    public void setDigital_date(String digital_date_for_go) {
        this.digital_date_for_go = digital_date_for_go;
    }


    public static ArrayList<String> getKeys() {
        return keys;
    }

    public void add(String s)
    {
        keys.add(s);

    }


    public void setDigital_date_mon(String digital_date_mon) {
        this.digital_date_mon = digital_date_mon;
    }
}
