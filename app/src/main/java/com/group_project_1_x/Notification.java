package com.group_project_1_x;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("New Notification")
                .setMessage("Please check alerts panel")
                .setPositiveButton("Notify Astronaut", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Notification.this, "Not set yet", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                finish();
            }
        });
        builder.create();
        builder.show();
    }
}
