package com.lynnik.lchat.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynnik.lchat.R;

public class ConversationActivityFragment extends Fragment {

  public static ConversationActivityFragment newInstance() {
    Bundle args = new Bundle();

    ConversationActivityFragment fragment = new ConversationActivityFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater
        .inflate(R.layout.fragment_conversation, container, false);
    return v;
  }
}
