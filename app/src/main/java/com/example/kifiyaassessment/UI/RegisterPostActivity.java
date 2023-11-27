package com.example.kifiyaassessment.UI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kifiyaassessment.R;
import com.example.kifiyaassessment.http.viewModel.PostViewModel;
import com.example.kifiyaassessment.models.Post;
import com.example.kifiyaassessment.utils.ApiResponse;

public class RegisterPostActivity extends AppCompatActivity {
    EditText title, body;
    Button registerPost;
    PostViewModel postViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_post);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewModelProvider provider = new ViewModelProvider(this);
        postViewModel = provider.get(PostViewModel.class);

        title = findViewById(R.id.post_title);
        body = findViewById(R.id.post_body);
        registerPost = findViewById(R.id.submit_button);

        registerPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleValue = title.getText().toString();
                String bodyValue = body.getText().toString();

                Post post = new Post(1,1,titleValue, bodyValue);
                storePost(post);

            }
        });


    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void storePost(Post post){
        postViewModel.storeResponse().observe(this, this::consumeResponse);
    }

    private void consumeResponse(ApiResponse apiResponse) {

        switch (apiResponse.status) {

            case LOADING:
                break;

            case SUCCESS:
                renderSuccessResponse(apiResponse.data);
                System.out.println(apiResponse);
                break;

            case ERROR:
                System.out.println("ERRORR: "+apiResponse.error.getMessage());
                break;

            default:
                break;
        }
    }

    private void renderSuccessResponse(Post response) {
        System.out.println("Result: "+response.title);

    }
}