package com.rishabh.bloodbank.DataModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestDataModel {

  @SerializedName("id")
  @Expose
  private String id;
  @SerializedName("message")
  @Expose
  private String message;
  @SerializedName("url")
  @Expose
  private String url;
  @SerializedName("number")
  @Expose
  private String number;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

}