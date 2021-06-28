package com.group_project_1_x;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Astronaut1 extends Fragment {

    public TextView receivedText[] = new TextView[14];//how many textviews can be created
    Global g = com.group_project_1_x.Global.getInstance();
    Received rec = new Received();
    View astron1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        astron1 = inflater.inflate(R.layout.fragment_astronaut1, container, false);

        //sets receivedText TextView to the TextViews found in the fragment in order
        String as1 = "as10";//sets default view id
        //puts information into all textviews
        for (int i = 0; i<6; i++){
            as1 = "as1"+i;//view name to be set to
            int getID = getResources().getIdentifier(as1, "id", "com.group_project_1_x");//get id of view
            receivedText[i] = astron1.findViewById(getID);//set item in textview array to found textview
        }


        //I can't use the timer here because it creates a new thread
        //and only the creator thread of the views can access them
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Log.i("fan pre-percent: ", ""+rec.getInfo());
                astronaut();
            }
        }, 1000);

        return astron1;
    }
    public void astronaut(){
        astron1.invalidate();
        Percents p = new Percents();//used to calculate percent telemetry data has before being depleted
        receivedText[0].setText("#1");
        p.sort(rec);//sorts values before displaying them
        int j = 13;//used for ascending percentages
        //sets the values of the fragment to the ones with the lowest percents
        for (int i = 1; i<6; i++){
            receivedText[i].setText(""+p.out().get(j).s);
            receivedText[i].append(": "+p.out().get(j).f+"%");
            j--;
        }
        //r.invalidate();
    }
}
