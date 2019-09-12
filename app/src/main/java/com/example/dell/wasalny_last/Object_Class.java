package com.example.dell.wasalny_last;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by EMAD on 3/6/2018.
 */

public class Object_Class implements Parcelable {


    private String source;
    private String destination;
    private String buse_number;
    private String date_for_go;
    private String seats;
    private String  Driver_name;
    private String duration;
    private String time_for_go;
    private String price;
    public Object_Class()
    {

    }
    public Object_Class(String source, String destination, String buse_number, String date_for_go, String seats, String Driver_name, String duration,String time_for_go ,String price) {
        this.source = source;
        this.destination = destination;
        this.buse_number = buse_number;
        this.date_for_go = date_for_go;
        this.seats = seats;
        this.Driver_name = Driver_name;
        this.duration = duration;
        this.time_for_go = time_for_go;
        this.price = price;



    }
    protected Object_Class(Parcel in) {

        source = in.readString();;
        destination = in.readString();;
        buse_number = in.readString();;
        date_for_go = in.readString();;
        seats = in.readString();;
        Driver_name = in.readString();
        duration = in.readString();
        time_for_go = in.readString();
        price=in.readString();
    }
    public static final Creator<Object_Class> CREATOR = new Creator<Object_Class>() {
        @Override
        public Object_Class createFromParcel(Parcel in) {
            return new Object_Class(in);
        }

        @Override
        public Object_Class[] newArray(int size) {
            return new Object_Class[size];
        }
    };
    public String getseats() {
        return seats;
    }
    public void setseats(String seats) {
        this.seats = seats;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(source);
        dest.writeString(destination);
        dest.writeString(buse_number);
        dest.writeString(date_for_go);
        dest.writeString(seats);
        dest.writeString(Driver_name);
        dest.writeString(duration);
        dest.writeString(time_for_go);
        dest.writeString(price);

    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getBuse_number() {
        return buse_number;
    }

    public void setBuse_number(String buse_number) {
        this.buse_number = buse_number;
    }

    public String getDate_for_go() {
        return date_for_go;
    }

    public void setDate_for_go(String date_for_go) {
        this.date_for_go = date_for_go;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getDriver_name() {
        return Driver_name;
    }

    public void setDriver_name(String driver_name) {
        Driver_name = driver_name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getTime_for_go() {
        return time_for_go;
    }

    public void setTime_for_go(String time_for_go) {
        this.time_for_go = time_for_go;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}
