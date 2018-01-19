package bits.mac.icef_2018.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.Vector;

import bits.mac.icef_2018.Lists.SpeakersList;
import bits.mac.icef_2018.R;
import bits.mac.icef_2018.Speakers;

/**
 * Created by aayush on 1/1/18.
 */

public class Adapter_Speakers_rv extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    Vector<SpeakersList> vector;

    public Adapter_Speakers_rv(Context context, Vector<SpeakersList> vector) {
        this.context = context;
        this.vector = vector;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        View view = inflater.inflate(R.layout.item_horizontal_rv, parent, false);
        viewHolder = new VH_horizontal_rv(view);

        return viewHolder;

    }

    @Override
    public int getItemCount() {
        return vector.size();
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        VH_horizontal_rv mv = (VH_horizontal_rv) holder;
        mv.simpleDraweeView.setImageURI(vector.get(position).getImageurl());

    }


    public class VH_horizontal_rv extends RecyclerView.ViewHolder implements View.OnClickListener {
        SimpleDraweeView simpleDraweeView;
        public VH_horizontal_rv(View itemview) {
            super(itemview);
            simpleDraweeView = (SimpleDraweeView) itemview.findViewById(R.id.imageholder);
            simpleDraweeView.setOnClickListener(this);

        }



        @Override
        public void onClick(View v) {
            Log.e("ABC", String.valueOf(getAdapterPosition()));
            Speakers.mViewPager.setCurrentItem(getAdapterPosition(), true);
            notifyDataSetChanged();

        }

             }



}