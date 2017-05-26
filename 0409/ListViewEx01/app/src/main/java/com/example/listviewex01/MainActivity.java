package com.example.listviewex01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    ListView listView;
    String[] datas = "한개, 두개, 세개, 네개, 다섯개, 여섯개, 일곱개, 여덟개, 아홉개, 열개".split(",");

    //XML의 배열 읽어오기
    String strArray[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //리스트뷰를 찾는다.
        listView = (ListView)findViewById(R.id.listView);
        strArray = getResources().getStringArray(R.array.strArray);

        //어뎁터를 만든다. 어뎁터 : 많은 양의 데이터를 가지고 있으면서 선택위젯에 데이터를 표시하도록 뷰를 만들어서 리턴하는 역할
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_checked,strArray);
        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);

        //어뎁터를 붙인다.
        listView.setAdapter(adapter);

        //리스너를 지정한다.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),strArray[position] + "를 선택함",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
