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
import com.zeoharlem.gads.schoolmisc.Models.Parents;
import com.zeoharlem.gads.schoolmisc.Models.Students;
import com.zeoharlem.gads.schoolmisc.Models.Teachers;
import com.zeoharlem.gads.schoolmisc.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ParentRecyclerAdapter extends RecyclerView.Adapter<ParentRecyclerAdapter.MyParentsViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<Parents> mTeachersArrayList;
    private List<Parents> mParentsList;

    public ParentRecyclerAdapter(Context context, ArrayList<Parents> teachersArrayList) {
        mContext            = context;
        mTeachersArrayList  = teachersArrayList;
        mParentsList        = new ArrayList<>(mTeachersArrayList);
    }

    @NonNull
    @Override
    public ParentRecyclerAdapter.MyParentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(mContext).inflate(R.layout.parents_item_layout, parent, false);
        return new MyParentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyParentsViewHolder holder, int position) {
        Parents teachers    = mTeachersArrayList.get(position);
        holder.mTextViewFullName.setText(teachers.getFullName());
        holder.mTextViewLocation.setText(teachers.getLocation());
        holder.mTextViewPhone.setText(teachers.getPhoneNumber());
    }

    public ArrayList<Parents> getTeachersArrayList() {
        return mTeachersArrayList;
    }

    public List<Parents> getParentsList() {
        return mParentsList;
    }

    @Override
    public int getItemCount() {
        return mTeachersArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    private Filter mFilter  = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String textSearched = charSequence.toString().toLowerCase();
            List<Parents> parentsList = new ArrayList<>();
            if(textSearched.length() == 0 && textSearched.isEmpty()){
                parentsList.addAll(getParentsList());
            }
            else {
                for (Parents item : getParentsList()) {
                    if (item.getFullName().toLowerCase().contains(textSearched)) {
                        parentsList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values        = parentsList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mTeachersArrayList.clear();
            mTeachersArrayList.addAll((Collection<? extends Parents>) filterResults.values);
            notifyDataSetChanged();
        }
    };


    class MyParentsViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewFullName, mTextViewPhone, mTextViewLocation;
        CircularImageView mCircularImageView;

        public MyParentsViewHolder(@NonNull View itemView) {
            super(itemView);
            initProps(itemView);
        }

        private void initProps(View view){
            mTextViewFullName   = view.findViewById(R.id.textReceiver);
            mTextViewPhone      = view.findViewById(R.id.phoneTag);
            mTextViewLocation   = view.findViewById(R.id.locationTag);
        }
    }
}
