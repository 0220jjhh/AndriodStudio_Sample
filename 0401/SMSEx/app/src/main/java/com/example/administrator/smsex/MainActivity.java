package com.example.administrator.smsex;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 현재 권한을 얻어온다.
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS);

        if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
            // 권한이 부여되었다면
            // 프로그램을 정상적으로 진행......
            Toast.makeText(this, "SMS 수신 권한 있음.", Toast.LENGTH_LONG).show();
        } else {
            // 권한이 부여되어있지 않다면 적절한 조치를 취해야 한다.
            // 주로 권한을 요청하는 코드가 들어간다.....
            Toast.makeText(this, "SMS 수신 권한 없음.", Toast.LENGTH_LONG).show();
            // 권한 변경 이력이 있다면
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.RECEIVE_SMS)) {
                Toast.makeText(this, "SMS 권한 설명 필요함.", Toast.LENGTH_LONG).show();
            } else {
                // 권한 요청을 한다. 콜백메서드를 만들어야 한다.
                ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.RECEIVE_SMS},1004);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // 권한을 요청한 호출 횟수에 따라 case가 늘어난다.
        switch (requestCode){
            case 1004:
                // 권한 요청에 어떻게 응답하였는지 판단하여 적절한 조치를 취한다.
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED ){
                    // 권한을 허가 했다면 응용프로그램을 계속 진행하면된다.
                    Toast.makeText(this,"권한을 허가하였다~~~~",Toast.LENGTH_SHORT).show();
                }else{
                    // 권한을 거부 했다면 응용프로그램을 중지시키거나 경고를 보낸다.
                    Toast.makeText(this,"권한을 거부하였다~~~~",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
