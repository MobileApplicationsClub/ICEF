package bits.mac.icef_2018.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.macbitsgoa.icef.R;

import java.util.ArrayList;
import java.util.List;

import bits.mac.icef_2018.ICEF_HelperClass;
import bits.mac.icef_2018.Lists.EateriesList;
import bits.mac.icef_2018.ViewHolders.VH_Eateries;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class Adapter_Eateries extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<EateriesList> list = new ArrayList<>();
    Context context;
    TextView TITLE;
    TextView DESC;

    public Adapter_Eateries(Context context) {
        this.context = context;
        list.add(new EateriesList("CAFETERIA", "", R.drawable.ic, ICEF_HelperClass.IC));
        list.add(new EateriesList("RED CHILLIES", "", R.drawable.rc, ICEF_HelperClass.RED_CHILLIES));
        list.add(new EateriesList("A MESS", "", R.drawable.cm, ICEF_HelperClass.MESS_A));
        list.add(new EateriesList("C MESS", "", R.drawable.cm, ICEF_HelperClass.MESS_C));
        list.add(new EateriesList("Gaja Laxmi Snacks", "", R.drawable.gj, ICEF_HelperClass.GAJA_LAXMI));
        list.add(new EateriesList("ICE AND SPICE", "", R.drawable.inc, ICEF_HelperClass.ICE_SPICE));
        list.add(new EateriesList("FOOD KING", "", R.drawable.fk, ICEF_HelperClass.FOODKING));
        list.add(new EateriesList("DOMINOES", "", R.drawable.dominoes, ICEF_HelperClass.DOMINOES));
        list.add(new EateriesList("Night Canteen A", "", R.drawable.anc, ICEF_HelperClass.NC_A));
        list.add(new EateriesList("Night Canteen C", "", R.drawable.cnc, ICEF_HelperClass.NC_C));
        list.add(new EateriesList("Monginies", "", R.drawable.mongi, ICEF_HelperClass.MONGINIES));


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.list_main, parent, false);
        viewHolder = new VH_Eateries(view, list, parent.getContext());

        return viewHolder;

    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        VH_Eateries mv = (VH_Eateries) holder;
        mv.Title.setText(list.get(position).getTitle());
        mv.desc.setText(list.get(position).getDescription());
        mv.image.setImageURI(Uri.parse("res:///" + list.get(position).getBackground()));
    }
}
