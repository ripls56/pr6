package com.example.pr6.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pr6.Food;
import com.example.pr6.R;
import com.example.pr6.Repository.ApiInterface;
import com.example.pr6.Repository.RepositoryBuilder;
import com.example.pr6.databinding.FragmentHomeBinding;
import com.example.pr6.ui.AddProduct;
import com.example.pr6.ui.home.Recycler.CustomAdapter;

import java.io.IOException;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    RecyclerView foodRecycler;
    ApiInterface apiInterface;
    ArrayList<Food> foods;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.addFood.setOnClickListener(view -> {
            Intent intent = new Intent(requireContext(), AddProduct.class);
            startActivity(intent);
        });

        foodRecycler = binding.foodList;

        apiInterface = RepositoryBuilder.buildRequest().create(ApiInterface.class);

        Call<ArrayList<Food>> getFoodList = apiInterface.getFoodList();


        getFoodList.enqueue(new Callback<ArrayList<Food>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<Food>> call, @NonNull Response<ArrayList<Food>> response) {
                if (response.isSuccessful()){
                    foods = response.body();
                    CustomAdapter adapter = new CustomAdapter(getContext(), foods);
                    if (foods.size() > 0) {
                        foodRecycler.setAdapter(adapter);
                    }
                    else Toast.makeText(requireContext(), "Нет еды", Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(requireContext(), "Чет не так", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<Food>> call, @NonNull Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                if (foods != null){
                    Food food = foods.get(viewHolder.getAdapterPosition());
                    Call<Food> deleteFoodByID = apiInterface.deleteFoodByID(food.getId());
                    deleteFoodByID.enqueue(new Callback<Food>() {
                        @Override
                        public void onResponse(Call<Food> call, Response<Food> response) {
                            if (response.isSuccessful()){
                                Toast.makeText(requireContext(), "Еда удалена", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Food> call, Throwable t) {

                        }
                    });
                }
            }
        }).attachToRecyclerView(foodRecycler);




        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}