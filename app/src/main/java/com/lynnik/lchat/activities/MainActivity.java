package com.lynnik.lchat.activities;

import android.os.Bundle;

import com.lynnik.lchat.R;
import com.lynnik.lchat.fragments.MainActivityFragment;

public class MainActivity extends SingleFragmentActivity {

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_main;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    pasteFragment(
        MainActivityFragment.newInstance());
  }
}
