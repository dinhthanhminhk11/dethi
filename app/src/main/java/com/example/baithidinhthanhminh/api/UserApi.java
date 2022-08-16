package com.example.baithidinhthanhminh.api;


import com.example.baithidinhthanhminh.model.ListUser;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UserApi {
    @GET("api/users")
    Call<ListUser> getListUser(
            @Query("page") int page
    );
}
