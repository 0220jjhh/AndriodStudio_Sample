package com.example.layout07;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView[] imageViews = new ImageView[4];
    int index=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<imageViews.length;i++){
            imageViews[i]=(ImageView)findViewById(R.id.iv1+i);
        }
    }
    public  void changeImage(View view){
        /*
        index++;
        if(index==imageViews.length) index=0;
        for(int i=0;i<imageViews.length;i++){
            if(i==index)
                imageViews[i].setVisibility(View.VISIBLE);
            else
                imageViews[i].setVisibility(View.INVISIBLE);
        }
        */

        /*
        index = ++index%imageViews.length;

        for(int i=0;i<imageViews.length;i++){
            if(i==index)
                imageViews[i].setVisibility(View.VISIBLE);
            else
                imageViews[i].setVisibility(View.INVISIBLE);
        }
        */

        /*
        index = ++index%imageViews.length;
        for(int i=0;i<imageViews.length;i++) imageViews[i].setVisibility(View.INVISIBLE);
        imageViews[index].setVisibility(View.VISIBLE);
        */

        imageViews[index].setVisibility(View.INVISIBLE); //현재꺼 숨김
        index = ++index%imageViews.length; //번호 증가
        imageViews[index].setVisibility(View.VISIBLE);  //증가된 그림 보이게
    }
}
