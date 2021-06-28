package com.group_project_1_x;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Michael on 10/28/2019.
 */

public class TelemetryInDepth extends Fragment{
    private AstronautFragmentManager astronautFragmentManager;
    private ViewPager viewPager;

    public static final int ASTRONAUT_ONE = 0;
    public static final int ASTRONAUT_TWO = 1;
    public static final int ASTRONAUT_THREE = 2;
    public static final int ASTRONAUT_FOUR = 3;

    Button astronaut1, astronaut2, astronaut3, astronaut4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view= inflater.inflate(R.layout.activity_telemetry_in_depth, container, false);
        viewPager = view.findViewById(R.id.astronaut_fragment);
        astronautFragmentManager = new AstronautFragmentManager(getFragmentManager());
        astronautFragmentManager.addFragment(new Astronaut3());
        astronautFragmentManager.addFragment(new Astronaut3());
        astronautFragmentManager.addFragment(new Astronaut3());
        astronautFragmentManager.addFragment(new Astronaut3());
        viewPager.setAdapter(astronautFragmentManager);

        // Initializing side buttons
        astronaut1 = (Button) view.findViewById(R.id.side_btn_ast_1);
        astronaut2 = (Button) view.findViewById(R.id.side_btn_ast_2);
        astronaut3 = (Button) view.findViewById(R.id.side_btn_ast_3);
        astronaut4 = (Button) view.findViewById(R.id.side_btn_ast_4);

        // When side buttons are clicked
        astronaut1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setViewPager(ASTRONAUT_ONE);
            }
        });
        astronaut2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setViewPager(ASTRONAUT_TWO);
            }
        });
        astronaut3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setViewPager(ASTRONAUT_THREE);
            }
        });
        astronaut4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setViewPager(ASTRONAUT_FOUR);
            }
        });
        return  view;
    }
    public void setViewPager(int fragmentNumber) {

        // This fixed the bug
        //viewPager.setAdapter(astronautFragmentManager);
        viewPager.setCurrentItem(fragmentNumber);
    }
}
