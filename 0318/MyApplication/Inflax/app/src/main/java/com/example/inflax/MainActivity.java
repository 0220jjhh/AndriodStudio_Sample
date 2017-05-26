package com.example.inflax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout)findViewById(R.id.mainLayout);

    }
    public void readLayout(View view){
        int id = view.getId();
        mainLayout.removeAllViews(); //이미 있는 뷰들을 모두 지움
        switch (id){
            case R.id.btn1:
                LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.layout1,mainLayout);
                ImageButton button = (ImageButton) findViewById(R.id.ib);
                button.setOnClickListener(new View.OnClickListener() {
                    public  void onClick(View v){
                        // Toast.makeText(this,"누름",Toast.LENGTH_LONG).show(); // Error이유 : 여기서의 this는 onclicklistner이기 때문에.
                        //첫번째 인수는 this가 아니라 MainActivity 여야 함.
                        Toast.makeText(getApplicationContext(),"누름",Toast.LENGTH_LONG).show();
                    }
                });
                break;
            case R.id.btn2:
                getLayoutInflater().inflate(R.layout.layout2,mainLayout);
                break;
        }
    }
}
