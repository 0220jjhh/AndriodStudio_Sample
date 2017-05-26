package com.example.dialogex;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void viewDialog(View view){
        int id = view.getId();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("대화상자연습");
        builder.setIcon(android.R.drawable.ic_dialog_email);
        builder.setMessage("버튼 몇개인지 확인");
        switch (id){
            case R.id.btn3:
                builder.setNeutralButton("무시", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "무시", Toast.LENGTH_SHORT).show();
                    }
                });
            case R.id.btn2:
                builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "취소", Toast.LENGTH_SHORT).show();
                    }
                });
            case R.id.btn1:
                builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "확인", Toast.LENGTH_SHORT).show();
                    }
                });
        }
        builder.create().show();
    }

}
