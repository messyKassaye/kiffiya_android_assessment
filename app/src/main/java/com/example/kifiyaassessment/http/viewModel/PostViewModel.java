package com.example.kifiyaassessment.http.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.kifiyaassessment.config.Config;
import com.example.kifiyaassessment.constants.Constants;
import com.example.kifiyaassessment.http.repository.PostRepository;
import com.example.kifiyaassessment.models.Post;
import com.example.kifiyaassessment.utils.ApiResponse;

import java.util.ArrayList;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;

public class PostViewModel extends AndroidViewModel {
    private PostRepository postRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();
    private final MutableLiveData<ApiResponse> responseLiveData = new MutableLiveData<>();

    public PostViewModel(@NonNull Application application) {
        super(application);
        postRepository = new PostRepository(Config.getApiUrl(application));
    }

    public Call<ArrayList<Post>> getPosts(){
        return postRepository.getPosts();
    }

    public MutableLiveData<ApiResponse> storeResponse() {
        return responseLiveData;
    }

    public void store(Post post){
        Observable.just(postRepository.store(post)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((d) -> responseLiveData.setValue(ApiResponse.loading()))
                .subscribe(
                        result -> responseLiveData.setValue(ApiResponse.success(result)),
                        throwable -> responseLiveData.setValue(ApiResponse.error(throwable))
                ));
    }
}
