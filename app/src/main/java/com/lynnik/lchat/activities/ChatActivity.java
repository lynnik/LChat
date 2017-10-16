package com.lynnik.lchat.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lynnik.lchat.R;
import com.lynnik.lchat.fragments.chat.ChatActivityFragment;
import com.lynnik.lchat.fragments.LiveChatActivityFragment;

import static com.lynnik.lchat.R.id.chatButton;
import static com.lynnik.lchat.R.id.liveChatButton;

public class ChatActivity extends SingleFragmentActivity
    implements View.OnClickListener {

  private ProgressBar mProgressBar;
  private FrameLayout mButtonContainerFrameLayout;
  private FrameLayout mFragmentContainerFrameLayout;
  private View mChatButton;
  private View mLiveChatButton;

  public static Intent newIntent(Context context) {
    Intent i = new Intent(context, ChatActivity.class);
    return i;
  }

  @Override
  protected int getLayoutResId() {
    return R.layout.activity_chat;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    pasteFragment(
        ChatActivityFragment.newInstance());

    mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    mButtonContainerFrameLayout = (FrameLayout) findViewById(R.id.container);
    mFragmentContainerFrameLayout = (FrameLayout)
        findViewById(R.id.fragmentContainer);
    mChatButton = findViewById(chatButton);
    mChatButton.setOnClickListener(this);
    mLiveChatButton = findViewById(liveChatButton);
    mLiveChatButton.setOnClickListener(this);

    onShowLoading();

    chooseButton(mChatButton, mLiveChatButton);
    getFirstTextView(mChatButton).setText(R.string.chat);
    getFirstTextView(mLiveChatButton).setText(R.string.live_chat);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_chat, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.new_chat:
        startActivity(
            NewMessageActivity.newIntent(this));
        return true;
      default:
        return false;
    }
  }

  @Override
  public void onClick(View view) {
    switch (view.getId()) {
      case R.id.chatButton:
        chooseButton(mChatButton, mLiveChatButton);
        pasteFragment(
            ChatActivityFragment.newInstance());
        break;
      case R.id.liveChatButton:
        chooseButton(mLiveChatButton, mChatButton);
        pasteFragment(
            LiveChatActivityFragment.newInstance());
        break;
      default:
        break;
    }
  }

  public void onShowLoading() {
    mButtonContainerFrameLayout.setVisibility(View.GONE);
    mFragmentContainerFrameLayout.setVisibility(View.GONE);
    mProgressBar.setVisibility(View.VISIBLE);
  }

  public void onShowContent() {
    mProgressBar.setVisibility(View.GONE);
    mButtonContainerFrameLayout.setVisibility(View.VISIBLE);
    mFragmentContainerFrameLayout.setVisibility(View.VISIBLE);
  }

  public void setBadgeValues(int chatBadge, int liveChatBadge) {
    getSecondTextView(mChatButton).setText(String.valueOf(chatBadge));
    getSecondTextView(mLiveChatButton).setText(String.valueOf(liveChatBadge));
  }

  private void chooseButton(View select, View deselect) {
    select.setBackground(getContextDrawable(R.drawable.button_pressed));
    deselect.setBackground(getContextDrawable(R.drawable.button));

    getFirstTextView(select).setTextColor(
        getContextColor(R.color.buttonSelectText));
    getSecondTextView(select).setTextColor(
        getContextColor(R.color.buttonSelectBadgeText));
    getSecondTextView(select).setBackground(
        getContextDrawable(R.drawable.badge));

    getFirstTextView(deselect).setTextColor(
        getContextColor(R.color.buttonDeselectText));
    getSecondTextView(deselect).setTextColor(
        getContextColor(R.color.buttonDeselectBadgeText));
    getSecondTextView(deselect).setBackground(
        getContextDrawable(R.drawable.badge_pressed));
  }

  private Drawable getContextDrawable(int resId) {
    return ContextCompat.getDrawable(this, resId);
  }

  private int getContextColor(int resId) {
    return ContextCompat.getColor(this, resId);
  }

  private TextView getFirstTextView(View button) {
    return (TextView) ((ViewGroup) button).getChildAt(0);
  }

  private TextView getSecondTextView(View button) {
    return (TextView) ((ViewGroup) button).getChildAt(1);
  }
}
