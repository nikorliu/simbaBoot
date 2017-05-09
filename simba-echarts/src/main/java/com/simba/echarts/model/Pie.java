package com.simba.echarts.model;

import java.util.List;

/**
 * 饼图
 * 
 * @author caozj
 *
 */
public class Pie {


  /**
   * 标题
   */
  private String title;

  /**
   * 二级标题
   */
  private String subtext;

  /**
   * 图例说明列表
   */
  private List<String> legendList;

  /**
   * 值列表
   */
  private List<Double> valueList;

  /**
   * 饼图名称
   */
  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public List<String> getLegendList() {
    return legendList;
  }

  public void setLegendList(List<String> legendList) {
    this.legendList = legendList;
  }

  public List<Double> getValueList() {
    return valueList;
  }

  public void setValueList(List<Double> valueList) {
    this.valueList = valueList;
  }

}
