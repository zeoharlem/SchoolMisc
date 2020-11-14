package com.zeoharlem.gads.schoolmisc.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

import com.zeoharlem.gads.schoolmisc.Utils.L;

public class OtpVerifyResponse extends BroadcastReceiver {

    private EditText editTextOtp;
    private static String otpTextResponse;
    private static final String SMS_RECEIVED    = "android.provider.Telephony.SMS_RECEIVED";
    private static final String TAG             = "SmsBroadcastReceiver";

    private iOtpVerifyResponseListener mOtpVerifyResponseListener   = null;

    public iOtpVerifyResponseListener getOtpVerifyResponseListener() {
        return mOtpVerifyResponseListener;
    }

    public void setOtpVerifyResponseListener(Context context) {
        mOtpVerifyResponseListener  = (iOtpVerifyResponseListener) context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle dataBundle   = intent.getExtras();
        if(intent.getAction().equalsIgnoreCase(SMS_RECEIVED)){
            SmsMessage[] smsMessages    = Telephony.Sms.Intents.getMessagesFromIntent(intent);
            if(dataBundle != null){
                for(SmsMessage smsMessage : smsMessages){
                    String messageBody  = smsMessage.getMessageBody();
                    //otpTextResponse     = messageBody.split("\\s+")[0].trim();
                    L.l(context, ": code:"+smsMessage.getDisplayMessageBody());
                    if(mOtpVerifyResponseListener != null) {
                        mOtpVerifyResponseListener.onSmsReceived(
                                smsMessage.getDisplayOriginatingAddress(),
                                messageBody.split("\\s+")[0].trim()
                        );
                    }
                }
            }
        }

    }

    public interface iOtpVerifyResponseListener{
        void onSmsReceived(String sender, String smsMessage);
    }
}
