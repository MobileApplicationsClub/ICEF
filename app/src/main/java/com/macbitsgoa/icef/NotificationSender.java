package com.macbitsgoa.icef;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class NotificationSender extends AppCompatActivity {
    EditText textView;
    boolean b = false;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sender);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final FloatingActionButton fab = findViewById(R.id.fab);
        textView = findViewById(R.id.textView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message = textView.getText().toString();


                FirebaseMessaging.getInstance().subscribeToTopic("Message");

                RemoteMessage.Builder creator = new RemoteMessage.Builder("Message");
                creator.addData("Message", message);


                try {

                    URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Authorization", "key=AAAAOZ05D4Q:APA91bE-WLPZ21lxq8m5zPnZ73aeNDoP9KBZCVGJMPH3GstuFUdNEXLSYD8umq-PN_6cd4wPWhc5EqfzqbDM77o7TYFpjdAdM9rB4Hl6TVBXrHIf37LS_kgufgRF8W8QtGs-fDek--Dd");
                    connection.setDoOutput(true);
                    connection.connect();
                    Log.e("noti", "CONNECTED");


                    DataOutputStream os = new DataOutputStream(connection.getOutputStream());


                    Map<String, Object> data = new HashMap<>();
                    data.put("to", "/topics/" + "Message");
                    data.put("data", creator.build().getData());

                    JSONObject object = new JSONObject(data);
                    Log.e("noti", "o:" + object.toString());

                    String s2 = object.toString().replace("\\", "");
                    os.writeBytes(s2);
                    os.close();

                    Log.e("noti", "s:" + s2);

                    //recieving response
                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                    String line;
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                    }
                    rd.close();

                    Log.e("noti", "r:" + response.toString());


                    b = true;
                    textView.setText("");
                    Snackbar.make(textView, "Sent", Snackbar.LENGTH_LONG).show();

                } catch (Exception e) {

                    Snackbar.make(textView, "Check your internet connection", Snackbar.LENGTH_LONG).show();
                    Log.e("msg", e.getMessage());
                    e.printStackTrace();

                }

                if (b) {
                    DatabaseReference firebaseDatabase = FirebaseDatabase.getInstance().getReference().child("Notifications").push();
                    String key = firebaseDatabase.getKey();
                    firebaseDatabase.child("key").setValue(key);
                    firebaseDatabase.child("message").setValue(message);
                    firebaseDatabase.child("dt").setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
                }


            }
        });


    }

}
