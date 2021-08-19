package com.example.retrofitfetchdata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        tv.setText("");


        //Phase 1 -- Retrofit Object Creation
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Phase 2 Convert JSON Data To Model Class Object

        myapi api = retrofit.create(myapi.class);

        //Phase 3 -- Create a Call of model Class and Enqueue for processing

        Call<List<model>> call = api.getmodels();


        //Phase 4.0 --  Receive Response data in Simple Model type list

        call.enqueue(new Callback<List<model>>() {
            @Override
            public void onResponse(Call<List<model>> call, Response<List<model>> response) {
                //4.1 -- Receive Response data in Simple Model type list
                List<model> data = response.body();
                // make a loop for take the data from these list and append this on texview
                for(int i=0;i<data.size();i++){
                    tv.append("Get ID: "+data.get(i).getId()+"\n Title : "+data.get(i).getTitle()+"\n\n\n");
                }

            }

            @Override
            public void onFailure(Call<List<model>> call, Throwable t) {

            }
        });



    }
}