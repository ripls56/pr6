package com.example.pr6.ui.home.Recycler;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pr6.Food;
import com.example.pr6.R;
import com.example.pr6.ui.notifications.NotificationsFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{

    private final LayoutInflater inflater;
    private final ArrayList<Food> foodArrayList;

    public CustomAdapter(Context context, ArrayList<Food> foodArrayList){
        this.foodArrayList = foodArrayList;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public CustomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.food_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Food food = foodArrayList.get(position);
        Picasso.get().load(food.getImg()).into(holder.foodImg);
        holder.foodName.setText(food.getName());
        holder.foodDescription.setText(food.getDetail());
        holder.buy.setOnClickListener(view -> {

        });
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView foodImg;
        final TextView foodName;
        final TextView foodDescription;
        final Button buy;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodImg = itemView.findViewById(R.id.productImage);
            foodName = itemView.findViewById(R.id.productName);
            foodDescription = itemView.findViewById(R.id.description);
            buy = itemView.findViewById(R.id.buy);
        }
    }
}
