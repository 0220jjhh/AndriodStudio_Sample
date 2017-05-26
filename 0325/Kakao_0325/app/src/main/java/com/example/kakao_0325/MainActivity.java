package com.example.kakao_0325;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView[] textViews = new TextView[4];
    Button[] buttons = new Button[11];
    int count; //몇 번 버튼을 눌렀는지 확인
    String str=""; //눌린 번호를 저장

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //객체를 찾아서 변수에 대입
        for(int i = 0; i<textViews.length ; i++) textViews[i]=(TextView)findViewById(R.id.tv1 + i);
        for(int i = 0; i<buttons.length ; i++) buttons[i]=(Button) findViewById(R.id.btn1 + i);

    }

    public void clickMe(View view){
        int id = view.getId(); //이벤트가 발생한 뷰의 id를 얻어온다.

        //버튼의 텍스트를 읽어서 저장하고 위의 텍스트뷰에 표시를 해주면 된다.
        Button button = (Button)view; //인수로 넘어온 위젯을 버튼으로 변경
        String text = button.getText().toString();

        if(text.equals("◀")){
            if(count>0){
                count--;
                str = str.substring(0,str.length()-1);
            }
        } else {
            if(count<4) {
                count++;
                str += text;
            }
        }
        Log.d("입력된 번호", str + "(" + count + ")");

        for(int i = 0; i<textViews.length ; i++) textViews[i].setText("○");
        for(int i = 0; i<count ; i++) textViews[i].setText("●");
    }
}
