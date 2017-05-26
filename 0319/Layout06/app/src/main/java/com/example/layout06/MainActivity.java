package com.example.layout06;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.VectorEnabledTintResources;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView1;
    boolean isFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
    }

    public void changeImage(View view){
        if(isFlag)
            imageView1.setImageResource(R.drawable.image02);
        else
            imageView1.setImageResource(R.drawable.image01);
        isFlag = !isFlag;
    }
}
