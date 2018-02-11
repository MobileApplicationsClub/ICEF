package com.macbitsgoa.icef_2018;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.icef_2018.Lists.TimelineList;

import java.util.Random;
import java.util.Vector;

import bits.macbitsgoa.icef_2018.R;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    Vector<TimelineList> vector=new Vector<>();
    Adapter_Timeline adapter_timeline;
    RecyclerView recyclerView;
    String mChild;
    public PageFragment(){

    }

    @SuppressLint("ValidFragment")
    public PageFragment(String mChild){
        this.mChild=mChild;

    }

    public static PageFragment getItem(String mChild) {
        PageFragment fragment = new PageFragment(mChild);
       // bundle=new Bundle();
       // bundle.putString("mChild",mChild);
        return fragment;

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        adapter_timeline=new Adapter_Timeline(vector,getActivity());
        recyclerView=rootView.findViewById(R.id.timeline_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter_timeline);


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Timeline").child(mChild);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vector.clear();
                for(DataSnapshot shot:dataSnapshot.getChildren()){

                    vector.add(shot.getValue(TimelineList.class));

                }
                adapter_timeline.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }
}


class Adapter_Timeline extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Vector<TimelineList> vector;
    Context mContext;

    public Adapter_Timeline(){

    }

    public Adapter_Timeline(Vector<TimelineList> vector,Context mContext){
            this.vector=vector;
            this.mContext=mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        Log.e("msh1.", String.valueOf(viewType));

        if(viewType == 0) {
           view  = inflater.inflate(R.layout.item_timeline_rv, parent, false);
            viewHolder = new VH_Timeline_RV(view);
        }else {
            view = inflater.inflate(R.layout.item_timeline_rv_1, parent, false);
           viewHolder = new VH_Timeline_RV_1(view);
        }


        return viewHolder;

    }



    @Override
    public int getItemCount(){
        return vector.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("msh1..", String.valueOf(position));

        Log.e("msh1", String.valueOf(vector.get(position).getType()));
        return vector.get(position).getType();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0:

                VH_Timeline_RV vh_timeline_rv = (VH_Timeline_RV) holder;
                vh_timeline_rv.simpleDraweeView.setImageURI(vector.get(position).getImage());
                vh_timeline_rv.location.setText(vector.get(position).getLocation());
                vh_timeline_rv.time.setText(vector.get(position).getTime());
                vh_timeline_rv.event.setText(vector.get(position).getName());

                break;

            case 1:
                VH_Timeline_RV_1 vh_timeline_rv_1 = (VH_Timeline_RV_1) holder;
                vh_timeline_rv_1.location.setText(vector.get(position).getLocation());
                vh_timeline_rv_1.time.setText(vector.get(position).getTime());
                vh_timeline_rv_1.event.setText(vector.get(position).getName());
                vh_timeline_rv_1.topic.setText(vector.get(position).getTopic());

                break;
        }



    }   }





 class VH_Timeline_RV extends RecyclerView.ViewHolder  {
    TextView location;
    TextView time;
    TextView details;
    TextView event;
    ImageView b_location;
    ImageView b_time;
    ImageView   b_event;
    SimpleDraweeView simpleDraweeView;
    View decoration;
    View border;


    public VH_Timeline_RV(View itemView) {
        super(itemView);

      location=itemView.findViewById(R.id.TV_location);
            time=itemView.findViewById(R.id.TV_time);
            details=itemView.findViewById(R.id.TV_details);
            event=itemView.findViewById(R.id.Event_name);
            b_location=(ImageView)itemView.findViewById(R.id.b_location);
            b_time=(ImageView)itemView.findViewById(R.id.b_time);
            b_event=(ImageView)itemView.findViewById(R.id.b_details);
            decoration=itemView.findViewById(R.id.decoration);
            simpleDraweeView=itemView.findViewById(R.id.image_event);
            border=itemView.findViewById(R.id.back);

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomAndroidColor = Color.rgb(r,g,b);

            RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
            roundingParams.setBorder(randomAndroidColor, 4.0f);
            simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);

            decoration.setBackgroundColor(randomAndroidColor);

            b_event.setColorFilter(randomAndroidColor);
            b_event.setImageResource(R.drawable.ic_event_note_black_24dp);

            b_location.setColorFilter(randomAndroidColor);
            b_location.setImageResource(R.drawable.ic_mapicon);

            b_time.setColorFilter(randomAndroidColor);
            b_time.setImageResource(R.drawable.ic_access_time_black_24dp);





    }




}


class VH_Timeline_RV_1 extends RecyclerView.ViewHolder  {
    TextView location;
    TextView time;
    TextView details;
    TextView event;
    TextView topic;
    ViewGroup background;
    ViewGroup background1;
    ViewGroup btn;
    ImageView b_event;

    public VH_Timeline_RV_1(View itemView) {
        super(itemView);

        b_event=(ImageView)itemView.findViewById(R.id.b_details);
            location = itemView.findViewById(R.id.TV_location);
            time = itemView.findViewById(R.id.TV_time);
            details = itemView.findViewById(R.id.TV_details);
            background = itemView.findViewById(R.id.decoration);
            background1 = itemView.findViewById(R.id.deco2);
            event = itemView.findViewById(R.id.Event_name);
            topic = itemView.findViewById(R.id.topic);
            btn=itemView.findViewById(R.id.main);


            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomAndroidColor = Color.rgb(r, g, b);

            background.setBackgroundColor(randomAndroidColor);
            background1.setBackgroundColor(randomAndroidColor);

            b_event.setColorFilter(randomAndroidColor);
            b_event.setImageResource(R.drawable.ic_event_note_black_24dp);


    }

}


