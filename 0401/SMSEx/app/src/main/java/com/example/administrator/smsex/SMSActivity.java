package com.example.administrator.smsex;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SMSActivity extends AppCompatActivity {
    TextView smsTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        smsTV = (TextView)findViewById(R.id.smsTV);

        Intent intent = getIntent();
        String date = intent.getStringExtra("date");
        String address = intent.getStringExtra("address");
        String content = intent.getStringExtra("content");
        smsTV.setText(address + "에서\n" + date+"에 보낸문자\n" + content);
    }
    public void goBack(View view){ finish(); }
}
