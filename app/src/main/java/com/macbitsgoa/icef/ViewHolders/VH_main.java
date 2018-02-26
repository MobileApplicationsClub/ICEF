package com.macbitsgoa.icef.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.icef.BookOfAbstracts;
import com.macbitsgoa.icef.Faq;
import com.macbitsgoa.icef.Lists.GalleryFormat;
import com.macbitsgoa.icef.Lists.MainList;
import com.macbitsgoa.icef.Main_Eateries;
import com.macbitsgoa.icef.Participants;
import com.macbitsgoa.icef.R;
import com.macbitsgoa.icef.Speakers;
import com.macbitsgoa.icef.Timeline;
import com.macbitsgoa.icef.viewImage;

import java.util.ArrayList;
import java.util.List;

import static com.macbitsgoa.icef.ICEF_HelperClass.BOOK;
import static com.macbitsgoa.icef.ICEF_HelperClass.DATES;
import static com.macbitsgoa.icef.ICEF_HelperClass.EATERIES;
import static com.macbitsgoa.icef.ICEF_HelperClass.FAQ;
import static com.macbitsgoa.icef.ICEF_HelperClass.GALLERY;
import static com.macbitsgoa.icef.ICEF_HelperClass.MAIN_SPEAKERS;
import static com.macbitsgoa.icef.ICEF_HelperClass.MAIN_TIMELINE;
import static com.macbitsgoa.icef.ICEF_HelperClass.PARTICIPANTS;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class VH_main extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView titleView;
    public TextView descView;
    public SimpleDraweeView image;
    private List<MainList> list;
    private Context context;
    private ArrayList<GalleryFormat> imageList;

    public VH_main(View itemView, Context context, List<MainList> list) {
        super(itemView);
        this.context = context;
        this.list = list;

        //finding Textfields for all views
        titleView = itemView.findViewById(R.id.item_format_categories_title);
        descView = itemView.findViewById(R.id.item_format_categories_description);
        image = itemView.findViewById(R.id.item_format_categories_background);

        image.setOnClickListener(this);

    }


    @Override
    public void onClick(final View view) {
        if (list.get(getAdapterPosition() - 1).getId().equals(MAIN_TIMELINE)) {
            Intent TimelineActivity = new Intent(context, Timeline.class);
            context.startActivity(TimelineActivity);
        } else if (list.get(getAdapterPosition() - 1).getId().equals(MAIN_SPEAKERS)) {
            Intent SpeakersActivity = new Intent(context, Speakers.class);
            ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.simple_slide_in_right, R.anim.empty);
            context.startActivity(SpeakersActivity, activityOptionsCompat.toBundle());
        } else if (list.get(getAdapterPosition() - 1).getId().equals(DATES)) {
            Intent DatesActivity = new Intent(context, Main_Eateries.class);
            context.startActivity(DatesActivity);
        } else if (list.get(getAdapterPosition() - 1).getId().equals(EATERIES)) {
            Intent EateriesActivity = new Intent(context, Main_Eateries.class);
            context.startActivity(EateriesActivity);
        } else if (list.get(getAdapterPosition() - 1).getId().equals(FAQ)) {
            Intent FaqActivity = new Intent(context, Faq.class);
            context.startActivity(FaqActivity);
        } else if (list.get(getAdapterPosition() - 1).getId().equals(PARTICIPANTS)) {
            Intent ParticipantActivity = new Intent(context, Participants.class);
            context.startActivity(ParticipantActivity);
        } else if (list.get(getAdapterPosition() - 1).getId().equals(BOOK)) {
            new BookOfAbstracts(context);

        }else if(list.get(getAdapterPosition()-1).getId().equals(GALLERY)){
            imageList=new ArrayList<>();
            Intent gallery=new Intent(context,viewImage.class);
            FirebaseDatabase.getInstance().getReference().child(GALLERY).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for(DataSnapshot snapshot:dataSnapshot.getChildren())
                        imageList.add(snapshot.getValue(GalleryFormat.class));
                if(imageList.isEmpty()){
                    Snackbar.make(view,"No Images Available",Snackbar.LENGTH_LONG).show();
                }else{

                    Intent intent=new Intent(context,viewImage.class);
                    intent.putParcelableArrayListExtra("images",imageList);
                    intent.putExtra("dirPath","Events");
                    context.startActivity(intent);
                }


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

    }


}


