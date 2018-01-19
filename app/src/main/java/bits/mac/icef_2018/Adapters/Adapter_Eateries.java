package bits.mac.icef_2018.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

import bits.mac.icef_2018.Lists.EateriesList;
import bits.mac.icef_2018.R;
import bits.mac.icef_2018.ViewHolders.VH_Eateries;


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

public class Adapter_Eateries extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<EateriesList> list= new ArrayList<>();
    Context context;
    TextView TITLE;
    TextView DESC;

    public Adapter_Eateries(Context context){
        this.context=context;
        list.add(new EateriesList("CAFETERIA","Enjoy one of the best cappuccino in campus along with the snacks at shetty sons",R.drawable.ic_launcher_background,IC));
        list.add(new EateriesList("RED CHILLIES", "In-campus restaurant to celebrate your moments", R.drawable.ic_launcher_background, RED_CHILLIES));
        list.add(new EateriesList("A MESS", "", R.drawable.ic_launcher_background,MESS_A));
        list.add(new EateriesList("C MESS","",R.drawable.ic_launcher_background,MESS_C));
        list.add(new EateriesList("Gaja Laxmi Snacks", "If you love eggs than this place is for you", R.drawable.ic_launcher_background, GAJA_LAXMI));
        list.add(new EateriesList("ICE AND SPICE", "Time to fell in love\n with the smoothies over here", R.drawable.ic_launcher_background, ICE_SPICE));
        list.add(new EateriesList("FOOD KING", "Best Place to chill out", R.drawable.ic_launcher_background, FOODKING));
        list.add(new EateriesList("DOMINOES", "Get your pizza delivered in just 30min ", R.drawable.ic_launcher_background, DOMINOES));
        list.add(new EateriesList("Night Canteen A", "Hungry at night!!", R.drawable.ic_launcher_background, NC_A));
        list.add(new EateriesList("Night Canteen C", "Hungry at night!!", R.drawable.ic_launcher_background, NC_C));
        list.add(new EateriesList("Monginies", "",R.drawable.ic_launcher_background, MONGINIES));



    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_main, parent, false);
        viewHolder = new VH_Eateries(view,list,parent.getContext());

        return viewHolder;

    }



    @Override
    public int getItemCount(){
    return list.size();
    }




    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        VH_Eateries mv = (VH_Eateries) holder;
        mv.Title.setText(list.get(position).getTitle());
        mv.desc.setText(list.get(position).getDescription());
      //  mv.image.setImageURI(Uri.parse("ref///"+list.get(position).getBackground()));

    }
}
