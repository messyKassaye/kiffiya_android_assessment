package com.example.kifiyaassessment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kifiyaassessment.R;
import com.example.kifiyaassessment.UI.adapter.PostAdapter;
import com.example.kifiyaassessment.constants.Constants;
import com.example.kifiyaassessment.helpers.GridSpacingItemDecoration;
import com.example.kifiyaassessment.http.interfaces.PostInterface;
import com.example.kifiyaassessment.http.viewModel.PostViewModel;
import com.example.kifiyaassessment.models.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private PostViewModel postViewModel;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton registerPostButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewModelProvider provider = new ViewModelProvider(this);
        postViewModel = provider.get(PostViewModel.class);

        registerPostButton = findViewById(R.id.register_new_post);

        adapter = new PostAdapter(getBaseContext(), postArrayList);
        recyclerView = findViewById(R.id.post_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);


        //get all posts from JSON placeholder
        postViewModel.getPosts().enqueue(new Callback<ArrayList<Post>>() {
            public void onResponse(Call<ArrayList<Post>> call, Response<ArrayList<Post>> response) {
                if (response.isSuccessful()){
                    List<Post> posts = response.body();
                    postArrayList.addAll(posts);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Post>> call, Throwable t) {
                showErrorMessage(t.getMessage());
            }
        });

        //register new post
        registerPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterPostActivity();
            }
        });

    }

    public void showRegisterPostActivity(){
        Intent intent = new Intent(getBaseContext(), RegisterPostActivity.class);
        startActivity(intent);
    }

    public void showErrorMessage(String message){
        Toast.makeText(this, message,Toast.LENGTH_LONG).show();
    }
}