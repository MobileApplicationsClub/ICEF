package com.macbitsgoa.icef_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.icef_2018.Lists.FaqList;

import java.util.Vector;

import bits.macbitsgoa.icef_2018.R;

public class Faq extends AppCompatActivity {

    RecyclerView recyclerView;
    Vector<FaqList> vector=new Vector<>();
    Adapter_Faq adapter_faq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        recyclerView=findViewById(R.id.RV_FAQ);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter_faq=new Adapter_Faq(vector);
        recyclerView.setAdapter(adapter_faq);

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("FAQ");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                vector.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    FaqList value = snapshot.getValue(FaqList.class);
                    vector.add(value);
                }

                adapter_faq.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}



class Adapter_Faq extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Vector<FaqList> vector;


    public Adapter_Faq(){
    }

    public Adapter_Faq(Vector<FaqList> vector){
        this.vector=vector;
    }

    @Override
    public int getItemCount() {
        return vector.size();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_faq, parent, false);
        RecyclerView.ViewHolder viewHolder=new VH_FAQ(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH_FAQ abc=(VH_FAQ)holder;
        abc.question.setText(vector.get(position).getQuestion());
        abc.answer.setText(vector.get(position).getAnswer());

    }



    }


 class VH_FAQ extends RecyclerView.ViewHolder{
     public TextView answer;
     public TextView question;

     public VH_FAQ(View itemView) {
         super(itemView);
       question=itemView.findViewById(R.id.Question);
         answer=itemView.findViewById(R.id.Answer);
     }
 }