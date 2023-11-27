package com.example.kifiyaassessment.utils;

import static com.example.kifiyaassessment.utils.Status.ERROR;
import static com.example.kifiyaassessment.utils.Status.LOADING;
import static com.example.kifiyaassessment.utils.Status.SUCCESS;

import com.example.kifiyaassessment.models.Post;
import com.example.kifiyaassessment.models.SuccessResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;




public class ApiResponse {

  public final Status status;

  @Nullable
  public final Post data;

  @Nullable
  public final Throwable error;

  private ApiResponse(Status status, @Nullable Post data, @Nullable Throwable error) {
    this.status = status;
    this.data = data;
    this.error = error;
  }

  public static ApiResponse loading() {
    return new ApiResponse(LOADING, null, null);
  }

  public static ApiResponse success(@NonNull Post data) {
    return new ApiResponse(SUCCESS, data, null);
  }

  public static ApiResponse error(@NonNull Throwable error) {
    return new ApiResponse(ERROR, null, error);
  }

}