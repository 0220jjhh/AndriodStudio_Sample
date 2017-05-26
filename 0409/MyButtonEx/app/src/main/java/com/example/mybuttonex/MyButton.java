package com.example.mybuttonex;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

/**
 * Created by 박지현 on 2017-04-09.
 */

public class MyButton extends android.support.v7.widget.AppCompatButton{
    Paint paint;
    public MyButton(Context context) {
        super(context);
        paint = new Paint();
        setBackgroundResource(R.drawable.title_bitmap_button_normal);
    }

    public MyButton(Context context, AttributeSet attrs) {
        super(context, attr

                s);
        paint = new Paint();
        setBackgroundResource(R.drawable.title_bitmap_button_normal);
    }
    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        setTextColor(Color.WHITE);
        setTextSize(16f);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN) {
            setBackgroundResource(R.drawable.title_bitmap_button_clicked);
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            setBackgroundResource(R.drawable.title_bitmap_button_normal);
        }
        invalidate();
        return super.onTouchEvent(event);
    }
}
