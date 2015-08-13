package com.xiaoy.bug.service;

import java.util.List;

import com.xiaoy.base.entities.Bug;
import com.xiaoy.base.service.CommonService;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:55:03
 */
public interface BugService extends CommonService<Bug> {

	/**
	 * 更新BUG
	 * @param bug
	 */
	void updateBug(Bug bug);

	/**
	 * 根据条件查询
	 * @param bug
	 * @param start
	 * @param limit
	 * @return
	 */
	List<Bug> findCollectionByCondition(Bug bug, String start, String limit);

}
