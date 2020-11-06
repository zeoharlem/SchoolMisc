package com.zeoharlem.gads.schoolmisc;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.zeoharlem.gads.schoolmisc.Activities.QrScannerActivity;
import com.zeoharlem.gads.schoolmisc.Adapters.DashboardFragAdapter;
import com.zeoharlem.gads.schoolmisc.Fragments.ActivityFragment;
import com.zeoharlem.gads.schoolmisc.Fragments.ParentFragment;
import com.zeoharlem.gads.schoolmisc.Fragments.TeachersFragment;
import com.zeoharlem.gads.schoolmisc.Fragments.TodayFragment;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;
    TabLayout mTabLayout;
    ViewPager mViewPager;
    ArrayList<String> mArrayList;
    private TextView tootbarTitle;
    private Button scannerBtn, accountsBtn;
    private Button agetnsBtn, registerBtn, historyBtn;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        mToolbar        = findViewById(R.id.toolbar);
        tootbarTitle    = findViewById(R.id.toolbarTitle);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //mNavigationView = findViewById(R.id.nav_view);
        mTabLayout      = findViewById(R.id.mainTabLayout);
        mViewPager      = findViewById(R.id.mainViewPager);
        mFloatingActionButton   = findViewById(R.id.fab);
        mArrayList      = new ArrayList<>();

        mArrayList.add("TODAY");
        mArrayList.add("TRANSACTIONS");
        setViewPagerLayout(mViewPager, mArrayList);


        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent   = new Intent(DashboardActivity.this, QrScannerActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setViewPagerLayout(ViewPager viewPager, ArrayList<String> arrayList){
        DashboardFragAdapter dashboardFragAdapter   = new DashboardFragAdapter(getSupportFragmentManager());
        dashboardFragAdapter.addFragment(new TodayFragment(), "TODAY");
        dashboardFragAdapter.addFragment(new TeachersFragment(), "TEACHERS");
        dashboardFragAdapter.addFragment(new ParentFragment(), "PARENT");
        dashboardFragAdapter.addFragment(new ActivityFragment(), "ACTIVITIES");
        mViewPager.setAdapter(dashboardFragAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.dashboard_menu, menu);
//        //MenuItem menuItem       = menu.findItem(R.id.search_menu);
//        return super.onCreateOptionsMenu(menu);
//    }
}