package com.macbitsgoa.icef;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.macbitsgoa.icef.Adapters.ImageSliderAdapter;
import com.macbitsgoa.icef.fragments.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

interface touchCallback {
    void onClick();
}

public class viewImage extends BaseActivity implements touchCallback {
    private String localAbsoluteFilePath, dirPath;
    private ArrayList<GalleryFormat> images;
    private ViewPager pager;
    private ImageSliderAdapter adapter;
    private saveImage saveImage = new saveImage();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        makeActivityFullScreen();
        setContentView(R.layout.activity_view_image);

        setToolbar();
        showBackButton();
        setSupportActionBar(getToolbar());

        Bundle extra = getIntent().getExtras();
        if (extra == null)
            finish();

        dirPath = extra.containsKey("dirPath") ? extra.getString("dirPath") : "";
        images = getIntent().getExtras().getParcelableArrayList("images");
        initializeLayout();

        findViewById(R.id.viewButtonLayout).setVisibility(View.INVISIBLE);
    }

    void shareImage() {
        saveImage saveImage = new saveImage();

        GalleryFormat item = images.get(pager.getCurrentItem());
        if (localAbsoluteFilePath == null)
            localAbsoluteFilePath = saveImage.saveImageLocally(adapter.getImage(pager.getCurrentItem()), item.getName(), dirPath);
        if (localAbsoluteFilePath != null && !localAbsoluteFilePath.equals("")) {

            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            Uri phototUri = Uri.parse(localAbsoluteFilePath);
            shareIntent.setData(phototUri);
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
            this.startActivityForResult(Intent.createChooser(shareIntent, "Share Via"), 0);
        }
    }

    void saveToGallery() {
        showProgressDialog("Saving");
        GalleryFormat item = images.get(pager.getCurrentItem());
        localAbsoluteFilePath = saveImage.saveImageLocally(adapter.getImage(pager.getCurrentItem()), item.getName(), dirPath);
        hideProgressDialog();
        if (localAbsoluteFilePath != null)
            Toast.makeText(this, "Saved Successfully", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Failed", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            // delete temp file
            File file = new File(localAbsoluteFilePath);
            file.delete();
        }
    }


    void initializeLayout() {

        pager = findViewById(R.id.container);
        adapter = new ImageSliderAdapter(this, images);
        pager.setAdapter(adapter);

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                setToolbarTitle(images.get(position).getName());

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        int id[] = {R.id.button_share, R.id.button_save};
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.button_share:
                        shareImage();
                        break;
                    case R.id.button_save:
                        saveToGallery();
                        break;
                }
            }
        };
        for (int i : id) {
            ImageView button = findViewById(i);
            button.setOnClickListener(buttonListener);
        }
    }

    @Override
    public void onClick() {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final View viewButtonLayout = findViewById(R.id.viewButtonLayout);
                if (viewButtonLayout.getVisibility() == View.INVISIBLE) {
                    viewButtonLayout.setVisibility(View.VISIBLE);

                    new Timer().schedule(new TimerTask() {
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    viewButtonLayout.setVisibility(View.INVISIBLE);

                                }
                            });

                        }
                    }, 3500);
                }


            }
        });
    }
}