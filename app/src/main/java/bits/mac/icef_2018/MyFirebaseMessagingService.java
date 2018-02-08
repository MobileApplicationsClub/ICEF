package bits.mac.icef_2018;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

/**
 * Created by aayush on 6/2/18.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage){
        sendMyNotification(remoteMessage.getData().get("Message"));


    }

    private void sendMyNotification(String message) {
/*

     //   RemoteViews bigView = new RemoteViews(getApplicationContext().getPackageName(),R.layout.item_notification);
        //bigView.setTextViewText(R.id.title,"ICEF-2018");
       // bigView.setTextViewText(R.id.noti,message);

        Uri soundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"Message");
        NotificationCompat.BigTextStyle style =new NotificationCompat.BigTextStyle();
        style.bigText(message);

           builder = builder.setAutoCancel(true)
                      .setSound(soundUri)
                      .setSmallIcon(R.drawable.bits)
                      .setContentTitle("ICEF 2018")
                      .setContentIntent(pendingIntent)
                      .setStyle(style);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification=builder.build();
        notificationManager.notify(0, notification );
   */

    NotificationManager manager= (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(this,"Message")
                .setContentTitle("ICEF 2018")
                .setContentText(message)
                .setSmallIcon(R.drawable.bits)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true);

        NotificationCompat.BigTextStyle style =new NotificationCompat.BigTextStyle(builder);
        style.bigText(message);
        builder.setStyle(style);
        //On click of notification it redirect to this Activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);

        builder.setContentIntent(pendingIntent);
        manager.notify(0, builder.build());

    }

}

