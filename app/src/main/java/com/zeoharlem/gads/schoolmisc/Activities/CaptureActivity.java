package com.zeoharlem.gads.schoolmisc.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.zeoharlem.gads.schoolmisc.DashboardActivity;
import com.zeoharlem.gads.schoolmisc.DialogFragments.MyDialogFragmentBox;
import com.zeoharlem.gads.schoolmisc.R;
import com.zeoharlem.gads.schoolmisc.Utils.L;

import cdflynn.android.library.checkview.CheckView;

public class CaptureActivity extends AppCompatActivity implements MyDialogFragmentBox.iMyDialogFragmentListener {

    private Toolbar mToolbar;
    private Button markPupilButton;
    private MyDialogFragmentBox mMyDialogFragmentBox;
    private CheckView mCheckView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture);
        mToolbar    = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        markPupilButton = findViewById(R.id.mark_attendance);
        markPupilButton.setOnClickListener(markPupilButtonListener);
        mCheckView      = findViewById(R.id.checkMarker);
        mMyDialogFragmentBox    = new MyDialogFragmentBox();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mark_pupil_menu, menu);
        MenuItem menuItem   = menu.findItem(R.id.close_menu);
        menuItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                Intent intent   = new Intent(CaptureActivity.this, DashboardActivity.class);
                startActivity(intent);
                finish();
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    View.OnClickListener markPupilButtonListener    = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            showDialogMyFragDialogBox(view);
        }
    };

    private void showDialogMyFragDialogBox(View view){
        FragmentManager fragmentManager = getSupportFragmentManager();
        mMyDialogFragmentBox.show(fragmentManager, "MyFragDialogBox");
    }

    @Override
    public void onDialogMsgTask(boolean performTask, View view) {
        if(performTask){
            L.l(getApplicationContext(), "Success");
            Intent intent   = new Intent(CaptureActivity.this, DashboardActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP  | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            finish();
        }
        else{
            L.l(getApplicationContext(), "You canceled action");
        }
    }
}