package com.group_project_1_x;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class TelemetryAtAGlance extends Fragment {

    Button tutorial;
    View astronaut1;
    View astronaut2;
    View astronaut3;
    View astronaut4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View r;
        r= inflater.inflate(R.layout.activity_telemetry_at_aglance, container, false);
        super.onCreate(savedInstanceState);

        //tutorial = r.findViewById(R.id.tutorialButton);
        astronaut1 = r.findViewById(R.id.astronaut1_layout);
        astronaut2 = r.findViewById(R.id.astronaut2_layout);
        astronaut3 = r.findViewById(R.id.astronaut3_layout);
        astronaut4 = r.findViewById(R.id.astronaut4_layout);

        //to move to PDF/Image share
        /*tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelemetryAtAGlance.this, TrainingManualSelection.class);//initialize new activity
                startActivity(i);
            }
        });

         */

        //To move to Astronaut1 in depth
        /*astronaut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TelemetryAtAGlance.this, needed.class);//initialize new activity
                startActivity(i);
            }
        });
         */
        return r;
    }


}
