package com.lynnik.lchat.fragments.chat;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lynnik.lchat.R;
import com.lynnik.lchat.RecyclerItemTouchHelperListener;
import com.lynnik.lchat.activities.ChatActivity;
import com.lynnik.lchat.activities.ConversationActivity;
import com.lynnik.lchat.retrofit.entities.Channel;
import com.lynnik.lchat.retrofit.entities.Sender;

import java.util.List;

import static com.lynnik.lchat.R.id.recyclerView;

public class ChatActivityFragment extends Fragment
    implements RecyclerItemTouchHelperListener, ChatView {

  private ChatPresenter mPresenter;
  private UserAdapter mUserAdapter;

  private RecyclerView mRecyclerView;

  public static ChatActivityFragment newInstance() {
    Bundle args = new Bundle();

    ChatActivityFragment fragment = new ChatActivityFragment();
    fragment.setArguments(args);

    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setRetainInstance(true);

    ((ChatActivity) getActivity()).getSupportActionBar().setTitle("Chat");

    mPresenter = new ChatPresenter(this);
    mPresenter.initRetrofit(getActivity());
  }

  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState) {
    View v = inflater.inflate(R.layout.fragment_chat, container, false);

    mRecyclerView = (RecyclerView) v.findViewById(recyclerView);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    mRecyclerView.setItemAnimator(new DefaultItemAnimator());

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback =
        new RecyclerItemTouchHelper(0, ItemTouchHelper.RIGHT, this);
    new ItemTouchHelper(itemTouchHelperCallback)
        .attachToRecyclerView(mRecyclerView);

    mPresenter.createChatRequest();

    return v;
  }

  @Override
  public void onCreateAdapter(List<Channel> channels) {
    mUserAdapter = new UserAdapter(channels);
  }

  @Override
  public void onAddItemDecoration(int position) {
    mRecyclerView.addItemDecoration(new ItemOffsetDecoration(position));
  }

  @Override
  public void onSetAdapter() {
    mRecyclerView.setAdapter(mUserAdapter);
  }

  @Override
  public void onSetBadgeValues(int chatBadge, int liveChatBadge) {
    ((ChatActivity) getActivity()).setBadgeValues(chatBadge, liveChatBadge);
  }

  @Override
  public void onShowContent() {
    ((ChatActivity) getActivity()).onShowContent();
  }

  @Override
  public void onFailureResponse(Throwable t) {
    Toast.makeText(getActivity(), t.toString(), Toast.LENGTH_SHORT).show();
    getActivity().finish();
  }

  private class UserAdapter extends RecyclerView.Adapter<UserHolder> {

    private List<Channel> mChannels;

    public UserAdapter(List<Channel> channels) {
      mChannels = channels;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(getActivity());
      View v = inflater.inflate(R.layout.user_item, parent, false);

      return new UserHolder(v);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {
      Channel channel = mChannels.get(position);
      holder.bindItem(channel);
    }

    @Override
    public int getItemCount() {
      return mChannels.size();
    }

    private void removeItem(int position) {
      mChannels.remove(position);
      notifyItemRemoved(position);
    }
  }

  private class UserHolder extends RecyclerView.ViewHolder
      implements View.OnClickListener {

    private Channel mChannel;

    private ConstraintLayout mForegroundConstraintLayout;
    private ImageView mPhotoImageView;
    private TextView mFullNameTextView;
    private TextView mLastMessageTextView;
    private TextView mDateTextView;
    private TextView mUnreadMessagesTextView;

    public UserHolder(View v) {
      super(v);

      v.setOnClickListener(this);
      mForegroundConstraintLayout = (ConstraintLayout)
          v.findViewById(R.id.foregroundConstraintLayout);
      mPhotoImageView = (ImageView) v.findViewById(R.id.photoImageView);
      mFullNameTextView = (TextView) v.findViewById(R.id.fullNameTextView);
      mLastMessageTextView = (TextView)
          v.findViewById(R.id.lastMessageTextView);
      mDateTextView = (TextView) v.findViewById(R.id.dateTextView);
      mUnreadMessagesTextView = (TextView)
          v.findViewById(R.id.unreadMessagesTextView);
    }

    @Override
    public void onClick(View view) {
      startActivity(
          ConversationActivity.newIntent(getActivity(), mChannel));
    }

    private void bindItem(Channel channel) {
      mChannel = channel;

      Sender sender = mChannel.getLastMessage().getSender();

      Glide.with(getActivity())
          .load(sender.getPhoto())
          .apply(RequestOptions.circleCropTransform())
          .into(mPhotoImageView);

      mFullNameTextView.setText(
          sender.getFirstName() + " " + sender.getLastName());
      mLastMessageTextView.setText(channel.getLastMessage().getText());
      mDateTextView.setText(
          channel.getLastMessage().getCreateDate().substring(0, 10));

      if (channel.getUnreadMessagesCount() == 0) {
        mUnreadMessagesTextView.setVisibility(View.INVISIBLE);
      } else {
        mUnreadMessagesTextView.setVisibility(View.VISIBLE);
        String unreadMessage = String.valueOf(channel.getUnreadMessagesCount());
        mUnreadMessagesTextView.setText(unreadMessage);
      }
    }
  }

  private class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private static final int BOTTOM_MARGIN = 10;

    private int mPosition;

    public ItemOffsetDecoration(int position) {
      this.mPosition = position;
    }

    @Override
    public void getItemOffsets(
        Rect rect, View v, RecyclerView parent, RecyclerView.State s) {
      if (parent.getChildAdapterPosition(v) == mPosition)
        rect.bottom = BOTTOM_MARGIN;
    }
  }

  private class RecyclerItemTouchHelper
      extends ItemTouchHelper.SimpleCallback {

    private RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(
        int dragDirs,
        int swipeDirs,
        RecyclerItemTouchHelperListener listener) {
      super(dragDirs, swipeDirs);
      this.listener = listener;
    }

    @Override
    public boolean onMove(
        RecyclerView recyclerView,
        RecyclerView.ViewHolder viewHolder,
        RecyclerView.ViewHolder target) {
      return true;
    }

    @Override
    public void onSelectedChanged(
        RecyclerView.ViewHolder viewHolder, int actionState) {
      if (viewHolder != null) {
        final View foregroundView = ((UserHolder) viewHolder)
            .mForegroundConstraintLayout;
        getDefaultUIUtil().onSelected(foregroundView);
      }
    }

    @Override
    public void onChildDrawOver(
        Canvas c, RecyclerView recyclerView,
        RecyclerView.ViewHolder viewHolder, float dX, float dY,
        int actionState, boolean isCurrentlyActive) {
      final View foregroundView = ((UserHolder) viewHolder)
          .mForegroundConstraintLayout;
      getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView,
          dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(
        RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
      final View foregroundView = ((UserHolder) viewHolder)
          .mForegroundConstraintLayout;
      getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public void onChildDraw(
        Canvas c, RecyclerView recyclerView,
        RecyclerView.ViewHolder viewHolder, float dX, float dY,
        int actionState, boolean isCurrentlyActive) {
      final View foregroundView = ((UserHolder) viewHolder)
          .mForegroundConstraintLayout;

      getDefaultUIUtil().onDraw(c, recyclerView, foregroundView,
          dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
      listener.onSwiped(viewHolder, direction, viewHolder.getAdapterPosition());
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
      return super.convertToAbsoluteDirection(flags, layoutDirection);
    }
  }

  @Override
  public void onSwiped(
      RecyclerView.ViewHolder viewHolder, int direction, int position) {
    mUserAdapter.removeItem(viewHolder.getAdapterPosition());
  }
}
