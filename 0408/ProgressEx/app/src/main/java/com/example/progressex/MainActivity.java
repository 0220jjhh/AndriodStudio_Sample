package com.example.progressex;

import android.app.ProgressDialog;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView progressTV;
    ProgressBar progressBar;
    ProgressDialog progressDialog;
    Handler handler;
    int progressValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressTV = (TextView)findViewById(R.id.progressTV);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        handler = new Handler();  //반드시 android.os 를 사용해야 한다.
        //프로그래스의 값을 스레드로 변경시켜보자
        //주의할 사항은 안드로이드는 작업스레드에서 UI를 절대 변경시킬 수 없다.
        //시작하자마자 위에 내용은 움직이게 만들어보자
        //숫자의 변경은 스레드를 이용하자
        new Thread(new Runnable() {
            @Override
            public void run() {
                //이 안의 내용이 별도의 스레드로 작동된다.
                progressValue++;
                //안드로이드는 작업스레드에서 UI를 절대 변경시킬 수 없다.
                //progressTV.setText("진행상태 : " + progressValue + "%"); 따라서 이렇게 할 수 없음
                //UI를 변경하기 위해서는 Handler객체를 이용한다. Handler의 post메서드를 호출하여 UI변경
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //여기서 UI를 변경한다.
                        progressTV.setText("진행상태 : " + progressValue + "%");
                        progressBar.setProgress(progressValue);
                    }
                });
                try { Thread.sleep(100);
                } catch (InterruptedException e){;}
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressTV.setText("진행상태 : 작업완료");
                    }
                });
            }
        }).start();
    }

    public void viewProgress(View view){
        //여기서는 프로그래스 다이알로그를 띄워보자
        progressDialog = new ProgressDialog(this);
        //progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setTitle("다운로드");
        progressDialog.setMessage("다운로드 작업중입니다.\n완료될때까지 기다려주세요.");
        progressDialog.setIcon(android.R.drawable.ic_dialog_dialer);
        progressDialog.setCancelable(false); //취소를 못하게 함
        progressDialog.show(); //띄우기
        new Thread(new Runnable() {
            public void run() {
                    @Override
                        try{Thread.sleep(30000);}
                        catch (InterruptedException e) {;} //3초 대기
                        progressDialog.dismiss(); //닫기
                }).start();
    }}
}
