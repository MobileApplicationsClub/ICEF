package com.macbitsgoa.icef_2018;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.PowerManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.macbitsgoa.icef_2018.Lists.TimelineList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Vector;


public class PageFragment extends Fragment {
    private static final String ARG_PAGE_NUMBER = "page_number";
    Vector<TimelineList> vector=new Vector<>();
    Adapter_Timeline adapter_timeline;
    RecyclerView recyclerView;
    String mChild;
    public PageFragment(){

    }

    @SuppressLint("ValidFragment")
    public PageFragment(String mChild){
        this.mChild=mChild;

    }

    public static PageFragment getItem(String mChild) {
        PageFragment fragment = new PageFragment(mChild);
       // bundle=new Bundle();
       // bundle.putString("mChild",mChild);
        return fragment;

    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pager, container, false);
        adapter_timeline=new Adapter_Timeline(vector,getActivity());
        recyclerView=rootView.findViewById(R.id.timeline_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter_timeline);


        FirebaseDatabase firebaseDatabase=FirebaseDatabase.getInstance();
        DatabaseReference databaseReference=firebaseDatabase.getReference().child("Timeline").child(mChild);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                vector.clear();
                for(DataSnapshot shot:dataSnapshot.getChildren()){

                    vector.add(shot.getValue(TimelineList.class));

                }
                adapter_timeline.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return rootView;
    }
}


class Adapter_Timeline extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Vector<TimelineList> vector;
    Context mContext;
    VH_Timeline_RV_1 vh_timeline_rv_1;


    public Adapter_Timeline(){

    }

    public Adapter_Timeline(Vector<TimelineList> vector,Context mContext){
            this.vector=vector;
            this.mContext=mContext;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        Log.e("msh1.", String.valueOf(viewType));

        if(viewType == 0) {
           view  = inflater.inflate(R.layout.item_timeline_rv, parent, false);
            viewHolder = new VH_Timeline_RV(view, mContext);
        }else {
            view = inflater.inflate(R.layout.item_timeline_rv_1, parent, false);
           viewHolder = new VH_Timeline_RV_1(view,mContext);
        }


        return viewHolder;

    }



    @Override
    public int getItemCount(){
        return vector.size();
    }

    @Override
    public int getItemViewType(int position) {
        Log.e("msh1..", String.valueOf(position));

        Log.e("msh1", String.valueOf(vector.get(position).getType()));
        return 1;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        switch (holder.getItemViewType()) {
            case 0:

                VH_Timeline_RV vh_timeline_rv = (VH_Timeline_RV) holder;
                vh_timeline_rv.simpleDraweeView.setImageURI(vector.get(position).getImage());
                vh_timeline_rv.location.setText(vector.get(position).getLocation());
                vh_timeline_rv.time.setText(vector.get(position).getTime());
                vh_timeline_rv.event.setText(vector.get(position).getName());

                break;

            case 1:
                vh_timeline_rv_1 = (VH_Timeline_RV_1) holder;
                vh_timeline_rv_1.location.setText(vector.get(position).getLocation());
                vh_timeline_rv_1.time.setText(vector.get(position).getTime());
                vh_timeline_rv_1.event.setText(vector.get(position).getName());
                vh_timeline_rv_1.topic.setText(vector.get(position).getTopic());

             /*   vh_timeline_rv_1.bpgcMapsActivity=new BPGCMapsActivity(vector.get(position).getLat(),vector.get(position).getLon());
                vh_timeline_rv_1.location.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vh_timeline_rv_1.bpgcMapsActivity.setCamera();
                    }
                });*/
                break;
        }



    }   }





 class VH_Timeline_RV extends RecyclerView.ViewHolder   {
    TextView location;
    TextView time;
    TextView details;
    TextView event;
    ImageView b_location;
    ImageView b_time;
    ImageView   b_event;
    SimpleDraweeView simpleDraweeView;
    View decoration;
    View border;


    public VH_Timeline_RV(View itemView, final Context mContext) {
        super(itemView);

      location=itemView.findViewById(R.id.TV_location);
            time=itemView.findViewById(R.id.TV_time);
            details=itemView.findViewById(R.id.TV_details);
            event=itemView.findViewById(R.id.Event_name);
            b_location= itemView.findViewById(R.id.b_location);
            b_time= itemView.findViewById(R.id.b_time);
            b_event= itemView.findViewById(R.id.b_details);
            decoration=itemView.findViewById(R.id.decoration);
            simpleDraweeView=itemView.findViewById(R.id.image_event);
            border=itemView.findViewById(R.id.back);

            Random rand = new Random();
            int r = rand.nextInt(255);
            int g = rand.nextInt(255);
            int b = rand.nextInt(255);
            int randomAndroidColor = Color.rgb(r,g,b);

            RoundingParams roundingParams = RoundingParams.fromCornersRadius(20f);
            roundingParams.setBorder(randomAndroidColor, 4.0f);
            simpleDraweeView.getHierarchy().setRoundingParams(roundingParams);

            decoration.setBackgroundColor(randomAndroidColor);

            b_event.setColorFilter(randomAndroidColor);
            b_event.setImageResource(R.drawable.ic_event_note_black_24dp);

            b_location.setColorFilter(randomAndroidColor);
            b_location.setImageResource(R.drawable.ic_mapicon);

            b_time.setColorFilter(randomAndroidColor);
            b_time.setImageResource(R.drawable.ic_access_time_black_24dp);


    }




}


