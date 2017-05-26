package com.example.threadex03;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ProgressHandler extends Handler {
    TextView infoTV;
    ProgressBar progressBar;
    public ProgressHandler(TextView infoTV,ProgressBar progressBar) {
        this.infoTV = infoTV;
        this.progressBar = progressBar;
    }
    @Override
    public void handleMessage(Message msg) { // handleMessage메서드를 오버라이딩 한다.
        super.handleMessage(msg);

        //infoTV.setText(msg.obj.toString()); //객체 받을 때
        //progressBar.setProgress(msg.arg1); //int 받을 때

        //bundle로 받기
        Bundle bundle =msg.getData();
        infoTV.setText(bundle.getString("text"));
        progressBar.setProgress(bundle.getInt("value"));
    }
}
