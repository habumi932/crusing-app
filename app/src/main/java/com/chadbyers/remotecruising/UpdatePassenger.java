package com.chadbyers.remotecruising;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.chadbyers.remotecruising.database.Passenger;
import com.chadbyers.remotecruising.database.RetrofitClient;
import com.chadbyers.remotecruising.database.WebInterface;
import com.chadbyers.remotecruising.databinding.ActivityUpdatePassengerBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePassenger extends AppCompatActivity {

    private ActivityUpdatePassengerBinding binding;

    // Listeners
    private View.OnClickListener button_find_passenger_clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            int id  = Integer.valueOf(binding.edittextId.getText().toString());

            WebInterface myWebInterface = RetrofitClient.getInstance().getWebInterface();

            Call<Passenger> call = myWebInterface.getPassengerById(id);
            call.enqueue(new Callback<Passenger>(){

                @Override
                public void onResponse(Call<Passenger> call, Response<Passenger> response) {
                    if(response.isSuccessful()){
                        Passenger p = response.body();

                        binding.edittextFirstName.setText(p.getFirstName());
                        binding.edittextLastName.setText(p.getLastName());
                        binding.edittextPassport.setText(String.valueOf(p.getPassportNumber()));
                        binding.edittextBirthdate.setText(p.getBirthdate());
                        binding.edittextCity.setText(p.getCity());
                        binding.edittextState.setText(p.getState());
                        
                    }
                }

                @Override
                public void onFailure(Call<Passenger> call, Throwable t) {

                }
            } );
        }
    };

    private View.OnClickListener button_update_passenger_clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePassengerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonFindPassenger.setOnClickListener(button_find_passenger_clickListener);
        binding.buttonUpdatePassenger.setOnClickListener(button_update_passenger_clickListener);
    }
}