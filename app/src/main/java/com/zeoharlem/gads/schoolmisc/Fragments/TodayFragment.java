package com.zeoharlem.gads.schoolmisc.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zeoharlem.gads.schoolmisc.Adapters.StudentsRecyclerAdapter;
import com.zeoharlem.gads.schoolmisc.Models.Students;
import com.zeoharlem.gads.schoolmisc.R;

import java.util.ArrayList;
import java.util.List;

public class TodayFragment extends Fragment {
    private RecyclerView mRecyclerView;
    ArrayList<Students> mStudentsArrayList;
    List<Students> mStudentsList;

    private StudentsRecyclerAdapter mStudentsRecyclerAdapter;

    public TodayFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view   = inflater.inflate(R.layout.today_fragment, container, false);
        mStudentsArrayList  = new ArrayList<>();
        mRecyclerView       = view.findViewById(R.id.accountsRecyclerView);

        setStudentsRecyclerAdapter();
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.dashboard_menu, menu);
        SearchView searchView   = (SearchView) menu.findItem(R.id.search_menu).getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mStudentsRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setUsersModel(){
        mStudentsArrayList.add(new Students("Theophilus Alamu", "Ibadan, Oyo", "+234 809 513 1631", "zeoharlem@yahoo.co.uk"));
        mStudentsArrayList.add(new Students("Dotun Fakeye", "Ikeja, Lagos", "+234 809 234 4327", "fakeye@gmail.com"));
        mStudentsArrayList.add(new Students("Lateef Oludamile", "Ikeja, Lagos", "+234 809 654 3333", "lateef@gmail.com"));
        mStudentsArrayList.add(new Students("Victor Oga", "Bergers, Abuja", "+234 234 654 0876", "victor@gmail.com"));
        mStudentsArrayList.add(new Students("Igwe Tupac-Sharkur", "Bergers, Abuja", "+234 234 987 0876", "igwe@gmail.com"));
        mStudentsArrayList.add(new Students("Atiba Debowale", "Wawa, Ogun", "+234 234 322 5431", "atibadebo2@gmail.com"));
        mStudentsArrayList.add(new Students("Oluwaseun Ayanwale", "Ikeja, Lagos", "+234 80 322 5431", "oluwaseun@gmail.com"));
        mStudentsArrayList.add(new Students("James Karacha", "Ikeja, Lagos", "+234 830 234 5555", "jameskaracha@gmail.com"));
        mStudentsArrayList.add(new Students("Kunglao Rachas", "Ikeja, Lagos", "+234 830 234 2453", "kinglao@yahoo.com"));
    }

    private void setStudentsRecyclerAdapter(){
        setUsersModel();
        mStudentsRecyclerAdapter    = new StudentsRecyclerAdapter(getContext(), mStudentsArrayList);
        final LinearLayoutManager layoutManager     = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mStudentsRecyclerAdapter);
    }

    private void setRecyclerViewOnClickListener(){
        mStudentsRecyclerAdapter.setAccountRecyclerOnClickListener(new StudentsRecyclerAdapter.iAccountRecyclerOnClickListener() {
            @Override
            public void onItemClickEvent(int position, View view) {

            }
        });
    }
}
