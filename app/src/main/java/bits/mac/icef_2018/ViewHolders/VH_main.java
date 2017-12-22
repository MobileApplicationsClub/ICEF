package bits.mac.icef_2018.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bits.mac.icef_2018.Lists.MainList;
import bits.mac.icef_2018.Main_Eateries;
import bits.mac.icef_2018.R;

import static bits.mac.icef_2018.ICEF_HelperClass.DATES;
import static bits.mac.icef_2018.ICEF_HelperClass.EATERIES;
import static bits.mac.icef_2018.ICEF_HelperClass.MAIN_SPEAKERS;
import static bits.mac.icef_2018.ICEF_HelperClass.MAIN_TIMELINE;

/**
 * Created by aayush on 18/12/17.
 */

public class VH_main extends RecyclerView.ViewHolder implements View.OnClickListener {
    List<MainList> list;
    private Context context;
    public TextView titleView;
    public TextView descView;
    public SimpleDraweeView image;

    public VH_main(View itemView, Context context, List<MainList> list) {
        super(itemView);
        this.context = context;
        this.list = list;


       //finding Textfields for all views
        titleView = (TextView) itemView.findViewById(R.id.item_format_categories_title);
        descView = (TextView) itemView.findViewById(R.id.item_format_categories_description);
        image = (SimpleDraweeView) itemView.findViewById(R.id.item_format_categories_background);

        image.setOnClickListener(this);

    }



   @Override
    public void onClick(View view){
       if (list.get(getAdapterPosition() - 1).getId().equals(MAIN_TIMELINE)) {
           Intent TimelineActivity = new Intent(context, Main_Eateries.class);
           context.startActivity(TimelineActivity);
       } else if (list.get(getAdapterPosition() - 1).getId().equals(MAIN_SPEAKERS)) {
           Intent SpeakersActivity = new Intent(context, Main_Eateries.class);
           ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.simple_slide_in_right, R.anim.empty);
           context.startActivity(SpeakersActivity, activityOptionsCompat.toBundle());
       } else if (list.get(getAdapterPosition() - 1).getId().equals(DATES)) {
           Intent DatesActivity = new Intent(context, Main_Eateries.class);
           context.startActivity(DatesActivity);
       } else if (list.get(getAdapterPosition() - 1).getId().equals(EATERIES)) {
           Intent EateriesActivity = new Intent(context, Main_Eateries.class);
           context.startActivity(EateriesActivity);
       }

       }


   }


