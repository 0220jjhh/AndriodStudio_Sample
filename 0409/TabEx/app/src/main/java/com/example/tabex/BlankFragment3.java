package com.example.tabex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

public class BlankFragment3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //프라그먼트에 있는 뷰에 대해 이벤트를 지정하려면
        //1. 화면을 만듬
        View view = inflater.inflate(R.layout.fragment_blank_fragment3,container,false);
        //2. 뷰를 찾음
        Button button = (Button)view.findViewById(R.id.btn3);
        //3. 이벤트 지정
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,"세번째 버튼",Snackbar.LENGTH_LONG).setAction("버튼 누르기", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Log.i("스낵바에서 액션 지정","버튼 누름");
                    }
                }).show();
            }
        });

        //4. 뷰 리턴
        return view;
    }
}
