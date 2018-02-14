package com.macbitsgoa.icef_2018.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.macbitsgoa.icef_2018.fragments.IcefContact;


/**
 * Created by aayush on 9/11/17.
 */

@SuppressWarnings("ALL")
public class Adapter_Tabs_Contacts extends FragmentStatePagerAdapter {
    private static int NUM_ITEMS = 3;

    public Adapter_Tabs_Contacts(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages.
    @Override
    public int getCount() {
        return 2;
        //NUM_ITEMS;
    }

    // Returns the fragment to display for a particular page.
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return IcefContact.newInstance("Icef");
            //return IcefContact.newInstance("Admin");
            case 1:
                // case 2:
                return IcefContact.newInstance("Cabs");
            default:
                return null;
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 1) {
            return "CABS";
        }
        if (position == 0) {
            return "ICEF";
        } else {
            return "CABS";
        }
    }

}