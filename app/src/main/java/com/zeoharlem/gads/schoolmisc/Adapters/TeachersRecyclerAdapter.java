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
import com.zeoharlem.gads.schoolmisc.Models.Teachers;
import com.zeoharlem.gads.schoolmisc.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class TeachersRecyclerAdapter extends RecyclerView.Adapter<TeachersRecyclerAdapter.MyTeachersViewHolder> implements Filterable {

    private Context mContext;
    private ArrayList<Teachers> mTeachersArrayList;
    private List<Teachers> mTeachersList;

    public TeachersRecyclerAdapter(Context context, ArrayList<Teachers> teachersArrayList) {
        mContext            = context;
        mTeachersArrayList  = teachersArrayList;
        mTeachersList       = new ArrayList<>(mTeachersArrayList);
    }

    public ArrayList<Teachers> getTeachersArrayList() {
        return mTeachersArrayList;
    }

    public List<Teachers> getTeachersList() {
        return mTeachersList;
    }

    @NonNull
    @Override
    public MyTeachersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view   = LayoutInflater.from(mContext).inflate(R.layout.teachers_item_layout, parent, false);
        return new MyTeachersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTeachersViewHolder holder, int position) {
        Teachers teachers = mTeachersArrayList.get(position);
        holder.mTextViewFullName.setText(teachers.getFullName());
        holder.mTextViewLocation.setText(teachers.getLocation());
        holder.mTextViewPhone.setText(teachers.getPhoneNumber());
    }

    @Override
    public int getItemCount() {
        return mTeachersArrayList.size();
    }

    private Filter mFilter  = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            String textSearched = charSequence.toString().toLowerCase();
            List<Teachers> teachersList = new ArrayList<>();
            if(textSearched.length() == 0 && textSearched.isEmpty()){
                teachersList.addAll(getTeachersList());
            }
            else{
                for (Teachers item : getTeachersList()) {
                    if (item.getFullName().toLowerCase().contains(textSearched)) {
                        teachersList.add(item);
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values        = teachersList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mTeachersArrayList.clear();
            mTeachersArrayList.addAll((Collection<? extends Teachers>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    class MyTeachersViewHolder extends RecyclerView.ViewHolder{
        TextView mTextViewFullName, mTextViewPhone, mTextViewLocation;
        CircularImageView mCircularImageView;

        public MyTeachersViewHolder(@NonNull View itemView) {
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
