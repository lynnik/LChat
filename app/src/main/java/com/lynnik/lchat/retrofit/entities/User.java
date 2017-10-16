package com.lynnik.lchat.retrofit.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable {

  @SerializedName("last_name")
  @Expose
  private String lastName;
  @SerializedName("photo")
  @Expose
  private String photo;
  @SerializedName("first_name")
  @Expose
  private String firstName;
  @SerializedName("id")
  @Expose
  private long id;
  @SerializedName("username")
  @Expose
  private String username;
  private final static long serialVersionUID = -8576534549800425796L;

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
