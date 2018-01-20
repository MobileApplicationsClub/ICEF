package bits.mac.icef_2018.Adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import bits.mac.icef_2018.ICEF_HelperClass;
import bits.mac.icef_2018.R;
import bits.mac.icef_2018.View.batch_detail;
import bits.mac.icef_2018.View.batch_model;


public class Adapter_Contacts extends RecyclerView.Adapter<Adapter_Contacts.batch_VH> {

    private ArrayList<batch_model> ContactList = new ArrayList<>();
    private SparseBooleanArray displayDetails = new SparseBooleanArray();
    public Adapter_Contacts(ArrayList<batch_model> list) {
        this.ContactList = list;
    }

    @Override
    public batch_VH onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View eventDetails = inflater.inflate(R.layout.event_short_cv, parent, false);
        return new batch_VH(eventDetails);
    }

    @Override
    public void onBindViewHolder(batch_VH holder, int position) {
        batch_model current = ContactList.get(position);
        holder.setCoverImage(current.getImage());
        holder.setIntent(current.getKey());
        holder.setName(current.getName());
        holder.setNumber(current.getNumber());
        holder.setEmail(current.getEmail());
        holder.setDescription(current.getDescription());
        holder.handleClick(position);

    }


    @Override
    public int getItemCount() {
        return ContactList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class batch_VH extends RecyclerView.ViewHolder {


        public batch_VH(final View itemView) {
            super(itemView);
        }

        public void handleClick(final int pos) {
            final View desc = itemView.findViewById(R.id.Description);

            if(displayDetails.get(pos,false))
                desc.setVisibility(View.VISIBLE);
            else
                desc.setVisibility(View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(displayDetails.get(pos,false)) {
                        desc.setVisibility(View.GONE);
                        displayDetails.put(pos,false);
                    }
                    else {
                        desc.setVisibility(View.VISIBLE);
                        displayDetails.put(pos,true);
                    }
                }
            });
        }


        public void setIntent(final String currentBatch) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent batchDetails = new Intent(itemView.getContext(), batch_detail.class);
                    batchDetails.putExtra(ICEF_HelperClass.batch_extra, currentBatch);
                    itemView.getContext().startActivity(batchDetails);
                }
            });
        }

        public void setCoverImage(String url) {
            final ImageView imageView = itemView.findViewById(R.id.contactImage);
            //SET Correct aspect ratio

            if (URLUtil.isValidUrl(url)) {
                try {
                    Uri uri = Uri.parse(url);
                    imageView.setImageURI(uri);
                }catch (Exception e) {
                }
            }
        }


        public void setName(String name) {
            Log.d("Tag",name);
            TextView name_view = itemView.findViewById(R.id.Name);
            name_view.setText(name);
        }



        public void setDescription(String details) {
            Log.d("Tag",details);
            TextView description = itemView.findViewById(R.id.Description);
            description.setText(details);
        }

        public void setNumber(String phone){
            Log.d("Tag",phone);
            TextView number=itemView.findViewById(R.id.Number);
            number.setText(phone);
        }

        public void setEmail(String Mail){
            Log.d("Tag",Mail);
            TextView mail=itemView.findViewById(R.id.Mail);
            mail.setText(Mail);
        }


    }
}
