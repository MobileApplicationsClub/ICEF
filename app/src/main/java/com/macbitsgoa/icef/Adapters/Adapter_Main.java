package com.macbitsgoa.icef.Adapters;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.net.Uri;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.macbitsgoa.icef.ICEF_HelperClass;
import com.macbitsgoa.icef.Lists.MainList;
import com.macbitsgoa.icef.R;
import com.macbitsgoa.icef.ViewHolders.VH_main;
import com.macbitsgoa.icef.ViewHolders.VH_timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static com.macbitsgoa.icef.ICEF_HelperClass.BOOK;
import static com.macbitsgoa.icef.ICEF_HelperClass.EATERIES;
import static com.macbitsgoa.icef.ICEF_HelperClass.GALLERY;
import static com.macbitsgoa.icef.ICEF_HelperClass.MAIN_SPEAKERS;
import static com.macbitsgoa.icef.ICEF_HelperClass.MAIN_TIMELINE;
import static com.macbitsgoa.icef.ICEF_HelperClass.PARTICIPANTS;

/**
 * Created by aayush on 18/12/17.
 */

@SuppressWarnings("ALL")
public class Adapter_Main extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MainList> list = new ArrayList<>();
    Context context;

    public Adapter_Main(Context context) {

        this.context = context;
        list.add(new MainList("TIMELINE", "", R.drawable.timeline, MAIN_TIMELINE));
        list.add(new MainList("SPEAKERS", "", R.drawable.mic1, MAIN_SPEAKERS));
        list.add(new MainList("PARTICIPANTS", "", R.drawable.parti, PARTICIPANTS));
        list.add(new MainList("EATERIES", "", R.drawable.eateries, EATERIES));
       // list.add(new MainList("FAQ", "", R.drawable.siri, FAQ));
        list.add(new MainList("MEMORIES", "", R.drawable.admissions,GALLERY));
        list.add(new MainList("Book Of Abstracts", "", R.drawable.oldbooks, BOOK));


    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());


        if (viewType == 0) {
            View v1 = inflater.inflate(R.layout.item_format_countdown, parent, false);
            viewHolder = new VH_timer(v1, parent.getContext());
        }
        if (viewType == 1) {
            View v2 = inflater.inflate(R.layout.list_main, parent, false);
            viewHolder = new VH_main(v2, parent.getContext(), list);
        }


        return viewHolder;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == 0) {

            //Countdown Timer
            final VH_timer cv = (VH_timer) holder;
            Date liveDate = Calendar.getInstance().getTime();
            Date wavesStartDate = null;

            //catching any possible parse exception
            try {
                wavesStartDate = new SimpleDateFormat("ddMMyyyy", Locale.UK).parse(ICEF_HelperClass.ICEFSTARTDATE);
            } catch (ParseException e) {
                e.printStackTrace();
            }


            assert wavesStartDate != null;
            //Get time difference and start a countdown timer if waves hasn't started
            if (wavesStartDate.getTime() - liveDate.getTime() >= 0) {
                cv.countDownTimer =
                        new CountDownTimer(wavesStartDate.getTime() - liveDate.getTime(), 1000) {

                            public void onTick(long millisUntilFinished) {
                                long days = millisUntilFinished / (1000 * 60 * 60 * 24);
                                long hours = millisUntilFinished / (1000 * 60 * 60) % 24;
                                long minutes = millisUntilFinished / (1000 * 60) % 60;
                                long seconds = millisUntilFinished / (1000) % 60;

                                if (days >= 10) {
                                    cv.daysText.setText(String.valueOf(days));
                                } else {
                                    cv.daysText.setText("0" + days);
                                }
                                if (hours >= 10) {
                                    cv.hoursText.setText(String.valueOf(hours));
                                } else {
                                    cv.hoursText.setText("0" + hours);
                                }
                                if (minutes >= 10) {
                                    cv.minutesText.setText(String.valueOf(minutes));
                                } else {
                                    cv.minutesText.setText("0" + minutes);
                                }
                                if (seconds >= 10) {
                                    cv.secondsText.setText(String.valueOf(seconds));
                                } else {
                                    cv.secondsText.setText("0" + seconds);
                                }

                            }

                            public void onFinish() {
                                //In case person is viewing MainActivity and timer goes down to zero. WAVES BITCH!
                                cv.daysText.setVisibility(View.GONE);
                                cv.secondsText.setVisibility(View.GONE);
                                cv.minutesText.setVisibility(View.GONE);
                                cv.hoursText.setVisibility(View.GONE);
                                cv.days.setVisibility(View.GONE);
                                cv.minutes.setVisibility(View.GONE);
                                cv.hours.setVisibility(View.GONE);
                                cv.seconds.setVisibility(View.GONE);

                            }
                        };
                cv.countDownTimer.start();
            }

            //Current date is more than waves start date
            //Later on we can add a check for end date also
            else {
                cv.daysText.setText("Thanks! FOR MAKING\nICEF 2018\nA GRAND SUCCESS");

                //Getting the width of the screen and setting it as the width of daysText
                WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
                Display display = windowmanager.getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int width = size.x;
                int height = 200;
                Resources resources = Resources.getSystem();
                float pixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, resources.getDisplayMetrics());
                ViewGroup.LayoutParams params = cv.daysText.getLayoutParams();
                params.width = width;
                params.height = (int) pixels;
                cv.daysText.setLayoutParams(params);
                cv.daysText.setGravity(Gravity.CENTER);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    cv.daysText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                }

                //Making all others Textboxes Gone
                cv.hoursText.setVisibility(View.GONE);
                cv.secondsText.setVisibility(View.GONE);
                cv.minutesText.setVisibility(View.GONE);
                cv.countDownToIcef.setVisibility(View.GONE);
                cv.colon1.setVisibility(View.GONE);
                cv.colon2.setVisibility(View.GONE);
                cv.colon3.setVisibility(View.GONE);
                cv.hours.setVisibility(View.GONE);
                cv.minutes.setVisibility(View.GONE);
                cv.seconds.setVisibility(View.GONE);
                cv.days.setVisibility(View.GONE);
            }


        } else if (holder.getItemViewType() == 1) {


            VH_main mv = (VH_main) holder;


            mv.titleView.setText(list.get(position - 1).getTitle());
            mv.descView.setText(list.get(position - 1).getDescription());
            mv.image.setImageURI(Uri.parse("res:///" + list.get(position - 1).getBackground()));


        }

    }

    @Override
    public int getItemViewType(int position) {


        if (position == 0)
            return 0;
        else
            return 1;
    }


    @Override
    public int getItemCount() {


        return (list.size() + 1);

    }
}

