package com.example.theadex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
//value값은 스레드에서 변경하고 표시는 main 스레드에서 함
public class MainActivity extends AppCompatActivity {
    TextView textView;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.valueTV);
        //스레드 시작
        BackgroundThread thread = new BackgroundThread();
        thread.setDaemon(true);
        thread.start();
    }
    public void updateUI(View view){
        textView.setText("현재값 : " + value);
    }
    class BackgroundThread
            extends Thread{
        @Override
        public void run() {
            while(true) {
                value++;
                Log.i("BackgroundThread", "현재값 : " + value);
                //textView.setText("현재값 : " + value); 에러! 스레드에서는 UI갱신이 불가능하기때문에
                try {
                    Thread.sleep(100); //0.1초마다 숫자가 바뀜
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
