package com.xiaoy.background.resourcesmsg.zip.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.background.resourcesmsg.zip.dao.ZipDao;
import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Zip;

@Repository
public class ZipDaoImpl extends CommonDaoImpl<Zip> implements ZipDao {

	@Override
	public List<Zip> getZipCondition(Zip zip, String start, String limit) {

		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWhere(hqlWhere, zip);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue, start, limit);
	}

	@Override
	public long getTotal(Zip zip) {
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWhere(hqlWhere, zip);
		return super.countByCollection(hqlWhere.toString(), paramsMapValue);
	}

	/**
	 * 拼接查询语句
	 * 
	 * @param hqlWhere
	 *            拼接HQL
	 * @param zip
	 *            查询条件
	 * @return
	 */
	private Map<String, Object> appendWhere(StringBuffer hqlWhere, Zip zip) {
		if (zip != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!StringUtils.isEmpty(zip.getId())) {
				hqlWhere.append(" and id = :id");
				map.put("id", zip.getId());
			}
			// 城市等级
			if (!StringUtils.isEmpty(zip.getLevelType())) {
				hqlWhere.append(" and levelType = :levelType ");
				map.put("levelType", zip.getLevelType());
			}
			// 上级行政码
			if (!StringUtils.isEmpty(zip.getParentId())) {
				hqlWhere.append(" and parentId = :parentId ");
				map.put("parentId", zip.getParentId());
			}
			// 地区名称(简称或全称)
			if (!StringUtils.isEmpty(zip.getName())) {
				hqlWhere.append(" and ( name like :name or shortName like :shortName )");
				map.put("name", "%" + zip.getName() + "%");
				map.put("shortName", "%" + zip.getName() + "%");
			}
			// 邮编
			if (!StringUtils.isEmpty(zip.getZipCode())) {
				hqlWhere.append(" and zipCode = :zipCode");
				map.put("zipCode", zip.getZipCode());
			}
			// 城市CODE
			if (!StringUtils.isEmpty(zip.getCityCode())) {
				hqlWhere.append(" and cityCode = :cityCode");
				map.put("cityCode", zip.getCityCode());
			}
			return map;
		}
		return null;
	}
}
