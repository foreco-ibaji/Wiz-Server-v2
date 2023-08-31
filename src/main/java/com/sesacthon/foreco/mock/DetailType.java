package com.sesacthon.foreco.mock;

public enum DetailType {
  BASIC("basic"), MAP("map"), BIG("big");

  private final String type;

  DetailType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }
}
