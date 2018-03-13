package com.example.sidkathuria14.namepredictor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.sidkathuria14.namepredictor.api.NamesApi;
import com.example.sidkathuria14.namepredictor.model.Result;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
EditText etInput;Button btnSubmit;TextView tvResult;
public static final String TAG = "names";
String address = "https://pure-harbor-90282.herokuapp.com/";
String ip = "http://192.168.1.9:3000/";
String name;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    btnSubmit = (Button)findViewById(R.id.btnSubmit);
    etInput = (EditText)findViewById(R.id.etInput);
    tvResult = (TextView)findViewById(R.id.tvResult);






    btnSubmit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "onClick: ");
name = etInput.getText().toString();
            Log.d(TAG, "onClick: " + name);

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .writeTimeout(60, TimeUnit.SECONDS)
                    .build();


//            retrofit = new Retrofit.Builder()
//                    .baseUrl(ip)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
                   retrofit =new Retrofit.Builder()
                    .baseUrl(ip)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

             NamesApi namesApi = retrofit.create(NamesApi.class);
           Callback<String> callback = new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    Log.d(TAG, "onResponse: " + response.body().toString());
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                    Log.d(TAG, "onFailure: " + t.getMessage() + t.getCause());

                }
            };
            namesApi.getPrediction(name).enqueue(callback);
        }
    });


    }
}
