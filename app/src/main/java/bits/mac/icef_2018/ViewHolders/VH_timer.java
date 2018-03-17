package bits.mac.icef_2018.ViewHolders;

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.macbitsgoa.icef.R;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class VH_timer extends RecyclerView.ViewHolder {
    public TextView daysText;
    public TextView hoursText;
    public TextView minutesText;
    public TextView secondsText;
    public TextView countDownToIcef; //TextView showing "CountDownToWaves"
    public TextView colon1;  //Textviews showing colons in CountDown
    public TextView colon2;
    public TextView colon3;
    public TextView days;  //TextView showing "days" under the count of days left
    public TextView minutes;
    public TextView hours;
    public TextView seconds;
    public CountDownTimer countDownTimer;
    public WindowManager windowManager;
    Context context;

    public VH_timer(View itemView, Context context) {
        super(itemView);
        this.context = context;
        daysText = itemView.findViewById(R.id.viewholder_main_timer_days);
        hoursText = itemView.findViewById(R.id.viewholder_main_timer_hours);
        minutesText = itemView.findViewById(R.id.viewholder_main_timer_minutes);
        secondsText = itemView.findViewById(R.id.viewholder_main_timer_seconds);

        countDownToIcef = itemView.findViewById(R.id.countdownToIcef);
        colon1 = itemView.findViewById(R.id.content_main_timer_colon_centre);
        colon2 = itemView.findViewById(R.id.content_main_timer_colon_days_hours);
        colon3 = itemView.findViewById(R.id.content_main_timer_colon_mins_sec);
        days = itemView.findViewById(R.id.days_text);
        minutes = itemView.findViewById(R.id.minutes_text);
        hours = itemView.findViewById(R.id.hours_text);
        seconds = itemView.findViewById(R.id.seconds_text);


    }
}
