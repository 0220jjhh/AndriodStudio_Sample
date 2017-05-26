package com.example.jetboyex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by 박지현 on 2017-04-22.
 */

public class ShipView2 extends View {
    Paint paint;
    Bitmap bgBitmap, memoryBitmap, morpheaBitmap;
    Bitmap[] ships = new Bitmap[4];
    Canvas memoryCanvas;
    Handler handler;
    int index,nx,ny,width,height;
    int imageX=0, imageY=0;

    public ShipView2(Context context) {
        super(context);
        //그림 읽기
        morpheaBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.morphea);
        width = morpheaBitmap.getWidth()/14;
        height = morpheaBitmap.getHeight()/8;

        bgBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.background_a); //배경
        ships[0] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2_hit_1);
        ships[1] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2_hit_2);
        ships[2] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2_hit_3);
        ships[3] = BitmapFactory.decodeResource(getResources(),R.drawable.ship2_hit_4);
        paint = new Paint();
        handler = new Handler();
        index=nx=ny=0;
    }

    @Override
    protected void onSizeChanged(int w,int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        memoryBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        memoryCanvas = new Canvas();
        memoryCanvas.setBitmap(memoryBitmap);
        ShipThread shipThread = new ShipThread();
        shipThread.setDaemon(true);
        shipThread.start();
        drawing();
    }
    private void drawing(){
        memoryCanvas.drawBitmap(bgBitmap,0,0,paint);//배경 그리기
        memoryCanvas.drawBitmap(ships[index],nx,ny,paint); //비행기 그리기
        //원본에서 그림을 원하는 만큼 잘라낸다.
        Bitmap bitmap = Bitmap.createBitmap(morpheaBitmap,(imageX+3)*width,imageY*height,width,height);
        //잘라낸 그림을 4배 확대
        Bitmap sbitmap = Bitmap.createScaledBitmap(bitmap,width*4,height*4,false);
        memoryCanvas.drawBitmap(sbitmap,150,250,paint);
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            imageY = ++imageY%4;
            drawing();
        }
        return super.onTouchEvent(event);
    }
    @Override
    protected void onDraw(Canvas canvas){
        if(memoryBitmap!=null)
            canvas.drawBitmap(memoryBitmap,0,0,paint);
    }
    class ShipThread extends Thread{
        @Override
        public void run(){
            super.run();
            while (true){
                index=++index%ships.length;
                imageX = ++imageX%3;
                nx += 5;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        drawing();
                    }
                });
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
