package com.jaystings.tribreathe;

/**
 * Cross-platform vertex class.
 * Created by James on 2/17/2016.
 */
public class Vertex {

    private float x, y, radius;
    // Android color code below.
    private int Color;

    public Vertex(){
        x = 0;
        y = 0;
        Color = 0;
    }

    public Vertex(float aX, float aY, int aColor){
        x = aX;
        y = aY;
        Color = aColor;
        radius = 30;
    }

    public float getX(){ return x; }
    public float getY(){ return y; }
    public float getSize(){ return radius; }
    public int getColor(){ return Color; }
    public void setColor(int aColor){ Color = aColor; }
    public void setX(int aX){ x = aX; }
}
