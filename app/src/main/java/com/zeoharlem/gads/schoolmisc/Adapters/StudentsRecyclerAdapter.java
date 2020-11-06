package com.zeoharlem.gads.schoolmisc.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.zeoharlem.gads.schoolmisc.Models.Students;
import com.zeoharlem.gads.schoolmisc.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentsRecyclerAdapter extends RecyclerView.Adapter<StudentsRecyclerAdapter.MyViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<Students> mUsersArrayList;
    private List<Students> mUsersList;

    private iAccountRecyclerOnClickListener mAccountRecyclerOnClickListener;

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public iAccountRecyclerOnClickListener getAccountRecyclerOnClickListener() {
        return mAccountRecyclerOnClickListener;
    }

    public void setAccountRecyclerOnClickListener(iAccountRecyclerOnClickListener accountRecyclerOnClickListener) {
        mAccountRecyclerOnClickListener = accountRecyclerOnClickListener;
    }

    public StudentsRecyclerAdapter(Context context, ArrayList<Students> usersArrayList) {
        mContext        = context;
        mUsersArrayList = usersArrayList;
        mUsersList      = new ArrayList<>(mUsersArrayList);
    }

    public List<Students> getUsersList() {
        return mUsersList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(mContext).inflate(R.layout.accounts_item_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Students users = mUsersArrayList.get(position);
        holder.mTextViewFullName.setText(users.getFullName());
        holder.mTextViewLocation.setText(users.getLocation());
        holder.mTextViewPhone.setText(users.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mUsersArrayList.size();
    }

    public ArrayList<Students> getUsersArrayList() {
        return mUsersArrayList;
    }

    public void setUsersArrayList(ArrayList<Students> usersArrayList) {
        mUsersArrayList = usersArrayList;
    }

    @Override
    public Filter getFilter() {
        return filterAccounts;
    }

    private Filter filterAccounts   = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String textSearched = charSequence.toString().toLowerCase();
            List<Students> usersList   = new ArrayList<>();
            if(textSearched.length() == 0 && textSearched.isEmpty()){
                usersList.addAll(getUsersList());
            }
            else {
                for(Students items:getUsersList()){
                    if(items.getFullName().toLowerCase().contains(textSearched)){
                        usersList.add(items);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values        = usersList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mUsersArrayList.clear();
            mUsersArrayList.addAll((Collection<? extends Students>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTextViewFullName, mTextViewPhone, mTextViewLocation;
        CircularImageView mCircularImageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initProps(itemView);
            itemView.setOnClickListener(this);
        }

        private void initProps(View view){
            mTextViewFullName   = view.findViewById(R.id.textReceiver);
            mTextViewPhone      = view.findViewById(R.id.phoneTag);
            mTextViewLocation   = view.findViewById(R.id.locationTag);
        }

        @Override
        public void onClick(View view) {
            if(mAccountRecyclerOnClickListener != null){
                mAccountRecyclerOnClickListener.onItemClickEvent(getAdapterPosition(), view);
            }
        }
    }
    
    public interface iAccountRecyclerOnClickListener{
        void onItemClickEvent(int position, View view);
    }
}
