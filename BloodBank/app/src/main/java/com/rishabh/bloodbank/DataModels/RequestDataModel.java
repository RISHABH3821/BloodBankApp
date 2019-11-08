package com.rishabh.bloodbank.DataModels;

public class RequestDataModel {

  private String message;
  private String imageUrl;

  public RequestDataModel(String message, String imageUrl) {
    this.message = message;
    this.imageUrl = imageUrl;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getImageUrl() {
    return imageUrl;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}
