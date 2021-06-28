package com.group_project_1_x;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import java.util.Timer;
import java.util.TimerTask;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

/**
 * Created by Michael on 12/09/2019.
 * Edited by Peter on 12/10/2019.
 */

public class Settings extends Fragment implements OnItemSelectedListener{

    String SETTINGS = "settings";
    View root;

    String [] refreshRateOptions= {" 5 seconds"," 10 seconds", " 30 seconds", " 1 minute", " 2 minutes", " 5 minutes" };
    ArrayAdapter<String> refreshRateOptionsAdapter;
    Spinner refreshRateSpinner;

    Global g = com.group_project_1_x.Global.getInstance();

    Button updateIP;
    EditText editIP;

    TextView bluetooth;
    TextView wifi;

    Button refresh;
    int refreshNumber;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_settings, container, false);
        // Loading refresh rate drop down items
        refreshRateOptionsAdapter = new ArrayAdapter<String>(getActivity(), R.layout.drop_down_item, R.id.spinner_example, refreshRateOptions);
        refreshRateSpinner = (Spinner) root.findViewById(R.id.refresh_rate_spinner);
        refreshRateSpinner.setAdapter(refreshRateOptionsAdapter);

        refreshRateSpinner.setOnItemSelectedListener(this);

        refresh = root.findViewById(R.id.refresh_rate_button);
        refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                g.setRefresh(refreshNumber);
                Toast.makeText(getContext(), "Refresh rate: "+(g.getRefresh()/1000)+" seconds", Toast.LENGTH_LONG);
                Log.i(SETTINGS, ""+g.getRefresh());
            }
        });

        updateIP = root.findViewById(R.id.update_ip);
        editIP = root.findViewById(R.id.edit_text_ip);

        updateIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //add checker if correct IP was entered.
                g.setIP(editIP.getText().toString());
                Toast.makeText(getContext(), "New IP: "+g.getIP(), Toast.LENGTH_LONG);
                Log.i(SETTINGS, g.getIP());
            }
        });

        bluetooth = root.findViewById(R.id.text_bluetooth_status);
        wifi = root.findViewById(R.id.text_wifi_status);
        connections();

        //refreshes connections
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask()
        {
            public void run()
            {
                connections();
            }
        }, 0, g.getRefresh());

        return root;
    }

    /**
     * Sets the bluetooth and wifi to DISCONNECTED or CONNECTED
     * depending on if they ae disconnected or connected
     * also changes the color of the text, yellow for disconnected and green for connected
     */
    public void connections() {
        try {
            if (g.getBluetooth().matches("DISCONNECTED")) {
                if (bluetooth.getText().toString() == "CONNECTED") {//if text does not match stored variable
                    root.invalidate();
                }
                bluetooth.setText("DISCONNECTED");
                bluetooth.setTextColor(getResources().getColor(R.color.red));
            } else if (g.getBluetooth().matches("CONNECTED")) {
                if (bluetooth.getText().toString() == "DISCONNECTED") {
                    root.invalidate();
                }
                bluetooth.setText("CONNECTED");
                bluetooth.setTextColor(getResources().getColor(R.color.green));
            } else {

            }

            if (g.getWifi().matches("DISCONNECTED")) {
                if (wifi.getText().toString() == "CONNECTED") {
                    root.invalidate();
                }
                wifi.setText("DISCONNECTED");
                wifi.setTextColor(getResources().getColor(R.color.red));
            } else if (g.getWifi().matches("CONNECTED")) {
                if (wifi.getText().toString() == "DISCONNECTED") {
                    root.invalidate();
                }
                wifi.setText("CONNECTED");
                wifi.setTextColor(getResources().getColor(R.color.green));
            }
        } catch (Exception e) {
            Log.i(SETTINGS, e.getMessage());
        }
    }

    /**
     * sets the refresh variable (to then set refresh rate when update is pressed)
     * refresh is set to the amount of milliseconds corresponding to the spinner selection
     */
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch(item){
            case " 5 seconds":
                refreshNumber = 5000;
                break;
            case " 10 seconds":
                refreshNumber = 10000;
                break;
            case " 30 seconds":
                refreshNumber = 30000;
                break;
            case " 1 minute":
                refreshNumber = 60000;
                break;
            case " 2 minute":
                refreshNumber = 120000;
                break;
            case " 5 minute":
                refreshNumber = 300000;
                break;
            default:
                break;
        }
        Log.i(SETTINGS, ""+item);
    }
    public void onNothingSelected(AdapterView<?> arg0) {
    }

}
