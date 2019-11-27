package com.logicblox.entity;

import org.springframework.data.annotation.Id;

public class RequestEntity {

  @Id
  private String id;
  private String header;
  private String method;
  private String uri;

  public RequestEntity (String header, String method, String uri) {
    this.header = header;
    this.method = method;
    this.uri = uri;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getHeader() {
    return header;
  }

  public void setHeader(String header) {
    this.header = header;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override
  public String toString() {
    return "RequestEntity{" +
            "id='" + id + '\'' +
            ", header='" + header + '\'' +
            ", method='" + method + '\'' +
            ", uri='" + uri + '\'' +
            '}';
  }

}
