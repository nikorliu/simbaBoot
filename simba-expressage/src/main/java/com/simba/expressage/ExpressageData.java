package com.simba.expressage;

/**
 * 快递数据对象
 * 
 * @author caozj
 *
 */
public class ExpressageData {

  /**
   * 每条跟踪信息的时间
   */
  private String time;

  /**
   * 每条跟综信息的描述
   */
  private String context;

  private String location;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ExpressageData [time=");
    builder.append(time);
    builder.append(", context=");
    builder.append(context);
    builder.append(", location=");
    builder.append(location);
    builder.append("]");
    return builder.toString();
  }

}
