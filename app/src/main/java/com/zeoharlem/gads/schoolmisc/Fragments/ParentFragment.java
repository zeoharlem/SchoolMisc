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

import com.zeoharlem.gads.schoolmisc.Adapters.ParentRecyclerAdapter;
import com.zeoharlem.gads.schoolmisc.Adapters.TeachersRecyclerAdapter;
import com.zeoharlem.gads.schoolmisc.Models.Parents;
import com.zeoharlem.gads.schoolmisc.Models.Teachers;
import com.zeoharlem.gads.schoolmisc.R;

import java.util.ArrayList;
import java.util.List;

public class ParentFragment extends Fragment {

    private RecyclerView mRecyclerView;
    ArrayList<Parents> mTeachersArrayList;
    List<Parents> mParentsList;
    private ParentRecyclerAdapter mTeachersRecyclerAdapter;

    public ParentFragment() {

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
        View view   = inflater.inflate(R.layout.parent_fragment, container, false);
        mTeachersArrayList  = new ArrayList<>();
        mRecyclerView       = view.findViewById(R.id.parentsRecyclerView);
        setTeachersRecyclerAdapter();
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
                mTeachersRecyclerAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    private void setTeachersRecyclerAdapter() {
        setUsersModel();
        mTeachersRecyclerAdapter    = new ParentRecyclerAdapter(getContext(), mTeachersArrayList);
        final LinearLayoutManager layoutManager     = new LinearLayoutManager(getContext());
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),layoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mTeachersRecyclerAdapter);
    }

    private void setUsersModel() {
        mTeachersArrayList.add(new Parents("Mr Ogungbade", "Ibadan, Oyo", "+234 809 513 1631", "zeoharlem@yahoo.co.uk"));
        mTeachersArrayList.add(new Parents("Mrs Adegbite", "Ikeja, Lagos", "+234 809 234 4327", "fakeye@gmail.com"));
        mTeachersArrayList.add(new Parents("Oladipupo Oludamile", "Ikeja, Lagos", "+234 809 654 3333", "lateef@gmail.com"));
        mTeachersArrayList.add(new Parents("Mr Benson Oga", "Bergers, Abuja", "+234 234 654 0876", "victor@gmail.com"));
        mTeachersArrayList.add(new Parents("Mr Goje Barka-Sharkur", "Bergers, Abuja", "+234 234 987 0876", "igwe@gmail.com"));
        mTeachersArrayList.add(new Parents("Fr. Debowale", "Wawa, Ogun", "+234 234 322 5431", "atibadebo2@gmail.com"));
        mTeachersArrayList.add(new Parents("Mrs. Goke Ayanwale", "Ikeja, Lagos", "+234 80 322 5431", "oluwaseun@gmail.com"));
        mTeachersArrayList.add(new Parents("Ronaldo Karacha", "Ikeja, Lagos", "+234 830 234 5555", "jameskaracha@gmail.com"));
        mTeachersArrayList.add(new Parents("Mr. Akinju Olawale", "Ikeja, Lagos", "+234 830 234 2453", "kinglao@yahoo.com"));
    }
}
