package com.group_project_1_x;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    String CHANNEL_ID = "AlertMain";//used for alert Notification
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //for now, immediatly jump to TelemetryAtAGlance
        //Intent i = new Intent(MainActivity.this, TelemetryAtAGlance.class);//initialize new activity
        //startActivity(i);
    }
}
