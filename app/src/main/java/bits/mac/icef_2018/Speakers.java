package bits.mac.icef_2018;

import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import bits.mac.icef_2018.Adapters.Adapter_Speakers_rv;
import bits.mac.icef_2018.Lists.TimelineList;

public class Speakers extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    public static SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public int pixels;
    static int position;
    public static ViewPager mViewPager;
    Adapter_Speakers_rv adapter_speakers_rv;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    static Vector<TimelineList> vector=new Vector<>();
    SepaeratorDecoration sepaeratorDecoration;
    FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=firebaseDatabase.getReference().child("Timeline");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_timeline);
        hide();
        position=0;
        pixels= this.getResources().getDisplayMetrics().widthPixels;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vector.clear();
               for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                   vector.add(snapshot.getValue(TimelineList.class));

               }
                adapter_speakers_rv.notifyDataSetChanged();
                mSectionsPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mViewPager = (ViewPager) findViewById(R.id.container);
        recyclerView=findViewById(R.id.h_rv);
        sepaeratorDecoration=new SepaeratorDecoration(Speakers.this,0);



        layoutManager= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        adapter_speakers_rv =new Adapter_Speakers_rv(this,vector);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),vector);

        final LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        mViewPager.setOffscreenPageLimit(0);
        recyclerView.setOnFlingListener(snapHelper);

        recyclerView.addItemDecoration(sepaeratorDecoration);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_speakers_rv);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(0);

    mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {


        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            recyclerView.removeItemDecoration(sepaeratorDecoration);
            recyclerView.smoothScrollToPosition(position);
            sepaeratorDecoration=new SepaeratorDecoration(Speakers.this,position);

         Log.e("call1",String.valueOf(position));
            recyclerView.addItemDecoration(sepaeratorDecoration);

        }
        @Override
        public void onPageScrollStateChanged(int state) {

        }

    });

    }


    public void hide(){

        View decorView = getWindow().getDecorView();
        // Hide the status bar.
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        // Remember that you should never show the action bar if the
        // status bar is hidden, so hide that too if necessary.
      //  ActionBar actionBar = getSupportActionBar();
        //actionBar.hide();

        int uiOptions1 = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions1);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_timeline, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
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
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {


        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public void setUserVisibleHint(boolean isVisibleToUser) {
            super.setUserVisibleHint(isVisibleToUser);
            if (isVisibleToUser){
                //do something  //Load or Refresh Data
                Speakers.mViewPager.notify();
            }
        }



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
             View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
             name=rootView.findViewById(R.id.Name);
             venue=rootView.findViewById(R.id.Venue);
             desc=rootView.findViewById(R.id.Description);
             timing=rootView.findViewById(R.id.Timing);
             image=rootView.findViewById(R.id.image);


            image.setImageURI(Speakers.vector.get(args.getInt(ARG_SECTION_NUMBER)).getImageurl());
            name.setText(Speakers.vector.get(args.getInt(ARG_SECTION_NUMBER)).getName());
            venue.setText(Speakers.vector.get(args.getInt(ARG_SECTION_NUMBER)).getVenue());
            timing.setText(Speakers.vector.get(args.getInt(ARG_SECTION_NUMBER)).getTimings());
            desc.setText(Speakers.vector.get(args.getInt(ARG_SECTION_NUMBER)).getDescription());


            return rootView;
        }
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentStatePagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm,Vector<TimelineList> vector) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
        Log.e("ABC",String.valueOf(position));

            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {

            return vector.size();
        }


    }

}
