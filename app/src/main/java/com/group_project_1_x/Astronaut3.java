package com.group_project_1_x;

import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import de.nitri.gauge.Gauge;

/**
 * Created by Michael on 10/28/2019.
 */

public class Astronaut3 extends Fragment {
    // Initializing the Telemetry class
    //Telemetry telemetry = new Telemetry();
    Received received = new Received();
    // Time Life TextViews
    TextView oxygen_time_life, battery_time_life, water_time_life;
    // Count down timer class
    private CountDownTimer oxygen_count_down_timer;
    private long oxygen_milliseconds = 600000; // Approx 10 minutes or so

    private CountDownTimer battery_count_down_timer;
    private long battery_milliseconds = 1200000; // Approx 20 minutes or so

    private CountDownTimer water_count_down_timer;
    private long water_milliseconds = 900000; // Approx 15 minutes or so

    Global g = com.group_project_1_x.Global.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View astronaut3 = inflater.inflate(R.layout.fragment_astronaut3, container, false);

        final Gauge oxygen_pressure = astronaut3.findViewById(R.id.oxygen_pressure);
        final Gauge h2o_gas_pressure = astronaut3.findViewById(R.id.h2o_gas_pressure);
        final Gauge h2o_liquid_pressure = astronaut3.findViewById(R.id.h2o_liquid_pressure);
        final Gauge sop_pressure = astronaut3.findViewById(R.id.sop_pressure);
        final Gauge sub_pressure = astronaut3.findViewById(R.id.sub_pressure);
        final Gauge internal_suit_pressure = astronaut3.findViewById(R.id.internal_suit_pressure);
        final Gauge oxygen_rate = astronaut3.findViewById(R.id.oxygen_rate);
        final Gauge sop_rate = astronaut3.findViewById(R.id.sop_rate);

        oxygen_time_life = astronaut3.findViewById(R.id.oxygen_time_life);
        battery_time_life = astronaut3.findViewById(R.id.battery_life);
        water_time_life = astronaut3.findViewById(R.id.water_time_life);

        oxygen_pressure.setValue(received.getOxygenPressure());
        h2o_gas_pressure.setValue((float) received.getH2OGasPressure());
        h2o_liquid_pressure.setValue((float) received.getH2OLiquidPressure());
        sop_pressure.setValue(received.getSOPPressure());
        sub_pressure.setValue(received.getSOPPressure());
        // Data for internal suit pressure not available as at 10/29/19
        // internal_suit_pressure.setValue(received.get);
        oxygen_rate.setValue((float)received.getOxygenRate());
        sop_rate.setValue((float)received.getSOPRate());

        // Running time for time-like looking variables
        startOxygenTime();
        startBatteryTime();
        startWaterTime();

        return astronaut3;
    }

    // Remaining oxygen time handler
    public void startOxygenTime(){
        oxygen_count_down_timer = new CountDownTimer(oxygen_milliseconds, 1000) {
            @Override
            public void onTick(long l) {
                // updating remaining time to the oxygen milliseconds variable
                oxygen_milliseconds = l;
                int minutes = (int) oxygen_milliseconds/60000;
                int seconds = (int) oxygen_milliseconds % 60000/1000;
                String timeLeft = "" + minutes;
                // Adding the column
                timeLeft += ":";
                if(seconds<10){
                    // We always wat two digits so we are going to add a leading 0
                    timeLeft += "0";
                }
                timeLeft += seconds;
                oxygen_time_life.setText(timeLeft);
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void startBatteryTime(){
        battery_count_down_timer = new CountDownTimer(battery_milliseconds, 1000) {
            @Override
            public void onTick(long l) {
                // updating remaining time to the oxygen milliseconds variable
                battery_milliseconds = l;
                int minutes = (int) battery_milliseconds/60000;
                int seconds = (int) battery_milliseconds % 60000/1000;
                String timeLeft = "" + minutes;
                // Adding the column
                timeLeft += ":";
                if(seconds<10){
                    // We always wat two digits so we are going to add a leading 0
                    timeLeft += "0";
                }
                timeLeft += seconds;
                battery_time_life.setText(timeLeft);
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }
    public void startWaterTime(){
        water_count_down_timer = new CountDownTimer(water_milliseconds, 1000) {
            @Override
            public void onTick(long l) {
                // updating remaining time to the oxygen milliseconds variable
                water_milliseconds = l;
                int minutes = (int) water_milliseconds/60000;
                int seconds = (int) water_milliseconds % 60000/1000;
                String timeLeft = "" + minutes;
                // Adding the column
                timeLeft += ":";
                if(seconds<10){
                    // We always wat two digits so we are going to add a leading 0
                    timeLeft += "0";
                }
                timeLeft += seconds;
                water_time_life.setText(timeLeft);
            }
            @Override
            public void onFinish() {

            }
        }.start();
    }
}
