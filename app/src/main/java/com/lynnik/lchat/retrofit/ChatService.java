package com.lynnik.lchat.retrofit;

import com.lynnik.lchat.retrofit.entities.Chat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface ChatService {

  @Headers("Authorization: Basic aW9zdGVzdDppb3N0ZXN0MmsxNyE=")
  @GET("https://iostest.db2dev.com/api/chat/channels/?format=json")
  Call<Chat> getChat();
}