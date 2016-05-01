package com.xiaoy.background.resourcesmsg.bug.service;

import java.util.List;

import com.xiaoy.base.entities.BugDto;
import com.xiaoy.base.service.CommonService;

/**
 * 
 * @author XiaoY
 * @date 2015年8月12日下午5:55:03
 */
public interface BugService extends CommonService<BugDto>
{

	/**
	 * 更新BUG
	 * 
	 * @param bug
	 */
	void updateBug(BugDto bug);

	/**
	 * 根据条件查询
	 * 
	 * @param bug
	 * @param start
	 * @param limit
	 * @return
	 */
	List<BugDto> findCollectionByCondition(BugDto bug, String start, String limit);

	/**
	 * 根据条件统计出现记录数
	 * 
	 * @param bug
	 * @return
	 */
	long countByCollection(BugDto bug);

}
