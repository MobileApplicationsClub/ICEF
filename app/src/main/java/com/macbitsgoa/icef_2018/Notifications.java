package com.macbitsgoa.icef_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import com.macbitsgoa.icef_2018.Lists.NotificationList;

import bits.macbitsgoa.icef_2018.R;

public class Notifications extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<NotificationList> vector=new Vector<>();
    Adapter_Notifications adapter_notifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        recyclerView=findViewById(R.id.RV_Notifications);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter_notifications=new Adapter_Notifications(vector);
        recyclerView.setAdapter(adapter_notifications);

        Query databaseReference= FirebaseDatabase.getInstance().getReference().child("Notifications").orderByChild("dt");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vector.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    NotificationList value = snapshot.getValue(NotificationList.class);
                    vector.add(value);
                }

                adapter_notifications.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}



class Adapter_Notifications extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Vector<NotificationList> vector;


    public Adapter_Notifications(){
    }

    public Adapter_Notifications(Vector<NotificationList> vector){
        this.vector=vector;
    }

    @Override
    public int getItemCount() {
        return vector.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_notifications, parent, false);
        RecyclerView.ViewHolder viewHolder=new VH_NOTIFICATIONS(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH_NOTIFICATIONS abc=(VH_NOTIFICATIONS)holder;
        abc.message.setText(vector.get(position).getMessage());
        abc.time.setText(vector.get(position).getDt());

    }



}


class VH_NOTIFICATIONS extends RecyclerView.ViewHolder{
    public TextView message;
    public TextView time;

    public VH_NOTIFICATIONS(View itemView) {
        super(itemView);
        message=itemView.findViewById(R.id.message);
        time=itemView.findViewById(R.id.time);
    }
}