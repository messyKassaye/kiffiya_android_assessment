package com.example.kifiyaassessment.http.interfaces;


import com.example.kifiyaassessment.models.Post;
import com.example.kifiyaassessment.utils.ApiURL;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostInterface {
    @GET(ApiURL.POST)
    Call<ArrayList<Post>> getPosts();

    @POST(ApiURL.POST)
    Observable<Post> store(@Body Post post);
}
