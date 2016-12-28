package com.shemul.dxball;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


public class Bar {
    private float barLeft, barTop, barRight, barBottom;

    public void setBar(Canvas canvas) {
        barLeft = (canvas.getWidth()/2)-(canvas.getWidth()/6);
        barRight = barLeft+(canvas.getWidth()/3);
        barBottom = canvas.getHeight()-30;
        barTop = barBottom - 40;
    }

    public void drawBar(Canvas canvas, Paint paint) {
        barRight = barLeft+(canvas.getWidth()/3);
        paint.setColor(Color.parseColor("#607D8B"));
        canvas.drawRect(barLeft, barTop, barRight, barBottom, paint);
       
    }

    public float getBarLeft(){
        return barLeft;
    }

    public float getBarBottom() {
        return barBottom;
    }

    public float getBarRight() {
        return barRight;
    }

    public float getBarTop() {
        return barTop;
    }

    public void setBarBottom(float barBottom) {
        this.barBottom = barBottom;
    }

    public void setBarTop(float barTop) {
        this.barTop = barTop;
    }

    public void setBarLeft(float barLeft) {
        this.barLeft = barLeft;
    }

    public void setBarRight(float barRight) {
        this.barRight = barRight;
    }
}
