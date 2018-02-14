package com.macbitsgoa.icef_2018.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.macbitsgoa.icef_2018.EateriesDetail;
import com.macbitsgoa.icef_2018.ICEF_HelperClass;
import com.macbitsgoa.icef_2018.Lists.EateriesList;
import com.macbitsgoa.icef_2018.R;

import java.util.List;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class VH_Eateries extends RecyclerView.ViewHolder implements View.OnClickListener {
    public TextView Title;
    public TextView desc;
    public SimpleDraweeView image;
    List<EateriesList> list;
    Context context;

    public VH_Eateries(View itemView, List<EateriesList> list, Context context) {
        super(itemView);
        this.context = context;
        this.list = list;
        Title = itemView.findViewById(R.id.item_format_categories_title);
        desc = itemView.findViewById(R.id.item_format_categories_description);
        image = itemView.findViewById(R.id.item_format_categories_background);

        image.setOnClickListener(this);
    }

    public void onClick(View view) {

        if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.NC_A)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.NC_A);


        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.NC_C)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.NC_C);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.MESS_A)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.MESS_A);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.MESS_C)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.MESS_C);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.MONGINIES)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.MONGINIES);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.ICE_SPICE)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.ICE_SPICE);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.FOODKING)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.FOODKING);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.RED_CHILLIES)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.RED_CHILLIES);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.IC)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.IC);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.GAJA_LAXMI)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.GAJA_LAXMI);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICEF_HelperClass.DOMINOES)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICEF_HelperClass.DOMINOES);
            context.startActivity(intent);
        } else {
            Toast toast = Toast.makeText(context, "INVALID", Toast.LENGTH_SHORT);
            toast.show();
        }

    }


}




