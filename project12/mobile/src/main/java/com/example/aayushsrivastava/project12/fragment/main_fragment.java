package com.example.aayushsrivastava.project12.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.aayushsrivastava.project12.R;

/**
 * Created by Aayush Srivastava on 01-07-2016.
 */
public class main_fragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_main,container,false);
        return rootview;



    }
}