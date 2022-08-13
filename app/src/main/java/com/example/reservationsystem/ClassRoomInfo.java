package com.example.reservationsystem;

public class ClassRoomInfo {
    private String Capacity;
    private String Status;

    ClassRoomInfo(String Capacity, String Status){
        this.Capacity = Capacity;
        this.Status = Status;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

}
