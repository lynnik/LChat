package com.lynnik.lchat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lynnik.lchat.fragments.NewMessageActivityFragment;
import com.lynnik.lchat.R;

public class NewMessageActivity extends SingleFragmentActivity {

  public static Intent newIntent(Context context) {
    Intent i = new Intent(context, NewMessageActivity.class);
    return i;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_new_message;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    pasteFragment(
        NewMessageActivityFragment.newInstance());
  }
}
