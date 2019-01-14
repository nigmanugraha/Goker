package com.android.nigma.goker.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nigma.goker.R;

public class JobFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
// Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.layout_job, container, false);
        Bundle bundle = savedInstanceState;
        return view;
    }
}
