package com.chadbyers.remotecruising;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.chadbyers.remotecruising.database.Passenger;
import com.chadbyers.remotecruising.database.RetrofitClient;
import com.chadbyers.remotecruising.database.WebInterface;
import com.chadbyers.remotecruising.databinding.ActivityReadPassengersBinding;

import java.lang.reflect.Array;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReadPassengers extends AppCompatActivity {

    private ActivityReadPassengersBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReadPassengersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // *** Pointer to the object that has implemented my WebInterface's methods ***
        WebInterface myWebInterface = RetrofitClient.getInstance().getWebInterface();

        //This does not send the request yet ... we're getting the object that's about to send the request to the server
        Call<List<Passenger>> call = myWebInterface.getAllPassengers();

        call.enqueue(new Callback<List<Passenger>>() {
            @Override
            public void onResponse(Call<List<Passenger>> call, Response<List<Passenger>> response) {
                if(response.isSuccessful()){
                    List<Passenger> results = response.body();
                    ArrayAdapter<Passenger> myAdapter = new ArrayAdapter<>(ReadPassengers.this, android.R.layout.simple_list_item_1, results);
                    binding.listviewPassengers.setAdapter(myAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<Passenger>> call, Throwable t) {

            }
        });

    }
}