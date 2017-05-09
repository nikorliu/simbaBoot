package com.simba.expressage;

/**
 * 快递公司
 * 
 * @author caozj
 *
 */
public class ExpressageCompany {

  /**
   * 公司编号
   */
  private String code;

  /**
   * 公司名称
   */
  private String name;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ExpressageCompany [code=");
    builder.append(code);
    builder.append(", name=");
    builder.append(name);
    builder.append("]");
    return builder.toString();
  }

}
