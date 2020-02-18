package com.vitesse.group.vghomedecor.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vitesse.group.vghomedecor.R;

public class VRFragment extends androidx.fragment.app.Fragment {
    public VRFragment() {
        // Required empty public constructor
    }

    public static VRFragment newInstance(String param1, String param2) {
        VRFragment fragment = new VRFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main_vr, container, false);

        return view;
    }
}
