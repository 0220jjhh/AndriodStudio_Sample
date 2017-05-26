package com.example.threadex04;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar progressBar;
    TextView infoTV;
    ProgressTask progressTask;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        infoTV = (TextView)findViewById(R.id.infoTV);
    }
    public void btnClick(View view){
        int id = view.getId();
        switch (id){
            case R.id.btn1:
                progressTask = new ProgressTask();
                progressTask.execute(100); //실행
                break;
            case R.id.btn2:
                progressTask.cancel(true);
                break;
        }
    }

    //여기에서 클래스를 만듬
    //첫번째 : doInBackground 에 전달될 자료형
    //두번째 : onProgressUpdate에 전달될 자료형
    //세번째 : onPostExecute에 전달될 자료형
    class ProgressTask extends AsyncTask<Integer,Integer,Void> {  //꺽쇄 안에는 기본 자료형은 쓸 수 없음(ex.void는 안되고 Void만 가능)
        @Override
        protected Void doInBackground(Integer... params) { //스레드에서 처리할 작업
            int value = 0;
            int max = params[0]; //프로그래스의 최대값을 인수로 받아서 처리
            while (isCancelled()==false) {
                value++;
                publishProgress(value);
                if(value==max) break;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute(){ //스레드 시작 전 작업
            super.onPreExecute();
            progressBar.setProgress(0);
            infoTV.setText("진행상태 : 0%");
        }

        @Override
        protected void onProgressUpdate(Integer... values){  //스레드 중간 작업(... = 가변인수=인수의 갯수가 정해져 있지 않다는 의미)
            super.onProgressUpdate(values);
            int value = values[0]; //넘어온 첫번째 인수를 받는다.
            progressBar.setProgress(value);
            infoTV.setText("진행상태 : "+value+"%");
        }

        @Override
        protected void onPostExecute(Void aVoid){  //스레드 종료 후 작업
            super.onPostExecute(aVoid);
            progressBar.setProgress(100);
            infoTV.setText("진행상태 : 작업완료");
        }

        @Override
        protected void onCancelled() { //취소를 사용하려면 만들어야함
            super.onCancelled();
            progressBar.setProgress(0);
            infoTV.setText("진행상태 : 취소됨");
        }
    }
}
