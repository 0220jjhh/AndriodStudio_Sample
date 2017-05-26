package com.example.threadex05;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    EditText urlET;
    TextView sourceTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlET = (EditText)findViewById(R.id.urlET);
        sourceTV = (TextView)findViewById(R.id.sourceTV);
    }
    public void readSource(View view){
        String urlAddress = urlET.getText().toString();
        new SourceTask().execute(urlAddress);
    }
    // 소스를 읽어오는 클래스를 만들자.......
    class SourceTask extends AsyncTask<String,Void,Void>{
        StringBuffer sb;
        @Override
        protected Void doInBackground(String... params) {
            String urlAddress = params[0];
            sb = new StringBuffer();
            try {
                URL url = new URL(urlAddress);
                Scanner sc = new Scanner(url.openStream(),"UTF-8");
                while (sc.hasNextLine()){
                    String t = sc.nextLine();
                    if(t!=null && t.trim().length()>0)  sb.append(t.trim() + "\n");
                }
                sc.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            sourceTV.setText(sb.toString());
        }
    }
}
