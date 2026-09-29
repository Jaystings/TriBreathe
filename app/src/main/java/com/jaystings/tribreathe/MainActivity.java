package com.jaystings.tribreathe;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.os.PowerManager;

public class MainActivity extends Activity {

    float Cx;
    float Cy;

    private float height = 500;

    // prevents the screen display from turning off
    protected PowerManager.WakeLock mWakeLock;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Cx = metrics.widthPixels / 2;
        Cy = metrics.heightPixels / 2;

        Note message = new Note("Breathe In", 0, Cy+height);

        // Create the array of vertices in order which they are highlighted.
        Vertex circles [] = new Vertex[12];
        circles[0] = new Vertex(Cx, Cy, Color.RED);
        circles[6] = new Vertex(Cx, Cy-height, Color.DKGRAY);
        int cirCount = 1;
        for(int pair = 1; pair < 6; pair++){
            float posX = Cx + (height*pair)/15;
            float negX = Cx - (height*pair)/15;
            float y = Cy - (height*pair)/5;
            if(pair == 5){
                circles[cirCount] = new Vertex(negX, y, Color.DKGRAY);
                circles[12-cirCount] = new Vertex(posX, y, Color.BLUE);
                continue;
            }
            circles[cirCount] = new Vertex(negX, y, Color.RED);
            circles[12-cirCount] = new Vertex(posX, y, Color.BLUE);
            cirCount++;
        }
        
        Web web = new Web(this, circles, message);
        setContentView(web);


        final PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        this.mWakeLock = pm.newWakeLock(PowerManager.SCREEN_DIM_WAKE_LOCK, "My Tag");
        this.mWakeLock.acquire();

    }

    @Override
    public void onDestroy() {
        this.mWakeLock.release();
        super.onDestroy();
    }


}