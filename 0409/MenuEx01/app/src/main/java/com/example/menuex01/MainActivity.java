package com.example.menuex01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    Button button1, button2, button3;
    boolean isLogin = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView)findViewById(R.id.tv);
        button1 = (Button)findViewById(R.id.btn1);
        button2 = (Button)findViewById(R.id.btn2);
        button3 = (Button)findViewById(R.id.btn3);
        //길게 누르면 나타나는 메뉴는 반드시 등록을 해줘야 한다.
        //두개의 메소드를 오버라이딩 해야 한다.
        registerForContextMenu(button1);
        registerForContextMenu(button2);
    }
    //길게 누를 때 나타나는 메뉴 지정
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        int id = v.getId(); //어떤 뷰에서 눌렀는지를 판단하기 위하여 id 값을 읽는다.
        switch (id){
            case R.id.btn1:
                getMenuInflater().inflate(R.menu.menu,menu);
                break;
            case  R.id.btn2:
                menu.setHeaderTitle("타이틀");
                menu.add(0,1,100,"빨강");
                menu.add(0,2,100,"파랑");
                SubMenu subMenu = menu.addSubMenu("글씨체 설정");
                subMenu.add(1,6,menu.NONE,"굴림체");
                subMenu.add(1,7,menu.NONE,"이탤릭체");
                break;
        }
    }
    //길게 누를 때 나타나는 메뉴 선택시 행동 지정
    @Override
    public boolean onContextItemSelected(MenuItem item){

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        menu.removeItem(11);
        if(isLogin)
            menu.add(0, 11, 200, "로그아웃");
        else
            menu.add(0,11,200,"로그인");
        isLogin=!isLogin;
        return super.onPrepareOptionsMenu(menu);
    }

    //옵션메뉴가 나타나기 위해서는 메소드를 오버라이딩 해야한다.
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        menu.add(0,4,100,"추가된 메뉴");
        return super.onCreateOptionsMenu(menu);
    }
    //옵션메뉴를 선택했을 떄 뭔가를 하고 싶다면 onOptionsItemSelected 메소드를 오버라이딩 해야한다.
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu1:
                tv.setTextColor(Color.RED);
                break;
            case R.id.menu2:
                tv.setTextColor(Color.GREEN);
                break;
            case R.id.menu3:
                tv.setTextColor(Color.BLUE);
                break;
            case  4:
                tv.setText("추가된 메뉴 선택");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewMenu (View view){
        PopupMenu popupMenu = new PopupMenu(this,view);
        Menu menu = popupMenu.getMenu();
        menu.add(0,31,1,"될까?");
        menu.add(0,32,2,"된당");
        //리스너 지정
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
            @Override

            public boolean onMenuItemClick(MenuItem item) {
                int id = item.getItemId();
                switch (id){
                    case 31: break;
                    case 32: break;
                }
                Toast.makeText(getApplicationContext(),item.getTitle(), Toast.LENGTH_SHORT).show();
                return false;
        }
        });
        popupMenu.show();
    }
}
