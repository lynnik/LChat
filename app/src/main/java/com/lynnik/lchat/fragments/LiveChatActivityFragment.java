package com.lynnik.lchat.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynnik.lchat.R;
import com.lynnik.lchat.activities.ChatActivity;

public class LiveChatActivityFragment extends Fragment {

  public static LiveChatActivityFragment newInstance() {
    Bundle args = new Bundle();

    LiveChatActivityFragment fragment = new LiveChatActivityFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    ((ChatActivity) getActivity()).getSupportActionBar().setTitle("Live chat");
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater
        .inflate(R.layout.fragment_live_chat_activity, container, false);
    return v;
  }
}
