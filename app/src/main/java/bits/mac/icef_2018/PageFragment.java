package bits.mac.icef_2018;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;
import java.util.Vector;

import bits.mac.icef_2018.Lists.TimelineList;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    Vector<TimelineList> vector=new Vector<>();
    Adapter_Timeline adapter_timeline;
    RecyclerView recyclerView;
    static Bundle bundle;
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
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.item_timeline_rv,parent,false);
        viewHolder = new VH_Timeline_RV(view);

        return viewHolder;

    }



    @Override
    public int getItemCount(){
        return vector.size();
    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH_Timeline_RV vh_timeline_rv=(VH_Timeline_RV)holder;
        vh_timeline_rv.location.setText(vector.get(position).getLocation());
        vh_timeline_rv.time.setText(vector.get(position).getTime());
        vh_timeline_rv.event.setText(vector.get(position).getName());
        vh_timeline_rv.simpleDraweeView.setImageURI(vector.get(position).getImage());


    }
}




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
