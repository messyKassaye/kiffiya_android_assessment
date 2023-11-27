package com.example.kifiyaassessment.http.repository;

import com.example.kifiyaassessment.http.RetrofitRequest;
import com.example.kifiyaassessment.http.interfaces.PostInterface;
import com.example.kifiyaassessment.models.Post;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;

public class PostRepository {

    private PostInterface postInterface;

    public PostRepository(String apiUrl){
        postInterface = RetrofitRequest.getApiInstance(apiUrl).create(PostInterface.class);
    }

    public Call<ArrayList<Post>> getPosts(){
        return postInterface.getPosts();
    }

    public Observable<Post> store(Post post){
        return postInterface.store(post);
    }
}
