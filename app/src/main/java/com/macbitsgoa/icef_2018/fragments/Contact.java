package com.macbitsgoa.icef_2018.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.macbitsgoa.icef_2018.Adapters.Adapter_Tabs_Contacts;
import bits.macbitsgoa.icef_2018.R;
import com.macbitsgoa.icef_2018.fragments.base.BaseFragment;


public class Contact extends BaseFragment {


    FragmentPagerAdapter adapterViewPager;


    public Contact() {

    }
    // TODO: Rename and change types and number of parameters
    public static Fragment newInstance() {
        BaseFragment fragment = new Contact();
        return (Fragment)fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_contact, container, false);

        ViewPager vpPager = (ViewPager) view.findViewById(R.id.vpPager);
        adapterViewPager = new Adapter_Tabs_Contacts(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);



        return view;
    }


    }

