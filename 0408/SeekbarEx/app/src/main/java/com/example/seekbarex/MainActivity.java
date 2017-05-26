package com.example.seekbarex;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout;
    TextView redTV,greenTV,blueTV;
    SeekBar redSeek,greenSeek,blueSeek;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        redTV = (TextView)findViewById(R.id.redTV);
        blueTV = (TextView)findViewById(R.id.blueTV);
        greenTV = (TextView)findViewById(R.id.greenTV);
        mainLayout = (LinearLayout) findViewById(R.id.mainLayout);

        redSeek = (SeekBar)findViewById(R.id.redSeek);
        blueSeek = (SeekBar)findViewById(R.id.blueSeek);
        greenSeek = (SeekBar)findViewById(R.id.greenSeek);

        SeekListener listener = new SeekListener();
        redSeek.setOnSeekBarChangeListener(listener);
        blueSeek.setOnSeekBarChangeListener(listener);
        greenSeek.setOnSeekBarChangeListener(listener);

        mainLayout.setBackgroundColor(Color.rgb(100,50,50));
    }
    class SeekListener implements SeekBar.OnSeekBarChangeListener{
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            int id = seekBar.getId();
            /*switch (id){
                case R.id.redSeek:
                    int r = redSeek.getProgress();
                    redTV.setText("Red : " + r);
                    break;
                case R.id.blueSeek:
                    int b = blueSeek.getProgress();
                    blueTV.setText("Blue : " + b);
                    break;
                case R.id.greenSeek:
                    int g = greenSeek.getProgress();
                    greenTV.setText("Green : " + g);
                    break;
            }*/

            int r = redSeek.getProgress();
            redTV.setText("Red : " + r);
            int g = greenSeek.getProgress();
            greenTV.setText("Green : " + g);
            int b = blueSeek.getProgress();
            blueTV.setText("Blue : " + b);

            //세가지 색을 섞어서 배경색 만들기ㅏ
            mainLayout.setBackgroundColor(Color.rgb(r,g,b));

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
