package bits.mac.icef_2018;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aayush on 6/2/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{


    public MyFirebaseMessagingService(){
        super();
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        sendMyNotification(remoteMessage.getData().get("Message"));


    }

    private void sendMyNotification(String message) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Creates an explicit intent for an ResultActivity to receive.
        Intent resultIntent = new Intent(this, Notifications.class);

// This ensures that the back button follows the recommended
// convention for the back key.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);

// Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(Notifications.class);

// Adds the Intent that starts the Activity to the top of the stack.
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(
                0, PendingIntent.FLAG_UPDATE_CURRENT);

// Create remote view and set bigContentView.
        RemoteViews expandedView = new RemoteViews(this.getPackageName(),
                R.layout.item_notification);

        expandedView.setTextViewText(R.id.title, "ICEF 2018");
        expandedView.setTextViewText(R.id.noti, message);

        Notification notification = new NotificationCompat.Builder(this,"Message")
                .setSmallIcon(R.drawable.bits)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle("ICEF 2018")
                .setContentText("Pinch out to expand")
                .setPriority(Notification.PRIORITY_MAX).build();

        notification.bigContentView = expandedView;

        notificationManager.notify(1,notification);


    }
            //not working
            @Override
            public void onMessageSent(String msgID) {
                Log.e("wouter", "##########onMessageSent: " + msgID );
            super.onMessageSent(msgID);
            Snackbar.make(null,"Message sent",Snackbar.LENGTH_LONG).show();

        }

        //not working
        @Override
        public void onSendError(String msgID, Exception exception) {
            Log.e("wouter", "onSendError ", exception );

            Snackbar.make(null,"Message wasn't sent",Snackbar.LENGTH_LONG).show();

            }
}

