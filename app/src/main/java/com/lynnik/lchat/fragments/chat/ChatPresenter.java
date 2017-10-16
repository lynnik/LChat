package com.lynnik.lchat.fragments.chat;

import android.content.Context;
import android.support.annotation.NonNull;

import com.lynnik.lchat.retrofit.entities.Channel;
import com.lynnik.lchat.retrofit.entities.Chat;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatPresenter {

  private ChatView mView;
  private ChatModel mModel;

  public ChatPresenter(ChatView view) {
    mView = view;
    mModel = new ChatModel();
  }

  public void initRetrofit(Context context) {
    mModel.initRetrofit(context);
  }

  public void createChatRequest() {
    mModel.getChatRequest().enqueue(new Callback<Chat>() {
      @Override
      public void onResponse(
          @NonNull Call<Chat> call, @NonNull Response<Chat> response) {
        List<Channel> channels = response.body().getChannels();
        mView.onCreateAdapter(channels);

        if (mModel.getItemDecorationPosition(channels) > -1)
          mView.onAddItemDecoration(
              mModel.getItemDecorationPosition(channels));

        if (mModel.getBadgeValue(channels) > 0)
          mView.onSetBadgeValues(mModel.getBadgeValue(channels), 3);

        mView.onCreateAdapter(channels);

        mView.onSetAdapter();

        mView.onShowContent();
      }

      @Override
      public void onFailure(@NonNull Call<Chat> call, @NonNull Throwable t) {
        mView.onFailureResponse(t);
      }
    });
  }
}
