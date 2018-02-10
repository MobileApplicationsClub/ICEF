package bits.mac.icef_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Vector;

import bits.mac.icef_2018.Lists.ParticipantList;
import bits.mac.icef_2018.ViewHolders.VH_timer;

public class Participants extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<ParticipantList> vector=new Vector<>();
    Adapter_Participants adapter_Participants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView=findViewById(R.id.RV_FAQ);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter_Participants=new Adapter_Participants(vector);
        recyclerView.setAdapter(adapter_Participants);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Participants");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
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
       // abc.question.setText(vector.get(position).getQuestion());
       // abc.answer.setText(vector.get(position).getAnswer());

    }



}


class VH_Participants extends RecyclerView.ViewHolder{
    public TextView answer;
    public TextView question;

    public VH_Participants(View itemView) {
        super(itemView);
        question=itemView.findViewById(R.id.Question);
        answer=itemView.findViewById(R.id.Answer);
    }
}