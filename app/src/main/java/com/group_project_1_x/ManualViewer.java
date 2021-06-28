package com.group_project_1_x;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ManualViewer extends AppCompatActivity {

    private FrameLayout fL;//parent view
    private ImageView iv;//for image createion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_viewer);

        //get data from previous activity
        Intent i = getIntent();
        final int imageId = i.getIntExtra("image", 1);

        fL = (FrameLayout) findViewById(R.id.imageView);//set parent view

        //get display's width
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        Integer x = size.x;

        onAddField(0, imageId, x);//adds image selected from previous page to new view

        //go back
        Button b = findViewById(R.id.imageBackButton);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //send image
        Button s = findViewById(R.id.imageSend);
        s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send(sendManual (d))
                //do the thing that sends the data
            }
        });
    }

    /**
     * @param i for the id
     * @param d image resource id
     * @param width width of view the new view will be under
     * creates image of what was selected from the previous activity to fill the screen
     */
    void onAddField(int i, int d, int width) {
        iv = new ImageView(getApplicationContext());
        iv.setId(i);
        Resources res = getResources();
        Bitmap draw = BitmapFactory.decodeResource(res, d);
        int w = draw.getWidth();
        int h = draw.getHeight();
        //had to break this up because putting it all in one equation resulted in 0.0
        //this is to scale the height with the width when the width changes to match the view width
        float one = w/width;
        float two = 1/one;
        float three = h*two;
        h = (int) three;
        Bitmap bm = Bitmap.createScaledBitmap(draw, width, h, false);
        iv.setImageBitmap(bm);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        fL.addView(iv, params);
    }

    //it is not possible to convert this object to it's Id. Hopefully, it will still send properly
    Drawable sendManual (int d){
        Drawable manual = getResources().getDrawable(d);
        return manual;
    }
}
