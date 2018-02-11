package com.macbitsgoa.icef_2018.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.macbitsgoa.icef_2018.fragments.ContactFragments.IcefContact;


/**
 * Created by aayush on 9/11/17.
 */

public class Adapter_Tabs_Contacts extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public Adapter_Tabs_Contacts(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return IcefContact.newInstance("Admin");
            case 1:
                return IcefContact.newInstance("Icef");
            case 2:
                return IcefContact.newInstance("Cabs");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if (position==0){return "ADMINISTRATION";}
    if (position==1){return "ICEF";}
    else{return "CABS";}
    }

}