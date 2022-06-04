package com.chadbyers.remotecruising;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chadbyers.remotecruising.database.Passenger;
import com.chadbyers.remotecruising.database.RetrofitClient;
import com.chadbyers.remotecruising.database.WebInterface;
import com.chadbyers.remotecruising.databinding.ActivityCreatePassengerBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePassenger extends AppCompatActivity {

    private ActivityCreatePassengerBinding binding;

    // Listener
    private View.OnClickListener button_create_passenger_clickListener = new View.OnClickListener(){

        @Override
        public void onClick(View view) {
            String firstName = binding.edittextFirstName.getText().toString();
            String lastName = binding.edittextLastName.getText().toString();
            int passport = Integer.valueOf(binding.edittextPassport.getText().toString());
            String birthdate = binding.edittextBirthdate.getText().toString();
            String city = binding.edittextCity.getText().toString();
            String state = binding.edittextState.getText().toString();

            Passenger newPassenger = new Passenger(firstName, lastName, passport, birthdate, city, state);

            WebInterface myWebInterface = RetrofitClient.getInstance().getWebInterface();

            Call<Integer> call = myWebInterface.insertPassenger(newPassenger);

            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if(response.isSuccessful()){
                        int id = response.body();
                        Toast.makeText(CreatePassenger.this,"Passenger added with id " + id,
                                Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreatePassengerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.buttonCreatePassenger.setOnClickListener(button_create_passenger_clickListener);
    }
}