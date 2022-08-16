package com.example.baithidinhthanhminh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.baithidinhthanhminh.adapter.UserAdapter;
import com.example.baithidinhthanhminh.api.UserApi;
import com.example.baithidinhthanhminh.model.ListUser;
import com.example.baithidinhthanhminh.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private NestedScrollView nestedscreollview;
    private RecyclerView recyclerView;
    private UserApi userApi;
    private UserAdapter userAdapter;
    private int pageCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nestedscreollview = (NestedScrollView) findViewById(R.id.nestedscreollview);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        Gson gson = new GsonBuilder().serializeNulls().create();

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        userApi = retrofit.create(UserApi.class);
        loadUser(pageCount);
        setUpPagination(true);
    }

    private void setUpPagination(boolean b) {
        if (b) {
            nestedscreollview.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                    loadUser(++pageCount);
                }
            });
        } else {
            nestedscreollview.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                Log.e("Minh", "error ; ");
            });
        }
    }

    private void loadUser(int pageCount) {
        Call<ListUser> call = userApi.getListUser(pageCount);
        call.enqueue(new Callback<ListUser>() {
            @Override
            public void onResponse(Call<ListUser> call, Response<ListUser> response) {
                if (!response.isSuccessful()) {
                    Log.e("Minh", "code ; " + response.code());
                }
                ListUser listUser = response.body();
                userAdapter = new UserAdapter(listUser.getListUser(), new UserAdapter.Callback() {
                    @Override
                    public void onClick(User user) {

                    }
                });
                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                recyclerView.setAdapter(userAdapter);

            }

            @Override
            public void onFailure(Call<ListUser> call, Throwable t) {
                Log.e("Minh", t.getMessage() + " error");
            }
        });
    }
}