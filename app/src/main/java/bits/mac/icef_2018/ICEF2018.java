package bits.mac.icef_2018;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

/**
 * Created by aayush on 21/1/18.
 */

@SuppressWarnings("ALL")
public class ICEF2018 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        Fresco.initialize(this);
        FirebaseMessaging.getInstance().subscribeToTopic("Message");
    }
}
