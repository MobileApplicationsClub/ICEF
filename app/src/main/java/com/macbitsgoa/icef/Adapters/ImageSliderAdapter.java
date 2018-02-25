package com.macbitsgoa.icef.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.macbitsgoa.icef.fragments.base.BaseActivity;
import com.macbitsgoa.icef.GalleryFormat;
import com.macbitsgoa.icef.R;
import com.macbitsgoa.icef.viewImage;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

import uk.co.senab.photoview.PhotoViewAttacher;

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

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.gallery_row, container, false);

        final BaseActivity activity = (BaseActivity) context;
        final ImageView imageView = itemView.findViewById(R.id.galleryImage);

        final View card = itemView.findViewById(R.id.container);
        final View pB = itemView.findViewById(R.id.progressBar);
        card.setVisibility(View.INVISIBLE);

        if (target == null) {
            target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageView.setImageBitmap(bitmap);
                    savedImages.put(position, bitmap);
                    card.setVisibility(View.VISIBLE);
                    pB.setVisibility(View.INVISIBLE);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    activity.showToast("Error Loading image");
                    activity.finish();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };
        }


        final PhotoViewAttacher mAttacher = new PhotoViewAttacher(imageView);
        TextView imageTitle = itemView.findViewById(R.id.imageTitle);
        if (savedImages.containsKey(position)) {
            imageView.setImageBitmap(savedImages.get(position));
            card.setVisibility(View.VISIBLE);
            pB.setVisibility(View.INVISIBLE);
            mAttacher.update();
        } else {
            if (activity.isNetworkAvailable(context)) {
                Picasso.with(context).load(images.get(position).getImageurl()).into(target);
            } else {
                activity.showSnack("Internet unavailable");
            }
        }
        imageTitle.setText(images.get(position).getName());


        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((viewImage) context).onClick();

            }
        });

        mAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                ((viewImage) context).onClick();

            }

            @Override
            public void onOutsidePhotoTap() {

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

        return savedImages.get(position);
    }
}