package com.lynnik.lchat.retrofit;

import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {

  public static final String REQUEST = "api/chat/channels/?format=json";

  private static final String BASE_URL = "https://iostest.db2dev.com/";

  public static Retrofit getRetrofit(Context context) {
    return new Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(SelfSigningClientBuilder.createClient(context))
        .build();
  }

  public static ChatService getService(Retrofit retrofit) {
    return retrofit.create(ChatService.class);
  }
}