package com.example.jetboyex;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by 박지현 on 2017-04-22.
 */

public class ShipView extends View {
    Bitmap bitmap;
    Paint paint;
    int width, height;

    public ShipView(Context context) {
        super(context);
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.ship2_1);
        width = bitmap.getWidth();
        height = bitmap.getHeight();
        paint = new Paint();
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //그림 출력
        canvas.drawBitmap(bitmap,0,0,paint);
        Matrix matrix = new Matrix();
        matrix.setScale(-1,1);
        Bitmap hBitmap = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,false);
        canvas.drawBitmap(hBitmap,width,0,paint);
        matrix.setScale(1,-1);
        Bitmap vBitmap = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,false);
        canvas.drawBitmap(vBitmap,0,height,paint);
        matrix.setScale(-1,-1);
        Bitmap hvBitmap = Bitmap.createBitmap(bitmap,0,0,width,height,matrix,false);
        canvas.drawBitmap(hvBitmap,width,height,paint);

        Bitmap sBitmap = Bitmap.createScaledBitmap(bitmap,width*2,height*2,false); //4배 확대
        canvas.drawBitmap(sBitmap,0,height*2,paint);

        Bitmap tBitmap = Bitmap.createBitmap(bitmap,0,0,width/2,height/2); //일부분만 잘라내서
        canvas.drawBitmap(tBitmap,0,height*4,paint);

        Bitmap ttBitmap = Bitmap.createBitmap(sBitmap,width,height,width,height); //일부분만 잘라내서
        canvas.drawBitmap(ttBitmap,0,height*5,paint);
    }
}
