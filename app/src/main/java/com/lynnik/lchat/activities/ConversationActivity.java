package com.lynnik.lchat.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.lynnik.lchat.fragments.ConversationActivityFragment;
import com.lynnik.lchat.R;
import com.lynnik.lchat.retrofit.entities.Channel;

public class ConversationActivity extends SingleFragmentActivity {

  private static final String EXTRA_CHANNEL =
      "com.lynnik.lchat.activities.ConversationActivity.channel";

  private Channel mChannel;
  private String mSenderName;

  public static Intent newIntent(Context context, Channel channel) {
    Intent i = new Intent(context, ConversationActivity.class);
    i.putExtra(EXTRA_CHANNEL, channel);

    return i;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_conversation;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mChannel = (Channel) getIntent().getSerializableExtra(EXTRA_CHANNEL);
    mSenderName = mChannel.getLastMessage().getSender().getFirstName()
        + " " + mChannel.getLastMessage().getSender().getLastName();

    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setTitle(mSenderName);

    pasteFragment(
        ConversationActivityFragment.newInstance());
  }
}
