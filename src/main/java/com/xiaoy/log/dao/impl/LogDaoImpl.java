package com.xiaoy.log.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.xiaoy.base.dao.impl.CommonDaoImpl;
import com.xiaoy.base.entities.Log;
import com.xiaoy.log.dao.LogDao;
import com.xiaoy.util.DateHelper;

@Repository
public class LogDaoImpl extends CommonDaoImpl<Log> implements LogDao
{

	@Override
	public List<Log> findCollectionByCondition(Log log)
	{
		StringBuffer hqlWhere = new StringBuffer("");
		Map<String, Object> paramsMapValue = this.appendWhere(hqlWhere, log);
		return this.findCollectionByCondition(hqlWhere.toString(), paramsMapValue);
	}

	/**
	 * 拼接HQL查询语句
	 * 
	 * @param hqlWhere
	 *            拼接的HQL语句
	 * @param log
	 *            查询条件
	 * @return
	 */
	private Map<String, Object> appendWhere(StringBuffer hqlWhere, Log log)
	{
		if (log != null)
		{
			Map<String, Object> map = new HashMap<String, Object>();
			// 运行的类名
			if (!StringUtils.isEmpty(log.getRunClass()))
			{
				hqlWhere.append(" and runClass like :runClass ");
				map.put("runClass", "%" + log.getRunClass() + "%");
			}
			// 执行的时间
			if (log.getCreateTime() != null)
			{
				String strDate = DateHelper.dateConverString(log.getCreateTime());
				hqlWhere.append(" and createTime > timestamp(:createTiem1, '00 00:00:00') ");
				map.put("createTiem1", strDate);

				hqlWhere.append(" and createTime < timestamp(:createTiem2, '01 00:00:00') ");
				map.put("createTiem2", strDate);
			}

			// 用户id查询
			if (!StringUtils.isEmpty(log.getUserId()))
			{
				hqlWhere.append(" and userId  in(:userId) ");
				map.put("userId", log.getUserIds());
			}

			// 操作类型
			if (!StringUtils.isEmpty(log.getOperation()))
			{
				hqlWhere.append(" and operation = :operation ");
				map.put("operation", log.getOperation());
			}
			hqlWhere.append(" order by createTime desc ");
			return map;
		}
		return null;
	}
}
