package com.example.pr6.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pr6.Food;
import com.example.pr6.R;
import com.example.pr6.Repository.ApiInterface;
import com.example.pr6.Repository.RepositoryBuilder;
import com.example.pr6.databinding.ActivityProductAddBinding;
import com.example.pr6.databinding.FragmentHomeBinding;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddProduct extends AppCompatActivity {

    ActivityProductAddBinding binding;
    RecyclerView foodRecycler;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextView foodName = binding.foodName;
        TextView foodDetail = binding.foodDetail;
        TextView foodImg = binding.foodImg;
        TextView studentFIO = binding.studentFIO;
        TextView foodScore = binding.foodScore;

        apiInterface = RepositoryBuilder.buildRequest().create(ApiInterface.class);

        binding.setFood.setOnClickListener(view -> {
            if (foodName.getText().toString().matches("") || foodDetail.getText().toString().matches("") || foodImg.getText().toString().matches("") || studentFIO.getText().toString().matches(""))
                Toast.makeText(getApplicationContext(), "Чет не так", Toast.LENGTH_LONG).show();
            else{
                Food food = new Food(
                        0,
                        binding.foodName.getText().toString(),
                        binding.foodDetail.getText().toString(),
                        binding.foodImg.getText().toString(),
                        binding.studentFIO.getText().toString(),
                        Integer.parseInt(binding.foodScore.getText().toString()));
                Call<Food> setFood = apiInterface.setFood(food);
                setFood.enqueue(new Callback<Food>() {
                    @Override
                    public void onResponse(Call<Food> call, Response<Food> response) {
                        if (response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Еда добавлена", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Food> call, Throwable t) {

                    }
                });
            }
        });
    }
}