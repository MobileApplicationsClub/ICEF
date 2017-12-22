package bits.mac.icef_2018.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import bits.mac.icef_2018.EateriesDetail;
import bits.mac.icef_2018.Lists.EateriesList;
import bits.mac.icef_2018.R;

import static bits.mac.icef_2018.ICEF_HelperClass.DOMINOES;
import static bits.mac.icef_2018.ICEF_HelperClass.FOODKING;
import static bits.mac.icef_2018.ICEF_HelperClass.GAJA_LAXMI;
import static bits.mac.icef_2018.ICEF_HelperClass.IC;
import static bits.mac.icef_2018.ICEF_HelperClass.ICE_SPICE;
import static bits.mac.icef_2018.ICEF_HelperClass.MESS_A;
import static bits.mac.icef_2018.ICEF_HelperClass.MESS_C;
import static bits.mac.icef_2018.ICEF_HelperClass.MONGINIES;
import static bits.mac.icef_2018.ICEF_HelperClass.NC_A;
import static bits.mac.icef_2018.ICEF_HelperClass.NC_C;
import static bits.mac.icef_2018.ICEF_HelperClass.RED_CHILLIES;

/**
 * Created by aayush on 18/12/17.
 */

public class VH_Eateries extends RecyclerView.ViewHolder implements View.OnClickListener {
   public TextView Title;
    public TextView desc;
    public SimpleDraweeView image;
    List<EateriesList> list;
    Context context;

    public VH_Eateries(View itemView,List list,Context context) {
        super(itemView);
        this.context=context;
        this.list=list;
        Title=(TextView)itemView.findViewById(R.id.item_format_categories_title);
        desc=(TextView)itemView.findViewById(R.id.item_format_categories_description);
        image=(SimpleDraweeView)itemView.findViewById(R.id.item_format_categories_background);

        image.setOnClickListener(this);
    }

    public void onClick(View view){

        if (list.get(getAdapterPosition()).getId().equals(NC_A)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", NC_A);


        } else if (list.get(getAdapterPosition()).getId().equals(NC_C)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", NC_C);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(MESS_A)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", MESS_A);
            Toast.makeText(context,"clicked",Toast.LENGTH_LONG);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(MESS_C)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", MESS_C);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(MONGINIES)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", MONGINIES);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(ICE_SPICE)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", ICE_SPICE);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(FOODKING)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", FOODKING);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(RED_CHILLIES)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", RED_CHILLIES);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(IC)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", IC);
            context.startActivity(intent);
        } else if (list.get(getAdapterPosition()).getId().equals(GAJA_LAXMI)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", GAJA_LAXMI);
            context.startActivity(intent);
        }else if (list.get(getAdapterPosition()).getId().equals(DOMINOES)) {
            Intent intent = new Intent(context, EateriesDetail.class);
            intent.putExtra("EATERY", DOMINOES);
            context.startActivity(intent);
        }        else {
            Toast toast = Toast.makeText(context, "INVALID", Toast.LENGTH_SHORT);
            toast.show();
        }

    }




    }




