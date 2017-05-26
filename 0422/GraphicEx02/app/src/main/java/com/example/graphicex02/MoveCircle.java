package com.example.graphicex02;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Trace;
import android.view.View;

/**
 * Created by 박지현 on 2017-04-22.
 */

public class MoveCircle extends View{
    Paint paint;
    Handler handler;
    Bitmap memoryBitmap;
    Canvas memoryCanvas;
    float nx=50,ny=50;
    int width, height; //화면의 크기 저장
    int swx=1, swy=1; //화면의 경계에 도달하면 충돌해 반전을 시키기 위한 변수
    public MoveCircle(Context context){
        super(context);
        paint = new Paint();
        handler = new Handler();
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        width = w; height = h;
        memoryBitmap = Bitmap.createBitmap(w,h, Bitmap.Config.ARGB_8888);
        memoryCanvas = new Canvas();
        memoryCanvas.setBitmap(memoryBitmap);
        //스레드 시작
        MoveThread thread = new MoveThread(); //스레드 객체 생성
        thread.setDaemon(true);//스레드를 데몬 스레드로 지정 (데몬 스레드 : 프로그램이 종료하면 스레드도 종료하는 스레드)
        thread.start(); //스레드 시작
        drawing();
    }

    private void drawing() {
        //여기서 그리기 작업을 함
        memoryCanvas.drawColor(Color.WHITE);
        paint.setColor(Color.RED);
        memoryCanvas.drawCircle(nx,ny,30,paint);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        if(memoryBitmap!=null){
            canvas.drawBitmap(memoryBitmap,0,0,null);
        }
    }
    //스레드에서 nx,ny 좌표를 변경
    class MoveThread extends Thread { //스레드 생성 방법 : 스레드를 상속받거나 runnable 인터페이스를 구현하거나
        @Override
        public void run() {
            //좌표를 변경한다.
            while (true) {
                nx += swx * 5;
                ny += swy * 5;
                //좌표가 바뀌었으니 화면을 다시 그림
                //안드로이드에서는 절대로 스레드에서 UI를 변경할 수 없다.
                //따라서 핸들러 객체의 post 메소드를 통하여 UI 갱신을 알려준다.
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        drawing();
                    }
                });
                if(nx<=30||nx>=width-30) swx *= -1; //경계에 도달하면 반전시킴
                if(ny<=30||ny>=height-30) swy *= -1; //경계에 도달하면 반전시킴
                //너무 빠르니까 잠시 대기시킴
                try {
                    Thread.sleep(10);//0.1초 대기
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
