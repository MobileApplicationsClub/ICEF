package com.macbitsgoa.icef_2018.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.macbitsgoa.icef_2018.Adapters.Adapter_Main;
import com.macbitsgoa.icef_2018.R;
import com.macbitsgoa.icef_2018.fragments.base.BaseFragment;


public class Dashboard extends BaseFragment
      {
        RecyclerView recyclerView;
        Adapter_Main adapter;
          public Dashboard() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        BaseFragment fragment = new Dashboard();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {//Set up firebase to store data offline
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        } catch (Exception e) {
        }
        Log.e("TAG", "" + FirebaseInstanceId.getInstance().getToken());
        //


        }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = view.findViewById(R.id.RV_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        adapter = new Adapter_Main(getContext());


        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();




        return view;



    }





}
