package com.example.toastex;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText offsetX, offsetY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        offsetX = (EditText)findViewById(R.id.offsetX);
        offsetY = (EditText)findViewById(R.id.offsetY);
    }

    public void viewToast(View view){
        int x = Integer.parseInt(offsetX.getText().toString());
        int y = Integer.parseInt(offsetY.getText().toString());
        Toast toast = Toast.makeText(this,"설정한 위치", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER,x,y); //가운데를 기준으로 x,y만큼 이동
        toast.show();
    }

    public void  viewToast2(View view) {
        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.toast,(ViewGroup)findViewById(R.id.mainLayout));
        TextView textView = (TextView) view1.findViewById(R.id.toastTV);
        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view1);
        toast.show();
    }

    public void  viewToast3(View view3) {

        ImageView imageView = new ImageView(this);
        imageView.setImageResource(R.drawable.image);

        Toast toast = new Toast(this);
        toast.setGravity(Gravity.CENTER,0,0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(imageView);
        toast.show();
    }
}
