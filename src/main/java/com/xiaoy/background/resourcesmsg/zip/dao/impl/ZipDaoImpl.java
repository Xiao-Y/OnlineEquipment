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
	public List<Zip> getZipCondition(Zip zip) {

		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWhere(hqlWhere, zip);
		return super.findCollectionByCondition(hqlWhere.toString(), paramsMapValue);
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
			if (!StringUtils.isEmpty(zip.getLevelType())) {
				hqlWhere.append(" and levelType = :levelType ");
				map.put("levelType", zip.getLevelType());
			}
			if(!StringUtils.isEmpty(zip.getParentId())){
				hqlWhere.append(" and parentId = :parentId ");
				map.put("parentId", zip.getParentId());
			}
			return map;
		}
		return null;
	}
}
