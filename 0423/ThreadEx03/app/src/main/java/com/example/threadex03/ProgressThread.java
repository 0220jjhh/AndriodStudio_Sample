package com.example.threadex03;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;


public class ProgressThread extends Thread {
    int value=0;
    ProgressBar progressBar;
    Handler handler;

    public ProgressThread(ProgressBar progressBar, Handler handler) {
        this.progressBar = progressBar;
        this.handler = handler;
    }

    @Override
    public void run() {
        while(value<progressBar.getMax()){
            value++;
            Message message = handler.obtainMessage(); // 핸들러에서 Message객체를 얻는다.
            //obj는 객체 보낼 때
            message.obj = "현재 상태 : "+ value + "%"; // 데이터를 넣는다.
            //arg1, arg2는 int 보낼 때
            message.arg1 = value;
            //bundle은 여러개 보낼 때
            Bundle bundle = new Bundle();
            bundle.putString("text", "현재 상태 : "+ value + "%");
            bundle.putInt("value", value);
            message.setData(bundle);
            handler.sendMessage(message); // 핸들러에게 데이터를 보낸다. handleMessage메서드 실행해라.
            try {
                Thread.sleep(100);
            } catch (
                    InterruptedException e) {;}
        }

        Message message = handler.obtainMessage(); // 핸들러에서 Message객체를 얻는다.
        message.obj = "현재 상태 : 작업 완료"; // 데이터를 넣는다.
        message.arg1 = value;

        Bundle bundle = new Bundle();
        bundle.putString("text","현재 상태 : 작업완료");
        bundle.putInt("value", value);
        message.setData(bundle);


        handler.sendMessage(message); // 핸들러에게 데이터를 보낸다. handleMessage메서드 실행해라.

    }
}
