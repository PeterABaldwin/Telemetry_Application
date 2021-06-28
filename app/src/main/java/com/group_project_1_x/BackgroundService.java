package com.group_project_1_x;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.util.Log;
import java.util.Timer;
import java.util.TimerTask;

@SuppressLint("NewApi")
public class BackgroundService extends IntentService {

    Global g = com.group_project_1_x.Global.getInstance();
    private String ipc = "136.160.233.245";//ip checker
    String BACKGROUND = "background";//logging
    boolean alert = false;//so the alert only plays once
    boolean blue = false;//remove when tlemetry won't dip above or below the required percent every second
    int refresh;


    @SuppressLint("NewApi")
    public BackgroundService() {
        super("Background");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        refresh = g.getRefresh();
        timer();
    }

    /**
     * activates all background actions
     * does so under a timer based on the refresh rate
     * when refresh rate is changed, timer ends and swaps to timer to
     * this starts the loop between the two
     */
    public void timer(){
        final Timer timer = new Timer();
        timer.purge();
        final TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                connect();
                if (alertset() && !alert) {
                    notif("Alert", "There is an alert", "Please check alerts");
                    alert = true;
                    startPopUp();playSound();
                }
                if (ipc != g.getIP()) {
                    ipc = g.getIP();
                    Log.i(BACKGROUND, "" + g.getIP());
                }
                if (g.getRefresh() != refresh) {
                    Log.i(BACKGROUND, "refresh: " + g.getRefresh());
                    cancel();
                    refresh = g.getRefresh();
                    timer2();
                }
            }
        };
        Log.i(BACKGROUND, "here");
        timer.scheduleAtFixedRate(timerTask, 0, g.getRefresh());
        timer.purge();
        return;
    }
    /**
     * same as timer, but starts timer when the refresh rate is changed
     */
    public void timer2(){
        final Timer timer = new Timer();
        timer.purge();
        final TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {
                connect();
                if (alertset() && !alert) {
                    notif("Alert", "There is an alert", "Please check alerts");
                    alert = true;
                    startPopUp();
                    playSound();
                }
                if (ipc != g.getIP()) {
                    ipc = g.getIP();
                    Log.i(BACKGROUND, "" + g.getIP());
                }
                if (g.getRefresh() != refresh) {
                    Log.i(BACKGROUND, "refresh: " + g.getRefresh());
                    cancel();
                    refresh = g.getRefresh();
                    timer();
                }
            }
        };
        Log.i(BACKGROUND, "here");
        timer.scheduleAtFixedRate(timerTask, 0, g.getRefresh());
        timer.purge();
        return;
    }
    /**
     * sets the alert fragment to include what alerts there are
     * @return if the alert has been set
     */
    public boolean alertset(){
        Alert a = new Alert();
        Received r = new Received();
        switch (a.SubPressureAlert(r.getSubPressure())){
            case 1://above
            case 2://below
                return true;
        }
        switch (a.FanSpeedAlert(r.getFanSpeed())){
            case 1://above
            case 2://below
                return true;
        }
        switch (a.EVATimeAlert(r.getEVATime())){
            case 1://above
            case 2://below
                return true;
        }
        /*switch (a.TiLiOxygenAlert(needed)){
            case 1://above
            case 2://below
            return true;
        }*/
        switch (a.OxygenPressureAlert(r.getOxygenPressure())){
            case 1://above
            case 2://below
                return true;
        }
        switch (a.OxygenRateAlert(r.getOxygenRate())){
            case 1://above
            case 2://below
                return true;
        }
        switch (a.BatteryCapacityAlert(r.getBatteryCapacity())){
            case 1://above
            case 2://below
                return true;
        }
        /*switch (a.TiLiBatteryAlert(needed)){
            case 1://above
            case 2://below
            return true;
        }*/
        switch (a.H2OGasPressureAlert(r.getH2OGasPressure())){
            case 1://above
            case 2://below
                return true;
        }
        switch (a.H2OLiquidPressureAlert(r.getH2OLiquidPressure())){
            case 1://above
            case 2://below
                return true;
        }
        /*switch (a.TiLiWaterAlert(needed))){
            case 1://above
            case 2://below
            return true;
        }*/
        switch (a.SOPPressureAlert(r.getSOPPressure())){
            case 1://above
            case 2://below
                return true;
        }
        switch (a.SOPRateAlert(r.getSOPRate())){
            case 1://above
            case 2://below
                return true;
        }
        return false;
    }
    /**
     * plays sound when popup pops up
     */
    public void playSound(){
        MediaPlayer alarm;
        //Toast.makeText(this, "You got a new alert", Toast.LENGTH_SHORT).show();
        try{
            alarm = MediaPlayer.create(getApplicationContext(), R.raw.warning);
            alarm.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
    /**
     * display popup when an alert is found
     */
    public void startPopUp(){
        Intent startNotification = new Intent(BackgroundService.this, com.group_project_1_x.Notification.class);
        startActivity(startNotification);
    }

    NotificationChannel channel;
    /**
     * creates a notification based on paramaters
     * @param title title of notification
     * @param subject subject of notification
     * @param body body of notification
     */
    public void notif (String title, String subject, String body){
        createNotificationChannel(title, subject, body);

        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        @SuppressLint({"NewApi", "LocalSuppress"}) Notification notify=new Notification.Builder
                (getApplicationContext())
                .setContentTitle(title)
                .setContentText(body)
                .setContentTitle(subject)
                .setSmallIcon(R.drawable.nasa_logo)
                .setChannelId(body)
                .build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }
    private void createNotificationChannel(String namei, String descriptioni, String id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            CharSequence name = "Channel";
//            String description = "BigBoi";
            CharSequence name = namei;
            String description = descriptioni;
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            channel = new NotificationChannel("010101", name, importance);
            channel = new NotificationChannel(id, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    /**
     * checks if wifi and bluetooth are connected
     */
    public void connect() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            //wifi
            NetworkInfo networkInfoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

            if (networkInfoWifi.isConnected()) {
                Log.i("BGS", "wifi is available");
                g.setWifi("CONNECTED");
            } else {
                Log.i("BGS", "wifi is not available");
                g.setWifi("DISCONNECTED");
            }
        }catch(Exception e){
            Log.i(BACKGROUND, e.getMessage());
        }

        //bluetooth
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null && !blue) {
            // Device doesn't support Bluetooth
            notif("Bluetooth", "support", "Your device doesn't support Bluetooth.");
            Log.i(BACKGROUND, "Device doesn't support bluetooth");
            blue=true;
        }else{
            try {
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfoBluetooth = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_BLUETOOTH);
                if (networkInfoBluetooth.isConnected()) {
                    Log.i("BGS", "bluetooth is available");

                    g.setBluetooth("CONNECTED");
                } else {
                    Log.i("BGS", "wifi is not available");
                    g.setBluetooth("DISCONNECTED");
                }
            }catch(Exception e){
                Log.i(BACKGROUND, e.getMessage());
            }
        }
    }
}
