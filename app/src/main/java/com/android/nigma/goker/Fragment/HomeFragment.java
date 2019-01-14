package com.android.nigma.goker.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nigma.goker.JobListRecycler;
import com.android.nigma.goker.Model.JobModel;
import com.android.nigma.goker.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.LinkedList;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private JobListRecycler jobListRecycler;
    private LinkedList<JobModel> jobList;
    private FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.layout_fragment_home, container, false);
        db = FirebaseFirestore.getInstance();
        jobList = new LinkedList<>();

        db.collection("joblist")
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(!task.isSuccessful()){

                    return;
                }
                for(DocumentSnapshot doc : task.getResult()){
                    Log.d("tes",doc.toObject(JobModel.class).getIdCreator());
                    jobList.add(doc.toObject(JobModel.class));
                }
                recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
                // Create an adapter and supply the data to be displayed.
                jobListRecycler = new JobListRecycler(view.getContext(), jobList);
                // Connect the adapter with the RecyclerView.
                recyclerView.setAdapter(jobListRecycler);
                // Give the RecyclerView a default layout manager.
                recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
            }
        });

        return view;
    }
}
