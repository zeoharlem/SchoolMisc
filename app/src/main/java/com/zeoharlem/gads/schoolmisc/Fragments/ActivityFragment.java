package com.zeoharlem.gads.schoolmisc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.zeoharlem.gads.schoolmisc.R;

public class ActivityFragment extends Fragment {
    public ActivityFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view   = inflater.inflate(R.layout.activity_fragment, container, false);
        return view;
    }
}
