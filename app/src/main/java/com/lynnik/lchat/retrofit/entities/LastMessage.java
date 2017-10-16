package com.lynnik.lchat.retrofit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LastMessage implements Serializable {

  @SerializedName("sender")
  @Expose
  private Sender sender;
  @SerializedName("is_read")
  @Expose
  private boolean isRead;
  @SerializedName("create_date")
  @Expose
  private String createDate;
  @SerializedName("text")
  @Expose
  private String text;
  private final static long serialVersionUID = 3585336695684974471L;

  public Sender getSender() {
    return sender;
  }

  public void setSender(Sender sender) {
    this.sender = sender;
  }

  public boolean isIsRead() {
    return isRead;
  }

  public void setIsRead(boolean isRead) {
    this.isRead = isRead;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }
}
