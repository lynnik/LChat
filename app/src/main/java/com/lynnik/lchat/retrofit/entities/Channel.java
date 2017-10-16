package com.lynnik.lchat.retrofit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Channel implements Serializable {

  @SerializedName("last_message")
  @Expose
  private LastMessage lastMessage;
  @SerializedName("users")
  @Expose
  private List<User> users = null;
  @SerializedName("unread_messages_count")
  @Expose
  private long unreadMessagesCount;
  @SerializedName("id")
  @Expose
  private long id;
  private final static long serialVersionUID = -308221567131883566L;

  public LastMessage getLastMessage() {
    return lastMessage;
  }

  public void setLastMessage(LastMessage lastMessage) {
    this.lastMessage = lastMessage;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public long getUnreadMessagesCount() {
    return unreadMessagesCount;
  }

  public void setUnreadMessagesCount(long unreadMessagesCount) {
    this.unreadMessagesCount = unreadMessagesCount;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }
}
