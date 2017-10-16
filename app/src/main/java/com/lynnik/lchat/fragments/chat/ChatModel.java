package com.lynnik.lchat.fragments.chat;

import android.content.Context;

import com.lynnik.lchat.retrofit.ChatService;
import com.lynnik.lchat.retrofit.RetrofitUtil;
import com.lynnik.lchat.retrofit.entities.Channel;
import com.lynnik.lchat.retrofit.entities.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;

public class ChatModel {

  private Retrofit mRetrofit;

  public void initRetrofit(Context context) {
    mRetrofit = RetrofitUtil.getRetrofit(context);
  }

  public Call<Chat> getChatRequest() {
    ChatService service = RetrofitUtil.getService(mRetrofit);
    return service.getChat();
  }

  public int getItemDecorationPosition(List<Channel> channels) {
    int position = -1;
    for (Channel c : channels) {
      if (c.getUnreadMessagesCount() > 0)
        position++;
    }

    return position;
  }

  public int getBadgeValue(List<Channel> channels) {
    return getItemDecorationPosition(channels) + 1;
  }
}
