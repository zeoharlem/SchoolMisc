package com.zeoharlem.gads.schoolmisc.Receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.EditText;

public class OtpVerifyResponse extends BroadcastReceiver {

    private static EditText editTextOtp;
    private static String otpTextResponse;

    public static EditText getEditTextOtp() {
        return editTextOtp;
    }

    public static void setEditTextOtp(EditText editTextOtp) {
        OtpVerifyResponse.editTextOtp   = editTextOtp;
    }

    public static String getOtpTextResponse() {
        return otpTextResponse;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] smsMessages    = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for(SmsMessage smsMessage : smsMessages){
            String messageBody  = smsMessage.getMessageBody();
            String otpReceived  = messageBody.split("\\s+")[0];
            //editTextOtp.setText(otpReceived);
            otpTextResponse     = otpReceived;
        }
    }
}
