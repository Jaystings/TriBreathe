package com.jaystings.tribreathe;

/**
 * Android platform web drawer. Utilizes hardware-independent Vertex class.
 * Animates clockwise, lighting each circle sequentially every second.
 * Created by James C Hastings on 2/13/2016.
 */
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Paint;

public class Web extends View {

    private Paint paint;
    private Vertex [] circles;
    private int numSec;
    private Note message;

    public Web(Context context, AttributeSet as){
        super(context);
        circles = new Vertex[12];
        for(int i=0; i<12; i++){
            circles[i] = new Vertex();
        }
    }

    public Web(Context context, Vertex [] aCircles, Note aMessage) {
        super(context);
        circles = aCircles;
        numSec = 0;
        message = aMessage;
    }

    public void onDraw(Canvas canvas) {
        paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawColor(Color.BLACK);

        paint.setTextSize(48);
        canvas.drawText(message.getMessage(), message.getX(), message.getY(), paint);

        // Draw each vertex based on its assigned coordinates
        // provided from the parameters of the contructor above.
        for( int vert = 0; vert < circles.length; vert++ ) {
            paint.setColor(circles[vert].getColor());
            canvas.drawCircle(circles[vert].getX(), circles[vert].getY(),
                    circles[vert].getSize(), paint);
        }

        reDraw(canvas);
    }

    private void reDraw(final Canvas canvas){

        // Check which circle is supposed to be highlighted.
        switch(circles[numSec].getColor()) {
            case Color.RED:
                circles[numSec].setColor(Color.MAGENTA);
                break;
            case Color.BLUE:
                circles[numSec].setColor(Color.CYAN);
                break;
            case Color.DKGRAY:
                circles[numSec].setColor(Color.WHITE);
                break;
        }

        if(numSec>0){
            // Reset previous color
            switch(circles[numSec-1].getColor()) {
                case Color.CYAN:
                    circles[numSec-1].setColor(Color.BLUE);
                    break;
                case Color.WHITE:
                    circles[numSec-1].setColor(Color.DKGRAY);
                    break;
                case Color.MAGENTA:
                    circles[numSec-1].setColor(Color.RED);
            }
        }
        else{
            // Reset previous color
            switch(circles[11].getColor()) {
                case Color.CYAN:
                    circles[11].setColor(Color.BLUE);
                    break;
                case Color.WHITE:
                    circles[11].setColor(Color.DKGRAY);
                    break;
                case Color.MAGENTA:
                    circles[11].setColor(Color.RED);
            }
        }

        if( numSec < 6 && numSec > 0 ){
            canvas.drawText("Breathe In", message.getX(), message.getY(), paint);
        } else if( numSec < 8 && numSec > 0 ){
            canvas.drawText("Hold", message.getX(), message.getY(), paint);
        } else {
            if( numSec==0 ){ paint.setColor(Color.BLUE); }
            canvas.drawText("Breathe Out", message.getX(), message.getY(), paint);
        }

        invalidate();
        if(numSec < 11) { numSec++; }
        else { numSec = 0; }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}