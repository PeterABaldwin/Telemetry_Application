package com.group_project_1_x;

import android.util.Log;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Percents {

    //for percentages, it's the value minus the minimum divided by the range
    //translates how much a value is to the percentage left for that value
    public int InPressurePercent (double ip){
        double i = (ip-2)/2;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int SubPressurePercent (double sp){
        double i = (sp-2)/2;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int FanSpeedPercent (int fs){
        float l = (fs-10000)/4;
        float c = l/10000;
        BigDecimal bd = new BigDecimal(c).setScale(2, RoundingMode.HALF_UP);
        Log.i("fan percent: ", ""+fs+" "+bd.floatValue() );
        return (int)(bd.floatValue()*100);

    }
    public int EVATimePercent (String et){
        try {
            float i = Integer.parseInt(et.substring(1, 2));
            double j = i / 9;
            BigDecimal bd = new BigDecimal(j).setScale(2, RoundingMode.HALF_UP);
            return (int) (bd.floatValue() * 100);
        }catch (Exception e){
            Log.i("received percent", e.getMessage());
            return 0;
        }
    }
    public int TiLiOxygenPercent (String tlo){
        double i = (Integer.parseInt(tlo.substring(0,1)))/10;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int OxygenPressurePercent (int op) {
        double l = (op-750)/2;
        double i = l/100;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int OxygenRatePercent (double or) {
        double i = (or-0.5)/0.5;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int BatteryCapacityPercent (int bc) {
        double l = (bc)/3;
        double i = l/10;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int TiLiBatteryPercent (String tlb){
        double i = (Integer.parseInt(tlb.substring(0,1)))/10;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int H2OGasPressurePercent (double hgp) {
        double i = (hgp-14)/2;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int H2OLiquidPressurePercent (double hlp) {
        double i = (hlp-14)/2;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int TiLiWaterPercent (String tlw){
        double i = (Integer.parseInt(tlw.substring(0,1)))/10;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int SOPPressurePercent (int sopp) {
        double l = (sopp-750)/2;
        double i = l/100;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }
    public int SOPRatePercent (double sopr) {
        double i = (sopr-0.5)/0.5;
        BigDecimal bd = new BigDecimal(i).setScale(2, RoundingMode.HALF_UP);
        return (int)(bd.floatValue()*100);
    }

    //paired list with the first value being the percentage and the second being what it represents
    private List<Pair<Integer, String>> percentArr = new ArrayList<>();

    /**
     * initializes percentArr
     * sets left to 0
     * sets right to "null"
     */
    protected void set0 (){
        for (int i = 0; i<14; i++)
            percentArr.add(new Pair<>(0, "null"));
    }

    /**
     * sets array with values from Received
     * sets left value to be the value of the received data
     * sets right value to the name of the received data
     * @param r received data (telemetry)
     */
    protected void toArray(Received r){


        percentArr.set(0,new Pair<>(100, "InPressure"));
        //percentArr[0]=InPressurePercent(needed);
        percentArr.set(1,new Pair<>(SubPressurePercent(r.getSubPressure()), "SubPressure"));
        percentArr.set(2,new Pair<>(FanSpeedPercent(r.getFanSpeed()), "FanSpeed"));
        percentArr.set(3,new Pair<>(EVATimePercent(r.getEVATime()), "EVATime"));
        percentArr.set(4,new Pair<>(100, "TiLiOxygen"));
        //percentArr[4]=TiLiOxygenPercent(needed);
        percentArr.set(5,new Pair<>(OxygenPressurePercent(r.getOxygenPressure()), "OxygenPressure"));
        percentArr.set(6,new Pair<>(OxygenRatePercent(r.getOxygenRate()), "OxygenRate"));
        percentArr.set(7,new Pair<>(BatteryCapacityPercent(r.getBatteryCapacity()), "BatteryCapacity"));
        percentArr.set(8,new Pair<>(100, "TiLiBattery"));
        //percentArr[8]=TiLiBatteryPercent(needed);
        percentArr.set(9,new Pair<>(H2OGasPressurePercent(r.getH2OGasPressure()), "H2OGasPressure"));
        percentArr.set(10,new Pair<>(H2OLiquidPressurePercent(r.getH2OLiquidPressure()), "H2OLiquidPressure"));
        percentArr.set(11,new Pair<>(100, "TiLiWater"));
        //percentArr[11]=TiLiWaterPercent(needed);
        percentArr.set(12,new Pair<>(SOPPressurePercent(r.getSOPPressure()), "SOPPressure"));
        percentArr.set(13,new Pair<>(SOPRatePercent(r.getSOPRate()), "SOPRate"));
    }

    /**
     * Initializes & sets values from Received
     */
    public void set(Received rec){
        set0();
        toArray(rec);
    }

    /**
     * sorts array based on percent (high to low)
     * uses left value for sorting
     */
    public void sort(Received rec){
        set(rec);
        Collections.sort(percentArr, new Comparator<Pair<Integer, String>>() {
            @Override
            public int compare(final Pair<Integer, String> o1, final Pair<Integer, String> o2) {
                if (o1.getF() > o2.getF()) {
                    return -1;
                } else if (o1.getF().equals(o2.getF())) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
    }

    //returns paired array
    public List<Pair<Integer, String>> out(){
        return percentArr;
    }
}
