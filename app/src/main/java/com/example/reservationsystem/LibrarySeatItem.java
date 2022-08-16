package com.example.reservationsystem;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

public class LibrarySeatItem implements Parcelable {
    String seat_number;
    Boolean status;
    String seat_time;
    int postIdx, postImg;

    public LibrarySeatItem(String seat_number, Boolean status, String seat_time, int postIdx){
        this.seat_number = seat_number;
        this.status = status;
        this.seat_time = seat_time;
        this.postIdx = postIdx;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    protected LibrarySeatItem(Parcel in){
        seat_number = in.readString();
        status = in.readBoolean();
        seat_time = in.readString();
        postIdx = in.readInt();
    }

    public static final Creator<LibrarySeatItem> CREATOR = new Creator<LibrarySeatItem>() {
        @RequiresApi(api = Build.VERSION_CODES.Q)
        @Override
        public LibrarySeatItem createFromParcel(Parcel in) {
            return new LibrarySeatItem(in);
        }

        @Override
        public LibrarySeatItem[] newArray(int size) {
            return new LibrarySeatItem[size];
        }
    };

    public void setSeat_number(String seat_number) {
        this.seat_number = seat_number;
    }
    public String getSeat_number(){ return seat_number; }

    public void setStatus(boolean status){this.status = status; }
    public Boolean getStatus(){return status; }

    public void setSeat_time(String seat_time){this.seat_time = seat_time;}
    public String getSeat_time(){return seat_time;}

    public void setPostImg(int postImg) {
        this.postImg = postImg;
    }
    public int getPostImg() {
        return postImg;
    }

    public void setPostIdx(int postIdx) {
        this.postIdx = postIdx;
    }
    public int getPostIdx() {
        return postIdx;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(seat_number);
        parcel.writeBoolean(status);
        parcel.writeString(seat_time);
        parcel.writeInt(postIdx);
    }

}
