package bits.mac.icef_2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.auth.FirebaseAuth;

import bits.mac.icef_2018.fragments.AboutUs;
import bits.mac.icef_2018.fragments.Contact;
import bits.mac.icef_2018.fragments.Dashboard;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment dashboard= Dashboard.newInstance();
    Fragment contact= Contact.newInstance();
    Fragment Aboutus= AboutUs.newInstance();
    Intent intent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);

        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,dashboard).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sign_out_button) {
            FirebaseAuth.getInstance().signOut();
            finish();
            // Handle the camera action
        }else if (id == R.id.nav_timeline) {
            intent=new Intent(this,Timeline.class);
            startActivity(intent);

        } else if (id == R.id.nav_speakers) {
            intent=new Intent(this,Speakers.class);
            startActivity(intent);

        } else if (id == R.id.nav_CampusMap) {
            intent=new Intent(this,BPGCMapsActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_eateries) {
            intent=new Intent(this,Main_Eateries.class);
            startActivity(intent);

        } else if (id == R.id.notification_sender) {
            intent=new Intent(this,NotificationSender.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

//actions for dashboard tabs
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();

            switch (item.getItemId()) {

                case R.id.navigation_dashboard:
                    fragmentTransaction.replace(R.id.frameLayout,dashboard).commit();
                    return true;

                case R.id.navigation_contact:
                    fragmentTransaction.replace(R.id.frameLayout,contact).commit();
                    return true;

                case R.id.navigation_notifications:
                    fragmentTransaction.replace(R.id.frameLayout,Aboutus).commit();
                    return true;
            }

            return false;
        }
    };





}
