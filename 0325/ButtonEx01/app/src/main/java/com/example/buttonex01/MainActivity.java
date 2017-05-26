package com.example.buttonex01;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nameET, ageET;
    RadioButton rb1, rb2;
    CheckBox cb1, cb2, cb3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameET = (EditText)findViewById(R.id.nameET);
        ageET = (EditText)findViewById(R.id.ageET);
        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb2 = (CheckBox)findViewById(R.id.cb2);
        cb3 = (CheckBox)findViewById(R.id.cb3);
    }

    public void allCheck(View view){
        if(((Button)view).getText().toString().equals("모두선택")) {
            cb1.setChecked(true);
            cb2.setChecked(true);
            cb3.setChecked(true);
            ((Button)view).setText("모두해제");
        } else{
            cb1.setChecked(false);
            cb2.setChecked(false);
            cb3.setChecked(false);
            ((Button)view).setText("모두선택");
        }
    }

    public  void viewResult(View view){
        String name = nameET.getText().toString();
        String age = ageET.getText().toString();
        String gender = rb1.isChecked() ? "남" : "여";
        String hobby = cb1.isChecked() ? cb1.getText().toString() : "";
        hobby += cb2.isChecked() ? " ," + cb2.getText().toString() : "";
        hobby += cb3.isChecked() ? " ," + cb3.getText().toString() : "";

        Toast.makeText(this,
                        name + "씨(" + age + "세, "+ gender + ")\n취미 : " + hobby,
                        Toast.LENGTH_SHORT).show();
    }
}
