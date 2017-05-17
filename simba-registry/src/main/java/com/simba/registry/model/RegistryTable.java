package com.simba.registry.model;

import java.io.Serializable;

import com.simba.annotation.DescAnnotation;

/**
 * 注册表
 * 
 * @author caozj
 *
 */
@DescAnnotation(desc="注册表")
public class RegistryTable implements Serializable {

	private static final long serialVersionUID = 2680172964264059018L;

	private int id;

	/**
	 * 编码
	 */
	@DescAnnotation(desc="编码")
	private String code;

	/**
	 * 值
	 */
	@DescAnnotation(desc="值")
	private String value;

	/**
	 * 类型ID
	 */
	@DescAnnotation(desc="类型ID")
	private int typeID;

	/**
	 * 描述
	 */
	@DescAnnotation(desc="描述")
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getTypeID() {
		return typeID;
	}

	public void setTypeID(int typeID) {
		this.typeID = typeID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RegistryTable [id=");
		builder.append(id);
		builder.append(", code=");
		builder.append(code);
		builder.append(", value=");
		builder.append(value);
		builder.append(", typeID=");
		builder.append(typeID);
		builder.append(", description=");
		builder.append(description);
		builder.append("]");
		return builder.toString();
	}

}
