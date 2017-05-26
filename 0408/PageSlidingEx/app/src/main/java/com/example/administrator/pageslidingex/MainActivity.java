package com.example.administrator.pageslidingex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView slidingTV;
    Button button;
    Animation leftAnim, rightAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        slidingTV = (TextView) findViewById(R.id.slidingTV);
        button = (Button)findViewById(R.id.btn);
        // 애니메이션 읽기
        leftAnim = AnimationUtils.loadAnimation(this,R.anim.left);
        rightAnim = AnimationUtils.loadAnimation(this,R.anim.right);
        // 리스너 지정
        leftAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                slidingTV.setVisibility(View.VISIBLE);
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                button.setText("Close");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {;}
        });
        rightAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {;}
            @Override
            public void onAnimationEnd(Animation animation) {
                slidingTV.setVisibility(View.INVISIBLE);
                button.setText("Open");
            }
            @Override
            public void onAnimationRepeat(Animation animation) {;}
        });
    }
    public void openClose(View view){
        Button button1 = (Button)view;
        String title = button1.getText().toString();
        if(title.equalsIgnoreCase("open")){
            slidingTV.startAnimation(leftAnim);
        }else{
            slidingTV.startAnimation(rightAnim);
        }
    }

    // 버튼이 아닌 터치 이벤트를 이용하여 보자
    float downX, upX;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN){ // 눌렸을때
            downX = event.getX();
        }else if(event.getAction()==MotionEvent.ACTION_UP){ // 놓았을때
            upX = event.getX();
            if(downX>upX){ // 오른쪽에서 왼쪽으로 드래그
                slidingTV.startAnimation(leftAnim);
            }else if(downX<upX){ // 왼쪽에서 오른쪽으로 드래그
                slidingTV.startAnimation(rightAnim);
            }
        }
        return super.onTouchEvent(event);
    }
}
