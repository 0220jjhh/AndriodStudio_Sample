package com.example.administrator.smsex;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MySMSReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // 현재 보내온 글로벌 이벤트가 SMS라면.....
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Toast.makeText(context,"문자수신",Toast.LENGTH_LONG).show();
            // SMS 메시지를 파싱합니다.
            Bundle bundle = intent.getExtras(); // 전달된 인텐트에서 모든 데이터를 받는다.
            Object[] objs = (Object[])bundle.get("pdus"); // 그 중에서 문자만 받는다. 그런데 Object배열이다.
            // Object배열을 문자로 파싱 가능한 SmsMessage객체로 변환을 해야 한다.
            SmsMessage[] messages = new SmsMessage[objs.length]; // 똑같은 크기의 배열 선언
            for(int i = 0; i < objs.length; i++) { // 전체 반복
                // PDU 포맷으로 되어 있는 메시지를 복원합니다.
                // Object타입을 SmsMessage타입으로 변경을 한다. 버전에따라 해석하는 방법이 변했다....
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // API 23 이상
                    String format = bundle.getString("format");
                    messages[i] = SmsMessage.createFromPdu((byte[]) objs[i], format);
                } else {
                    messages[i] = SmsMessage.createFromPdu((byte[]) objs[i]);
                }
            }
            // 여기서부터 문자를 읽어보자~~~~~~~ 여기서는 첫번째 문자만 읽었다.....
            // SMS 수신 시간 확인
            Date receivedDate = new Date(messages[0].getTimestampMillis());
            Log.i("문자수신", "SMS received date : " + receivedDate.toString());
            // SMS 발신 번호 확인
            String sender = messages[0].getOriginatingAddress();
            Log.i("문자수신", "SMS sender : " + sender);
            // SMS 메시지 확인
            String contents = messages[0].getMessageBody().toString();
            Log.i("문자수신", "SMS contents : " + contents);

            // 여기서 사용자 화면을 띄워서 문자메세지를 보여준다.......
            Intent intent1 = new Intent(context,SMSActivity.class);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss");
            intent1.putExtra("date",sdf.format(receivedDate));
            intent1.putExtra("address",sender);
            intent1.putExtra("content",contents);
            context.startActivity(intent1);
        }
    }
}
