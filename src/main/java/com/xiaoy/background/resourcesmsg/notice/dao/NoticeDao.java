package com.xiaoy.background.resourcesmsg.notice.dao;

import java.util.List;

import com.xiaoy.base.dao.CommonDao;
import com.xiaoy.base.entities.Notice;

/**
 * 公告信息Dao层
 * 
 * @author XiaoY
 * @date: 2015年10月3日 下午9:18:10
 */
public interface NoticeDao extends CommonDao<Notice> {

	/**
	 * 根据条件查询出现公告信息
	 * 
	 * @param notice
	 * @return
	 * @date 2015年10月3日 下午9:12:08
	 */
	List<Notice> getNoticeList(Notice notice);

	/**
	 * 根据条件统计公告记录数
	 * 
	 * @param notice
	 * @return
	 * @date 2015年10月3日 下午9:12:43
	 */
	long getTotal(Notice notice);

}
