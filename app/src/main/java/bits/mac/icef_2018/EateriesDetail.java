package bits.mac.icef_2018;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import bits.mac.icef_2018.Adapters.Adapter_Eateries_Details;
import jp.wasabeef.blurry.Blurry;



public class EateriesDetail extends AppCompatActivity {

    Intent intent;
    String eatery;
    Boolean clicked = true;

    Adapter_Eateries_Details mAdapter_Eateries_Details;
    ViewPager mViewPager;
    ArrayList<String> arrayList;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FrameLayout mContainer;
    FrameLayout mFullScreen;
    FloatingActionButton fab;
    SimpleDraweeView profile;
    TextView timings;
    private ProgressBar spinner;
    ImageButton Call;
    ImageButton location;


    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eateries_detail);
      Fresco.initialize(this);


        //finding Views
        mFullScreen = findViewById(R.id.fullscreen_content);
        mContainer = findViewById(R.id.Container);
        mViewPager =(ViewPager) findViewById(R.id.ViewPager);
        fab=findViewById(R.id.fab);
        Call=findViewById(R.id.Call);
        location=findViewById(R.id.location);
        spinner = (ProgressBar)findViewById(R.id.progressBar1);
        timings=findViewById(R.id.Timing);
        //referencing database
        intent = getIntent();
        eatery = intent.getStringExtra("EATERY");
        ref = database.getReference().child("Eateries").child(eatery);


        arrayList = new ArrayList<String>();
        mAdapter_Eateries_Details = new Adapter_Eateries_Details(this, arrayList);


        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        final Query qref = ref.orderByKey();
        qref.keepSynced(true);
        qref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    String abc = postSnapshot.getValue(String.class);
                    arrayList.add(abc);
                }

                mViewPager.setAdapter(mAdapter_Eateries_Details);
                spinner.setVisibility(View.GONE);

                timings.setText(arrayList.get(1));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });



        final Context mcontext = this;

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("TAG","Clicked");
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



