package com.lynnik.lchat.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lynnik.lchat.R;

public class NewMessageActivityFragment extends Fragment {

  public static NewMessageActivityFragment newInstance() {
    Bundle args = new Bundle();

    NewMessageActivityFragment fragment = new NewMessageActivityFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater
        .inflate(R.layout.fragment_new_message, container, false);
    return v;
  }
}
