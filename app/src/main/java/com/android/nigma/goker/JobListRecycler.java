package com.android.nigma.goker;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.nigma.goker.Model.JobModel;

import java.util.LinkedList;

public class JobListRecycler extends RecyclerView.Adapter<JobListRecycler.WordViewHolder> {

    private final LinkedList<JobModel> listJob;
    private LayoutInflater mInflater;
    private Context context;

    public JobListRecycler(Context context, LinkedList<JobModel> listJob) {
        mInflater = LayoutInflater.from(context);
        this.listJob = listJob;
        this.context = context;
    }
    @NonNull
    @Override
    public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.job_list_item, parent, false);
        return new WordViewHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(WordViewHolder holder, int position) {
        JobModel job = listJob.get(position);
        holder.titleView.setText(job.getTitle());
        holder.descView.setText(job.getDesc());
    }

    @Override
    public int getItemCount() {
        return listJob.size();
    }

    class WordViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final TextView titleView;
        public final TextView descView;
        final JobListRecycler mAdapter;

        public WordViewHolder(View itemView, JobListRecycler adapter) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title);
            descView = (TextView) itemView.findViewById(R.id.desc);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Get the position of the item that was clicked.
            int mPosition = getLayoutPosition();
            // get data model yang dipilih
            JobModel job = listJob.get(mPosition);
            // parsing data ke activity ResepPreview

            // Notify the adapter, that the data has changed so it can
            // update the RecyclerView to display the data.
            mAdapter.notifyDataSetChanged();
        }
    }
}