class VH_Timeline_RV_1 extends RecyclerView.ViewHolder {
    TextView location;
    TextView time;
    TextView details;
    TextView event;
    TextView topic;
    ViewGroup background;
    ViewGroup background1;
    ViewGroup btn;
    ImageView b_event;
    ProgressDialog mProgressDialog;
    DownloadTask downloadTask;
    BPGCMapsActivity bpgcMapsActivity;

    public VH_Timeline_RV_1(View itemView, final Context mContext) {
        super(itemView);

        b_event = itemView.findViewById(R.id.b_details);
        location = itemView.findViewById(R.id.TV_location);
        time = itemView.findViewById(R.id.TV_time);
        details = itemView.findViewById(R.id.TV_details);
        background = itemView.findViewById(R.id.decoration);
        background1 = itemView.findViewById(R.id.deco2);
        event = itemView.findViewById(R.id.Event_name);
        topic = itemView.findViewById(R.id.topic);
        btn = itemView.findViewById(R.id.main);


        Random rand = new Random();
        int r = rand.nextInt(255);
        int g = rand.nextInt(255);
        int b = rand.nextInt(255);
        int randomAndroidColor = Color.rgb(r, g, b);

        background.setBackgroundColor(randomAndroidColor);
        background1.setBackgroundColor(randomAndroidColor);

        b_event.setColorFilter(randomAndroidColor);
        b_event.setImageResource(R.drawable.ic_event_note_black_24dp);



        b_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// instantiate it within the onCreate method
                mProgressDialog = new ProgressDialog(mContext);
                mProgressDialog.setMessage("Downloading");
                mProgressDialog.setIndeterminate(true);
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(true);

// execute this when the downloader must be fired
         FirebaseDatabase.getInstance().getReference().child("Url").addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
                 String url=dataSnapshot.getValue(String.class);
                 if(url.equals("no")){

                     Toast.makeText(mContext,"File will be availabe soon to download",Toast.LENGTH_LONG).show();

                 }else {
                     Log.e("URL",url);
                     downloadTask = new DownloadTask(mContext,url);
                     downloadTask.execute();

                     mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                         @Override
                         public void onCancel(DialogInterface dialog) {
                             downloadTask.cancel(true);

                         }
                     });
                     
                 
             }}

             @Override
             public void onCancelled(DatabaseError databaseError) {

             }
         });    
             
             
            }
        });



    }



    private class DownloadTask extends AsyncTask<String, Integer, String> {

        private Context context;
        private PowerManager.WakeLock mWakeLock;
        private String URL;

        public DownloadTask(Context context,String URL) {
            this.context = context;
            this.URL=URL;
        }

        @Override
        protected String doInBackground(String... sUrl) {
            InputStream input = null;
            OutputStream output = null;
            HttpURLConnection connection = null;
            try {
                URL url = new URL(URL);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();

                // expect HTTP 200 OK, so we don't mistakenly save error report
                // instead of the file
                if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    return "Server returned HTTP " + connection.getResponseCode()
                            + " " + connection.getResponseMessage();
                }

                // this will be useful to display download percentage
                // might be -1: server did not report the length
                int fileLength = connection.getContentLength();

                // download the file
                input = connection.getInputStream();
                output = new FileOutputStream(Environment.getExternalStorageDirectory()+"/Schedule.pdf");

                byte data[] = new byte[4096];
                long total = 0;
                int count;
                while ((count = input.read(data)) != -1) {
                    // allow canceling with back button
                    if (isCancelled()) {
                        input.close();
                        return null;
                    }
                    total += count;
                    // publishing the progress....
                    if (fileLength > 0) // only if total length is known
                        publishProgress((int) (total * 100 / fileLength));
                    output.write(data, 0, count);
                }
            } catch (Exception e) {
                return e.toString();
            } finally {
                try {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                } catch (IOException ignored) {
                }

                if (connection != null)
                    connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // take CPU lock to prevent CPU from going off if the user
            // presses the power button during download
            PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,
                    getClass().getName());
            mWakeLock.acquire();
            mProgressDialog.show();
        }


        @Override
        protected void onProgressUpdate(Integer... progress) {
            super.onProgressUpdate(progress);
            // if we get here, length is known, now set indeterminate to false
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.setMax(100);
            mProgressDialog.setProgress(progress[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            mWakeLock.release();
            mProgressDialog.dismiss();
            
            if (result != null)
                Toast.makeText(context,"Download error: "+result, Toast.LENGTH_LONG).show();
            else
                Toast.makeText(context,"File downloaded", Toast.LENGTH_SHORT).show();
            File file = new File(Environment.getExternalStorageDirectory()+"/Schedule.pdf");
          
            //  Uri path = Uri.fromFile(file);
          /*  Uri path = FileProvider.getUriForFile(context, context.getApplicationContext().getPackageName() + ".my.package.name.provider", file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try {
                context.startActivity(intent);
            }
            catch (ActivityNotFoundException e) {
                Toast.makeText(context, "No application available to view PDF", Toast.LENGTH_LONG).show();
            }
*/
            Uri path = Uri.fromFile(file);
            Intent objIntent = new Intent(Intent.ACTION_VIEW);
            objIntent.setDataAndType(path, "application/pdf");
            objIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            Intent intent1 = Intent.createChooser(objIntent, "Open PDF with..");
            try {
                context.startActivity(intent1);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(context, "Activity Not Found Exception ", Toast.LENGTH_SHORT).show();
            }

        }
    }
}