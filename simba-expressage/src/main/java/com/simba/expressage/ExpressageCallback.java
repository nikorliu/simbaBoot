package com.simba.expressage;

import java.util.List;

/**
 * 快递回调参数对象
 * 
 * @author caozj
 *
 */
public class ExpressageCallback {

  /**
   * 物流公司编号
   */
  private String com;

  /**
   * 物流单号
   */
  private String nu;

  /**
   * 物流信息列表
   */
  private List<ExpressageData> data;

  /**
   * 查询结果状态： 0：物流单暂无结果， 1：查询成功， 2：接口出现异常
   */
  private String status;

  /**
   * 快递单当前的状态 ： 0：在途，即货物处于运输过程中； 1：揽件，货物已由快递公司揽收并且产生了第一条跟踪信息； 2：疑难，货物寄送过程出了问题； 3：签收，收件人已签收；
   * 4：退签，即货物由于用户拒签、超区等原因退回，而且发件人已经签收； 5：派件，即快递正在进行同城派件； 6：退回，货物正处于退回发件人的途中；
   */
  private String state;

  private String message;

  private String comcontact;

  private String ischeck;

  private String comurl;

  private String companytype;

  private String signname;

  private String condition;

  private String codenumber;

  private String destination;

  private String pickuptime;

  private String departure;

  private String addressee;

  private String signedtime;

  public String getSignedtime() {
    return signedtime;
  }

  public void setSignedtime(String signedtime) {
    this.signedtime = signedtime;
  }

  public String getCompanytype() {
    return companytype;
  }

  public void setCompanytype(String companytype) {
    this.companytype = companytype;
  }

  public String getSignname() {
    return signname;
  }

  public void setSignname(String signname) {
    this.signname = signname;
  }

  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public String getCodenumber() {
    return codenumber;
  }

  public void setCodenumber(String codenumber) {
    this.codenumber = codenumber;
  }

  public String getDestination() {
    return destination;
  }

  public void setDestination(String destination) {
    this.destination = destination;
  }

  public String getPickuptime() {
    return pickuptime;
  }

  public void setPickuptime(String pickuptime) {
    this.pickuptime = pickuptime;
  }

  public String getDeparture() {
    return departure;
  }

  public void setDeparture(String departure) {
    this.departure = departure;
  }

  public String getAddressee() {
    return addressee;
  }

  public void setAddressee(String addressee) {
    this.addressee = addressee;
  }

  public String getIscheck() {
    return ischeck;
  }

  public void setIscheck(String ischeck) {
    this.ischeck = ischeck;
  }

  public String getComurl() {
    return comurl;
  }

  public void setComurl(String comurl) {
    this.comurl = comurl;
  }

  public String getComcontact() {
    return comcontact;
  }

  public void setComcontact(String comcontact) {
    this.comcontact = comcontact;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getCom() {
    return com;
  }

  public void setCom(String com) {
    this.com = com;
  }

  public String getNu() {
    return nu;
  }

  public void setNu(String nu) {
    this.nu = nu;
  }

  public List<ExpressageData> getData() {
    return data;
  }

  public void setData(List<ExpressageData> data) {
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("ExpressageCallback [com=");
    builder.append(com);
    builder.append(", nu=");
    builder.append(nu);
    builder.append(", data=");
    builder.append(data);
    builder.append(", status=");
    builder.append(status);
    builder.append(", state=");
    builder.append(state);
    builder.append(", message=");
    builder.append(message);
    builder.append(", comcontact=");
    builder.append(comcontact);
    builder.append(", ischeck=");
    builder.append(ischeck);
    builder.append(", comurl=");
    builder.append(comurl);
    builder.append(", companytype=");
    builder.append(companytype);
    builder.append(", signname=");
    builder.append(signname);
    builder.append(", condition=");
    builder.append(condition);
    builder.append(", codenumber=");
    builder.append(codenumber);
    builder.append(", destination=");
    builder.append(destination);
    builder.append(", pickuptime=");
    builder.append(pickuptime);
    builder.append(", departure=");
    builder.append(departure);
    builder.append(", addressee=");
    builder.append(addressee);
    builder.append(", signedtime=");
    builder.append(signedtime);
    builder.append("]");
    return builder.toString();
  }

}
