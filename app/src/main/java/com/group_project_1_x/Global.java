package com.group_project_1_x;

import android.app.Application;

import java.util.List;

public class Global extends Application {
    //setters and getters for variables spanning activities and classes
    private static String ip = "136.160.232.62";
    private static String bluetooth = "DISCONNECTED";
    private static String wifi = "DISCONNECTED";
    private static int refresh = 5000;//5 seconds default for the refresh rate

    private static final Global globalInstance = new Global();
    public static Global getInstance() {
        return globalInstance;
    }

    public String getIP() {
        return ip;
    }
    public void setIP(String ip) {
        this.ip = ip;
    }

    public String getBluetooth() {
        return bluetooth;
    }
    public void setBluetooth(String bluetooth) {
        this.bluetooth = bluetooth;
    }

    public String getWifi() {
        return wifi;
    }
    public void setWifi(String wifi) {
        this.wifi = wifi;
    }

    public int getRefresh (){
        return refresh;
    }
    public void setRefresh(int refresh){
        this.refresh = refresh;
    }

    private static List<String> allMessages;
    public List<String> getAllMessages (){
        return allMessages;
    }
    public void setAllMessages(List<String> allMessages){
        this.allMessages = allMessages;
    }
}
