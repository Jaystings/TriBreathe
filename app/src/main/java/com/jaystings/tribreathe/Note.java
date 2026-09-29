package com.jaystings.tribreathe;

/**
 * Created by James on 2/20/2016.
 */
public class Note {
    private String message;
    private float x;
    private float y;

    public Note(String aMessage, float aX, float aY){
        message = aMessage;
        x = aX;
        y = aY;
    }

    public String getMessage(){ return message; }
    public float getX(){ return x; }
    public float getY(){ return y; }
    public void setMessage(String aMessage){ message = aMessage; }
}
