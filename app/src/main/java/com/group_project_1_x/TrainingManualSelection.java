package com.group_project_1_x;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.ThumbnailUtils;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.support.v4.app.Fragment;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.net.Inet4Address;

public class TrainingManualSelection extends Fragment {

    private GridLayout gL;
    public String IMAGE = "image";
    int mxi = 20;//max images

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.activity_training_manual_selection, container, false);

        //getActivity().setSupportActionBar(toolbar);


        gL = (GridLayout) root.findViewById(R.id.imageViewer);

        //random for testing purposes
        for (int i = 0; i<mxi; i++) {
            double dr = Math.random()*50;
            int d = (int) dr;
            if (d%2==1){
                onAddField(i, R.drawable.jest);
            }else{
                onAddField(i, R.drawable.chi_rho_large);
            }
        }

        return root;
    }

    View.OnClickListener ocl = new View.OnClickListener(){
        @Override
        public void onClick (View v) {
            Integer l = (Integer) v.getTag();
            final int d = l;
            Intent in = new Intent(getActivity(), ManualViewer.class);//initialize new activity
            in.putExtra("image", d);
            startActivity(in);
        }
    };

    void onAddField(int i, int d) {
        ImageView iv = new ImageView(getActivity());
        iv.setId(i);
        iv.setPadding(10, 10, 10, 10);
        iv.setTag(d);
        iv.setOnClickListener(ocl);

        final int SIZE = 340;
        Resources res = getResources();
        Bitmap draw = BitmapFactory.decodeResource(res, d);
        Bitmap bm = ThumbnailUtils.extractThumbnail(draw, SIZE, SIZE);

        iv.setImageBitmap(bm);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        gL.addView(iv, params);
    }
}