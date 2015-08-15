package com.xiaoy.menu.service.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Menu;
import com.xiaoy.base.service.impl.CommonServiceImpl;
import com.xiaoy.menu.dao.MenuDao;
import com.xiaoy.menu.service.MenuService;

@Service
public class MenuServiceImpl extends CommonServiceImpl<Menu> implements MenuService {
	SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	private MenuDao menuDao;

	@Resource
	@Override
	public void setCommonDao(CommonDao<Menu> commonDao) {
		this.menuDao = (MenuDao) commonDao;
		super.commonDao = commonDao;
	}

	@Override
	public List<Menu> getParentMenuList() {
		String hqlWhere = " and parentId = -1 ";
		Map<String, Object> paramsMapValue = new HashMap<>();
		List<Menu> parentMenus = menuDao.findCollectionByCondition(hqlWhere, paramsMapValue);
		return parentMenus;
	}

	@Override
	public List<Menu> getChildMenuList() {
		String hqlWhere = " and parentId <> -1 ";
		Map<String, Object> paramsMapValue = new HashMap<>();
		List<Menu> childMenus = menuDao.findCollectionByCondition(hqlWhere, paramsMapValue);
		return childMenus;
	}

	@Override
	public List<Menu> findCollectionByCondition(Menu menu, String start, String limit) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, menu);
		List<Menu> list = menuDao.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
		return list;
	}

	@Override
	public long countByCollection(Menu menu) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appHql(hqlWhere, menu);
		return menuDao.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	/**
	 * 拼接查询语句，设置查询参数
	 * 
	 * @param where
	 *            查询语句
	 * @param menu
	 *            查询参数
	 * @return Map
	 */
	private Map<String, Object> appHql(StringBuffer where, Menu menu) {
		Map<String, Object> paramsMapValue = null;
		if (menu != null) {
			paramsMapValue = new HashMap<>();
			// 父级id
			if (!StringUtils.isEmpty(menu.getParentId()) && !menu.getParentId().equals("0")) {
				where.append(" and parentId = :parentId ");
				paramsMapValue.put("parentId", menu.getParentId());
			}

			// 菜单名
			if (!StringUtils.isEmpty(menu.getMenuName())) {
				where.append(" and menuName like :menuName ");
				paramsMapValue.put("menuName", "%" + menu.getMenuName() + "%");
			}

			// 菜单类型
			if (!StringUtils.isEmpty(menu.getMenuType()) && !menu.getMenuType().equals("-1")) {
				where.append(" and menuType = :menuType ");
				paramsMapValue.put("menuType", menu.getMenuType());
			}

			// 创建时间
			if (menu.getCreateTime() != null) {
				where.append(" and createTime > timestamp(:createTiem1, '00 00:00:00') ");
				paramsMapValue.put("createTiem1", sf.format(menu.getCreateTime()));

				where.append(" and createTime < timestamp(:createTiem2, '01 00:00:00') ");
				paramsMapValue.put("createTiem2", sf.format(menu.getCreateTime()));
			}

			// 更新时间
			if (menu.getUpdateTime() != null) {
				where.append(" and updateTime > timestamp(:updateTime1, '00 00:00:00') ");
				paramsMapValue.put("updateTime1", sf.format(menu.getUpdateTime()));

				where.append(" and updateTime < timestamp(:updateTime2, '01 00:00:00') ");
				paramsMapValue.put("updateTime2", sf.format(menu.getUpdateTime()));
			}
			
			where.append(" order by updateTime desc");
		}
		return paramsMapValue;
	}

	@Override
	public void updateMenu(Menu menu) {
		Menu obj = menuDao.findObjectById(menu.getId());
		obj.setMenuName(menu.getMenuName());
		obj.setMenuType(menu.getMenuType());
		obj.setMenuUrl(menu.getMenuUrl());
		obj.setParentId(menu.getParentId());
		obj.setRemark(menu.getRemark());
		obj.setSeq(menu.getSeq());
		obj.setUpdateTime(menu.getUpdateTime());
	}

	@Override
	public List<Menu> getChildMenuListByParentId(String parentId) {
		if(!StringUtils.isEmpty(parentId)){
			String hqlWhere = " and parentId = :parentId ";
			Map<String, Object> paramsMapValue = new HashMap<String, Object>();
			paramsMapValue.put("parentId", parentId);
			List<Menu> menus = menuDao.findCollectionByCondition(hqlWhere, paramsMapValue);
			return menus;
		}
		return null;
	}
}
