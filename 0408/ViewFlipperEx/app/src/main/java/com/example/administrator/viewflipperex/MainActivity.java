package com.example.administrator.viewflipperex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.viewflipperex.R;

public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    int index=0;
    TextView infoTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper)findViewById(R.id.viewFlipper);
        infoTV = (TextView) findViewById(R.id.infoTV);
        // 뷰플리퍼에 뷰를 등록하자....
        for(int i=0;i<10;i++){
            ImageView imageView = new ImageView(this);
            imageView.setImageResource(R.drawable.gametitle_01 + i);
            viewFlipper.addView(imageView);
        }
        // viewFlipper.startFlipping();
    }
    float downX, upX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            downX = event.getX();
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            upX = event.getX();
            if(downX>upX) {
                index = ++index==10 ? 0 : index; // 증가되다가 10이되면 다시 0으로
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.wallpaper_open_enter));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.wallpaper_open_exit));
                viewFlipper.showPrevious();
            }else if(downX<upX) {
                index = --index==-1 ? 9 : index; // 감소되다가 -1이되면 다시 9로
                viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
                viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_out));
                viewFlipper.showNext();
            }
            String temp = "";
            for(int i=0;i<10;i++){
                temp += i==index ? "●" : "○";
                if(i<9) temp += " ";
            }
            infoTV.setText(temp);
        }
        return super.onTouchEvent(event);
    }
}
