package com.lynnik.lchat.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynnik.lchat.R;
import com.lynnik.lchat.activities.ChatActivity;

public class MainActivityFragment extends Fragment {

  public static MainActivityFragment newInstance() {
    Bundle args = new Bundle();

    MainActivityFragment fragment = new MainActivityFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_main, container, false);

    v.findViewById(R.id.chatButton)
        .setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            startActivity(
                ChatActivity.newIntent(getActivity()));
          }
        });

    return v;
  }
}
