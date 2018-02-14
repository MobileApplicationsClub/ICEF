package com.macbitsgoa.icef_2018;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.icef_2018.Adapters.Adapter_Eateries_Details;

import java.util.ArrayList;

import jp.wasabeef.blurry.Blurry;



public class EateriesDetail extends AppCompatActivity {

    String eatery;
    Boolean clicked = true;

    Adapter_Eateries_Details mAdapter_Eateries_Details;
    ViewPager mViewPager;
    ArrayList<String> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FrameLayout mContainer;
    FrameLayout mFullScreen;
    FloatingActionButton fab;
    TextView timings;
    TextView current;
    TextView count;
    private ProgressBar spinner;
    ImageButton Call;
    ImageButton location;


    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eateries_detail);


        //finding Views
        mFullScreen = findViewById(R.id.fullscreen_content);
        mContainer = findViewById(R.id.Container);
        mViewPager = findViewById(R.id.ViewPager);

        fab=findViewById(R.id.fab);
        Call=findViewById(R.id.Call);
        location=findViewById(R.id.location);
        spinner = findViewById(R.id.progressBar1);
        timings=findViewById(R.id.Timing);
        count=findViewById(R.id.count);
        current=findViewById(R.id.current);

        spinner.setVisibility(View.VISIBLE);
        eatery = getIntent().getStringExtra("EATERY");
        ref = database.getReference().child("Eateries").child(eatery);


        arrayList = new ArrayList<>();
        mAdapter_Eateries_Details = new Adapter_Eateries_Details(this, arrayList);
        mViewPager.setAdapter(mAdapter_Eateries_Details);
        mViewPager.setOffscreenPageLimit(5);



            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                arrayList.clear();
                count.setText(String.valueOf(snapshot.getChildrenCount()-2));
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {

                    String abc = postSnapshot.getValue(String.class);
                    arrayList.add(abc);

                }

                mAdapter_Eateries_Details.notifyDataSetChanged();
                spinner.setVisibility(View.GONE);
                timings.setText(arrayList.get(1));
                current.setText("1");

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                current.setText(String.valueOf(position+1));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        final Context mcontext = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (clicked) {
                    Blurry.with(mcontext)
                            .radius(6)
                            .sampling(8)
                            .async()
                            .animate(100)
                            .onto(mFullScreen);
                    mContainer.setVisibility(View.VISIBLE);
                    clicked = false;

                } else {

                    mContainer.setVisibility(View.GONE);
                    clicked = true;
                    Blurry.delete(mFullScreen);

                }
            }
        });

        Call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+arrayList.get(0)));
                startActivity(intent);
            }
        });

        location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           Intent intent=new Intent(mcontext,BPGCMapsActivity.class);
                intent.putExtra("Location",eatery);
                startActivity(intent);
        }
        });



    }





    }



