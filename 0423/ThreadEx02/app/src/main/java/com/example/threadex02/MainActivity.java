package com.example.threadex02;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView infoTV;
    ProgressBar progressBar;
    Handler handler;
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTV = (TextView)findViewById(R.id.infoTV);
        progressBar = (ProgressBar)findViewById(R.id.progress);
        handler = new Handler(); //핸들러 객체 선언
        ProgressThread thread  = new ProgressThread();
        thread.setDaemon(true);
        thread.start();
    }

    class ProgressThread extends Thread{
        @Override
        public void run(){
            super.run();
            while (value<progressBar.getMax()){
                value++;
                //infoTV.setText("현재상태 : " + value/10 + "%");
                //progressBar.setProgress(value); //프로그레스 값 변경
                //핸들러를 통하여 메인스레드에게 UI 갱신을 처리하도록 해야한다. 여기서는 값 변경 불가!
                //이때 post메소드를 호출한다. post메소드 Runnable객체를 인수로 받아야 한다.
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //여기서 UI를 갱신하면 된다.
                        infoTV.setText("현재상태 : " + value/10 + "%");
                        progressBar.setProgress(value); //프로그레스 값 변경
                    }
                });
                Log.d("ProgressThread","현재값 : " + value);
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //여기서 UI를 갱신하면 된다.
                    infoTV.setText("현재상태 : 작업 완료");
                }
            });
            //infoTV.setText("현재상태 : 작업 완료");
        }
    }
}
