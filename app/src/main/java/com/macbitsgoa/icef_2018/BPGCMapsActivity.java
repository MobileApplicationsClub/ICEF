package com.macbitsgoa.icef_2018;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Calendar;

import static com.macbitsgoa.icef_2018.ICEF_HelperClass.DOMINOES;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.FOODKING;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.GAJA_LAXMI;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.IC;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.ICE_SPICE;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.MESS_A;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.MESS_C;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.NC_A;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.NC_C;
import static com.macbitsgoa.icef_2018.ICEF_HelperClass.RED_CHILLIES;

public class BPGCMapsActivity extends FragmentActivity implements OnMapReadyCallback {

    LatLng leftBottomBound = new LatLng(15.385178, 73.868207);
    LatLng rightTopBound = new LatLng(15.394957, 73.885857);
    LatLng centre = new LatLng(15.389557, 73.876974);
    FloatingActionButton mapfab;
    private GoogleMap map;
    SupportMapFragment mapFragment;

    public BPGCMapsActivity(long lat,long lon){
        LatLng marker = new LatLng(lat, lon);
        map.addMarker(new MarkerOptions().position(marker));

    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bpgcmaps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.bitsMap);

        mapFragment.getMapAsync(this);
        mapfab = (FloatingActionButton) findViewById(R.id.fab_map);

        mapfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/d/u/0/viewer?mid=1oWEtH59EbMs82Z49uj00UnxQD2o"));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Snackbar.make(mapfab, "Google Maps is not installed!", Snackbar.LENGTH_LONG).show();
                }
            }
        });


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;

        int hour;
        hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour <= 6 || hour >= 19) {
            map.setMapStyle(MapStyleOptions.loadRawResourceStyle(this, R.raw.style_json));
        } else {
            Log.e("TAG", "morning " + hour);
        }

        Intent intent = getIntent();
        String location = intent.getStringExtra("Location");
        LatLngBounds bounds = new LatLngBounds(leftBottomBound, rightTopBound);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(centre, 0));
        if (location != null && location.length() < 16) {
            if (location.equals("Auditorium")) {
                LatLng marker = new LatLng(15.39304, 73.88051);
                map.addMarker(new MarkerOptions().position(marker).title("Auditorium"));
            } else if (location.equals("Audi")) {
                LatLng marker = new LatLng(15.39304, 73.88051);
                map.addMarker(new MarkerOptions().position(marker).title("Audi"));
            } else if (location.equals("Crossroads")) {
                LatLng marker = new LatLng(15.38899, 73.87638);
                map.addMarker(new MarkerOptions().position(marker).title("Crossroads"));
            } else if (location.equals("Computer Centre")) {
                LatLng marker = new LatLng(15.391701, 73.880938);
                map.addMarker(new MarkerOptions().position(marker).title("Computer Centre"));
            } else if (location.equals("CC")) {
                LatLng marker = new LatLng(15.391701, 73.880938);
                map.addMarker(new MarkerOptions().position(marker).title("CC"));
            } else if (location.equals("Library Lawns")) {
                LatLng marker = new LatLng(15.391680, 73.880251);
                map.addMarker(new MarkerOptions().position(marker).title("Library Lawns"));
            } else if (location.equals("Outdoor Stage")) {
                LatLng marker = new LatLng(15.392513, 73.879994);
                map.addMarker(new MarkerOptions().position(marker).title("Outdoor Stage"));
            } else if (location.contains("LT 3-4")) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals("LT-3")) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals("LT-4")) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("LT 1-2")) {
                LatLng marker = new LatLng(15.392513, 73.880931);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals("LT-1")) {
                LatLng marker = new LatLng(15.392513, 73.880931);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals("LT-2")) {
                LatLng marker = new LatLng(15.392513, 73.880931);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("A-")) {
                LatLng marker = new LatLng(15.393009, 73.879715);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("A ")) {
                LatLng marker = new LatLng(15.393009, 73.879715);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("LC-")) {
                LatLng marker = new LatLng(15.391701, 73.880938);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("LC ")) {
                LatLng marker = new LatLng(15.391701, 73.880938);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("C-")) {
                LatLng marker = new LatLng(15.392280, 73.880514);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.contains("C ")) {
                LatLng marker = new LatLng(15.392280, 73.880514);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals("VGH")) {
                LatLng marker = new LatLng(15.38811, 73.87663);
                map.addMarker(new MarkerOptions().position(marker).title("VGH"));
            } else if (location.equals("Basement")) {
                LatLng marker = new LatLng(15.392650, 73.880128);
                map.addMarker(new MarkerOptions().position(marker).title("Basement"));
            }else if (location.equals(MESS_A)) {
                LatLng marker = new LatLng(15.39304, 73.88051);
                map.addMarker(new MarkerOptions().position(marker).title("Auditorium"));
            } else if (location.equals(MESS_C)) {
                LatLng marker = new LatLng(15.39304, 73.88051);
                map.addMarker(new MarkerOptions().position(marker).title("Audi"));
            } else if (location.equals(NC_A)) {
                LatLng marker = new LatLng(15.38899, 73.87638);
                map.addMarker(new MarkerOptions().position(marker).title("Crossroads"));
            } else if (location.equals(NC_C)) {
                LatLng marker = new LatLng(15.391701, 73.880938);
                map.addMarker(new MarkerOptions().position(marker).title("Computer Centre"));
            } else if (location.equals(IC)) {
                LatLng marker = new LatLng(15.391701, 73.880938);
                map.addMarker(new MarkerOptions().position(marker).title("CC"));
            } else if (location.equals(FOODKING)) {
                LatLng marker = new LatLng(15.391680, 73.880251);
                map.addMarker(new MarkerOptions().position(marker).title("Library Lawns"));
            } else if (location.equals(ICE_SPICE)) {
                LatLng marker = new LatLng(15.392513, 73.879994);
                map.addMarker(new MarkerOptions().position(marker).title("Outside Stage"));
            } else if (location.equals(GAJA_LAXMI)) {
                LatLng marker = new LatLng(15.392513, 73.879994);
                map.addMarker(new MarkerOptions().position(marker).title("Outdoor Stage"));
            } else if (location.contains(DOMINOES)) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals(RED_CHILLIES)) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            } else if (location.equals("DLT6")) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            }else if (location.equals("D101")) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            }else if (location.equals("D104")) {
                LatLng marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title(location));
            }else {

                LatLng marker = new LatLng(15.39171, 73.87601);
                map.addMarker(new MarkerOptions().position(marker).title("Pharmacy"));
                marker = new LatLng(15.393009, 73.879715);
                map.addMarker(new MarkerOptions().position(marker).title("A side"));
                marker = new LatLng(15.392280, 73.880514);
                map.addMarker(new MarkerOptions().position(marker).title("C side"));
                marker = new LatLng(15.38811, 73.87663);
                map.addMarker(new MarkerOptions().position(marker).title("Visitor's Guest House"));
                marker = new LatLng(15.3917, 73.87605);
                map.addMarker(new MarkerOptions().position(marker).title("Medical Centre"));
                marker = new LatLng(15.391248, 73.880643);
                map.addMarker(new MarkerOptions().position(marker).title("FoodKing"));
                marker = new LatLng(15.39295, 73.87635);
                map.addMarker(new MarkerOptions().position(marker).title("Cricket Ground"));
                marker = new LatLng(15.39208, 73.8756);
                map.addMarker(new MarkerOptions().position(marker).title("Student Activity Centre"));
                marker = new LatLng(15.392558, 73.878976);
                map.addMarker(new MarkerOptions().position(marker).title("Ice n Spice"));
                marker = new LatLng(15.391766, 73.880724);
                map.addMarker(new MarkerOptions().position(marker).title("Monginis"));
                marker = new LatLng(15.39191, 73.8764);
                map.addMarker(new MarkerOptions().position(marker).title("Borkars Super Store"));
                marker = new LatLng(15.39199, 73.87626);
                map.addMarker(new MarkerOptions().position(marker).title("SBI Branch"));
                marker = new LatLng(15.39202, 73.87619);
                map.addMarker(new MarkerOptions().position(marker).title("SBI ATM"));
                marker = new LatLng(15.39218, 73.87621);
                map.addMarker(new MarkerOptions().position(marker).title("Persian Court"));
                marker = new LatLng(15.38743, 73.87574);
                map.addMarker(new MarkerOptions().position(marker).title("Main gate"));
                marker = new LatLng(15.38899, 73.87638);
                map.addMarker(new MarkerOptions().position(marker).title("Crossroads"));
                marker = new LatLng(15.3921, 73.87963);
                map.addMarker(new MarkerOptions().position(marker).title("Flag Lawns"));
                marker = new LatLng(15.39362, 73.87911);
                map.addMarker(new MarkerOptions().position(marker).title("Workshop"));
                marker = new LatLng(15.39304, 73.88051);
                map.addMarker(new MarkerOptions().position(marker).title("Auditorium"));
                marker = new LatLng(15.391414, 73.877857);
                map.addMarker(new MarkerOptions().position(marker).title("A Mess"));
                marker = new LatLng(15.390460, 73.878817);
                map.addMarker(new MarkerOptions().position(marker).title("C Mess"));
                marker = new LatLng(15.392513, 73.880931);
                map.addMarker(new MarkerOptions().position(marker).title("LT 1-2 Lawns"));
                marker = new LatLng(15.393423, 73.879933);
                map.addMarker(new MarkerOptions().position(marker).title("LT 3-4 Lawns"));


            }
        } else {


            LatLng marker = new LatLng(15.39171, 73.87601);
            map.addMarker(new MarkerOptions().position(marker).title("Pharmacy"));
            marker = new LatLng(15.393009, 73.879715);
            map.addMarker(new MarkerOptions().position(marker).title("A side"));
            marker = new LatLng(15.392280, 73.880514);
            map.addMarker(new MarkerOptions().position(marker).title("C side"));
            marker = new LatLng(15.38811, 73.87663);
            map.addMarker(new MarkerOptions().position(marker).title("Visitor's Guest House"));
            marker = new LatLng(15.3917, 73.87605);
            map.addMarker(new MarkerOptions().position(marker).title("Medical Centre"));
            marker = new LatLng(15.391248, 73.880643);
            map.addMarker(new MarkerOptions().position(marker).title("FoodKing"));
            marker = new LatLng(15.39295, 73.87635);
            map.addMarker(new MarkerOptions().position(marker).title("Cricket Ground"));
            marker = new LatLng(15.39208, 73.8756);
            map.addMarker(new MarkerOptions().position(marker).title("Student Activity Centre"));
            marker = new LatLng(15.392558, 73.878976);
            map.addMarker(new MarkerOptions().position(marker).title("Ice n Spice"));
            marker = new LatLng(15.391766, 73.880724);
            map.addMarker(new MarkerOptions().position(marker).title("Monginis"));
            marker = new LatLng(15.39191, 73.8764);
            map.addMarker(new MarkerOptions().position(marker).title("Borkars Super Store"));
            marker = new LatLng(15.39199, 73.87626);
            map.addMarker(new MarkerOptions().position(marker).title("SBI Branch"));
            marker = new LatLng(15.39202, 73.87619);
            map.addMarker(new MarkerOptions().position(marker).title("SBI ATM"));
            marker = new LatLng(15.39218, 73.87621);
            map.addMarker(new MarkerOptions().position(marker).title("Persian Court"));
            marker = new LatLng(15.38743, 73.87574);
            map.addMarker(new MarkerOptions().position(marker).title("Main gate"));
            marker = new LatLng(15.38899, 73.87638);
            map.addMarker(new MarkerOptions().position(marker).title("Crossroads"));
            marker = new LatLng(15.3921, 73.87963);
            map.addMarker(new MarkerOptions().position(marker).title("Flag Lawns"));
            marker = new LatLng(15.39362, 73.87911);
            map.addMarker(new MarkerOptions().position(marker).title("Workshop"));
            marker = new LatLng(15.39304, 73.88051);
            map.addMarker(new MarkerOptions().position(marker).title("Auditorium"));
            marker = new LatLng(15.391414, 73.877857);
            map.addMarker(new MarkerOptions().position(marker).title("A Mess"));
            marker = new LatLng(15.390460, 73.878817);
            map.addMarker(new MarkerOptions().position(marker).title("C Mess"));
            marker = new LatLng(15.392513, 73.880931);
            map.addMarker(new MarkerOptions().position(marker).title("LT 1-2 Lawns"));
            marker = new LatLng(15.393423, 73.879933);
            map.addMarker(new MarkerOptions().position(marker).title("LT 3-4 Lawns"));

        }
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(centre)      // Sets the center of the map to Mountain View
                .zoom(16f)                   // Sets the zoom
                // Sets the orientation of the camera to east
                .tilt(50)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }

    public void setCamera(LatLng marker){
        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(marker)      // Sets the center of the map to Mountain View
                .zoom(16f)                   // Sets the zoom
                // Sets the orientation of the camera to east
                .tilt(50)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

    }
}
