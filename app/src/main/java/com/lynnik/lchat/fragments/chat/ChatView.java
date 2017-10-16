package com.lynnik.lchat.fragments.chat;

import com.lynnik.lchat.retrofit.entities.Channel;

import java.util.List;

public interface ChatView {

  void onCreateAdapter(List<Channel> channels);

  void onAddItemDecoration(int position);

  void onSetAdapter();

  void onSetBadgeValues(int chatBadge, int liveChatBadge);

  void onShowContent();

  void onFailureResponse(Throwable t);
}
