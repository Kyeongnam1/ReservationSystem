package com.example.reservationsystem;

public class LibrarySeatInfo {
    private String number;
    private String status;
    private String time;

    LibrarySeatInfo(String number, String status, String time){
        this.number = number;
        this.status = status;
        this.time = time;
    }

    public String getNumber(){return number;}

    public void setNumber(String number){
        this.number = number;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
