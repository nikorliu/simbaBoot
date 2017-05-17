package com.simba.cluster.registryTable;

import java.io.Serializable;

/**
 * 注册表在集群中传递的对象
 * 
 * @author caozj
 *
 */
public class RegistryTableClusterData implements Serializable {

  private static final long serialVersionUID = 2687616734474020143L;

  /**
   * 执行的方法,add | remove
   */
  private String method;

  /**
   * 编码
   */
  private String code;

  /**
   * 值
   */
  private String value;

  public RegistryTableClusterData() {

  }

  public RegistryTableClusterData(String method, String code) {
    this.method = method;
    this.code = code;
  }

  public RegistryTableClusterData(String method, String code, String value) {
    this.method = method;
    this.code = code;
    this.value = value;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("RegistryTableClusterData [method=");
    builder.append(method);
    builder.append(", code=");
    builder.append(code);
    builder.append(", value=");
    builder.append(value);
    builder.append("]");
    return builder.toString();
  }


}
