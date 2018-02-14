package com.macbitsgoa.icef_2018;

import android.app.Application;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by aayush on 21/1/18.
 */

@SuppressWarnings("ALL")
public class ICEF2018 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().setPersistenceCacheSizeBytes(Long.MAX_VALUE);
        FirebaseDatabase.getInstance().getReference().addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Log.i("TAG","Child Added");
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                Log.i("TAG","Child Changed");
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Log.i("TAG","Child Removed");
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                Log.i("TAG","Child Moved");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG","Cancelled");
            }
        });
    }
}
