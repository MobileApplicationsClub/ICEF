package com.macbitsgoa.icef.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.icef.Adapters.Adapter_Tabs_Contacts;
import com.macbitsgoa.icef.R;
import com.macbitsgoa.icef.fragments.base.BaseFragment;


public class Contact extends BaseFragment {


    Adapter_Tabs_Contacts adapterViewPager;


    public Contact() {

    }

    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        return new Contact();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contact, container, false);

        ViewPager vpPager = view.findViewById(R.id.vpPager);
        adapterViewPager = new Adapter_Tabs_Contacts(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);


        return view;
    }


}

