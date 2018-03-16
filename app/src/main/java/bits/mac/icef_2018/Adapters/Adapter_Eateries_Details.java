package bits.mac.icef_2018.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.chrisbanes.photoview.PhotoView;
import com.macbitsgoa.icef.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;



public class Adapter_Eateries_Details extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> list;


    public Adapter_Eateries_Details(Context context, ArrayList<String> list) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    public Adapter_Eateries_Details() {

    }

    @Override
    public int getCount() {
        return (list.size() - 2);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.layout_pager_item, container, false);
        PhotoView imageView = itemView.findViewById(R.id.imageView);

        Picasso.with(mContext).load(String.valueOf(list.get(position + 2))).into(imageView);
        imageView.setZoomable(true);
        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

    }


}