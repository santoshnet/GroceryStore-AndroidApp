package com.frontendsource.grocerystore.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frontendsource.grocerystore.R;
import com.frontendsource.grocerystore.api.clients.RestClient;
import com.frontendsource.grocerystore.model.Prescription;
import com.frontendsource.grocerystore.util.localstorage.LocalStorage;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Drug App
 * https://github.com/quintuslabs/GroceryStore
 * Created on 18-Feb-2019.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */
public class PrescriptionAdapter extends RecyclerView.Adapter<PrescriptionAdapter.MyViewHolder> {

    List<Prescription> prescriptionList;
    Activity context;

    LocalStorage localStorage;

    public PrescriptionAdapter(List<Prescription> prescriptionList, Activity context) {
        this.prescriptionList = prescriptionList;
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView;


        itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_prescription, parent, false);

        return new MyViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        final Prescription prescription = prescriptionList.get(position);

        Picasso.get().load(RestClient.BASE_URL+prescription.getImage()).into(holder.imageView);
        holder.prescription_title.setText(prescription.getTitle());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog=new Dialog(context,R.style.full_screen_dialog);
                dialog.setContentView(R.layout.product_image);
                ImageView imageView1 = dialog.findViewById(R.id.image_popup);
                Picasso.get().load(RestClient.BASE_URL+prescription.getImage()).into(imageView1);
                dialog.show();
            }
        });

    }


    @Override
    public int getItemCount() {

        return prescriptionList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView prescription_title;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            prescription_title = itemView.findViewById(R.id.prescription_title);
            imageView = itemView.findViewById(R.id.imageView);


        }
    }
}
