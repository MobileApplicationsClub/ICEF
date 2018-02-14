package com.macbitsgoa.icef_2018;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import com.macbitsgoa.icef_2018.Lists.ParticipantList;

public class Participants extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<ParticipantList> vector=new Vector<>();
    Adapter_Participants adapter_Participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participants);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            int colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
            getWindow().setStatusBarColor(colorPrimary);
            getWindow().setNavigationBarColor(colorPrimary);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if (myToolbar != null) {
            myToolbar.setNavigationOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            onBackPressed();
                        }
                    });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
        recyclerView=findViewById(R.id.RV_Participants);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter_Participants=new Adapter_Participants(vector);
        recyclerView.setAdapter(adapter_Participants);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Participants");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vector.clear();

                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ParticipantList value = snapshot.getValue(ParticipantList.class);
                    vector.add(value);
                }

                adapter_Participants.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}



class Adapter_Participants extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Vector<ParticipantList> vector;


    public Adapter_Participants(){
    }

    public Adapter_Participants(Vector<ParticipantList> vector){
        this.vector=vector;
    }

    @Override
    public int getItemCount() {
        return vector.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_participants, parent, false);
        RecyclerView.ViewHolder viewHolder=new VH_Participants(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH_Participants abc=(VH_Participants)holder;
        abc.id.setText(vector.get(position).getID());
        abc.room.setText(vector.get(position).getRoom());
        abc.university.setText(vector.get(position).getCollege());
        abc.name.setText(vector.get(position).getName());
        abc.simpleDraweeView.setImageResource(R.drawable.defaultprofile);

    }



}


class VH_Participants extends RecyclerView.ViewHolder{
    public TextView name;
    public TextView id;
    public TextView university;
    public TextView room;
    public SimpleDraweeView simpleDraweeView;

    public VH_Participants(View itemView) {
        super(itemView);
        room=itemView.findViewById(R.id.room);
        id=itemView.findViewById(R.id.id);
        university=itemView.findViewById(R.id.college);
        name=itemView.findViewById(R.id.name);
        simpleDraweeView=itemView.findViewById(R.id.simpleDraweeView);
    }
}