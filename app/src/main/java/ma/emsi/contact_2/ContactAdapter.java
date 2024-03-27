package ma.emsi.contact_2;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private Context context;
    private ArrayList<ContactModel> contacts_list;

    public ContactAdapter(Context context, ArrayList<ContactModel> contacts_list) {
        this.context = context;
        this.contacts_list = contacts_list;
    }

    class ContactViewHolder extends RecyclerView.ViewHolder
    {
        ImageView photo,contact_number;
        TextView contact_name;
        public ContactViewHolder(@NonNull View itemView)
        {
            super(itemView);
            photo=itemView.findViewById(R.id.contact_photo);
            contact_number=itemView.findViewById(R.id.contact_number);
            contact_name=itemView.findViewById(R.id.contact_name);
        }
    }


    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_contact_item,parent,false);
        ContactViewHolder vh=new ContactViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position)
    {
        ContactModel contact=contacts_list.get(position);
        Log.d(" postn = "+position,"----------------------------------------"+contact.getName());
        String id=contact.getId();
        String name=contact.getName();
        String image=contact.getImage();
        holder.contact_name.setText(name);
        if (image.equals(""))
        {
            holder.photo.setImageResource(R.drawable.ic_baseline_phone_24);
        }else
        {
            holder.photo.setImageURI(Uri.parse(image));
        }

        holder.contact_number.setOnClickListener(e->{
            Toast.makeText(context, "phone number", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return contacts_list.size();
    }


}
