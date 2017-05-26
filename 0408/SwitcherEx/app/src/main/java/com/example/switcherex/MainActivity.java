package com.example.switcherex;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends Activity {
    //글자 전환에 사용될 문자열 배열
    private static final String[] TEXTS = { "Background", "XP", "Sky" };
    //그림 전환에 사용될 이미지 리소스 배열
    private static final int[] IMAGES = { R.drawable.background, R.drawable.sample,
            R.drawable.sample_2 };
    private int mPosition = 0;
    private TextSwitcher mTextSwitcher;
    private ImageSwitcher mImageSwitcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //위젯 찾기
        mTextSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);
        //팩토리를 지정한다
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(getApplicationContext());
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        //애니메이션 지정
        mTextSwitcher.setInAnimation(this, android.R.anim.fade_in);
        mTextSwitcher.setOutAnimation(this, android.R.anim.fade_out);

        mImageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        mImageSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                ImageView imageView = new ImageView(MainActivity.this);
                return imageView;
            }
        });
        mImageSwitcher.setInAnimation(this, android.R.anim.slide_in_left);
        mImageSwitcher.setOutAnimation(this, android.R.anim.slide_out_right);

        onSwitch(null); //이벤트를 호출, 최초 실행시 화면에 보이는 것
    }
    //버튼 이벤트 지정
    public void onSwitch(View view) {
        mTextSwitcher.setText(TEXTS[mPosition]); //텍스트 스위쳐 : 글자 변경
        mImageSwitcher.setBackgroundResource(IMAGES[mPosition]);//이미지 스위터 : 이미지 변경
        mPosition = (mPosition + 1) % TEXTS.length;// 위치를 바꿈
    }
}
