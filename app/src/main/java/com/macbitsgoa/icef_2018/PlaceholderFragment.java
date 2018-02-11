package com.macbitsgoa.icef_2018;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.icef_2018.Lists.SpeakersList;

import java.util.Vector;

import bits.macbitsgoa.icef_2018.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */

    static Bundle args;
    TextView name;
    TextView venue;
    TextView timing;
    TextView desc;
    SimpleDraweeView image;
    int position;
    Vector<SpeakersList> vector=new Vector<>();
    private static final String ARG_SECTION_NUMBER = "section_number";

    public PlaceholderFragment() {

    }

    @SuppressLint("ValidFragment")
    public PlaceholderFragment(int position, Vector<SpeakersList> vector) {
        this.vector=vector;
        this.position=position;
    }



    public static PlaceholderFragment newInstance(int sectionNumber,Vector<SpeakersList> vector) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);

        return fragment;
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_speakers, container, false);

        name=rootView.findViewById(R.id.Name);
        venue=rootView.findViewById(R.id.Venue);
        desc=rootView.findViewById(R.id.Description);
        timing=rootView.findViewById(R.id.Timing);
        image=rootView.findViewById(R.id.image);


        image.setImageURI(vector.get(position).getImageurl());
        name.setText(vector.get(position).getName());
        venue.setText(vector.get(position).getVenue());
        timing.setText(vector.get(position).getTimings());
        desc.setText(vector.get(position).getDescription());

        return rootView;
    }


}



 class SectionsPagerAdapter extends FragmentPagerAdapter {

    Vector<SpeakersList> vector=new Vector<>();
    public SectionsPagerAdapter(FragmentManager fm, Vector<SpeakersList> vector) {
        super(fm);
        this.vector=vector;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        Log.e("ABC",String.valueOf(position));
        PlaceholderFragment placeholderFragment=new PlaceholderFragment(position,vector);

        return placeholderFragment;
    }

    @Override
    public int getCount() {

        return vector.size();
    }


}


