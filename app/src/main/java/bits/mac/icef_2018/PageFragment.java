package bits.mac.icef_2018;

import android.content.Context;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

import bits.mac.icef_2018.ViewHolders.VH_Eateries;
import bits.mac.icef_2018.ViewHolders.VH_timer;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    static int page;

    public PageFragment() {
    }

    public static PageFragment newInstance(int page) {
        PageFragment fragment = new PageFragment();
        PageFragment.page=page;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Timeline").child("Day "+page);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot shot:dataSnapshot.getChildren()){

                    shot.getValue();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






        return rootView;
    }
}


class Adapter_Timeline extends RecyclerView.Adapter<RecyclerView.ViewHolder>{


public Adapter_Timeline(){

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
        return 2;
    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }
}




 class VH_Timeline_RV extends RecyclerView.ViewHolder  {
    TextView location;
    TextView time;
    TextView details;
    TextView event;
    ImageButton b_location;
    ImageButton b_time;
    ImageButton b_event;
    SimpleDraweeView simpleDraweeView;
    View decoration;


    public VH_Timeline_RV(View itemView) {
        super(itemView);

        location=itemView.findViewById(R.id.TV_location);
        time=itemView.findViewById(R.id.TV_time);
        details=itemView.findViewById(R.id.TV_details);
        event=itemView.findViewById(R.id.Event_name);
        b_location=itemView.findViewById(R.id.b_location);
        b_time=itemView.findViewById(R.id.b_time);
        b_event=itemView.findViewById(R.id.b_details);
        decoration=itemView.findViewById(R.id.decoration);
        simpleDraweeView=itemView.findViewById(R.id.simpleDraweeView);


        int[] androidColors = itemView.getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        decoration.setBackgroundColor(randomAndroidColor);
        b_event.setColorFilter(randomAndroidColor , PorterDuff.Mode.SRC_IN);
        b_location.setColorFilter(randomAndroidColor , PorterDuff.Mode.SRC_IN);
        b_time.setColorFilter(randomAndroidColor , PorterDuff.Mode.SRC_IN);


    }




}
