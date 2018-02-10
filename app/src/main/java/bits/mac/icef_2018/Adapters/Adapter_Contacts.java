package bits.mac.icef_2018.Adapters;

import android.content.Context;
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
    Context mContext;
    private ArrayList<batch_model> ContactList = new ArrayList<>();
    private SparseBooleanArray displayDetails = new SparseBooleanArray();


    public Adapter_Contacts(ArrayList<batch_model> list,Context context) {
        this.ContactList = list;
        mContext=context;
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
        holder.handleClick(position,mContext);

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
        TextView number;
        TextView mail;


        public void handleClick(final int pos, final Context context) {
            final View desc = itemView.findViewById(R.id.Description);




            number=itemView.findViewById(R.id.Number);
            number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:"+number.getText()));
                    context.startActivity(intent);
                }
            });

           mail=itemView.findViewById(R.id.Mail);
            mail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_SEND);
                    intent.setType("message/rfc822");
                    intent.putExtra(Intent.ACTION_SENDTO, mail.getText());
                    Intent mailer = Intent.createChooser(intent, "Choose your E-mail application");
                    context.startActivity(mailer);
                }
            });

        }


        public void setIntent(final String currentBatch) {

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
