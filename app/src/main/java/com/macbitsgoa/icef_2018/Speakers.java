package com.macbitsgoa.icef_2018;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.icef_2018.Adapters.Adapter_Speakers_rv;
import com.macbitsgoa.icef_2018.Lists.SpeakersList;

import java.util.Vector;


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
    public static ViewPager mViewPager;
    static int position;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    public int pixels;
    Adapter_Speakers_rv adapter_speakers_rv;
    LinearLayoutManager layoutManager;
    RecyclerView recyclerView;
    Vector<SpeakersList> vector = new Vector<>();
    SepaeratorDecoration sepaeratorDecoration;
    FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    DatabaseReference databaseReference = firebaseDatabase.getReference().child("Speakers");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speakers);
        //hide();
        position = 0;
        pixels = this.getResources().getDisplayMetrics().widthPixels;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        databaseReference.keepSynced(true);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vector.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    vector.add(snapshot.getValue(SpeakersList.class));

                }
                adapter_speakers_rv.notifyDataSetChanged();
                mSectionsPagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mViewPager = findViewById(R.id.container);
        recyclerView = findViewById(R.id.h_rv);
        sepaeratorDecoration = new SepaeratorDecoration(Speakers.this, 0);


        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        adapter_speakers_rv = new Adapter_Speakers_rv(this, vector);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), vector);

        final LinearSnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
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
                sepaeratorDecoration = new SepaeratorDecoration(Speakers.this, position);

                Log.e("call1", String.valueOf(position));
                recyclerView.addItemDecoration(sepaeratorDecoration);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }


    public void hide() {

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

}





