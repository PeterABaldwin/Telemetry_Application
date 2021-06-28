package com.group_project_1_x;

import android.util.Log;

public class Alert {
    //all methods each check if received telemetry is above, below, or within bounds
    public int InPressureAlert (double ip){
        if (ip > 4) {
            return 1;
        }else if (ip < 2) {
            return 2;
        }else{
            return 0;
        }
    }
    public int SubPressureAlert (double sp){
        if (sp > 4) {
            return 1;
        }else if (sp < 2) {
            return 2;
        }else{
            return 0;
        }
    }
    public int FanSpeedAlert (int fs){
        if (fs > 40000) {
            return 1;
        }else if (fs < 10000) {
            return 2;
        }else{
            return 0;
        }
    }
    public int EVATimeAlert (String et){
        try {
            int i = Integer.parseInt(et.substring(0, 2));
            if (i > 9) {
                return 1;
            } else {
                return 0;
            }
        }catch (Exception e){
            Log.d("Alerts", e.getMessage());
            return 0;
        }
    }
    public int TiLiOxygenAlert (String tlo){
        if (Integer.parseInt(tlo.substring(0,1)) > 10) {
            return 1;
        }else if (Integer.parseInt(tlo.substring(0,1)) < 0) {
            return 2;
        }else{
            return 0;
        }
    }
    public int OxygenPressureAlert (int op) {
        if (op > 950) {
            return 1;
        } else if (op < 750) {
            return 2;
        } else {
            return 0;
        }
    }
    public int OxygenRateAlert (double or) {
        if (or > 1) {
            return 1;
        } else if (or < 0.5) {
            return 2;
        } else {
            return 0;
        }
    }
    public int BatteryCapacityAlert (int bc) {
        if (bc > 30) {
            return 1;
        } else if (bc <= 0) {
            return 2;
        } else {
            return 0;
        }
    }
    public int TiLiBatteryAlert (String tlb){
        if (Integer.parseInt(tlb.substring(0,1)) > 10) {
            return 1;
        }else if (Integer.parseInt(tlb.substring(0,1)) < 0) {
            return 2;
        }else{
            return 0;
        }
    }
    public int H2OGasPressureAlert (double hgp) {
        if (hgp > 16) {
            return 1;
        } else if (hgp < 14) {
            return 2;
        } else {
            return 0;
        }
    }
    public int H2OLiquidPressureAlert (double hlp) {
        if (hlp > 16) {
            return 1;
        } else if (hlp < 14) {
            return 2;
        } else {
            return 0;
        }
    }
    public int TiLiWaterAlert (String tlw){
        if (Integer.parseInt(tlw.substring(0,1)) > 10) {
            return 1;
        }else if (Integer.parseInt(tlw.substring(0,1)) < 0) {
            return 2;
        }else{
            return 0;
        }
    }
    public int SOPPressureAlert (int sopp) {
        if (sopp > 950) {
            return 1;
        } else if (sopp < 750) {
            return 2;
        } else {
            return 0;
        }
    }
    public int SOPRateAlert (double sopr) {
        if (sopr > 1) {
            return 1;
        } else if (sopr < 0.5) {
            return 2;
        } else {
            return 0;
        }
    }
}
