package com.lynnik.lchat.activities;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.lynnik.lchat.R;

public abstract class SingleFragmentActivity extends AppCompatActivity {

  protected Toolbar mToolbar;

  private FragmentManager fm;

  @LayoutRes
  protected abstract int getLayoutResId();

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(getLayoutResId());

    fm = getSupportFragmentManager();

    mToolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(mToolbar);
  }

  protected void pasteFragment(Fragment fragment) {
    if (isFragmentContainerEmpty())
      addFragment(fragment);
    else
      replaceFragment(fragment);
  }

  private boolean isFragmentContainerEmpty() {
    Fragment fragment = fm.findFragmentById(R.id.fragmentContainer);
    return fragment == null;
  }

  private void addFragment(Fragment fragment) {
    fm.beginTransaction()
        .add(R.id.fragmentContainer, fragment)
        .commit();
  }

  private void replaceFragment(Fragment fragment) {
    fm.beginTransaction()
        .replace(R.id.fragmentContainer, fragment)
        .commit();
  }
}