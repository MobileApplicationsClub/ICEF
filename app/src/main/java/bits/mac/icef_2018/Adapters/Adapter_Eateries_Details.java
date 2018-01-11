package bits.mac.icef_2018.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import bits.mac.icef_2018.R;

/**
 * Created by aayush on 19/12/17.
 */

public class Adapter_Eateries_Details extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> list;
    String Url;

    public Adapter_Eateries_Details(Context context, ArrayList<String> list) {
        mContext = context;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @Override
    public int getCount() {
        return (list.size()-2);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.layout_pager_item, container, false);
        Url = list.get(position+2);
     SimpleDraweeView imageView = (SimpleDraweeView) itemView.findViewById(R.id.imageView);
    imageView.setImageURI(Url);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }


}