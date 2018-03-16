package com.macbitsgoa.icef.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.chrisbanes.photoview.OnPhotoTapListener;
import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.macbitsgoa.icef.Lists.GalleryFormat;
import com.macbitsgoa.icef.R;
import com.macbitsgoa.icef.fragments.base.BaseActivity;
import com.macbitsgoa.icef.viewImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class ImageSliderAdapter extends PagerAdapter {
    Context context;
    ArrayList<GalleryFormat> images;
    LayoutInflater layoutInflater;
    private ArrayMap<Integer, Bitmap> savedImages = new ArrayMap<>();
    private Target target;

    public ImageSliderAdapter(Context context, ArrayList<GalleryFormat> images) {
        this.context = context;
        this.images = images;

        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.gallery_row, container, false);

        final BaseActivity activity = (BaseActivity) context;
        final ImageView imageView = itemView.findViewById(R.id.galleryImage);

        final View card = itemView.findViewById(R.id.container);
        final View pB = itemView.findViewById(R.id.progressBar);
        card.setVisibility(View.INVISIBLE);

        pB.setVisibility(View.VISIBLE);
        if (activity.isNetworkAvailable(context)) {
            Picasso.with(context).load(images.get(position).getImageurl()).into(imageView);
            card.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.VISIBLE);
            pB.setVisibility(View.GONE);
            final PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
            mAttacher.update();
            mAttacher.setOnPhotoTapListener(new OnPhotoTapListener() {
                @Override
                public void onPhotoTap(ImageView view, float x, float y) {
                    ((viewImage) context).onClick();

                }
            });

        } else {
            pB.setVisibility(View.GONE);
            activity.showSnack("Internet unavailable");
        }


        TextView imageTitle = itemView.findViewById(R.id.imageTitle);
        imageTitle.setText(images.get(position).getName());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((viewImage) context).onClick();

            }
        });


        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((CoordinatorLayout) object);
    }

    public Bitmap getImage(int position) {

        return getBitmapFromURL(images.get(position).getImageurl());
    }


    public static Bitmap getBitmapFromURL(String src) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

}