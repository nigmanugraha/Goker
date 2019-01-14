package com.android.nigma.goker.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.nigma.goker.LoginActivity;
import com.android.nigma.goker.R;
import com.google.firebase.auth.FirebaseAuth;

public class AccountFragment extends Fragment {

    FirebaseAuth auth;
    Button btnLogout,btnPostJob,btnMyjob;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.layout_account_fragment, container, false);
        auth = FirebaseAuth.getInstance();
        if(auth.getCurrentUser()==null){
            startActivity(new Intent(view.getContext(),LoginActivity.class));
            return view;
        }
        btnLogout = view.findViewById(R.id.btnLogout);
        btnMyjob = view.findViewById(R.id.btnMyJob);
        btnPostJob = view.findViewById(R.id.btnPostJob);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnLogout();
                startActivity(new Intent(view.getContext(),LoginActivity.class));
            }
        });
        btnPostJob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnPostJob();
            }
        });
        return view;
    }

    private void btnMyJob(){

    }
    private  void btnPostJob(){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.frameLayout,new PostNewJobFragment());
        ft.commit();
    }
    private void btnLogout(){
        auth.signOut();
    }
}
