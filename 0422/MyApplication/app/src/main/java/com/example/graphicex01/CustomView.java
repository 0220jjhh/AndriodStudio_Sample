package com.example.graphicex01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

/**
 * Created by 박지현 on 2017-04-22.
 */

public class CustomView extends View {
    Paint paint;
    float nx,ny;
    public CustomView(Context context) {
        super(context);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        Random rnd = new Random(); //난수 발생기
        paint.setColor(Color.rgb(rnd.nextInt(256),rnd.nextInt(256),rnd.nextInt(256)));
        canvas.drawCircle(nx,ny,50,paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            nx=event.getX();
            ny=event.getY();
            Log.d("마우스 위치",String.format("(%f,%f)",nx,ny));
            invalidate(); //화면을 다시 그림
        }
        return super.onTouchEvent(event);
    }
}