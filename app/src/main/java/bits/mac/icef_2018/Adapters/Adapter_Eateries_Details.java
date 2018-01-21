package bits.mac.icef_2018.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.facebook.drawee.view.SimpleDraweeView;

import java.net.URL;
import java.util.ArrayList;
import bits.mac.icef_2018.R;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by aayush on 19/12/17.
 */

public class Adapter_Eateries_Details extends PagerAdapter {

    Context mContext;
    LayoutInflater mLayoutInflater;
    ArrayList<String> list;


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
        return view ==  object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.layout_pager_item, container, false);

        PhotoView imageView = itemView.findViewById(R.id.imageView);
        try {
            URL url = new URL(list.get(position + 2));
            Bitmap bmp= BitmapFactory.decodeStream(url.openConnection().getInputStream());
            imageView.setImageBitmap(bmp);
        }catch (Exception e){
            imageView.setImageBitmap(null);
        }

        imageView.setZoomable(true);
        container.addView(itemView);
        return itemView;

    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);

    }


}