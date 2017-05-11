package com.simba.bootstrap.model;

import java.util.ArrayList;
import java.util.List;

/**
 * bootstrape的treeview的数据对象
 * 
 * @author caozhejun
 *
 */
public class TreeViewData {

	/**
	 * 列表树节点上的文本
	 */
	private String text;

	/**
	 * 列表树节点上的图标
	 */
	private String icon;

	/**
	 * 当某个节点被选择后显示的图标
	 */
	private String selectedIcon;

	/**
	 * 结合全局enableLinks选项为列表树节点指定URL
	 */
	private String href;

	/**
	 * 节点的前景色
	 */
	private String color;

	/**
	 * 节点的背景色
	 */
	private String backColor;

	/**
	 * 通过结合全局showTags选项来在列表树节点的右边添加额外的信息
	 */
	private String tags;

	private int id;

	private String name;

	private String value;

	/**
	 * 节点的初始状态
	 */
	private TreeViewState state;

	/**
	 * 子节点
	 */
	List<TreeViewData> nodes = new ArrayList<>();

	public void addChildren(TreeViewData node) {
		nodes.add(node);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public TreeViewState getState() {
		return state;
	}

	public void setState(TreeViewState state) {
		this.state = state;
	}

	public List<TreeViewData> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeViewData> nodes) {
		this.nodes = nodes;
	}

}
