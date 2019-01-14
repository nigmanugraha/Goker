package com.android.nigma.goker.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.nigma.goker.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class PostNewJobFragment extends Fragment {
    EditText inputTitle,inputDesc,inputSpec,inputPrice;
    Button btnPost;
    FirebaseAuth auth;
    FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.layout_job, container, false);
        auth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        inputTitle = view.findViewById(R.id.inputTitle);
        inputDesc = view.findViewById(R.id.inputDesc);
        inputSpec = view.findViewById(R.id.inputSpec);
        inputPrice = view.findViewById(R.id.inputPrice);
        btnPost = view.findViewById(R.id.btnPost);


        return view;
    }
}
