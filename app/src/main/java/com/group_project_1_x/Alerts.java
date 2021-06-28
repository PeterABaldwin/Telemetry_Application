package com.group_project_1_x;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.LinearLayout;

public class Alerts extends Fragment {

    public Received r = new Received();//received data (telemetry)
    private View root;//root view
    private LinearLayout lL;//linear lay out (to create views for each alert in

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_alerts, container, false);
        lL = (LinearLayout) root.findViewById(R.id.alert_layout);//set linearlayout view
        return root;
    }

    public void onResume(){
        super.onResume();
        //sets id's for alerts
        int i[] = new int[14];
        for (int j = 0; j<14; j++){
            i[j]=j;
        }
        //to check telemetry data being out of bounds
        alertset(i);
    }

    /**
     * uses Alert.java to check if data is within or out of acceptable range
     * if there is a cause for alert, a new view containg information about that alert is created
     * if telemetry comes back within bounds, the alert will be removed
     * @param i view id alert will be set to (if created)
     */
    public void alertset(int i[]){
        Alert a = new Alert();
        switch (a.SubPressureAlert(r.getSubPressure())){
            case 0://inbounds
                if (root.findViewById(i[0])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[0]));
                }
                break;
            case 1://above
                onAddField(i[0], "SubPressure is high");
                break;
            case 2://below
                onAddField(i[0], "SubPressure is low");
                break;
        }
        switch (a.FanSpeedAlert(r.getFanSpeed())){
            case 0://inbounds
                if (root.findViewById(i[1])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[1]));
                }
                break;
            case 1://above
                onAddField(i[1], "FanSpeed is high");
                break;
            case 2://below
                onAddField(i[1], "FanSpeed is low");
                break;
        }
        switch (a.EVATimeAlert(r.getEVATime())){
            case 0://inbounds
                if (root.findViewById(i[2])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[2]));
                }
                break;
            case 1://above
                onAddField(i[2], "EVATime is high");
                break;
            case 2://below
                onAddField(i[2], "EVATime is low");
                break;
        }
        /*switch (a.TiLiOxygenAlert(needed)){
            case 0://inbounds
                if (root.findViewById(i[3])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[3]));
                }
                break;
            case 1://above
                onAddField(i[3], "TiLiOxygen is high");
                break;
            case 2://below
                onAddField(i[3], "TiLiOxygen is low");
                break;
        }*/
        switch (a.OxygenPressureAlert(r.getOxygenPressure())){
            case 0://inbounds
                if (root.findViewById(i[4])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[4]));
                }
                break;
            case 1://above
                onAddField(i[4], "OxygenPressure is high");
                break;
            case 2://below
                onAddField(i[4], "OxygenPressure is low");
                break;
        }
        switch (a.OxygenRateAlert(r.getOxygenRate())){
            case 0://inbounds
                if (root.findViewById(i[5])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[5]));
                }
                break;
            case 1://above
                onAddField(i[5], "OxygenRate is high");
                break;
            case 2://below
                onAddField(i[5], "OxygenRate is low");
                break;
        }
        switch (a.BatteryCapacityAlert(r.getBatteryCapacity())){
            case 0://inbounds
                if (root.findViewById(i[6])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[6]));
                }
                break;
            case 1://above
                onAddField(i[6], "BatteryCapacity is high");
                break;
            case 2://below
                onAddField(i[6], "BatteryCapacity is low");
                break;
        }
        /*switch (a.TiLiBatteryAlert(needed)){
            case 0://inbounds
                if (root.findViewById(i[7])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[7]));
                }
                break;
            case 1://above
                onAddField(i[7], "TiLiBattery is high");
                break;
            case 2://below
                onAddField(i[7], "TiLiBattery is low");
                break;
        }*/
        switch (a.H2OGasPressureAlert(r.getH2OGasPressure())){
            case 0://inbounds
                if (root.findViewById(i[8])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[8]));
                }
                break;
            case 1://above
                onAddField(i[8], "H2OGasPressure is high");
                break;
            case 2://below
                onAddField(i[8], "H2OGasPressure is low");
                break;
        }
        switch (a.H2OLiquidPressureAlert(r.getH2OLiquidPressure())){
            case 0://inbounds
                if (root.findViewById(i[9])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[9]));
                }
                break;
            case 1://above
                onAddField(i[9], "H2OLiquidPressure is high");
                break;
            case 2://below
                onAddField(i[9], "H2OLiquidPressure is low");
                break;
        }
        /*switch (a.TiLiWaterAlert(needed))){
            case 0://inbounds
                if (root.findViewById(i[10])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[10]));
                }
                break;
            case 1://above
                onAddField(i[10], "TiLiWater is high");
                break;
            case 2://below
                onAddField(i[10], "TiLiWater is low");
                break;
        }*/
        switch (a.SOPPressureAlert(r.getSOPPressure())){
            case 0://inbounds
                if (root.findViewById(i[12])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[12]));
                }
                break;
            case 1://above
                onAddField(i[12], "SOPPressure is high");
                break;
            case 2://below
                onAddField(i[12], "SOPPressure is low");
                break;
        }
        switch (a.SOPRateAlert(r.getSOPRate())){
            case 0://inbounds
                if (root.findViewById(i[13])!=null){//remove with  if exists
                    lL.removeView(root.findViewById(i[13]));
                }
                break;
            case 1://above
                onAddField(i[13], "SOPRate is high");
                break;
            case 2://below
                onAddField(i[13], "SOPRate is low");
                break;
        }
    }

    /**
     * creates alert view within LinearLayout
     * @param i alert id
     * @param s alert text
     */
    void onAddField(int i, String s) {
        TextView tv = new TextView(getContext());
        tv.setText(s);
        tv.setTextSize(24);
        tv.setPadding(5,5,5,5);
        tv.setTextColor(Color.parseColor("#ffffff"));
        tv.setId(i);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, R.style.AlertStyle);
        lL.addView(tv);
    }
}
