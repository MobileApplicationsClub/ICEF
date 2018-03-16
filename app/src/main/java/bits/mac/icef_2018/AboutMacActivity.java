package bits.mac.icef_2018;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.Toast;

import com.macbitsgoa.icef.R;

import bits.mac.icef_2018.fragments.base.BaseActivity;

public class AboutMacActivity extends BaseActivity implements View.OnClickListener {

    public static String ABOUT_US_FACEBOOK_URL = "https://www.facebook.com/MACBITSGoa";
    public static String ABOUT_US_FACEBOOK_PAGE_ID = "MACBITSGoa";

    @Override
    public void supportNavigateUpTo(@NonNull Intent upIntent) {
        super.supportNavigateUpTo(upIntent);
        onBackPressed();
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_mac);

        Toolbar toolbar = findViewById(R.id.about_us_toolbar);

        ImageButton facebookImgBtn = findViewById(R.id.content_about_mac_fb_imgbtn);
        ImageButton googlePlayImgBtn = findViewById(R.id.content_about_mac_google_play_imgbtn);
        //RecyclerView contributorsRecyclerView = (RecyclerView) findViewById(R.id.content_about_mac_rv);

        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("About the developers");

        //toolbar.setNavigationIcon(R.drawable.ic_phone);

        //These flags are for system bar on top
        //Don't bother yourself with this code
        Window window = this.getWindow();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // clear FLAG_TRANSLUCENT_STATUS flag
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        }


        facebookImgBtn.setOnClickListener(this);
        googlePlayImgBtn.setOnClickListener(this);
        findViewById(R.id.email_hardik).setOnClickListener(this);
        findViewById(R.id.email_aayush).setOnClickListener(this);
    }

    //method to get the right URL to use in the intent
    public String getFacebookPageURL(final Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + ABOUT_US_FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + ABOUT_US_FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return ABOUT_US_FACEBOOK_URL; //normal web url
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.content_about_mac_fb_imgbtn:
                try {
                    Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
                    String facebookUrl = getFacebookPageURL(this);
                    facebookIntent.setData(Uri.parse(facebookUrl));
                    startActivity(facebookIntent);
                } catch (ActivityNotFoundException e) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(ABOUT_US_FACEBOOK_URL));
                    Toast.makeText(this, "Opening in browser", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }
                break;
            case R.id.content_about_mac_google_play_imgbtn:
                Intent googlePlayIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/developer?id=Mobile+App+Club+-+BITS+Goa"));
                //googlePlayIntent.setData(Uri.parse("market://search?q=pub:Mobile App Club - BITS Goa"));
                startActivity(googlePlayIntent);
                break;
            case R.id.email_hardik: {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hardik12498@gmail.com"});
                startActivity(Intent.createChooser(intent, "Contact Hardik"));
                break;
            }
            case R.id.email_aayush: {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"f20170815@goa.bits-pilani.ac.in"});
                startActivity(Intent.createChooser(intent, "Contact Aayush"));
                break;
            }
            default:
                break;
        }
    }
}
