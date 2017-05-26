package com.example.threadex03;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView infoTV;
    ProgressBar progressBar;
    ProgressHandler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        infoTV = (TextView)findViewById(R.id.infoTV);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        handler = new ProgressHandler(infoTV, progressBar);
        ProgressThread thread = new ProgressThread(progressBar,handler);
        thread.setDaemon(true);
        thread.start();
    }


}
