package com.zeoharlem.gads.schoolmisc.Activities;

import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.zeoharlem.gads.schoolmisc.DashboardActivity;
import com.zeoharlem.gads.schoolmisc.MainActivity;
import com.zeoharlem.gads.schoolmisc.R;

//import cdflynn.android.library.checkview.CheckView;

public class QrScannerActivity extends AppCompatActivity {


    //private CheckView mCheckViewMarker;
    private IntentIntegrator mIntentIntegrator;
    private static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_qr_scanner);
        ViewGroup contentFrame  = findViewById(R.id.layFrameOut);
        mIntentIntegrator   = new IntentIntegrator(this);
        mIntentIntegrator.setOrientationLocked(false);
        mIntentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        //mIntentIntegrator.setCameraId(Camera.CameraInfo.CAMERA_FACING_FRONT);
        mIntentIntegrator.setCameraId(Camera.CameraInfo.CAMERA_FACING_BACK);
        mIntentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult intentResult   = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);
        if(intentResult != null){
            if(intentResult.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                Intent intent   = new Intent(QrScannerActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(this, "Scanned: " + intentResult.getContents(), Toast.LENGTH_LONG).show();
                Intent intent   = new Intent(QrScannerActivity.this, CaptureActivity.class);
                intent.putExtra("keyQrCode", intentResult.getContents());
                startActivity(intent);
                finish();
            }
        }
        else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}