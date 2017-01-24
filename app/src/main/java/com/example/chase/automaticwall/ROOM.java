package com.example.chase.automaticwall;

/**
 * Created by chase on 16-01-2017.
 */

public class ROOM {
    private String Room_Name;
    private String Room_IP_Address;
    private String EE1, EE2, EE3, EE4, EE5, EE6, EE7, EE8;
//    private boolean Status_EE1, Status_EE2, Status_EE3, Status_EE4, Status_EE5, Status_EE6, Status_EE7, Status_EE8;
//    private boolean State_EE1, State_EE2, State_EE3, State_EE4, State_EE5, State_EE6, State_EE7, State_EE8;


    //Contructor

    public ROOM(String room_Name, String room_IP_Address, String EE1, String EE2, String EE3, String EE4, String EE5, String EE6, String EE7, String EE8) {
        Room_Name = room_Name;
        Room_IP_Address = room_IP_Address;
        this.EE1 = EE1;
        this.EE2 = EE2;
        this.EE3 = EE3;
        this.EE4 = EE4;
        this.EE5 = EE5;
        this.EE6 = EE6;
        this.EE7 = EE7;
        this.EE8 = EE8;
    }


    //getter and setter


    public String getRoom_Name() {
        return Room_Name;
    }

    public void setRoom_Name(String room_Name) {
        Room_Name = room_Name;
    }

    public String getRoom_IP_Address() {
        return Room_IP_Address;
    }

    public void setRoom_IP_Address(String room_IP_Address) {
        Room_IP_Address = room_IP_Address;
    }

    public String getEE1() {
        return EE1;
    }

    public void setEE1(String EE1) {
        this.EE1 = EE1;
    }

    public String getEE2() {
        return EE2;
    }

    public void setEE2(String EE2) {
        this.EE2 = EE2;
    }

    public String getEE3() {
        return EE3;
    }

    public void setEE3(String EE3) {
        this.EE3 = EE3;
    }

    public String getEE4() {
        return EE4;
    }

    public void setEE4(String EE4) {
        this.EE4 = EE4;
    }

    public String getEE5() {
        return EE5;
    }

    public void setEE5(String EE5) {
        this.EE5 = EE5;
    }

    public String getEE6() {
        return EE6;
    }

    public void setEE6(String EE6) {
        this.EE6 = EE6;
    }

    public String getEE7() {
        return EE7;
    }

    public void setEE7(String EE7) {
        this.EE7 = EE7;
    }

    public String getEE8() {
        return EE8;
    }

    public void setEE8(String EE8) {
        this.EE8 = EE8;
    }
}
