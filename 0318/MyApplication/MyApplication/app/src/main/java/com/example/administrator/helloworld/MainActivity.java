package com.example.administrator.helloworld;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button secondBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //괄호 안에 있는 것을 보여줌, R.layout=layout에 있는 것을 보여달라는 것

        secondBtn = (Button) findViewById(R.id.secondBtn);
        secondBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
                startActivity(intent);
            }
        });
    }

    public void clickMe(View view){
        Toast.makeText(this,"왜눌러 아프잖아!!!",Toast.LENGTH_LONG).show();
    }

    public void phone(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01036928374"));
        startActivity(intent);
    }

    public void newActivity(View view){
        Intent intent = new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
}
