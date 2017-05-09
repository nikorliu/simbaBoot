package com.simba.echarts.model;

/**
 * 仪表盘
 * 
 * @author caozj
 *
 */
public class Gauge {

  /**
   * 标题
   */
  private String title;

  /**
   * 二级标题
   */
  private String subtext;

  /**
   * 仪表盘的名称
   */
  private String name;

  /**
   * 值的名称
   */
  private String valueName;

  /**
   * 值
   */
  private double value;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getSubtext() {
    return subtext;
  }

  public void setSubtext(String subtext) {
    this.subtext = subtext;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getValueName() {
    return valueName;
  }

  public void setValueName(String valueName) {
    this.valueName = valueName;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
    this.value = value;
  }

}
