package com.group_project_1_x;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        notif();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // First Fragment to show
        setTitle("Astronaut");
        TelemetryAtAGlance telemetryAtAGlance = new TelemetryAtAGlance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, telemetryAtAGlance, "GLANCE").commit();

        // Make the floating action button invisible
        fab.setVisibility(View.INVISIBLE);
        MediaPlayer alarm = new MediaPlayer();
        try{
            alarm.setDataSource(getApplicationContext(), Uri.parse("android.resource://group_project_1_x/res/raw/warning"));
            alarm.prepare();
            alarm.start();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        // Starting Background Service
        launchBackgroundService();
    }

    private Boolean exit= false;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (exit) {
                finish(); // finish activity
            } else {
                Toast.makeText(this, "Press Back again to Exit.",
                        Toast.LENGTH_SHORT).show();
                exit = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        exit = false;
                    }
                }, 3 * 1000);

            }

        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.menu_glance) {
            // Open Telemetry In depth
            setTitle("All Telemetry");
            TelemetryAtAGlance telemetryAtAGlance = new TelemetryAtAGlance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, telemetryAtAGlance, "GLANCE").commit();
        } else if (id == R.id.menu_in_depth) {
            // Open Telemetry In depth
            setTitle("Astronauts");
            TelemetryInDepth telemetryInDepth = new TelemetryInDepth();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, telemetryInDepth, "IN DEPTH").commit();
        } else if (id == R.id.menu_manuals) {
            // Opening Training Manual Page
            setTitle("Training Manuals");
            TrainingManualSelection trainingManualSelection = new TrainingManualSelection();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, trainingManualSelection, "MANUALS").commit();
        }
        else if(id == R.id.menu_message){
            // Open Messages Fragment
            setTitle("Messages");
            Messages messages = new Messages();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, messages, "MESSAGES").commit();
        }
        else if (id == R.id.menu_alerts){
            setTitle("Alerts");
            Alerts alerts = new Alerts();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, alerts, "ALERTS").commit();
        }
        else if (id == R.id.menu_settings){
            setTitle("Settings");
            Settings settings = new Settings();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_drawer_fragment, settings, "SETTINGS").commit();
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void launchBackgroundService(){
        Intent i = new Intent(this, BackgroundService.class);
        startService(i);
    }

    public void notif (){
        String tittle="Hello";
        String subject="World";
        String body="here";

        NotificationManager notif=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notify=new Notification.Builder
                (getApplicationContext()).setContentTitle(tittle).setContentText(body).
                setContentTitle(subject).setSmallIcon(R.drawable.ic_menu_camera).build();

        notify.flags |= Notification.FLAG_AUTO_CANCEL;
        notif.notify(0, notify);
    }

}
