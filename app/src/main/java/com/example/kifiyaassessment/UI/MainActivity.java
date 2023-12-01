package com.example.kifiyaassessment.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.kifiyaassessment.R;
import com.example.kifiyaassessment.UI.adapter.PostAdapter;
import com.example.kifiyaassessment.databinding.ActivityMainBinding;
import com.example.kifiyaassessment.http.viewModel.PostViewModel;
import com.example.kifiyaassessment.models.Post;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private PostViewModel postViewModel;
    private ArrayList<Post> postArrayList = new ArrayList<>();
    private PostAdapter adapter;
    private RecyclerView recyclerView;
    private FloatingActionButton registerPostButton;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        ViewModelProvider provider = new ViewModelProvider(this);
        postViewModel = provider.get(PostViewModel.class);
        adapter = new PostAdapter(getBaseContext(), postArrayList);
        binding.postRecyclerview.setLayoutManager(new LinearLayoutManager(getBaseContext(),LinearLayoutManager.VERTICAL,false));
        binding.postRecyclerview.setItemAnimator(new DefaultItemAnimator());
        binding.postRecyclerview.setAdapter(adapter);


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
        binding.registerNewPost.setOnClickListener(new View.OnClickListener() {
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