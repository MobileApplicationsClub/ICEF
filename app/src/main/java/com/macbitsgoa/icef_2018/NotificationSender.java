package com.macbitsgoa.icef_2018;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import bits.macbitsgoa.icef_2018.R;

public class NotificationSender extends AppCompatActivity {
    EditText textView;
    boolean b=false;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sender);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                .permitAll().build();
        StrictMode.setThreadPolicy(policy);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
         textView=findViewById(R.id.textView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                message=textView.getText().toString();


                FirebaseMessaging.getInstance().subscribeToTopic("Message");

                String pName = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
                RemoteMessage.Builder creator = new RemoteMessage.Builder("Message");
                creator.addData("Message", message);


                try {
                    Log.e("noti","ENTERED");

                    URL url = new URL("https://fcm.googleapis.com/fcm/send");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Authorization", "key=AAAAOZ05D4Q:APA91bFj006ZDIsv5iUHUT5rsgM8OHknzZUna7qXEbOXCtlKjeV5ZN-oGcF2sH9EH3oEEJMZ852DZQdwRq34pXeNin7oIaSTUUKw9NgK4lwL-xqfp7IqmuwUWFb3oYmtiqlUSaWmq1X7");
                    connection.setDoOutput(true);
                    connection.connect();
                    Log.e("noti","CONNECTED");


                    DataOutputStream os = new DataOutputStream(connection.getOutputStream());
                    OutputStreamWriter writer = new OutputStreamWriter(os);


                    Map<String, Object> data = new HashMap<String, Object>();
                    data.put("to", "/topics/" + "Message");
                    data.put("data", creator.build().getData());

                    JSONObject object = new JSONObject(data);
                    Log.e("noti","o:"+object.toString());

                    String s2 = object.toString().replace("\\", "");
                    os.writeBytes(s2);
                    os.close();

                    Log.e("noti","s:"+s2);


                    InputStream is = connection.getInputStream();
                    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                    StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                    String line;
                    while ((line = rd.readLine()) != null) {
                        response.append(line);
                    }
                    rd.close();

                    Log.e("noti","r:"+response.toString());

                    textView.setText("");
                    Snackbar.make(textView,"Notification sent succesfully",Snackbar.LENGTH_LONG).show();
                    b=true;
                } catch (Exception e) {

                    Snackbar.make(textView,"Check your internet connection",Snackbar.LENGTH_LONG).show();
                    Log.e("msg",e.getMessage());
                    e.printStackTrace();

                }
                if(b){
                    DatabaseReference firebaseDatabase= FirebaseDatabase.getInstance().getReference().child("Notifications").push();
                    String key=firebaseDatabase.getKey();
                    firebaseDatabase.child("key").setValue(key);
                    firebaseDatabase.child("message").setValue(message);
                    firebaseDatabase.child("dt").setValue(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(Calendar.getInstance().getTime()));
                }


            }
        });






    }

}
