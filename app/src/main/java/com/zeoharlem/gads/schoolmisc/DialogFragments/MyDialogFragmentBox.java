package com.zeoharlem.gads.schoolmisc.DialogFragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.zeoharlem.gads.schoolmisc.R;

import cdflynn.android.library.checkview.CheckView;

public class MyDialogFragmentBox extends DialogFragment implements View.OnClickListener {

    private Button confirm, cancel;
    private iMyDialogFragmentListener mIMyDialogFragmentListener;
    //private CheckView mCheckView;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mIMyDialogFragmentListener  = (iMyDialogFragmentListener) context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view   = inflater.inflate(R.layout.my_dialog_fragment_box, null);
        confirm     = view.findViewById(R.id.acceptBtn);
        cancel      = view.findViewById(R.id.cancelBtn);
        //mCheckView  = view.findViewById(R.id.checkMarker);
        setCancelable(false);

        confirm.setOnClickListener(this);
        cancel.setOnClickListener(this);
        return view;
    }

    private void setActionsButton(){

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.acceptBtn){
            mIMyDialogFragmentListener.onDialogMsgTask(true, view);
        }
        else if(view.getId() == R.id.cancelBtn){
            dismiss();
        }
    }

    public interface iMyDialogFragmentListener {
        void onDialogMsgTask(boolean performTask, View view);
    }
}
