package com.simba.util.send;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.simba.framework.util.json.FastJsonUtil;
import com.simba.model.wx.ErrMsg;
import com.simba.model.wx.menu.Menu;
import com.simba.model.wx.menu.MenuSearchResult;
import com.simba.util.common.AccessTokenUtil;
import com.simba.util.common.GetUtil;
import com.simba.util.common.PostJsonUtil;
import com.simba.util.common.WxConstantData;

/**
 * 自定义菜单
 * 
 * @author caozhejun
 *
 */
@Component
public class MenuWxUtil {

	@Autowired
	private AccessTokenUtil accessTokenUtil;

	@Autowired
	private PostJsonUtil postJsonUtil;

	@Autowired
	private GetUtil getUtil;

	/**
	 * 创建自定义菜单(自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单,一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以“
	 * ...”代替,)
	 * 
	 * @param menu
	 */
	public void create(Menu menu) {
		String url = WxConstantData.createMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		String json = FastJsonUtil.toJson(menu);
		postJsonUtil.postJson(url, json, "创建自定义菜单", ErrMsg.class);
	}

	/**
	 * 自定义菜单查询(在设置了个性化菜单后，使用本自定义菜单查询接口可以获取默认菜单和全部个性化菜单信息)(仅能查询到使用API设置的菜单配置)
	 * 
	 * @return
	 */
	public MenuSearchResult search() {
		String url = WxConstantData.searchMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		return getUtil.get(url, "自定义菜单", MenuSearchResult.class);
	}

	/**
	 * 自定义菜单删除(在个性化菜单时，调用此接口会删除默认菜单及全部个性化菜单)
	 */
	public void delete() {
		String url = WxConstantData.deleteMenuUrl + "?access_token=" + accessTokenUtil.getAccessToken();
		getUtil.get(url, "自定义菜单删除", ErrMsg.class);
	}

}
