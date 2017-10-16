package com.lynnik.lchat.retrofit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Chat implements Serializable {

  @SerializedName("channels")
  @Expose
  private List<Channel> channels = null;
  private final static long serialVersionUID = -568958967632711355L;

  public List<Channel> getChannels() {
    return channels;
  }

  public void setChannels(List<Channel> channels) {
    this.channels = channels;
  }
}
